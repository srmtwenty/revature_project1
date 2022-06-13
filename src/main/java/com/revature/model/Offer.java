package com.revature.model;

import java.util.Objects;

public class Offer {
    private int id;
    private double amount;
    private OfferStatus offerStatus;

    public Offer(){

    }
    public Offer(int id, double amount){
        this.id=id;
        this.amount=amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && Double.compare(offer.amount, amount) == 0 && offerStatus == offer.offerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, offerStatus);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", amount=" + amount +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
