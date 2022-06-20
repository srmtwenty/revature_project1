package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    private int id;
    private String name;
    private String manufacturer;
    private double price;

    private CarType carType;

    public Car(){
    }

    public Car(int id, String name, String manufacturer, double price, CarType carType){
        this.id=id;
        this.name=name;
        this.manufacturer=manufacturer;
        this.price=price;
        this.carType=carType;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Double.compare(car.price, price) == 0 && Objects.equals(name, car.name) && Objects.equals(manufacturer, car.manufacturer) && carType == car.carType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, price, carType);
    }

    @Override
    public String toString(){
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Manufacturer: " + manufacturer+"\n" +
                "Price: " + price+"\n" +
                "Car Type: "+ carType + "\n";
    }
}
