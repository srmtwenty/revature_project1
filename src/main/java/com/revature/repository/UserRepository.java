package com.revature.repository;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "insert into users(first_name, last_name, username, password) values(?, ?, ?, ?)";

        Connection connection = ConnectionUtility.getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());

            int success = stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        //if(users.add(user)){
        //    return user;
        //}
        return null;
    }

    @Override
    public List<User> getAll() {
        // Empty lists of users, will add any new users from the result set
        List<User> users=new ArrayList<>();

        //FIRST, establish your connection.
        // We need to know where we are going with this command,
        // who is asking to execute this command, etc
        Connection connection= ConnectionUtility.getConnection();

        //SECOND, write string sql to determine in plain text what you want to do.
        String sql="select * from users";
        try {
            //Third, we use our connection to prepare statement of that string: what sql do we want to execute?
            PreparedStatement stmt = connection.prepareStatement(sql);

            //Fourth, we execute that statement query.
            // ExecuteQuery returns result set, which is pointer I must move to grab each individual record.
            //
            ResultSet results = stmt.executeQuery();

            while(results.next()){

                //System.out.println(results.getString("first_name"));

                //go through each result, build a User object for that data, add that user object the users list
                User user = new User();
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUsername(results.getString("username"));
                user.setPassword(results.getString("password"));
                user.setId(results.getInt("id"));

                users.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(int id) {

        System.out.println(id);
        for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i));
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
