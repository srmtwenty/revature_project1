package com.revature.repository;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;

import java.util.ArrayList;
import java.util.List;

public class OfferRepository implements DAO<Offer>{
    private List<Offer> offers;

    public OfferRepository(){
        offers=new ArrayList();
    }

    @Override
    public Offer create(Offer offer) {
        if(offers.add(offer)){
            return offer;
        }
        return null;
    }

    @Override
    public List<Offer> getAll() {
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
