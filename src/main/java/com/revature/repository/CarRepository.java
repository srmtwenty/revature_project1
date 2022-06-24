package com.revature.repository;

import com.revature.model.Car;
import com.revature.model.CarType;
import com.revature.model.Offer;
import com.revature.util.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements DAO<Car>{

    @Override
    public Car create(Car car) {
        String sql="insert into cars(name, manufacturer, color, price, carType_id, user_id) values(?, ?, ?, ?, ?, ?)";

        try{
            Connection connection= ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, car.getName());
            stmt.setString(2, car.getManufacturer());
            stmt.setString(3, car.getColor());
            stmt.setDouble(4, car.getPrice());
            stmt.setInt(5, car.getCarType().ordinal());
            stmt.setInt(6, car.getUser_id());

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
                        .setColor(results.getString("color"))
                        .setPrice(results.getDouble("price"))
                        .setCarType(CarType.values()[results.getInt("carType_id")])
                        .setUser_id(results.getInt("user_id"))
                        .setId(results.getInt("id"))
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cars;
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
                        .setColor(rs.getString("color"))
                        .setPrice(rs.getDouble("price"))
                        .setCarType(CarType.values()[rs.getInt("carType_id")])
                        .setUser_id((rs.getInt("user_id")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Car getByName(String name){
        String sql="select * from cars where name=?";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Car()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setManufacturer(rs.getString("manufacturer"))
                        .setColor(rs.getString("color"))
                        .setPrice(rs.getDouble("price"))
                        .setCarType(CarType.values()[rs.getInt("carType_id")])
                        .setUser_id(rs.getInt("user_id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> getCarsByCarType(CarType carType){
        List<Car> cars = new ArrayList<>();

        String sql = "select * from cars where carType_id=? order by id";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, carType.ordinal());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                cars.add(new Car()
                                .setName(rs.getString("name"))
                                .setManufacturer(rs.getString("manufacturer"))
                                .setPrice(rs.getDouble("price"))
                                .setColor(rs.getString("color"))
                                .setCarType(CarType.values()[rs.getInt("carType_id")])
                                .setUser_id(rs.getInt("user_id"))
                        .setId(rs.getInt("id"))
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return cars;
    }

    public List<Car> getAllCarsByUser_id(int user_id){
        List<Car> cars = new ArrayList<>();
        String sql = "select * from offers where user_id=? order by id";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user_id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                cars.add(new Car()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setManufacturer(rs.getString("manufacturer"))
                        .setPrice(rs.getDouble("price"))
                        .setColor(rs.getString("color"))
                        .setCarType(CarType.values()[rs.getInt("carType_id")])
                        .setUser_id(rs.getInt("user_id"))
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public Car update(Car car) {
        String sql="update cars set name=?, color=?, manufacturer=?, price=?, carType_id=?, user_id=? where id=?";
        try{
            Connection connection=ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, car.getName());
            stmt.setString(2, car.getColor());
            stmt.setString(3, car.getManufacturer());
            stmt.setDouble(4, car.getPrice());
            stmt.setInt(5, car.getCarType().ordinal());
            stmt.setInt(6, car.getUser_id());
            stmt.setInt(7, car.getId());

            int success = stmt.executeUpdate();

            if(success!=0){
                return getById(car.getId());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from cars where id=?";

        try{Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            int success = stmt.executeUpdate();

            return success !=0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
