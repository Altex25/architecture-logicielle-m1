package org.example.accountservice.usable;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AccountUsable {
    private Long id;
    private String name;
    private String email;
    private Integer Solde;

    private List<CardUsable> cards;
    private List<LoanUsable> loans;
}
