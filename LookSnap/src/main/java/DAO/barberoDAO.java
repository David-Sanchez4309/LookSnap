package DAO;

import entities.barbero;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class barberoDAO {
    public boolean existeBarbero(String correo) {
        String query = "SELECT 1 FROM barberos WHERE correo = ?";
        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar barbero: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarBarbero(barbero b) {
        if (existeBarbero(b.getCorreo())) {
            System.out.println("El barbero ya está registrado.");
            return false;
        }

        String query = "INSERT INTO barberos (nombre, especialidad, correo, telefono, contrasena) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, b.getNombre());
            ps.setString(2, b.getEspecialidad());
            ps.setString(3, b.getCorreo());
            ps.setString(4, b.getTelefono());
            ps.setString(5, b.getContrasena());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al registrar barbero: " + e.getMessage());
            return false;
        }
    }

    public barbero login(String correo, String contrasena) {
        String query = "SELECT * FROM barberos WHERE correo = ? AND contrasena = ?";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    barbero b = new barbero();
                    b.setId(rs.getInt("id"));
                    b.setNombre(rs.getString("nombre"));
                    b.setEspecialidad(rs.getString("especialidad"));
                    b.setCorreo(rs.getString("correo"));
                    b.setTelefono(rs.getString("telefono"));
                    b.setContrasena(rs.getString("contrasena"));

                    return b;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al autenticar barbero: " + e.getMessage());
        }

        return null;
    }

    public boolean eliminarBarbero(int id) {
        boolean eliminado = false;
        try (Connection conn = DataBaseConnection.conectar()) {
            String sql = "DELETE FROM barberos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            eliminado = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eliminado;
    }

    public List<barbero> obtenerTodos() {
        List<barbero> lista = new ArrayList<>();
        try (Connection conn = DataBaseConnection.conectar()) {
            String sql = "SELECT * FROM barberos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                barbero b = new barbero();
                b.setId(rs.getInt("id"));
                b.setNombre(rs.getString("nombre"));
                b.setCorreo(rs.getString("correo"));
                b.setTelefono(rs.getString("telefono"));
                b.setEspecialidad(rs.getString("especialidad"));
                b.setContrasena(rs.getString("contrasena"));
                lista.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


}
