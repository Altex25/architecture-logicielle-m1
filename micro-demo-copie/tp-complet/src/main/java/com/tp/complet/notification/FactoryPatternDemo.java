package com.tp.complet.notification;

import com.tp.complet.notification.multipleFactoryClasses.EmailNotificationFactory;
import com.tp.complet.notification.multipleFactoryClasses.NotificationFactory;
import com.tp.complet.notification.multipleFactoryClasses.PushNotificationFactory;
import com.tp.complet.notification.multipleFactoryClasses.SmsNotificationFactory;
import com.tp.complet.notification.simpleFactoryPattern.NotificationType;
import com.tp.complet.notification.simpleFactoryPattern.SimpleNotificationFactory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("--- Approach A : Multiple Factory Classes");
        NotificationFactory emailFactory = new EmailNotificationFactory();
        NotificationFactory smsFactory = new SmsNotificationFactory();
        NotificationFactory pushFactory = new PushNotificationFactory();

        emailFactory.createNotificationService().send("a@mail.com", "Yo !");
        smsFactory.createNotificationService().send("+334", "Le SMS");
        pushFactory.createNotificationService().send("push Recipient", "Nouvelle notification push");

        System.out.println("--- Approach B: Simple Factory Pattern");
        SimpleNotificationFactory simpleFactory = new SimpleNotificationFactory();

        simpleFactory.createNotificationService(NotificationType.EMAIL)
                .send("b@mail.com", "Email Simple Factory");
        simpleFactory.createNotificationService(NotificationType.SMS)
                .send("+335", "SMS Simple Factory");
        simpleFactory.createNotificationService(NotificationType.PUSH)
                .send("push recipient 2", "Push Simple Factory");
    }
}
