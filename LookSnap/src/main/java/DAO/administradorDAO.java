package DAO;

import entities.administrador;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class administradorDAO {
    public boolean existeAdministrador(String correo) {
        String query = "SELECT 1 FROM administradores WHERE correo = ?";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar administrador: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarAdministrador(administrador admin) {
        if (existeAdministrador(admin.getCorreo())) {
            System.out.println("El administrador ya está registrado.");
            return false;
        }

        String query = "INSERT INTO administradores (nombre, correo, telefono, contrasena) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getCorreo());
            ps.setString(3, admin.getTelefono());
            ps.setString(4, admin.getContrasena());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al registrar administrador: " + e.getMessage());
            return false;
        }
    }

    public administrador login(String correo, String contrasena) {
        String query = "SELECT * FROM administradores WHERE correo = ? AND contrasena = ?";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador admin = new administrador();
                    admin.setId(rs.getInt("id"));
                    admin.setNombre(rs.getString("nombre"));
                    admin.setCorreo(rs.getString("correo"));
                    admin.setTelefono(rs.getString("telefono"));
                    admin.setContrasena(rs.getString("contrasena"));

                    return admin;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al autenticar administrador: " + e.getMessage());
        }

        return null;
    }
}
