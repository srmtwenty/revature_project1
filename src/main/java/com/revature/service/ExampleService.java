package com.revature.service;

import com.revature.model.Example;

import java.util.ArrayList;
import java.util.List;

public class ExampleService {
    private List<Example> examples;

    public ExampleService(){
        examples= new ArrayList<>();
    };

    public ExampleService(List<Example> examples){
        this.examples=examples;
    }

    public boolean createExample(Example example){
        //throw new Exception();
        return examples.add(example);
    }

    public List<Example> getAllExamples(){
        return examples;
    }

    public Example getExampleById(int id){
        for(int i=0; i<examples.size(); i++){
            if(examples.get(i).id==id){
                return examples.get(i);
            }
        }
        return null;
    }

}
