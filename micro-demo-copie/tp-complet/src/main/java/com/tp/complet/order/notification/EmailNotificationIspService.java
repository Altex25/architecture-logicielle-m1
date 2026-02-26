package com.tp.complet.order.notification;

public class EmailNotificationIspService implements NotificationIspService {
    @Override
    public void send(String user, String message) {
        System.out.println("Email to " + user + ": " + message);
    }
}
