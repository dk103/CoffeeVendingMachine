package com.vending.machine.service;

import com.vending.machine.domain.AddsOn;
import com.vending.machine.domain.Beverage;
import com.vending.machine.domain.Ingredient;
import com.vending.machine.domain.Order;
import com.vending.machine.exception.DrinkNotAvailableException;
import com.vending.machine.ims.Inventory;

import java.util.Collection;

public class DrinkMaker {

    private Inventory<?> inventory;

    public void checkDrinkFeasibility(Beverage beverage, Collection<AddsOn> addsOns) {
        try {
            beverage.getIngredientRequirements()
                    .forEach((id,ingredientStock) -> {
                       Ingredient requestedStock = (Ingredient) inventory.getIngredient(id, ingredientStock.getSize());
                    });
            if (addsOns != null && addsOns.size() > 0) {
                addsOns.stream().forEach(x -> {
                    Ingredient requestedAddsOn = (Ingredient) inventory.getIngredient(x.getIngredient().getId(), 1);
                });
            }

        } catch (Exception e) {
            throw new DrinkNotAvailableException("Ingredient lack quantity");
        }
    }

    public void makeDrink(Order order) {

        System.out.println("Preparing Order");
        Beverage beverage = order.getBeverage();
        Collection<AddsOn> addsOns = order.getBeverage().getAddsOn();

        beverage.getIngredientRequirements()
                .forEach((id,ingredientStock) -> {
                    Ingredient requestedStock = (Ingredient) inventory.getIngredient(id, ingredientStock.getSize());
                    System.out.println(" Added :- " + requestedStock.getName());
                });

        if (addsOns != null && addsOns.size() > 0) {
            System.out.println("------- ADD_ONS ------");
            addsOns.stream().forEach(x -> {
                Ingredient requestedAddsOn = (Ingredient) inventory.getIngredient(x.getIngredient().getId(), 1);
                System.out.println(" Added:- " + requestedAddsOn.getName());
            });
        }
        System.out.println("Drink Done");
    }

}
