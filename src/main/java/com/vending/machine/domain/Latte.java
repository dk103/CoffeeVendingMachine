package com.vending.machine.domain;

public class Latte extends Beverage {

    public static final String LATTE = "Latte";

    public Latte(String drinkName) {
        super(LATTE);
    }

    @Override
    public Price getPrice() {
        Price price = getAddsOnPrice();
        return Price.builder()
                .val(6d)
                .currency(Currency.DOLLAR)
                .build();
    }
}
