package org.example.cardservice.service;

import org.example.cardservice.usable.AccountUsable;

public interface AccountService {
    AccountUsable getAccountById(Long id);
}
