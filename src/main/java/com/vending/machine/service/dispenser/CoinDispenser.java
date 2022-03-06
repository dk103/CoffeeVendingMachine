package com.vending.machine.service.dispenser;

import com.vending.machine.service.PaymentService;

public abstract class CoinDispenser {

    private CoinDispenser next;
    private int coinCount;
    private int prevCoinCount;

    public CoinDispenser(CoinDispenser next, int coins) {
        this.next = next;
        this.coinCount = coins;
        prevCoinCount = coinCount;
    }

    public CoinDispenser getNext() {
        return next;
    }

    protected boolean isCoinAvailable(int requestedCoin) {
        if (requestedCoin > coinCount) {
            return false;
        }
        return true;
    }

    protected void updateCoin(int requestedCoin) {
        this.prevCoinCount = this.coinCount;
        this.coinCount = this.coinCount - requestedCoin;
    }

    public abstract PaymentService.Change processAmt(Double price);

    protected void rollback() {
        this.restoreCoin();
        if (this.getNext() != null) {
            this.getNext().rollback();;
        }
    }

    private void restoreCoin() {
        this.coinCount = prevCoinCount;
    }

    public int getCoinCount() {
        return coinCount;
    }

}
