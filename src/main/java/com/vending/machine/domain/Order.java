package com.vending.machine.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private String userPhoneNumber;
    private Price orderPrice;
    private Beverage beverage;
    private Status status;

    public enum Status {
        CREATED, PAID, FAILED, DONE
    }

}
