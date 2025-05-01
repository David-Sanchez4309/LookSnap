package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String pass = "camilomena";

    public static Connection conectar(){
        try{
            return DriverManager.getConnection(URL, user, pass);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
