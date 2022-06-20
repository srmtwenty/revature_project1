package com.revature.repository;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository implements DAO<Offer>{
    private List<Offer> offers;

    /*public OfferRepository(){
        offers=new ArrayList();
    }
    */
    @Override
    public Offer create(Offer offer) {
        String sql="insert into offers(name, amount, cars_id, offer_status) values(?, ?, ?, ?)";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, offer.getName());
            stmt.setDouble(2, offer.getAmount());
            stmt.setInt(3, offer.getCarId());
            stmt.setString(4, offer.getOfferStatus().name());

            int success = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        //(offers.add(offer)){
        //    return offer;
        //}
        return null;
    }

    @Override
    public List<Offer> getAll() {
        List<Offer> offers=new ArrayList<>();
        //return offers;

        String sql="select * offers";
        try{
            Connection connection=ConnectionUtility.getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql);

            ResultSet results = stmt.executeQuery();

            while(results.next()){
                Offer offer=new Offer();
                offer.setName(results.getString("name"));
                offer.setAmount(results.getDouble("amount"));
                offer.setCarId(results.getInt("car_id"));
                offer.setOfferStatus(OfferStatus.valueOf(results.getString("offer_status")));
                offer.setId(results.getInt("id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public Offer getById(int id) {
        for(int i=0; i<offers.size(); i++){
            if(offers.get(i).getId()==id){
                return offers.get(i);
            }
        }
        return null;
    }

    public List<Offer> getAllOffersByOfferStatus(OfferStatus offerStatus){
        List<Offer> filteredOffers = new ArrayList<>();
        for(Offer offer:offers){
            if(offer.getOfferStatus()==offerStatus){
                filteredOffers.add(offer);
            }
        }
        return filteredOffers;
    }

    @Override
    public Offer update(Offer offer) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
