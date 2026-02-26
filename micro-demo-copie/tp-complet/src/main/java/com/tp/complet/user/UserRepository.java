package com.tp.complet.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepository {
    private final List<String> users = new ArrayList<>();

    private final UsernameValidator usernameValidator = new UsernameValidator();

    public void save(String username) {
        users.add(username);
        System.out.println("Saving " + username + " to database");
    }

    public void remove(String username) {
        users.remove(username);
    }

    public List<String> findAll() {
        return Collections.unmodifiableList(users);
    }
}
