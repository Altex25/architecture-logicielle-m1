package com.tp.complet.notification.multipleFactoryClasses;

import com.tp.complet.notification.NotificationService;

public interface NotificationFactory {
    NotificationService createNotificationService();
}
