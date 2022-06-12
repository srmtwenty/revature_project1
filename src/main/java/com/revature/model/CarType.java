package com.revature.model;

public enum CarType {
    TRUCK("TRUCK"),
    FOUR_WHEEL("FOUR_WHEEL");

    public final String value;

    CarType(String value){
        this.value=value;
    }
}
