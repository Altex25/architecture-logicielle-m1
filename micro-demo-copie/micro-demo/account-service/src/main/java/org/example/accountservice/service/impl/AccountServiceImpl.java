package org.example.accountservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.entity.Account;
import org.example.accountservice.kafka.AccountKafkaProducer;
import org.example.accountservice.repository.AccountRepository;
import org.example.accountservice.service.AccountService;
import org.example.accountservice.usable.AccountUsable;
import org.example.accountservice.usable.CardUsable;
import org.example.accountservice.usable.LoanUsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountKafkaProducer accountKafkaProducer;
    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8090";

    private static final String CARD_SERVICE_URL = "http://card-service/cards/byAccount/";
    private static final String LOAN_SERVICE_URL = "http://loans-service/loans/byAccount/";

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
        accountKafkaProducer.sendAccountDelete(id);
        accountRepository.deleteById(id);
    }

    public List<CardUsable> getCardsByAccountId(Long accountId) {
        try {
            ResponseEntity<List<CardUsable>> response = restTemplate.exchange(
                    CARD_SERVICE_URL + accountId,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<CardUsable>>() {
                    }
            );
            return response.getBody();
        } catch (Exception e) {
            // Log the error
            return Collections.emptyList();
        }
    }

    public List<LoanUsable> getLoansByAccountId(Long accountId) {
        try {
            ResponseEntity<List<LoanUsable>> response = restTemplate.exchange(
                    LOAN_SERVICE_URL + accountId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<LoanUsable>>() {
                    });
            return response.getBody();
        } catch (Exception e) {
            // Log the error or handle it as necessary
            return Collections.emptyList();
        }
    }
}
