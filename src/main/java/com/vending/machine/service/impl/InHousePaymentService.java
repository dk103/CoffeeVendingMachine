package com.vending.machine.service.impl;

import com.vending.machine.domain.Price;
import com.vending.machine.service.CoinProcessor;
import com.vending.machine.service.PaymentService;

public class InHousePaymentService implements PaymentService {

    private CoinProcessor coinProcessor;

    public InHousePaymentService() {
        this.coinProcessor = new CoinProcessor();
    }

    @Override
    public Change processPayment(Double userPaid, Price orderPrice) {
        try {
            System.out.println("Total payment made -" + userPaid);
            return coinProcessor.dispenseChange(userPaid - orderPrice.getVal());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return Change.builder()
                .description("payment failed")
                .build();
    }


}
