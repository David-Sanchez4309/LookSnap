package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
<<<<<<< HEAD
    private static final String pass = "gonzalo1220";
=======
    private static final String pass = "camilomena";
>>>>>>> d66f04423236b9ddd00eba4917e9ab8e53684eaf

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
