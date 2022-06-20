package com.revature.controller;

import com.revature.model.Car;
import com.revature.model.CarType;
import com.revature.service.CarService;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CarController {
    CarService carService = new CarService();

    public CarController(){
        carService = new CarService();
    }

    public CarController(CarService carService){
        this.carService=carService;
    }
    public Handler getAllCars= ctx->{
        List<Car> cars = carService.getAllCars();
        String carTypeParam=ctx.pathParam("carType");

        if(carTypeParam == null){
            cars=carService.getAllCars();
        }
        else{
            try{
                CarType carType = CarType.valueOf(carTypeParam.toUpperCase(Locale.ROOT));
                cars = carService.getAllCarsByCarType(carType);
            }catch(IllegalArgumentException e){
                String failureMessage="{\"success\":false, \"message\":\"" +
                        "Please only use the following role values: " + Arrays.toString(CarType.values())
                        + "\"}";
                ctx.status(400).json(failureMessage);
                return;
            }
        }
        ctx.json(cars);
    };
    public Handler postCar=ctx->{
        Car car=new Car();
        carService.createCar(car);
    };

    public Handler getCarById=ctx->{
        String param=ctx.pathParam("id");
        int id=0;
        try {
            id = Integer.parseInt(param);
            ctx.json(carService.getCarById(id));
        }catch(NumberFormatException e){
            ctx.result("Enter id number please");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }catch(NullPointerException e){
            System.out.println("NULL");
        }

    };
}
