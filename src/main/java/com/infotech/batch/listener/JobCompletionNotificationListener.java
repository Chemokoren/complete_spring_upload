package com.infotech.batch.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.infotech.batch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<User> results = jdbcTemplate.query("SELECT firstName, lastName FROM users", new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int row) throws SQLException {
//					return new User(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4));
					return new User(rs.getString(1), rs.getString(2));
				}
			});

			for (User user : results) {
				log.info("Found <" + user + "> in the database.");
			}

		}
	}
}