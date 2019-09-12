package com.infotech.batch.processor;

import com.infotech.batch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public User process(final User user) throws Exception {
        final String firstName = user.getFirstName().toUpperCase();
        final String lastName = user.getLastName().toUpperCase();

        final User transformedUser = new User(firstName, lastName);

        log.info("Converting (" + user + ") into (" + transformedUser + ")");

        return transformedUser;
    }

}