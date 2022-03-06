package com.vending.machine.service;

import com.vending.machine.service.dispenser.*;


public class CoinProcessor {

    private static CoinDispenser head;

    public PaymentService.Change dispenseChange(Double price) {
        CoinDispenser handler = getCoinHandler();
        return handler.processAmt(price*100);
    }

    private static CoinDispenser getCoinHandler() {
        if (head == null) {
            Penny penny = new Penny(null, 100);
            Nickel nickel = new Nickel(penny, 100);
            Dime dime = new Dime(nickel, 100);
            head = new Quarter(dime, 200);
        }
        return head;
    }
}
