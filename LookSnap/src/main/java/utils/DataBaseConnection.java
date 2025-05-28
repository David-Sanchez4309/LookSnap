package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/LookSnapDB";
    private static final String user = "postgres";
<<<<<<< HEAD
    private static final String pass = "gonzalo1220";
=======
    private static final String pass = "4324";
>>>>>>> 75a88ea922dbadbffe15be8317fb58769416bda7



    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver"); // Agregado: Forzar carga del driver
            return DriverManager.getConnection(URL, user, pass);
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el Driver de PostgreSQL.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
