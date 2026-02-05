package org.example.accountservice.usable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoanUsable {
    private Long id;
    private Double amount;
    private String type;
    private Long accountId;
}
