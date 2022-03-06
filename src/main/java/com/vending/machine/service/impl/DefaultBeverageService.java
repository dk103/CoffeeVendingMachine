package com.vending.machine.service.impl;

import com.vending.machine.domain.AddsOn;
import com.vending.machine.domain.Beverage;
import com.vending.machine.domain.Order;
import com.vending.machine.exception.DrinkNotAvailableException;
import com.vending.machine.exception.OrderException;
import com.vending.machine.pubsub.impl.MyOrderTopic;
import com.vending.machine.service.BeverageService;
import com.vending.machine.service.DrinkMaker;
import com.vending.machine.service.PaymentService;

import java.util.Collection;

public class DefaultBeverageService implements BeverageService {

    DrinkMaker maker;
    PaymentService inHouseService;
    MyOrderTopic orderTopic;

    public DefaultBeverageService(PaymentService inHouseService) {
        this.maker = new DrinkMaker();
        this.inHouseService = inHouseService;
    }

    @Override
    public Order placeDrinkRequest(Beverage beverage, Collection<AddsOn> addsOn, String phoneNumber) {
        try {
            addsOn.forEach(addOn -> beverage.addAddOn(addOn));
            maker.checkDrinkFeasibility(beverage, addsOn);
            return Order.builder()
                    .beverage(beverage)
                    .orderPrice(beverage.getPrice())
                    .userPhoneNumber(phoneNumber)
                    .status(Order.Status.CREATED)
                    .build();
        } catch (DrinkNotAvailableException e) {
            throw new OrderException("Unable to Serve request");
        }
    }

    @Override
    public void collectPayment(Order order, Double userPaid) {

        PaymentService.Change change = inHouseService.processPayment(userPaid, order.getOrderPrice());

        if (!change.getDescription().equalsIgnoreCase("settled")) {
            order.setStatus(Order.Status.FAILED);
            System.out.println("Payment failed to process");
            return;
        }
        order.setStatus(Order.Status.PAID);
        orderTopic.postMessage(order);
        System.out.println("payment done :: change returned - " + change.getVal());

    }
}
