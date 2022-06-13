package com.revature.service;



import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    //As a Customer, I can view available Cars on the lot
    //As a Customer, I can make an Offer on an available Car
    //As a Customer, I can view my open offers
    //As a Customer, I can view the Cars that I own
    //private List<User> users;

    private UserRepository userRepository;

    //public UserService(List<User> users){
    //    this.users=users;
    //}
    public UserService(){
        userRepository=new UserRepository();
    }
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User createUser(User user){
        return userRepository.create(user);
    }

    public List<User> getAllUsers(){

        return userRepository.getAll();
    }

    public List<User> getAllUsersByRole(Role role){
        return userRepository.getAllUsersByRole(role);
    }

    public User getUserById(int id){
        return userRepository.getById(id);
    }

    //public boolean deleteUserById(int id){
    //    for(int i=0; i<users.size();i++){
    //        if(users.get(i).getId()==id){
    //            users.remove(i);
    //            return true;
    //        }
    //    }
    //    return false;
    //}
}
