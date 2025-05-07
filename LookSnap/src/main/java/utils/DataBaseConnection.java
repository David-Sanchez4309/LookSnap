package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/LookSnapDB";
    private static final String user = "postgres";
    private static final String pass = "4324";



    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver PostgreSQL cargado correctamente.");
            Connection conn = DriverManager.getConnection(URL, user, pass);
            System.out.println("Conexión a base de datos establecida correctamente.");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el Driver de PostgreSQL.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos:");
            e.printStackTrace();
            return null;
        }
    }

}
