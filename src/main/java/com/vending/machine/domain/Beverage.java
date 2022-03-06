package com.vending.machine.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class Beverage {

    private String drinkName;
    private Collection<AddsOn> addsOn;

    private Map<String, IngredientStock> ingredientRequirements;

    public Beverage(String drinkName) {
        this.drinkName = drinkName;
        this.ingredientRequirements = new HashMap<>();
    }

    public Price totalPrice() {
        return Price.builder()
                .val(this.getPrice().getVal() + getAddsOnPrice().getVal())
                .build();
    }

    public abstract Price getPrice();

    protected Price getAddsOnPrice() {
        if (addsOn != null || addsOn.isEmpty()) {
            return Price.builder()
                    .val(0d)
                    .build();
        }
        Double totalPrice = addsOn.stream()
                .map(x -> x.getPrice().getVal())
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getSum();

        return Price.builder()
                .val(totalPrice)
                .build();
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        this.ingredientRequirements
                .put(ingredient.getId(), IngredientStock.builder()
                        .ingredient(ingredient)
                        .Size(quantity)
                        .build()
                );
    }

    public String getDrinkName() {
        return this.drinkName;
    }

    public Collection<AddsOn> getAddsOn() {
        return addsOn;
    }

    public void addAddOn(AddsOn addsOn) {
        this.addsOn.add(addsOn);
    }

    public Map<String, IngredientStock> getIngredientRequirements() {
        return ingredientRequirements;
    }
}
