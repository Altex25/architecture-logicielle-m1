package org.example.loansservice.service.impl;


import org.example.loansservice.entity.Loan;
import org.example.loansservice.repository.LoanRepository;
import org.example.loansservice.service.AccountService;
import org.example.loansservice.service.LoanService;
import org.example.loansservice.usable.AccountUsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountService accountService;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public List<Loan> getLoansByAccountId(Long accountId) {
        return loanRepository.findByAccountId(accountId);
    }

    public Loan saveLoan(Loan loan) {
        if (isAccountExisting(loan.getAccountId())) {
            return loanRepository.save(loan);
        }
        return null;
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    private boolean isAccountExisting(Long accountId) {
        AccountUsable accountUsable = null;
        accountUsable = accountService.getAccountById(accountId);
        return accountUsable != null;
    }
}

