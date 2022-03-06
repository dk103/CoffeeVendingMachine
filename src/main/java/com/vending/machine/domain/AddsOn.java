package com.vending.machine.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddsOn extends Beverage {

    private Ingredient ingredient;
    private int quantity;
    private Price price;

    public AddsOn(Ingredient ingredient, Double val, int quantity) {
        super("ADDS_ON - " + ingredient.getName());
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.price = Price.builder()
                .val(val)
                .build();
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public Price getPrice() {
        return price;
    }
}
