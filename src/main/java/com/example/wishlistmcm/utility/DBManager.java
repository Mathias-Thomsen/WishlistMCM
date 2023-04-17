package com.example.wishlistmcm.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    

    public static Connection getConnection(){

        String username = null;
        String password = null;
        String url = null;

        try(InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            con = DriverManager.getConnection(url,username,password);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
