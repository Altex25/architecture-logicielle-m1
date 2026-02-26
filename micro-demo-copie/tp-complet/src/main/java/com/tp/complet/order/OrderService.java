package com.tp.complet.order;

import com.tp.complet.order.data.DataRepository;
import com.tp.complet.order.notification.NotificationIspService;

public class OrderService {
    private final DataRepository dataRepository;
    private final NotificationIspService notificationService;

    public OrderService(DataRepository dataRepository, NotificationIspService notificationService) {
        this.dataRepository = dataRepository;
        this.notificationService = notificationService;
    }

    public void createOrder(String userId, String bookId) {
        String orderKey = "order_" + userId;
        dataRepository.save(orderKey, bookId);
        notificationService.send(userId, "Order created successfully");
    }
}
