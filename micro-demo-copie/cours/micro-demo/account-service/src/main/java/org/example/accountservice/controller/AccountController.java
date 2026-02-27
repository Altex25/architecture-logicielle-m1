package org.example.accountservice.controller;


import org.example.accountservice.entity.Account;
import org.example.accountservice.service.AccountService;
import org.example.accountservice.usable.AccountUsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping(value = "/details/{id}", produces = "application/json")
    public AccountUsable getAccountDetailsById(@PathVariable Long id) {
        return accountService.getAccountDetailsById(id);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        System.out.println(account.getName());
        return accountService.saveAccount(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}