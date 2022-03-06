package com.vending.machine.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Ingredient {

    private String name;
    private String id;
}
