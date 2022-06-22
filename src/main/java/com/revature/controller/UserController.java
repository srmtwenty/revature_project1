package com.revature.controller;


import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UserController {
    UserService userService = new UserService();

    public UserController(){
        userService = new UserService();
    }
    public UserController(UserService userService){
        this.userService=userService;
    }

    public Handler getAllUsers =ctx->{
        /*List<User> users=userService.getAllUsers();
        String roleParam = ctx.queryParam("role");

        if(roleParam ==null){
            users=userService.getAllUsers();
        }
        else {
            try {
                Role role = Role.valueOf(roleParam.toUpperCase(Locale.ROOT));
                users = userService.getAllUsersByRole(role);
            }catch(IllegalArgumentException e){
                String failureMessage="{\"success\":false, \"message\":\"" +
                        "Please only use the following role values: " + Arrays.toString(Role.values())
                        + "\"}";
                ctx.status(400).json(failureMessage);
                return;
            }
        }
        ctx.json(users);*/
        ctx.json(userService.getAllUsers());
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
        /*int id = 0;
        try {
            id=Integer.parseInt(param);
            ctx.json(userService.getUserById(id));
        }catch(NumberFormatException e){
            ctx.result("Enter id number please");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }catch(NullPointerException e){
            System.out.println("NULL");
        }
        //User user=userService.getUserById(); */

    };
}
