package com.tp.complet.notification.multipleFactoryClasses;

import com.tp.complet.notification.NotificationService;
import com.tp.complet.notification.push.PushService;

public class PushNotificationFactory implements NotificationFactory {

    @Override
    public NotificationService createNotificationService() {
        return new PushService();
    }
}
