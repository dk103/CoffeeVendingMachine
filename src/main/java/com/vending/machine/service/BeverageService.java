package com.vending.machine.service;

import com.vending.machine.domain.AddsOn;
import com.vending.machine.domain.Beverage;
import com.vending.machine.domain.Order;

import java.util.Collection;

public interface BeverageService {

    Order placeDrinkRequest(Beverage beverage, Collection<AddsOn> addsOn, String phoneNumber);

    void collectPayment(Order order, Double userPaid);
}
