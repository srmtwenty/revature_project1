package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
    private static Connection instance;
    private static Properties properties;

    public static Connection getConnection(){
        try{
            //Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password
            );
            return connection;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
