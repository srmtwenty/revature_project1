package com.revature.repository;

import com.revature.model.Car;

import java.util.List;

public class CarRepository implements DAO<Car>{

    @Override
    public Car create(Car car) {
        return null;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public Car getById(int id) {
        return null;
    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
