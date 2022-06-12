package com.revature.controller;

import com.revature.model.Example;
import com.revature.service.ExampleService;
import io.javalin.http.Handler;

import java.util.List;

public class ExampleController {
    ExampleService exampleService = new ExampleService();

    public Handler createExample = ctx->{
        Example example = ctx.bodyAsClass(Example.class);
        exampleService.createExample(example);
    };

    public Handler getAllExamples = ctx->{
        List<Example> examples= exampleService.getAllExamples();
        ctx.json(examples);
    };
    public Handler getExampleById = ctx->{
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        ctx.json(exampleService.getExampleById(id));
    };
}
