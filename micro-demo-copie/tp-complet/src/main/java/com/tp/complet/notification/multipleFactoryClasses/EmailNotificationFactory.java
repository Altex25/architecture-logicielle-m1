package com.tp.complet.notification.multipleFactoryClasses;

import com.tp.complet.notification.NotificationService;
import com.tp.complet.notification.email.EmailService;

public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public NotificationService createNotificationService() {
        return new EmailService();
    }
}
