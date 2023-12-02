package org.example.model;

import lombok.Data;

@Data
public class CreditCard {
    public CreditCard(long creditCardNumber, Integer cvvNumber) {
        this.creditCardNumber = creditCardNumber;
        this.cvvNumber = cvvNumber;
    }

    private long creditCardNumber;
    private Integer cvvNumber;
}
