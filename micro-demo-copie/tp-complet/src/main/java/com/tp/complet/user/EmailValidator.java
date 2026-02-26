package com.tp.complet.user;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public boolean isValid(String email) {
        return email.matches(EMAIL_REGEX);
    }
}

