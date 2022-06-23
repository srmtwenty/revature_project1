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
        /*List<Car> cars = carService.getAllCars();
        String carTypeParam=ctx.queryParam("carType");
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

         */
        ctx.json(carService.getAllCars());
    };
    public Handler postCar=ctx->{
        /*Car car=new Car();
        carService.createCar(car);
         */
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
        //int id=0;
        try {
            //id = Integer.parseInt(param);
            //ctx.json(carService.getCarById(id));
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
        //catch(NullPointerException e){
        //    System.out.println("NULL");
        //}
    };

    public Handler updateCar=ctx->{
        Car car = new Car();
        car=ctx.bodyAsClass(Car.class);

        if(car!=null){
            ctx.status(200).json(car);
        }else{
            ctx.status(400).result("Could not update the car");
        }
    };
}
