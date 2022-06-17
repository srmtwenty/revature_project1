package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    private final static String url="jdbc:postgresql://localhost:5432/postgres?currentSchema=flash_bash";
    private final static String user="postgres";
    private final static String password="rldjrgoenrpTek81@";

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
