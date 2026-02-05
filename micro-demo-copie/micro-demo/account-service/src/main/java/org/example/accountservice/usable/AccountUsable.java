package org.example.accountservice.usable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AccountUsable {
    private Long id;
    private String name;
    private String email;
    private Integer Solde;

    private List<CardUsable> cards;
    private List<LoanUsable> loans;
}
