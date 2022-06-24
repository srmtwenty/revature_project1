package com.revature.model;

import java.util.Objects;

public class Offer {
    private int id;

    private String name;

    private double amount;

    //private int carId;
    private OfferStatus offerStatus;

    private int car_id;

    private int offerUser_id;

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

    public int getCar_id() {
        return car_id;
    }

    public Offer setCar_id(int car_id) {
        this.car_id = car_id;
        return this;
    }

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

    public int getOfferUser_id() {
        return offerUser_id;
    }

    public Offer setOfferUser_id(int offerUser_id) {
        this.offerUser_id = offerUser_id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && Double.compare(offer.amount, amount) == 0 && car_id == offer.car_id && offerUser_id == offer.offerUser_id && Objects.equals(name, offer.name) && offerStatus == offer.offerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, offerStatus, car_id, offerUser_id);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", offerStatus=" + offerStatus +
                ", car_id=" + car_id +
                ", offerUser_id=" + offerUser_id +
                '}';
    }
}
