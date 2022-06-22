package com.revature.model;

import java.util.Objects;

public class Offer {
    private int id;

    private String name;

    private double amount;

    //private int carId;
    private OfferStatus offerStatus;


    public Offer(){

    }
    /*public Offer(int id, String name, double amount, int carId, OfferStatus offerStatus){
        this.id=id;
        this.name=name;
        this.amount=amount;
        this.carId=carId;
        this.offerStatus=offerStatus;
    }*/

    public int getId() {
        return id;
    }

    public Offer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Offer setName(String name) {
        this.name = name;
        return this;
    }

    //public int getCarId() {
    //    return carId;
    //}

    //public Offer setCarId(int carId) {
    //    this.carId = carId;
    //    return this;
    //}

    public double getAmount() {
        return amount;
    }

    public Offer setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public Offer setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && Double.compare(offer.amount, amount) == 0 && Objects.equals(name, offer.name) && offerStatus == offer.offerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, offerStatus);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +

                ", offerStatus=" + offerStatus +
                '}';
    }
}
