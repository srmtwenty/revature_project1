package com.revature.controller;

import com.revature.model.Offer;
import com.revature.model.OfferStatus;
import com.revature.model.Role;
import com.revature.service.OfferService;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class OfferController {
    OfferService offerService=new OfferService();

    public Handler getAllOffers= ctx->{
        List<Offer> offers=offerService.getAllOffers();
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
    };

    public Handler postOffer=ctx->{
        Offer offer=ctx.bodyAsClass(Offer.class);
        offerService.createOffer(offer);
    };

    public Handler getOfferById=ctx->{
        String param=ctx.pathParam("id");
        int id=0;
        try{
            id=Integer.parseInt(param);
            ctx.json(offerService.getOfferById(id));
        }catch(NumberFormatException e){
            ctx.result("Enter id number please");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }catch(NullPointerException e){
            System.out.println("NULL");
        }
    };
}
