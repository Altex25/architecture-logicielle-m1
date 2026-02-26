package com.tp.complet.order;

import com.tp.complet.order.data.DataRepository;
import com.tp.complet.order.notification.NotificationIspService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {
    @Test
    void shouldCreateOrderWithDatabaseAndEmailStyleDependencies() {
        SpyDataRepository dataRepository = new SpyDataRepository();
        SpyNotificationService notificationService = new SpyNotificationService();

        OrderService orderService = new OrderService(dataRepository, notificationService);
        orderService.createOrder("user1", "book42");

        assertEquals("order_user1", dataRepository.savedKey);
        assertEquals("book42", dataRepository.savedValue);
        assertEquals("user1", notificationService.recipient);
        assertEquals("Order created successfully", notificationService.message);
    }

    @Test
    void shouldCreateOrderWithCacheAndSmsStyleDependencies() {
        SpyDataRepository dataRepository = new SpyDataRepository();
        SpyNotificationService notificationService = new SpyNotificationService();

        OrderService orderService = new OrderService(dataRepository, notificationService);
        orderService.createOrder("user2", "book99");

        assertEquals("order_user2", dataRepository.savedKey);
        assertEquals("book99", dataRepository.savedValue);
        assertEquals("user2", notificationService.recipient);
        assertEquals("Order created successfully", notificationService.message);
    }

    private static class SpyDataRepository implements DataRepository {
        private String savedKey;
        private String savedValue;

        @Override
        public void save(String key, String value) {
            this.savedKey = key;
            this.savedValue = value;
        }
    }

    private static class SpyNotificationService implements NotificationIspService {
        private String recipient;
        private String message;

        @Override
        public void send(String user, String message) {
            this.recipient = user;
            this.message = message;
        }
    }
}
