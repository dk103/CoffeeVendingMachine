package com.vending.machine.service.dispenser;

import com.vending.machine.service.PaymentService;

public class Quarter extends CoinDispenser {

    public Quarter(CoinDispenser next, int numCoin) {
        super(next, numCoin);
    }

    @Override
    public PaymentService.Change processAmt(Double price) {
        try {
            if (price > 25) {
                int quarterCoinNeeded = (int) (price / 25);
                double leftOver = price - quarterCoinNeeded * 25;

                if (getCoinCount() > quarterCoinNeeded) {
                    updateCoin(quarterCoinNeeded);
                } else {
                    leftOver = price - getCoinCount() * 25;
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

        } catch (Exception e) {
            this.rollback();
        }
        throw new RuntimeException("Not enough change");
    }

}
