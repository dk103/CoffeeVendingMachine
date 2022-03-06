package com.vending.machine.pubsub;

public interface Subject<T> {

    void register(Observer obj);

    void unregister(Observer obj);

    void notifyObservers();

    T getUpdate(Observer obj);

}
