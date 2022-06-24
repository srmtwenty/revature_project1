package com.revature;

import com.revature.controller.AuthController;
import com.revature.controller.CarController;
import com.revature.controller.OfferController;
import com.revature.controller.UserController;
import com.revature.model.Role;
import io.javalin.Javalin;

public class Driver {
    public static void main(String [] args){
        System.out.println(Role.EMPLOYEE.ordinal());

        UserController userController=new UserController();
        CarController carController=new CarController();
        OfferController offerController=new OfferController();


        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx->ctx.result("Welcome to the Scott Project1 API"));

        app.get("/users", userController.getAllUsers);
        app.get("/users/{id}", userController.getUserById);
        app.post("/users", userController.postUser);
        app.put("/users/{id}", userController.updateUser);
        app.delete("/users/{id}", userController.deleteUser);

        app.get("/cars", carController.getAllCars);
        app.get("/cars/{id}", carController.getCarById);
        app.post("/cars", carController.postCar);
        app.put("/cars/{id}", carController.updateCar);
        app.delete("/cars/{id}", carController.deleteCar);

        app.get("/offers", offerController.getAllOffers);
        app.get("/offers/{id}", offerController.getOfferById);
        app.post("/offers", offerController.postOffer);
        app.put("/offers/{id}", offerController.updateOffer);
        app.delete("/offers/{id}", offerController.deleteOffer);

        app.post("/authenticate", AuthController.authenticate);
    }
}
