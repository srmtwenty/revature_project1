package com.revature;

import com.revature.controller.CarController;
import com.revature.controller.OfferController;
import com.revature.controller.UserController;
import com.revature.util.ConnectionUtility;
import io.javalin.Javalin;

public class Driver {
    public static void main(String [] args){

        UserController userController=new UserController();
        CarController carController=new CarController();
        OfferController offerController=new OfferController();


        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx->ctx.result("Welcome to the Scott Project1 API"));

        app.get("/users", userController.getAllUsers);
        app.get("/users/{id}", userController.getUserById);
        app.post("/users", userController.postUser);

        app.get("/cars", carController.getAllCars);
        app.get("/cars/{id}", carController.getCarById);
        app.post("/cars", carController.postCar);

        app.get("/offers", offerController.getAllOffers);
        app.get("/offers/{id}", offerController.getOfferById);
        app.post("/offers", offerController.postOffer);
    }
}
