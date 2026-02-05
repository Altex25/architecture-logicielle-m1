package org.example.accountservice.service;

import org.example.accountservice.entity.Account;
import org.example.accountservice.usable.AccountUsable;
import org.example.accountservice.usable.CardUsable;
import org.example.accountservice.usable.LoanUsable;

import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();

    public Account getAccountById(Long id);

    AccountUsable getAccountDetailsById(Long id);

    public Account saveAccount(Account account);

    public void deleteAccount(Long id);

    List<CardUsable> getCardsByAccountId(Long id);

    List<LoanUsable> getLoansByAccountId(Long id);

    void updateCardCount(Long accountId, int delta);

    void updateLoanCount(Long accountId, int delta);
}
