package com.vending.machine.service.dispenser;

import com.vending.machine.service.PaymentService;

public class Penny extends CoinDispenser {

    public Penny(CoinDispenser next, int numCoin) {
        super(next, numCoin);
    }

    @Override
    public PaymentService.Change processAmt(Double price) {

        if (price > 1) {
            int quarterCoinNeeded = (int) (price / 1);
            double leftOver = price - quarterCoinNeeded * 1;

            if (getCoinCount() > quarterCoinNeeded) {
                updateCoin(quarterCoinNeeded);
            } else {
                leftOver = price - getCoinCount() * 1;
                updateCoin(0);
            }

            if (leftOver == 0) {
                return PaymentService.Change.builder()
                        .description("settled")
                        .build();
            } else if (getNext() != null) {
                return getNext().processAmt(price);
            }

        } else {
            if (getNext() != null) {
                return getNext().processAmt(price);
            }
        }
        throw new RuntimeException("Not enough change");
    }

}
