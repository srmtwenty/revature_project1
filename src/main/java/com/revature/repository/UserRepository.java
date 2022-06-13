package com.revature.repository;

import com.revature.model.Role;
import com.revature.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DAO<User>{
    private List<User> users;

    public UserRepository(){
        users = new ArrayList<>();
    }

    public UserRepository(List<User> users){
        this.users=users;
    }
    @Override
    public User create(User user) {
        if(users.add(user)){
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(int id) {
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getId() == id){
                return users.get(i);
            }
        }
        return null;
    }

    public List<User> getAllUsersByRole(Role role){
        List<User> filteredUsers = new ArrayList<>();

        for(User user: users){
            if(user.getRole().equals(role)){
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
