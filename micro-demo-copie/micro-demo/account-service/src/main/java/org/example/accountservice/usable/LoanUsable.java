package org.example.accountservice.usable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoanUsable {
    private Long id;
    private Double amount;
    private String type;
    private Long accountId;
}
