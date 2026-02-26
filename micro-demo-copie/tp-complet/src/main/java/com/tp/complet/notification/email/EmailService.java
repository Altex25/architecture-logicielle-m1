package com.tp.complet.notification.email;

import com.tp.complet.notification.NotificationService;

public class EmailService implements NotificationService {

    @Override
    public void send(String recipient, String message) {
        System.out.println("Email To : " + recipient + " Message : " + message);
    }
}
