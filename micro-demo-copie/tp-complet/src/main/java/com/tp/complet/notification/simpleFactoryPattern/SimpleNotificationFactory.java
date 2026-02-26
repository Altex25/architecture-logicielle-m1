package com.tp.complet.notification.simpleFactoryPattern;

import com.tp.complet.notification.NotificationService;
import com.tp.complet.notification.email.EmailService;
import com.tp.complet.notification.push.PushService;
import com.tp.complet.notification.sms.SmsService;

public class SimpleNotificationFactory {
    public NotificationService createNotificationService(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailService();
            case SMS -> new SmsService();
            case PUSH -> new PushService();
            default -> throw new IllegalArgumentException("Invalid notification type");
        };
    }
}
