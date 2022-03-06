package com.vending.machine.domain;

public class Espresso extends Beverage {

    public static final String ESPRESSO = "ESPRESSO";

    public Espresso(String drinkName) {
        super(ESPRESSO);
    }

    @Override
    public Price getPrice() {
        return Price.builder()
                .val(2d)
                .currency(Currency.DOLLAR)
                .build();
    }

}
