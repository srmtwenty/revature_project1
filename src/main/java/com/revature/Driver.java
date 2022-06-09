package com.revature;

import com.revature.controller.UserController;
import io.javalin.Javalin;

public class Driver {
    public static void main(String [] args){
        UserController userController=new UserController();
        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx->ctx.result("Welcome to the Scott Project1 API"));

        app.get("/users", userController.getAllUsers);
        app.post("/users", userController.postUser);
    }
}
