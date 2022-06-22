package com.revature.repository;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.util.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DAO<User>{
    //private List<User> users;

    //public UserRepository(){
        //users = new ArrayList<>();
    //}

    //public UserRepository(List<User> users){
        //this.users=users;
    //}
    @Override
    public User create(User user) {
        String sql = "insert into users(first_name, last_name, username, password, role_id) values(?, ?, ?, ?, ?)";


        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRole().ordinal());
            //stmt.setString(5, user.getRole().name());

            int success = stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            //TODO: Return the created User
            if(keys.next()){
                int id=keys.getInt(1);
                return user.setId(id);
            }
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
        String sql="select * from users order by id";
        try {
            Connection connection= ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){


                users.add(new User()
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setRole(Role.values()[rs.getInt("role_id")])
                        .setId(rs.getInt("id"))
                );
                User user2 = new User().setFirstName("first");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(int id) {
        String sql="select * from users where id=?";
        try{
            Connection connection= ConnectionUtility.getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new User()
                        .setId(rs.getInt("id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setRole(Role.values()[rs.getInt("role_id")]);

                /*User user = new User();
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUsername(results.getString("username"));
                user.setPassword(results.getString("password"));
                user.setRole(Role.valueOf(results.getString("role")));
                user.setId(results.getInt("id"));*/
                //return user;
            }

        }catch(SQLException e){
          e.printStackTrace();
        }
        return null;

        /*System.out.println(id);
        for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i));
            if(users.get(i).getId() == id){
                return users.get(i);
            }
        }


         */
    }

    /*public List<User> getAllUsersByRole(Role role){
        List<User> filteredUsers = new ArrayList<>();

        for(User user: users){
            if(user.getRole().equals(role)){
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }*/

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
