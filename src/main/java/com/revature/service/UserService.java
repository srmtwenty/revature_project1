package com.revature.service;



import com.revature.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    //As a Customer, I can view available Cars on the lot
    //As a Customer, I can make an Offer on an available Car
    //As a Customer, I can view my open offers
    //As a Customer, I can view the Cars that I own
    private List<User> users = new ArrayList<>();

    public UserService(){

    }

    public void createUser(User user){
        users.add(user);
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User getUserById(int id){
        for(User user: users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
}
