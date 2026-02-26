package com.tp.complet.order.notification;

public class SmsNotificationService implements NotificationIspService {
    @Override
    public void send(String user, String message) {
        System.out.println("SMS to " + user + ": " + message);
    }
}
