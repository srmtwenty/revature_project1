package com.revature.repository;

import org.junit.jupiter.api.Test;

public class CarRepositoryTest {
    @Test
    public void testGetAllCars(){
        CarRepository carRepository = new CarRepository();

        carRepository.getAll();
    }
}
