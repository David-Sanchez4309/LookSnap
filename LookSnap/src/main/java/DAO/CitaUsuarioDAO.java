package DAO;

import entities.CitaUsuario;
import utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaUsuarioDAO {

    /**
     * Lista las citas de un usuario específico por su ID.
     */
    public List<CitaUsuario> listarCitasPorUsuario(int usuarioId) {
        List<CitaUsuario> lista = new ArrayList<>();
        String sql = "SELECT c.id as id_cita, u.nombre, u.telefono, u.correo, u.direccion, " +
                "c.fecha, c.hora, c.descripcion " +
                "FROM usuarios u JOIN citas c ON u.id = c.id_usuario " +
                "WHERE u.id = ? ORDER BY c.fecha, c.hora";

        try (Connection con = DataBaseConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CitaUsuario cu = new CitaUsuario();
                    cu.setIdCita(rs.getInt("id_cita"));
                    cu.setNombre(rs.getString("nombre"));
                    cu.setTelefono(rs.getString("telefono"));
                    cu.setCorreo(rs.getString("correo"));
                    cu.setDireccion(rs.getString("direccion"));
                    cu.setFecha(rs.getDate("fecha"));
                    cu.setHora(rs.getTime("hora"));
                    cu.setDescripcion(rs.getString("descripcion"));
                    lista.add(cu);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar citas por usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Elimina una cita por su ID.
     */
    public boolean eliminarCita(int idCita) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (Connection con = DataBaseConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza una cita específica.
     */
    public boolean actualizarCita(int idCita, Date fecha, Time hora, String descripcion) {
        String sql = "UPDATE citas SET fecha = ?, hora = ?, descripcion = ? WHERE id = ?";
        try (Connection con = DataBaseConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new Date(fecha.getTime()));
            ps.setTime(2, hora);
            ps.setString(3, descripcion);
            ps.setInt(4, idCita);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista las citas asociadas a un barbero específico.
     */
    public List<CitaUsuario> listarCitasPorBarbero(int barberoId) {
        List<CitaUsuario> lista = new ArrayList<>();
        String sql = "SELECT c.id as id_cita, u.nombre, u.telefono, u.correo, u.direccion, " +
                "c.fecha, c.hora, c.descripcion " +
                "FROM barberos b JOIN citas c ON b.id = c.id_barbero " +
                "JOIN usuarios u ON u.id = c.id_usuario " +
                "WHERE b.id = ? ORDER BY c.fecha, c.hora";

        try (Connection con = DataBaseConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, barberoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CitaUsuario cu = new CitaUsuario();
                    cu.setIdCita(rs.getInt("id_cita"));
                    cu.setNombre(rs.getString("nombre"));
                    cu.setTelefono(rs.getString("telefono"));
                    cu.setCorreo(rs.getString("correo"));
                    cu.setDireccion(rs.getString("direccion"));
                    cu.setFecha(rs.getDate("fecha"));
                    cu.setHora(rs.getTime("hora"));
                    cu.setDescripcion(rs.getString("descripcion"));
                    lista.add(cu);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar citas por barbero: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
    public static boolean actualizarDescripcionCita(int idCita, String nuevaDescripcion) {
        String sql = "UPDATE citas SET descripcion = ? WHERE id = ?";
        try (Connection con = DataBaseConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaDescripcion);
            ps.setInt(2, idCita);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar solo la descripción de la cita: " + e.getMessage());
            return false;
        }
    }


    // Método de prueba (main)
    public static void main(String[] args) {
        try {
            Connection con = DataBaseConnection.conectar();
            System.out.println("✅ Conexión establecida correctamente");

            String sql = "SELECT * FROM citas WHERE id_usuario = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n📋 Citas encontradas para usuario 1:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Fecha: " + rs.getDate("fecha") +
                        " | Hora: " + rs.getTime("hora"));
            }

            if (!rs.isBeforeFirst()) {
                System.out.println("⚠️ No se encontraron citas para el usuario 1");
            }

            con.close();
        } catch (Exception e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
