package com.revature.model;

public enum CarType {
    SUV("SUV"),
    HATCHBACK("HATCHBACK"),
    CROSSOVER("CROSSOVER"),
    SEDAN("SEDAN"),
    SPORTS_CAR("SPORTS_CAR"),
    COUPE("COUPE"),
    MINIVAN("MINIVAN");

    public final String value;

    CarType(String value){
        this.value=value;
    }
}
