package org.example.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection (){}


    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public  Connection getConnection(){
        if(connection == null){
            try{
                String url  = "jdbc:oracle:thin:@//localhost:1521/xe";
                String user = "BDJ";
                String password = "bducdec";
                connection = DriverManager.getConnection(url,user,password);
            }catch (SQLException e){
                System.out.println("There's been an error: " + e.getMessage());
            }
        }
        return connection;
    }


    public void closeConnection(){
        if (connection != null){
            try{
                connection.close();
                connection = null;
            }catch (SQLException e){
                System.out.println("There's been an error closing the connection: " + e.getMessage());
            }
        }
    }
}
