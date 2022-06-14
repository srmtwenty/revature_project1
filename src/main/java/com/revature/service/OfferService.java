package com.revature.service;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.repository.OfferRepository;

import java.util.List;

public class OfferService {
    private OfferRepository offerRepository;

    public OfferService(){
        offerRepository=new OfferRepository();
    }

    public OfferService(OfferRepository offerRepository){
        this.offerRepository=offerRepository;
    }

    public Offer createOffer(Offer offer){
        return offerRepository.create(offer);
    }

    public List<Offer> getAllOffers(){
        return offerRepository.getAll();
    }

    public List<Offer> getAllOffersByOfferStatus(OfferStatus offerStatus){
        return offerRepository.getAllOffersByOfferStatus(offerStatus);
    }

    public Offer getOfferById(int id){
        return offerRepository.getById(id);
    }
}
