package com.vending.machine.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class IngredientStock {

    int Size;
    Ingredient ingredient;

    public IngredientStock(int size, Ingredient ingredient) {
        Size = size;
        this.ingredient = ingredient;
    }
}