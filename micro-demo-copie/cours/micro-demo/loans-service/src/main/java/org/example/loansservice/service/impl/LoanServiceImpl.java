package org.example.loansservice.service.impl;


import org.example.loansservice.entity.Loan;
import org.example.loansservice.repository.LoanRepository;
import org.example.loansservice.service.AccountService;
import org.example.loansservice.service.LoanService;
import org.example.loansservice.usable.AccountUsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.loansservice.kafka.LoanKafkaProducer;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoanKafkaProducer loanKafkaProducer;

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
            Loan saved = loanRepository.save(loan);
            loanKafkaProducer.sendLoanCreated(saved.getAccountId(), saved.getId());
            return saved;
        }
        return null;
    }

    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        loanRepository.deleteById(id);
        loanKafkaProducer.sendLoanDeleted(loan.getAccountId(), loan.getId());
    }

    private boolean isAccountExisting(Long accountId) {
        AccountUsable accountUsable = null;
        accountUsable = accountService.getAccountById(accountId);
        return accountUsable != null;
    }
}
