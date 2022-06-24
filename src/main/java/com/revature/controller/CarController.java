package com.revature.controller;

import com.revature.model.Car;
import com.revature.model.CarType;
import com.revature.model.Role;
import com.revature.service.CarService;
import io.javalin.http.Handler;


import java.util.Arrays;


public class CarController {
    CarService carService = new CarService();

    public CarController(){
        carService = new CarService();
    }

    public CarController(CarService carService){
        this.carService=carService;
    }
    public Handler getAllCars= ctx->{
        String carTypeParam = ctx.queryParam("carType");
        String nameParam = ctx.queryParam("name");
        String userIdParam = ctx.queryParam("user_id");

        if(carTypeParam==null && nameParam==null && userIdParam==null){
            ctx.json(carService.getAllCars());
        }else if(nameParam!=null){
            ctx.json(carService.getCarByName(nameParam));
        }else if (userIdParam!=null){
            ctx.json(carService.getAllCarsByUserId(Integer.parseInt(userIdParam)));
        }
        else{
            try{
                CarType carType = CarType.valueOf(carTypeParam.toUpperCase());
                ctx.json(carService.getAllCarsByCarType(carType));
            }catch(IllegalArgumentException e){
                ctx.status(400).result("Please enter a valid car type: "+ Arrays.toString(Role.values()));
            }
        }
    };
    public Handler postCar=ctx->{
        Car car = ctx.bodyAsClass(Car.class);
        car = carService.createCar(car);

        if(car!=null){
            ctx.json(car);
        }else{
            ctx.result("Car not created!").status(400);
        }
    };

    public Handler getCarById=ctx->{
        String param=ctx.pathParam("id");

        try {

            Car car = carService.getCarById(
                Integer.parseInt(param)
            );
                if(car !=null){
                    ctx.json(car);
                }else{
                    ctx.result("Car not found! Please try with another id instead.").status(404);
                }
        }catch(NumberFormatException e){
            ctx.result("Please enter only valid integer as an id");
            ctx.status(400);
        }
    };

    public Handler updateCar=ctx->{
        Car car=ctx.bodyAsClass(Car.class);
        car=carService.updateCar(car);

        if(car!=null){
            ctx.status(200).json(car);
        }else{
            ctx.status(400).result("Could not update the car");
        }
    };
    
    public Handler deleteCar=ctx->{
        String param = ctx.pathParam("id");
                
        try{
            int id = Integer.parseInt(param);
            if(carService.deleteById(id)){
                ctx.status(204);
            }else{
                ctx.status(400).result("Could not delete the Car!");
            }
        }catch(NumberFormatException e){
            ctx.status(400).result("Please enter a valid id");
        }
    };
}
