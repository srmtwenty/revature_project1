package com.revature.model;

public enum OfferStatus {
    OPEN("OPEN"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    public final String value;

    OfferStatus(String value) {
        this.value = value;
    }
}