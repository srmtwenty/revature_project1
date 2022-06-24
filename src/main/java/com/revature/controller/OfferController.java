package com.revature.controller;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.service.OfferService;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class OfferController {
    OfferService offerService=new OfferService();

    public OfferController(){
        offerService = new OfferService();
    }

    public OfferController(OfferService offerService){
        this.offerService=offerService;
    }
    public Handler getAllOffers= ctx->{
        String offerStatusParam = ctx.queryParam("offerStatus");
        String carIdParam=ctx.queryParam("car_id");

        if(carIdParam==null && offerStatusParam==null){
            ctx.json(offerService.getAllOffers());
        }else if(carIdParam!=null){
            ctx.json(offerService.getAllOffersByCarId(Integer.parseInt(carIdParam)));
        }else {
            try {
                OfferStatus offerStatus=OfferStatus.valueOf(offerStatusParam.toUpperCase());
                ctx.json(offerService.getAllOffersByOfferStatus(offerStatus));

            }catch(IllegalArgumentException e){
                ctx.status(400).result("Please enter a valid offer status!");
            }
        }
    };

    public Handler postOffer=ctx->{
        Offer offer = ctx.bodyAsClass(Offer.class);
        offer = offerService.createOffer(offer);

        if(offer!=null){
            ctx.json(offer);
        }else{
            ctx.result("Offer not created!").status(400);
        }
    };

    public Handler getOfferById=ctx->{
        String param=ctx.pathParam("id");

        try{
            Offer offer = offerService.getOfferById(
                    Integer.parseInt(param)
            );
            if(offer!=null){
                ctx.json(offer);
            }else{
                ctx.result("Offer not found! Please try with another id instead");
            }
        }catch(NumberFormatException e){
            ctx.result("Please enter only valid integer as an id");
            ctx.status(400);
        }
    };

    public Handler updateOffer=ctx->{
        Offer offer = ctx.bodyAsClass(Offer.class);
        offer = offerService.updateOffer(offer);

        if(offer!=null){
            ctx.status(200).json(offer);
        }else{
            ctx.status(400).result("Could not update the offer");
        }
    };

    public Handler deleteOffer=ctx->{
        String param = ctx.pathParam("id");

        try{
            int id = Integer.parseInt(param);
            if(offerService.deleteById(id)){
                ctx.status(204);
            }else{
                ctx.status(400).result("Could not delete the offer!");
            }
        }catch(NumberFormatException e){
            ctx.status(400).result("Please enter a valid id");
        }
    };
}
