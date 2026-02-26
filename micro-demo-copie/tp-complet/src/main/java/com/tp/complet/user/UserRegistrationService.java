package com.tp.complet.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegistrationService {
    private final UsernameValidator usernameValidator;
    private final EmailValidator emailValidator;
    private final UserRepository userRepository;
    private final UserNotificationService notificationService;

    public boolean registerUser(String username, String email) {
        if (!usernameValidator.isValid(username)) {
            return false;
        }
        if (!emailValidator.isValid(email)) {
            return false;
        }

        userRepository.save(username);
        notificationService.notifyUser(username, "Welcome " + username + "!");
        return true;
    }

}
