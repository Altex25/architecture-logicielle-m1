package com.tp.complet.user;

public class UserNotificationService {
    public void notifyUser(String username, String message) {
        System.out.println("Email sent to " + username + ": " + message);
    }
}
