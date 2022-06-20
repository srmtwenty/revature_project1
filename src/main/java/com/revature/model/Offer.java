package com.revature.model;

import java.util.Objects;

public class Offer {
    private int id;

    private String name;

    private double amount;

    private int carId;
    private OfferStatus offerStatus;


    public Offer(){

    }
    public Offer(int id, String name, double amount, int carId, OfferStatus offerStatus){
        this.id=id;
        this.name=name;
        this.amount=amount;
        this.carId=carId;
        this.offerStatus=offerStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return id == offer.id && Double.compare(offer.amount, amount) == 0 && carId == offer.carId && Objects.equals(name, offer.name) && offerStatus == offer.offerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, carId, offerStatus);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", carId=" + carId +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
