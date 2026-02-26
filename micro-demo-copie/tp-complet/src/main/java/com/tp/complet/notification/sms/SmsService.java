package com.tp.complet.notification.sms;

import com.tp.complet.notification.NotificationService;

public class SmsService implements NotificationService {

    @Override
    public void send(String recipient, String message) {
        System.out.println( "SMS To : " + recipient + " Message : " + message);
    }
}
