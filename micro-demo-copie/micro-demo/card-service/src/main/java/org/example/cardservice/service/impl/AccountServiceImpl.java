package org.example.cardservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cardservice.service.AccountService;
import org.example.cardservice.usable.AccountUsable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final RestTemplate restTemplate;

    String BASE_URL = "http://localhost:8090";

    @Override
    public AccountUsable getAccountById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/accounts/{id}", AccountUsable.class, id);
    }
}
