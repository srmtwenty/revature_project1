package com.revature.service;

import com.revature.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class CarServiceUnitTest {
    List<Car> mockList= Mockito.mock(List.class);

    @Test
    public void whenGivingCarObjectCreateNewObjectAddsToList(){
        Car car = new Car();
        CarService carService = new CarService();

        Assertions.assertDoesNotThrow(()->carService.createCar(car));
    }

    @Test
    public void whenGivenCarObjectCreateNewObjectReturnTrue(){
        Car car = new Car();
        CarService carService=new CarService(mockList);
        Mockito.when(mockList.add(car)).thenReturn(true);

        boolean result1=carService.createCar(car);
        Assertions.assertTrue(result1);
    }

    @Test
    public void whenGivenValidIdGetCarByIdReturnsCarWithThatId(){
        Car car = new Car(0, "name1", "manufacturer", 1.00);

        Mockito.when(mockList.size()).thenReturn(1);
        Mockito.when(mockList.get(0)).thenReturn(car);
        CarService carService = new CarService(mockList);
        Car result=carService.getCarById(0);

        Assertions.assertEquals(car, result);
    }
}
