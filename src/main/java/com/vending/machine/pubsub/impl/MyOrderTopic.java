package com.vending.machine.pubsub.impl;

import com.vending.machine.domain.Order;
import com.vending.machine.pubsub.Observer;
import com.vending.machine.pubsub.Subject;

import java.util.ArrayList;
import java.util.List;

public class MyOrderTopic implements Subject<Order> {

    public Order message;
    private List<Observer> observers;
    private final Object MUTEX= new Object();
    boolean changed;

    public MyOrderTopic(){
        this.observers=new ArrayList<>();
    }
    @Override
    public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if(!observers.contains(obj)) observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }

    }

    @Override
    public Order getUpdate(Observer obj) {
        return this.message;
    }

    //method to post message to the topic
    public void postMessage(Order msg){
        System.out.println("Message Posted to Topic:" + msg);
        this.message = msg;
        this.changed=true;
        notifyObservers();
    }
}
