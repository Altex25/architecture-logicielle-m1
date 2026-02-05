package org.example.accountservice.usable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CardUsable {
    private Long id;
    private String cardNumber;
    private String cardType;
    private Long accountId;
}
