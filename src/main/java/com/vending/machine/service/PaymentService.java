package com.vending.machine.service;

import com.vending.machine.domain.Currency;
import com.vending.machine.domain.Price;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface PaymentService {

    Change processPayment(Price priceLeft);

    @Builder
    @ToString
    @Getter
    class Change {
        Double val;
        Currency currency;
        String description;
    }
}
