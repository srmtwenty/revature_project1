package com.revature.model;

import java.util.Objects;

public class Offer {
    private int id;

    private int carId;
    private double amount;
    private OfferStatus offerStatus;


    public Offer(){

    }
    public Offer(int id, int carId, double amount){
        this.id=id;
        this.carId=carId;
        this.amount=amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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
        return id == offer.id && carId == offer.carId && Double.compare(offer.amount, amount) == 0 && offerStatus == offer.offerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, amount, offerStatus);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", carId=" + carId +
                ", amount=" + amount +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
