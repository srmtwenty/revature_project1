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
        String sql="insert into offers(name, amount, offerStatus_id) values(?, ?, ?)";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, offer.getName());
            stmt.setDouble(2, offer.getAmount());
            //stmt.setInt(3, offer.getCarId());
            stmt.setInt(3, offer.getOfferStatus().ordinal());


            int success = stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if(keys.next()){
                int id=keys.getInt(1);
                return offer.setId(id);
            }
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
                        .setId(rs.getInt("id"))
                );
                Offer offer2 = new Offer().setName("first");
                /*Offer offer=new Offer();
                offer.setName(results.getString("name"));
                offer.setAmount(results.getDouble("amount"));
                offer.setCarId(results.getInt("cars_id"));
                offer.setOfferStatus(OfferStatus.valueOf(results.getString("offer_status")));
                offer.setId(results.getInt("id"));

                offers.add(offer);

                 */
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
                        .setOfferStatus(OfferStatus.values()[rs.getInt("offerStatus_id")]);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /*public List<Offer> getAllOffersByOfferStatus(OfferStatus offerStatus){
        List<Offer> filteredOffers = new ArrayList<>();
        for(Offer offer:offers){
            if(offer.getOfferStatus()==offerStatus){
                filteredOffers.add(offer);
            }
        }
        return filteredOffers;
    }

     */

    @Override
    public Offer update(Offer offer) {
        String sql="update offers set name=?, amount=?, offerStatus_id=? where id=?";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, offer.getName());
            stmt.setDouble(2, offer.getAmount());

            stmt.setInt(3, offer.getOfferStatus().ordinal());
            stmt.setInt(4, offer.getId());

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
        return false;
    }
}
