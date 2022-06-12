package com.revature.service;

import com.revature.model.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

public class ExampleServiceTest {
    List<Example> mockList = Mockito.mock(List.class);
    @Test
    public void whenGivenExampleObjectCreateNewObjectAddsToList(){
        ExampleService exampleService=new ExampleService();
        Example example = new Example();

        Assertions.assertDoesNotThrow(()->exampleService.createExample(example));
    }

    @Test
    public void whenGivenExampleObjectCreateReturnsTrue(){
        Example example = new Example();
        Example example1 = new Example();
        ExampleService exampleService=new ExampleService(mockList);
        Mockito.when(mockList.add(example)).thenReturn(true);

        boolean result1 = exampleService.createExample(example);
        Assertions.assertTrue(result1);

    }


    @Test
    public void whenGivenExampleId(){

        Example example = new Example(0, "name1", "desc1");

        Mockito.when(mockList.size()).thenReturn(1);
        Mockito.when(mockList.get(0)).thenReturn(example);
        ExampleService exampleService = new ExampleService(mockList);
        Example result = exampleService.getExampleById(0);

        Assertions.assertEquals(example.id, result.id);
        Assertions.assertEquals(example.name, result.name);
        Assertions.assertEquals(example.desc, result.desc);
    }

}
