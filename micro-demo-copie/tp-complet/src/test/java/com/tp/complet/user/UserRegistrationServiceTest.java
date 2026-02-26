package com.tp.complet.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRegistrationServiceTest {
    @Test
    void shouldRegisterUser() {
        UserRepository repository = new UserRepository();
        UserRegistrationService service = new UserRegistrationService(
                new UsernameValidator(),
                new EmailValidator(),
                repository,
                new UserNotificationService()
        );

        boolean result = service.registerUser("alex", "alex@example.com");

        assertTrue(result);
        assertEquals(1, repository.findAll().size());
        assertEquals("alex", repository.findAll().get(0));
    }

    @Test
    void shouldFailWhenUsernameFailed() {
        UserRepository repository = new UserRepository();
        UserRegistrationService service = new UserRegistrationService(
                new UsernameValidator(),
                new EmailValidator(),
                repository,
                new UserNotificationService()
        );

        boolean result = service.registerUser("al", "alex@example.com");

        assertFalse(result);
        assertTrue(repository.findAll().isEmpty());
    }
}
