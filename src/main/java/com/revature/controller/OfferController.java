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
        /*List<Offer> offers=offerService.getAllOffers();
        String offerStatusParam=ctx.queryParam("offerStatus");

        if(offerStatusParam==null){
            offers=offerService.getAllOffers();
        }
        else{
            try{
                OfferStatus offerStatus= OfferStatus.valueOf(offerStatusParam.toUpperCase(Locale.ROOT));
                offers=offerService.getAllOffersByOfferStatus(offerStatus);
            }catch(IllegalArgumentException e){
                String failureMessage="{\"success\":false, \"message\":\"" +
                        "Please only use the following offer status values: " + Arrays.toString(OfferStatus.values())
                        + "\"}";
                ctx.status(400).json(failureMessage);
                return;
            }
        }
        ctx.json(offers);

         */
        ctx.json(offerService.getAllOffers());
    };

    public Handler postOffer=ctx->{
        Offer offer=ctx.bodyAsClass(Offer.class);
        offer=offerService.createOffer(offer);

        if(offer!=null){
            ctx.json(offer);
        }else{
            ctx.result("Offer not created!").status(400);
        }
    };

    public Handler getOfferById=ctx->{
        String param=ctx.pathParam("id");
        //int id=0;
        try{
            //id=Integer.parseInt(param);
            //ctx.json(offerService.getOfferById(id));
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
        Offer offer = new Offer();
        offer = ctx.bodyAsClass(Offer.class);

        if(offer!=null){
            ctx.status(200).json(offer);
        }else{
            ctx.status(400).result("Could not update the offer");
        }
    };
}
