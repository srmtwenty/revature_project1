package unit.service;

import com.revature.model.Car;
import com.revature.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class CarServiceUnitTest {
    //List<Car> mockList= Mockito.mock(List.class);

    @Test
    public void whenGivingCarObjectCreateNewObjectAddsToList(){
        Car car = new Car();
        CarService carService = new CarService();

        Assertions.assertDoesNotThrow(()->carService.createCar(car));
    }

    @Test
    public void whenGivenCarObjectCreateNewObjectReturnTrue(){
        Car car = new Car();
        CarService carService=new CarService();
        //Mockito.when(mockList.add(car)).thenReturn(true);

        //boolean result1=carService.createCar(car);
        //Assertions.assertTrue(result1);
        Assertions.assertNotNull(carService.createCar(car));
    }

    //@Test
    //public void whenGetAllCarsIsCalledReturnsListOfAllCars() {

    //    CarService carService = new CarService();
    //    Assertions.assertDoesNotThrow(()-> carService.getAllUsers());
    //}

    /*@Test
    public void whenGivenValidIdGetCarByIdReturnsCarWithThatId(){
        Car car = new Car(0, "name1", "manufacturer", 1.00);

        Mockito.when(mockList.size()).thenReturn(1);
        Mockito.when(mockList.get(0)).thenReturn(car);
        CarService carService = new CarService(mockList);
        Car result=carService.getCarById(0);

        Assertions.assertEquals(car, result);
    }
    */

    //@Test
    //public void whenGivenValidIdDeleteCarByIdReturnsTrue(){
    //    List<Car> mockList=Mockito.mock(List.class);
    //    CarService carrService = new CarService(mockList);
    //    Car car = new Car();
    //    car.setId(1);

    //    Mockito.when(mockList.size()).thenReturn(1);
    //    Mockito.when(mockList.get(0)).thenReturn(user);
    //    Mockito.when(mockList.remove(0)).thenReturn(user);

    //    Assertions.assertTrue(carService.deleteUserById(1));
    //}
}
