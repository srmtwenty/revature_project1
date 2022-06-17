package com.revature.model;

public enum Role {
    USER("USER"),

    EMPLOYEE("EMPLOYEE"),
    CUSTOMER("CUSTOMER");

    public final String value;

    Role(String value) {
        this.value = value;
    }


}
