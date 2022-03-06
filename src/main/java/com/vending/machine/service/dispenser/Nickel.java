package com.vending.machine.service.dispenser;

import com.vending.machine.service.PaymentService;

public class Nickel extends CoinDispenser {

    private CoinDispenser next;

    public Nickel(CoinDispenser next, int numCoin) {
        super(next, numCoin);
    }

    @Override
    public PaymentService.Change processAmt(Double price) {

        if (price > 5) {
            int quarterCoinNeeded = (int) (price / 5);
            double leftOver = price - quarterCoinNeeded * 5;

            if (getCoinCount() > quarterCoinNeeded) {
                updateCoin(quarterCoinNeeded);
            } else {
                leftOver = price - getCoinCount() * 5;
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
