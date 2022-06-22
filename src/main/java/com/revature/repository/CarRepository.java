package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarType;
import com.revature.util.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements DAO<Car>{
    //private List<Car> cars;
    @Override
    public Car create(Car car) {
        String sql="insert into cars(name, manufacturer, price, carType_id) values(?, ?, ?, ?)";

        try{
            Connection connection= ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, car.getName());
            stmt.setString(2, car.getManufacturer());
            stmt.setDouble(3, car.getPrice());
            stmt.setInt(4, car.getCarType().ordinal());

            int success = stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if(keys.next()){
                int id=keys.getInt(1);
                return car.setId(id);
            }
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
                cars.add(new Car()
                        .setName(results.getString("name"))
                        .setManufacturer(results.getString("manufacturer"))
                        .setPrice(results.getDouble("price"))
                        //.setUserId(results.getInt("users_id"))
                        .setCarType(CarType.valueOf(results.getString("carType_id")))
                        .setId(results.getInt("id"))
                );
                Car car2 = new Car().setName("first");
                /*
                Car car = new Car();
                car.setName(results.getString("name"));
                car.setManufacturer(results.getString("manufacturer"));
                car.setPrice(results.getDouble("price"));
                car.setUserId(results.getInt("users_id"));
                car.setCarType(CarType.valueOf(results.getString("car_type")));
                car.setId(results.getInt("id"));

                cars.add(car);

                 */
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cars;
        //return null;
    }
    public List<Car> getCarsByCarType(CarType carType){
        List<Car> filteredCars = new ArrayList<>();
        /*for(Car car: cars){
            if(car.getCarType().equals(carType)){
                filteredCars.add(car);
            }
        }

         */
        return filteredCars;
    }

    @Override
    public Car getById(int id) {
        String sql="select * from cars where id=?";
        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                return new Car()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setManufacturer(rs.getString("manufacturer"))
                        .setPrice(rs.getDouble("price"))
                        .setCarType(CarType.values()[rs.getInt("carType_id")]);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
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
