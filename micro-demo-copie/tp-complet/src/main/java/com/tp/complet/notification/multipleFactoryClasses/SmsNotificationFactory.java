package com.tp.complet.notification.multipleFactoryClasses;

import com.tp.complet.notification.NotificationService;
import com.tp.complet.notification.sms.SmsService;

public class SmsNotificationFactory implements NotificationFactory {
    @Override
    public NotificationService createNotificationService() {
        return new SmsService();
    }
}
