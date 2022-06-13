package com.revature;

import com.revature.controller.CarController;
import com.revature.controller.UserController;
import io.javalin.Javalin;

public class Driver {
    public static void main(String [] args){

        UserController userController=new UserController();
        ExampleController exampleController=new ExampleController();
        CarController carController=new CarController();

        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx->ctx.result("Welcome to the Scott Project1 API"));

        app.get("/users", userController.getAllUsers);
        app.get("/users/{id}", userController.getUserById);
        app.post("/users", userController.postUser);


        app.get("/examples", exampleController.getAllExamples);
        app.get("/examples/{id}", exampleController.getExampleById);
        app.post("/examples", exampleController.createExample);

        app.get("/cars", carController.getAllCars);
        app.get("/cars/{id}", carController.getCarById);
        app.post("/cars", carController.postCar);
    }
}
