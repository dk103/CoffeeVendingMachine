package com.vending.machine.pubsub.impl;

import com.vending.machine.domain.Order;
import com.vending.machine.pubsub.Observer;
import com.vending.machine.pubsub.Subject;
import com.vending.machine.service.DispenseService;

public class MyOrderSubscriber implements Observer {

    private String name;
    private Subject<?> topic;
    DispenseService dispenseService;

    public MyOrderSubscriber(String nm){
        this.name=nm;
    }
    @Override
    public void update() {
        Order msg = (Order) topic.getUpdate(this);
        if(msg == null || !(msg.getStatus() == Order.Status.PAID)){
            System.out.println(name+":: No new order");
        }else {
            dispenseService.dispenseOrder(msg);
            System.out.println(name + ":: Consuming message::" + msg);

        }
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }

}