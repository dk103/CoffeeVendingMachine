package com.vending.machine.service.dispenser;

import com.vending.machine.service.PaymentService;

public class Dime extends CoinDispenser {

    private CoinDispenser next;

    public Dime(CoinDispenser next, int numCoin) {
        super(next, numCoin);
    }
    @Override
    public PaymentService.Change processAmt(Double price) {

        if (price > 10) {
            int quarterCoinNeeded = (int) (price / 10);
            double leftOver = price - quarterCoinNeeded * 10;

            if (getCoinCount() > quarterCoinNeeded) {
                updateCoin(quarterCoinNeeded);
            } else {
                leftOver = price - getCoinCount() * 10;
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
