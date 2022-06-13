package com.revature.service;

import com.revature.model.Car;
import com.revature.model.CarType;
import com.revature.model.Role;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private List<Car> cars;

    public CarService(){
        cars=new ArrayList<>();
    }
    public CarService(List<Car> cars){
        this.cars=cars;
    }
    public boolean createCar(Car car){
        return cars.add(car);
    };

    public List<Car> getAllCars(){
        return cars;
    }

    public List<Car> getAllCarsByCarType(CarType carType){
        List<Car> filteredCars= new ArrayList<>();
        for(Car car:filteredCars){
            if(car.getCarType()==carType){
                filteredCars.add(car);
            }

        }
        return filteredCars;
    }
    public Car getCarById(int id){
        for(int i=0; i<cars.size();i++){
            if(cars.get(i).getId()==id){
                return cars.get(i);
            }
        }
        return null;
    }
    public boolean deleteById(int id){
        for(int i=0; i<cars.size(); i++){
            if(cars.get(i).getId()==id){
                cars.remove(i);
                return true;
            }
        }
        return false;
    }
}
