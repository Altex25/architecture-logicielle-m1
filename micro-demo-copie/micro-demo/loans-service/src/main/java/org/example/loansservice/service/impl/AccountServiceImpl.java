package org.example.loansservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.loansservice.service.AccountService;
import org.example.loansservice.usable.AccountUsable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final RestClient restClient = RestClient.create();
    String BASE_URL = "http://localhost:8090";

    @Override
    public AccountUsable getAccountById(Long id) {
        return restClient.get()
                .uri(BASE_URL + "/accounts/" + id)
                .retrieve()
                .body(AccountUsable.class);
    }
}
