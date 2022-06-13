package com.revature.controller;


import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class UserController {
    UserService userService = new UserService();

    public Handler getAllUsers =ctx->{
        List<User> users=userService.getAllUsers();
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
        ctx.json(users);
    };
    public Handler postUser = ctx->{
        User user = ctx.bodyAsClass(User.class);
        userService.createUser(user);

    };
    public Handler getUserById=ctx->{
        String param = ctx.pathParam("id");
        int id = 0;
        try {
            id=Integer.parseInt(param);
            ctx.json(userService.getUserById(id));
        }catch(NumberFormatException e){
            ctx.result("Enter id number please");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }catch(NullPointerException e){
            System.out.println("NULL");
        }
        //User user=userService.getUserById();

    };
}
