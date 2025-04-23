package DAO;

import entities.usuario;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usuariosDAO {
    public boolean existeUsuario(String correo) {
        String query = "SELECT 1 FROM usuarios WHERE correo = ?";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar si el usuario existe: " + e.getMessage());
            return false;
        }
    }

    public boolean existeUsuarioPorId(int id) {
        String query = "SELECT 1 FROM usuarios WHERE id = ?";
        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrarUsuario(usuario u) {
        if (existeUsuario(u.getCorreo())) return false;

        String query = "INSERT INTO usuarios (nombre, apellido, correo, telefono, direccion, contrasena) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, u.getNombre());
            ps.setString(6, u.getApellido());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getTelefono());
            ps.setString(4, u.getDireccion());
            ps.setString(5, u.getContrasena());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public usuario login(String correo, String contrasena) {
        String query = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario u = new usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setTelefono(rs.getString("telefono"));
                    u.setDireccion(rs.getString("direccion"));
                    u.setContrasena(rs.getString("contrasena"));

                    return u; // Usuario autenticado
                } else {
                    return null; // No coincide
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al intentar autenticar: " + e.getMessage());
            return null;
        }
    }

    public usuario obtenerUsuarioPorId(int id) {
        usuario u = null;

        try (Connection conn = DataBaseConnection.conectar()) {
            String sql = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u = new usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("contrasena")
                );
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el usuario: " + e.getMessage());
        }

        return u;
    }


}
