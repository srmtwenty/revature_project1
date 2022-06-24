package com.revature.repository;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.util.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository implements DAO<Offer>{

    @Override
    public Offer create(Offer offer) {
        String sql="insert into offers(name, amount, offerStatus_id, car_id, offerUser_id) values(?, ?, ?, ?, ?)";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, offer.getName());
            stmt.setDouble(2, offer.getAmount());
            stmt.setInt(3, offer.getOfferStatus().ordinal());
            stmt.setInt(4, offer.getCar_id());
            stmt.setInt(5, offer.getOfferUser_id());

            int success = stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if(keys.next()){
                int id=keys.getInt(1);
                return offer.setId(id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Offer> getAll() {
        List<Offer> offers=new ArrayList<>();
        String sql="select * from offers";

        try{
            Connection connection=ConnectionUtility.getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                offers.add(new Offer()
                        .setName(rs.getString("name"))
                        .setAmount(rs.getDouble("amount"))
                        .setOfferStatus(OfferStatus.values()[rs.getInt("offerStatus_id")])
                        .setCar_id(rs.getInt("car_id"))
                        .setOfferUser_id(rs.getInt("offerUser_id"))
                        .setId(rs.getInt("id"))
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public Offer getById(int id) {
        String sql="select * from offers where id=?";
        try{
            Connection connection=ConnectionUtility.getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Offer()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setAmount(rs.getDouble("amount"))
                        .setOfferStatus(OfferStatus.values()[rs.getInt("offerStatus_id")])
                        .setCar_id(rs.getInt("car_id"))
                        .setOfferUser_id(rs.getInt("offerUser_id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Offer> getAllOffersByCarId(int car_id){
        List<Offer> offers = new ArrayList<>();
        String sql="select * from offers where car_id=? order by id";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, car_id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                offers.add(new Offer()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setAmount(rs.getDouble("amount"))
                        .setOfferStatus(OfferStatus.values()[rs.getInt("offerStatus_id")])
                        .setCar_id(rs.getInt("car_id"))
                        .setOfferUser_id(rs.getInt("offerUser_id"))
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return offers;
    }

    public List<Offer> getAllOffersByOfferStatus(OfferStatus offerStatus){
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers where offerStatus_id=? order by id";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, offerStatus.ordinal());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                offers.add(new Offer()
                        .setName(rs.getString("name"))
                        .setAmount(rs.getDouble("amount"))
                        .setOfferUser_id(rs.getInt("offerUser_id"))
                        .setOfferStatus(OfferStatus.values()[rs.getInt("offerStatus_id")])
                        .setCar_id(rs.getInt("car_id"))
                        .setId(rs.getInt("id"))
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return offers;
    }

    @Override
    public Offer update(Offer offer) {
        String sql="update offers set name=?, amount=?, offerStatus_id=?, car_id=?, offerUser_id=? where id=?";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, offer.getName());
            stmt.setDouble(2, offer.getAmount());

            stmt.setInt(3, offer.getOfferStatus().ordinal());
            stmt.setInt(4, offer.getCar_id());
            stmt.setInt(5, offer.getOfferUser_id());
            stmt.setInt(6, offer.getId());

            int success = stmt.executeUpdate();

            if(success!=0){
                return getById(offer.getId());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from offers where id=?";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            int success = stmt.executeUpdate();

            return success !=0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Offer> getAllOffersByUser_id(int user_id){
        return null;
    }
}
