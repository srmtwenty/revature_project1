package com.revature.repository;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.util.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DAO<User>{

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
            }

        }catch(SQLException e){
          e.printStackTrace();
        }
        return null;
    }

    public User getByUsername(String username){
        String sql="select * from users where username=?";
        try{
            Connection connection= ConnectionUtility.getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new User()
                        .setId(rs.getInt("id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .setRole(Role.values()[rs.getInt("role_id")]);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getAllUsersByRole(Role role){
        List<User> users = new ArrayList<>();
        String sql = "select * from users where role_id=? order by id";

        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, role.ordinal());

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
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User update(User user) {

        String sql = "update users set first_name=?, last_name=?, username=?, password=?, role_id=? where id=?";
        try{
            Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRole().ordinal());
            stmt.setInt(6, user.getId());


            int success = stmt.executeUpdate();

            //TODO: Return the created User
            if(success !=0){
                return getById(user.getId());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from users where id=?";

        try{Connection connection = ConnectionUtility.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            int success = stmt.executeUpdate();

            return success !=0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
