package com.revature.controller;


import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.http.Handler;
import java.util.Arrays;


public class UserController {
    UserService userService = new UserService();

    public UserController(){
        userService = new UserService();
    }
    public UserController(UserService userService){
        this.userService=userService;
    }

    public Handler getAllUsers =ctx->{
        String roleParam = ctx.queryParam("role");
        String usernameParam=ctx.queryParam("username");

        if(roleParam == null && usernameParam == null){
            ctx.json(userService.getAllUsers());
        }else if(usernameParam != null){
            ctx.json(userService.getByUsername(usernameParam));
        }else{
            try{
                Role role = Role.valueOf(roleParam.toUpperCase());
                ctx.json(userService.getAllUsersByRole(role));
            }catch(IllegalArgumentException e){
                ctx.status(400).result("Please enter a valid role: "+ Arrays.toString(Role.values()));
            }
        }

    };
    public Handler postUser = ctx->{
        //grab the user object from the request body
        // send that to the service, which will return a user (eventually)

        User user = ctx.bodyAsClass(User.class);
        user = userService.createUser(user);

        if(user!=null){
            ctx.json(user);
        }else{
            // some issue with the request
            ctx.result("User not created!").status(400);
        }

    };
    public Handler getUserById=ctx->{
        String param = ctx.pathParam("id");

        try{
            //this is run-time issue: we are not forced to handle it and you won't be notified if there is an exception.
            // hopefully is not null
            User user = userService.getUserById(
                    Integer.parseInt(param)
		);
            if(user != null){
                // valid user, return it
                ctx.json(user);
            }else{
                // couldn't find the user, return a 404
                ctx.result("User not found! Please try with another id instead.").status(404);
            }
        }catch(NumberFormatException e){
            ctx.result("Please enter only valid integers as an id");
            ctx.status(400);
        }
    };
    public Handler updateUser=ctx->{
        User user = ctx.bodyAsClass(User.class);
        user=userService.updateUser(user);

        if(user!=null){
            ctx.status(200).json(user);
        }else{
            ctx.status(400).result("Could not update the user");
        }
    };

    public Handler deleteUser=ctx->{
        String param = ctx.pathParam("id");

        try{
            int id= Integer.parseInt(param);
            if(userService.deleteUserById(id)){
                ctx.status(204);
            }else{
                ctx.status(400).result("Could not delete the user!");
            }
        }catch(NumberFormatException e){
            ctx.status(400).result("Please enter a valid id");
        }
    };
}
