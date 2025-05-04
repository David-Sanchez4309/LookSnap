package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/LookSnapDB";
    private static final String user = "postgres";
    private static final String pass = "gonzalo1220";

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
