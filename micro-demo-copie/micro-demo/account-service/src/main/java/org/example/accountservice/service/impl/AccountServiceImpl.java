package org.example.accountservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.entity.Account;
import org.example.accountservice.repository.AccountRepository;
import org.example.accountservice.service.AccountService;
import org.example.accountservice.usable.AccountUsable;
import org.example.accountservice.usable.CardUsable;
import org.example.accountservice.usable.LoanUsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8090";

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public AccountUsable getAccountDetailsById(Long id) {
        Account account = getAccountById(id);
        List<CardUsable> cards = getCardsByAccountId(id);
        List<LoanUsable> loans = getLoansByAccountId(id);

        return new AccountUsable(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getSolde(),
                cards,
                loans
        );
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<CardUsable> getCardsByAccountId(Long id) {
        List<CardUsable> cards = restTemplate.getForObject(BASE_URL + "/cards/accounts/{id}", List.class, id);
        return cards;
    }

    public List<LoanUsable> getLoansByAccountId(Long id) {
        List<LoanUsable> loans = restTemplate.getForObject(BASE_URL + "/loans/accounts/{id}", List.class, id);
        return loans;
    }
}
