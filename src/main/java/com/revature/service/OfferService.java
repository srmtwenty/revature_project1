package com.revature.service;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.repository.OfferRepository;

import java.util.List;

public class OfferService {
    OfferRepository offerRepository;


    public OfferService() {
        offerRepository = new OfferRepository();
    }

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer createOffer(Offer offer) {
        return offerRepository.create(offer);
    }

    public List<Offer> getAllOffers() {
        return offerRepository.getAll();
    }

    public List<Offer> getAllOffersByCarId(int car_id){
        return offerRepository.getAllOffersByCarId(car_id);
    }

    public List<Offer> getAllOffersByOfferStatus(OfferStatus offerStatus){
        return offerRepository.getAllOffersByOfferStatus(offerStatus);
    }

    public Offer getOfferById(int id) {
        return offerRepository.getById(id);
    }

    public Offer updateOffer(Offer offer) {
        return offerRepository.update(offer);
    }

    public boolean deleteById(int id){
        return offerRepository.deleteById(id);
    }
}