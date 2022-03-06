package com.vending.machine.ims;


public interface Inventory<T> {

    void onBoardIngredient(T ingredient, int quantity);

    void removeIngredient(String id);

    T getIngredient(String id, int requestedQuantity);

    void refill(String id, int quantity);
}
