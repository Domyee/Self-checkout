package com.selfcheckout.dto.enumeration;

import lombok.Getter;

@Getter
public enum MeasurementUnit {
    KG("Kg"),
    G("g"),
    PZ("pz");

    private final String unit;

    MeasurementUnit(String unit) {
        this.unit = unit;
    }
}

