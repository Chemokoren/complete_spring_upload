package com.infotech.batch.config;

import javax.sql.DataSource;

import com.infotech.batch.model.BLSMember;
import com.infotech.batch.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.infotech.batch.listener.JobCompletionNotificationListener;
import com.infotech.batch.processor.PersonItemProcessor;

import java.util.Arrays;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
        FlatFileItemReader<BLSMember> reader1 = new FlatFileItemReader<BLSMember>();
        reader.setResource(new ClassPathResource("persons.csv"));
        reader1.setResource(new ClassPathResource("persons.csv"));
        reader.setLineMapper(new DefaultLineMapper<User>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"firstName", "lastName", "middleName", "gender", "national_id", "date_of_birth", "mobile_phone_number"});
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                setTargetType(User.class);
            }});
        }});
        reader1.setLineMapper(new DefaultLineMapper<BLSMember>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"member_id", "principal_id","pin", "relation", "mobile_phone_number", "job_group", "country"});
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<BLSMember>() {{
                setTargetType(BLSMember.class);
            }});
        }});
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcUpdateTable3Writer(DataSource dataSource) {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setAssertUpdates(true);
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO users (firstName,lastName,middleName,gender,date_of_birth,national_id,mobile_phone_number) " +
                "VALUES (:firstName,:lastName,:middleName,:gender,:date_of_birth,:national_id,:mobile_phone_number)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcUpdateTable1Writer(DataSource dataSource) {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setAssertUpdates(true);
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO role_user (role_id) VALUES (:role_id)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcInsertTable2Writer(DataSource dataSource) {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO BLS_members (member_id,principal_id,pin,relation,job_group,country) VALUES ( :member_id, :principal_id, :pin, :relation, :job_group, :country)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }


    @Bean
    @StepScope
    public CompositeItemWriter<User> compositeItemWriter(
            @Qualifier("jdbcUpdateTable1Writer") JdbcBatchItemWriter<User> jdbcUpdateTable1Writer,
            @Qualifier("jdbcInsertTable2Writer") JdbcBatchItemWriter<User> jdbcInsertTable2Writer,
            @Qualifier("jdbcUpdateTable3Writer") JdbcBatchItemWriter<User> jdbcUpdateTable3Writer
    ) {
        CompositeItemWriter<User> writer = new CompositeItemWriter<>();
        writer.setDelegates(Arrays.asList(jdbcUpdateTable1Writer, jdbcInsertTable2Writer, jdbcUpdateTable3Writer));
        return writer;
    }


    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<User, User>chunk(100000)
                .reader(reader())
                .processor(processor())
                .writer(compositeItemWriter(jdbcUpdateTable1Writer(dataSource), jdbcInsertTable2Writer(dataSource), jdbcUpdateTable3Writer(dataSource)))
                .build();
    }
}