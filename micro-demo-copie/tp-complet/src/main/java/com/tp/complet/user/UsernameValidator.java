package com.tp.complet.user;

public class UsernameValidator {
    public boolean isValid(String username) {
        return username != null && username.length() >= 3;
    }
}
