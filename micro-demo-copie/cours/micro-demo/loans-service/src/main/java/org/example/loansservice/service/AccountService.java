package org.example.loansservice.service;

import org.example.loansservice.usable.AccountUsable;

public interface AccountService {
    AccountUsable getAccountById(Long id);
}
