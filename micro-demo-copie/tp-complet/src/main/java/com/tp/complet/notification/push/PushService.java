package com.tp.complet.notification.push;

import com.tp.complet.notification.NotificationService;

public class PushService implements NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Push To : " + recipient + " Message : " + message);
    }
}
