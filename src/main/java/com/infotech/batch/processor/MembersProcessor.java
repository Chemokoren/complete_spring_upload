package com.infotech.batch.processor;

import com.infotech.batch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashSet;
import java.util.Set;

public class MembersProcessor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(MembersProcessor.class);

    // This assumes that User.equals() identifies the duplicates
    private Set<User> seenUsers = new HashSet<User>();

    public User process(final User user) throws Exception {
        if(seenUsers.contains(user)) {
            return null;
        }
        seenUsers.add(user);

        return user;

    }

}
