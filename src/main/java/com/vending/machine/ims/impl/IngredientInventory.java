package com.vending.machine.ims.impl;

import com.vending.machine.domain.Ingredient;
import com.vending.machine.domain.IngredientStock;
import com.vending.machine.ims.Inventory;

import java.util.HashMap;
import java.util.Map;

public class IngredientInventory implements Inventory<Ingredient> {

    private Map<String, IngredientStock> map;

    public IngredientInventory(Map<String, IngredientStock> map) {
        this.map = new HashMap<>();
    }

    @Override
    public void onBoardIngredient(Ingredient ingredient, int quantity) {
        this.map.put(ingredient.getId(), IngredientStock.builder()
                .ingredient(ingredient)
                .Size(quantity)
                .build()
        );
    }

    @Override
    public void removeIngredient(String id) {
      if (!map.containsKey(id)) {
          return;
      }
      this.map.remove(id);
    }

    @Override
    public Ingredient getIngredient(String id, int requestedQuantity) {
        IngredientStock stockRequested = map.computeIfPresent(id, (k,v) -> {
            IngredientStock curStock = v;
            if (curStock.getSize() >= requestedQuantity) {
                curStock.setSize(curStock.getSize() - requestedQuantity);
                return curStock;
            }
            throw new RuntimeException(String.format("Ingredient is over - %s", curStock.getIngredient().getName()));
        });
        return stockRequested.getIngredient();
    }

    @Override
    public void refill(String id, int quantity) {
       map.computeIfPresent(id, (k, v) -> {
           IngredientStock stock = v;
           stock.setSize(v.getSize() + quantity);
           return stock;
       });
    }
}
