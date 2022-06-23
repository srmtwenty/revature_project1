package com.revature.service;

import com.revature.model.Car;
import com.revature.model.CarType;
import com.revature.model.Role;
import com.revature.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    //private List<Car> cars;
    CarRepository carRepository;

    public CarService(){
        carRepository=new CarRepository();
    }
    public CarService(CarRepository carRepository){
        this.carRepository=carRepository;
    }
    public Car createCar(Car car){
        return carRepository.create(car);
    };

    public List<Car> getAllCars(){
        return carRepository.getAll();
    }

    public List<Car> getAllCarsByCarType(CarType carType){
        return carRepository.getCarsByCarType(carType);
    }
    public Car getCarById(int id){

        return carRepository.getById(id);
    }
    public Car updateCar(Car car){
        return carRepository.update(car);
    }

    public boolean deleteById(int id){
        return carRepository.deleteById(id);
    }
}
