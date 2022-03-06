package com.vending.machine.service;

import com.vending.machine.service.dispenser.*;


public class CoinProcessor {

    public PaymentService.Change dispenseChange(Double price) {
        CoinDispenser handler = getCoinHandler();
        return handler.processAmt(price*100);
    }

    private static CoinDispenser getCoinHandler() {
        Penny penny = new Penny(null, 100);
        Nickel nickel = new Nickel(penny, 100);
        Dime dime = new Dime(nickel, 100);
        Quarter quarter = new Quarter(dime, 200);
        return quarter;
    }
}
