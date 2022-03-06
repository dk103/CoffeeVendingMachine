package com.vending.machine.service.impl;

import com.vending.machine.domain.Currency;
import com.vending.machine.domain.Price;
import com.vending.machine.service.PaymentService;

public class InHousePaymentService implements PaymentService {


    @Override
    public Change processPayment(Price priceLeft) {
        try {
            System.out.println("Total payment made -" + priceLeft.getVal());

            return Change.builder()
                    .currency(Currency.DOLLAR)
                    .description("settled")
                    .build();

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return Change.builder()
                .description("payment failed")
                .build();
    }


}
