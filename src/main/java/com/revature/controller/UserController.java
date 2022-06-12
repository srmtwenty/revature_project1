package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.http.Handler;
import java.util.List;

public class UserController {
    UserService userService = new UserService();

    public Handler getAllUsers =ctx->{
        List<User> users=userService.getAllUsers();
        ctx.json(users);
    };
    public Handler postUser = ctx->{
        User user = ctx.bodyAsClass(User.class);
        userService.createUser(user);

    };
    public Handler getUserById=ctx->{
        String param = ctx.pathParam("id");
        int id = Integer.parseInt("id");

        //User user=userService.getUserById();
        ctx.json(userService.getUserById(id));
    };
}
