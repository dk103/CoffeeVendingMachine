package com.vending.machine.exception;

public class DrinkNotAvailableException extends RuntimeException {

    public DrinkNotAvailableException(String message) {
        super(message);
    }
}
