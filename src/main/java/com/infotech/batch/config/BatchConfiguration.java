package com.infotech.batch.config;

import javax.sql.DataSource;

import com.infotech.batch.model.User;
import com.infotech.batch.processor.MembersProcessor;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.infotech.batch.listener.JobCompletionNotificationListener;
import com.infotech.batch.processor.PersonItemProcessor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

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
        reader.setResource(new ClassPathResource("persons.csv"));
        reader.setLineMapper(new DefaultLineMapper<User>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"member_id", "principal_id", "firstName", "lastName", "middleName", "gender", "national_id", "pin", "date_of_birth", "relation", "mobile_phone_number", "job_group", "country", "t_one", "t_two"});
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                setTargetType(User.class);
            }});
        }});
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public MembersProcessor processor1() {
        return new MembersProcessor();
    }

//    @Bean
//    @StepScope
//    public JdbcBatchItemWriter<User> jdbcUpdateTable3Writer(DataSource dataSource) {
//        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
//        jdbcBatchItemWriter.setAssertUpdates(true);
//        jdbcBatchItemWriter.setDataSource(dataSource);
//        jdbcBatchItemWriter.setSql("INSERT INTO users (firstName,lastName,middleName,gender,date_of_birth,national_id,mobile_phone_number) " +
//                "VALUES (:firstName,:lastName,:middleName,:gender,:date_of_birth,:national_id,:mobile_phone_number)");
//        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
//        return jdbcBatchItemWriter;
//    }
//
//    @Bean
//    @StepScope
//    public JdbcBatchItemWriter<User> jdbcUpdateTable1Writer(DataSource dataSource) {
//        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
//        jdbcBatchItemWriter.setAssertUpdates(true);
//        jdbcBatchItemWriter.setDataSource(dataSource);
//        jdbcBatchItemWriter.setSql("INSERT INTO role_user (role_id) VALUES (:role_id)");
//        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
//        return jdbcBatchItemWriter;
//    }
//
//    @Bean
//    @StepScope
//    public JdbcBatchItemWriter<User> jdbcInsertTable2Writer(DataSource dataSource) {
//        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
//        jdbcBatchItemWriter.setDataSource(dataSource);
//        jdbcBatchItemWriter.setSql("INSERT INTO BLS_members (member_id,principal_id,pin,relation,job_group,country) VALUES ( :member_id, :principal_id, :pin, :relation, :job_group, :country)");
//        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
//        return jdbcBatchItemWriter;
//    }
//
//    @Bean
//    @StepScope
//    public JdbcBatchItemWriter<User> jdbcInsertTable4Writer(DataSource dataSource) {
//        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
//        jdbcBatchItemWriter.setDataSource(dataSource);
//        jdbcBatchItemWriter.setSql("INSERT INTO BLS_categories (scheme_id,category_name) VALUES (:scheme_id, :category_name)");
//        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
//        return jdbcBatchItemWriter;
//    }
//
//    @Bean
//    @StepScope
//    public JdbcBatchItemWriter<User> jdbcInsertTable5Writer(DataSource dataSource) {
//        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
//        jdbcBatchItemWriter.setDataSource(dataSource);
//        jdbcBatchItemWriter.setSql("INSERT INTO BLS_user_category (category_id) VALUES (:category_id)");
//        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
//        return jdbcBatchItemWriter;
//    }


//    @Bean
//    @StepScope
//    public CompositeItemWriter<User> compositeItemWriter(
//            @Qualifier("jdbcUpdateTable1Writer") JdbcBatchItemWriter<User> jdbcUpdateTable1Writer,
//            @Qualifier("jdbcInsertTable2Writer") JdbcBatchItemWriter<User> jdbcInsertTable2Writer,
//            @Qualifier("jdbcUpdateTable3Writer") JdbcBatchItemWriter<User> jdbcUpdateTable3Writer,
//            @Qualifier("jdbcInsertTable4Writer") JdbcBatchItemWriter<User> jdbcInsertTable4Writer,
//            @Qualifier("jdbcInsertTable5Writer") JdbcBatchItemWriter<User> jdbcInsertTable5Writer
//    ) {
//        CompositeItemWriter<User> writer = new CompositeItemWriter<>();
//        writer.setDelegates(Arrays.asList(jdbcUpdateTable1Writer, jdbcInsertTable2Writer, jdbcUpdateTable3Writer, jdbcInsertTable4Writer, jdbcInsertTable5Writer));
//        return writer;
//    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcUpdateTable3Writer() {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setAssertUpdates(true);
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO users (password,firstName,lastName,middleName,gender,date_of_birth,national_id,mobile_phone_number) " +
                "VALUES (:member_id, :firstName,:lastName,:middleName,:gender,:date_of_birth,:national_id,:mobile_phone_number)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcUpdateTable1Writer() {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setAssertUpdates(true);
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO role_user (role_id) VALUES (:role_id)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcInsertTable2Writer() {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO BLS_members (member_id,principal_id,pin,relation,job_group,country) VALUES ( :member_id, :principal_id, :pin, :relation, :job_group, :country)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcInsertTable4Writer() {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO BLS_categories (scheme_id,category_name) VALUES (:scheme_id, :category_name)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> jdbcInsertTable5Writer() {
        JdbcBatchItemWriter<User> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql("INSERT INTO BLS_user_category (category_id) VALUES (:category_id)");
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        return jdbcBatchItemWriter;
    }


    @Bean
    @StepScope
    public CompositeItemWriter<User> compositeItemWriter() {
        CompositeItemWriter<User> writer = new CompositeItemWriter<>();
        writer.setDelegates(Arrays.asList(jdbcUpdateTable3Writer(), jdbcUpdateTable1Writer(), jdbcInsertTable2Writer(), jdbcInsertTable4Writer(), jdbcInsertTable5Writer()));
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
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(20);
        return asyncTaskExecutor;
    }

//     .processor(processor())

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<User, User>chunk(100000)
                .reader(reader())
                .processor(processor1())
                .writer(compositeItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }
}


//         .stream(jdbcUpdateTable3Writer())
//                 .stream(jdbcUpdateTable1Writer())
//                 .stream(jdbcInsertTable2Writer())
//                 .stream(jdbcInsertTable4Writer())
//                 .stream(jdbcInsertTable5Writer())