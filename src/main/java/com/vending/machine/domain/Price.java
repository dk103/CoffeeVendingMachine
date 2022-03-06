package com.vending.machine.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class Price {

    @Builder.Default
    private Currency currency = Currency.DOLLAR;
    private Double val;

}
