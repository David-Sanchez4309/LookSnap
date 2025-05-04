package DAO;

import entities.administrador;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public boolean eliminarAdministrador(int id) {
        boolean eliminado = false;
        try (Connection conn = DataBaseConnection.conectar()) {
            String sql = "DELETE FROM administradores WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            eliminado = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eliminado;
    }

    public List<administrador> obtenerTodos() {
        List<administrador> lista = new ArrayList<>();
        try (Connection conn = DataBaseConnection.conectar()) {
            String sql = "SELECT * FROM administradores";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                administrador a = new administrador();
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setCorreo(rs.getString("correo"));
                a.setTelefono(rs.getString("telefono"));
                a.setContrasena(rs.getString("contrasena"));
                lista.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }



}
