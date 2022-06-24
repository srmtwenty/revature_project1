package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    private int id;
    private String name;
    private String manufacturer;

    private String color;
    private double price;

    private CarType carType;

    private int user_id;


    public Car(){
    }

    public int getId(){
        return id;
    }
    public Car setId(int id){
        this.id=id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Car setName(String name) {
        this.name = name;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Car setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Car setPrice(double price) {
        this.price = price;
        return this;
    }

    public CarType getCarType() {
        return carType;
    }

    public Car setCarType(CarType carType) {
        this.carType = carType;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public Car setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Double.compare(car.price, price) == 0 && user_id == car.user_id && Objects.equals(name, car.name) && Objects.equals(manufacturer, car.manufacturer) && Objects.equals(color, car.color) && carType == car.carType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, color, price, carType, user_id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", carType=" + carType +
                ", user_id=" + user_id +
                '}';
    }
}
