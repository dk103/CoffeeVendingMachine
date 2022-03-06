package com.vending.machine.service;

import com.vending.machine.domain.Price;
import com.vending.machine.service.dispenser.*;


public class CoinProcessor {

    public PaymentService.Change dispenseChange(Price price) {
        CoinDispenser handler = getCoinHandler();
        handler.processAmt(price.getVal()*100);
        return null;
    }

    private static CoinDispenser getCoinHandler() {
        Penny penny = new Penny(null, 100);
        Nickel nickel = new Nickel(penny, 100);
        Dime dime = new Dime(nickel, 100);
        Quarter quarter = new Quarter(dime, 200);
        return quarter;
    }
}
