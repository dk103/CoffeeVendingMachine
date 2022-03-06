package com.vending.machine.domain;

public class Capaccino extends Beverage {

    public Capaccino(String drinkName) {
        super("Cappacino");
    }

    @Override
    public Price getPrice() {
        return Price.builder()
                .val(4d)
                .currency(Currency.DOLLAR)
                .build();
    }
}
