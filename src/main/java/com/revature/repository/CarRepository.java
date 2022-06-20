package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarType;
import com.revature.util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements DAO<Car>{
    private List<Car> cars;
    @Override
    public Car create(Car car) {
        String sql="insert into cars(name, manufacturer, price) values(?, ?, ?, ?)";

        try{
            Connection connection= ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, car.getName());
            stmt.setString(2, car.getManufacturer());
            stmt.setDouble(3, car.getPrice());

            int success = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getAll(){
        List<Car> cars = new ArrayList<>();

        String sql="select * from cars";
        try{
            Connection connection=ConnectionUtility.getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql);

            ResultSet results = stmt.executeQuery();

            while(results.next()){
                Car car = new Car();
                car.setName(results.getString("name"));
                car.setManufacturer(results.getString("manufacturer"));
                car.setPrice(results.getDouble("price"));
                car.setId(results.getInt("id"));

                cars.add(car);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cars;
        //return null;
    }
    public List<Car> getCarsByCarType(CarType carType){
        List<Car> filteredCars = new ArrayList<>();
        for(Car car: cars){
            if(car.getCarType().equals(carType)){
                filteredCars.add(car);
            }
        }
        return filteredCars;
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
