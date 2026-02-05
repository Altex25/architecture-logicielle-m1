package org.example.accountservice.usable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardUsable {
    private Long id;
    private String cardNumber;
    private String cardType;
    private Long accountId;
}
