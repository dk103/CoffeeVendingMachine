package com.vending.machine.service.impl;

import com.vending.machine.domain.Order;
import com.vending.machine.service.DispenseService;
import com.vending.machine.service.DrinkMaker;

public class InHouseDispenserService implements DispenseService {

    DrinkMaker maker;

    @Override
    public void dispenseOrder(Order order) {
        maker.makeDrink(order);
        order.setStatus(Order.Status.DONE);
        System.out.println("dispense order - " + order.getBeverage().getDrinkName());
    }
}
