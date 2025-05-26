package DAO;

import entities.CitaUsuario;
import utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaUsuarioDAO {
    // Método para listar citas de un usuario específico (no solo por fecha)
    public List<CitaUsuario> listarCitasPorUsuario(int usuarioId) {
        List<CitaUsuario> lista = new ArrayList<>();
        String sql = "SELECT c.id as id_cita, u.nombre, u.telefono, u.correo, u.direccion, c.fecha, c.hora, c.descripcion " +
                "FROM usuarios u JOIN citas c ON u.id = c.id_usuario WHERE u.id = ? ORDER BY c.fecha, c.hora";

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
            e.printStackTrace();
        }
        return lista;
    }

    // Método para eliminar una cita
    public boolean eliminarCita(int idCita) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (Connection con = DataBaseConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar una cita
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
            e.printStackTrace();
            return false;
        }
    }



    // 🔽 Método main para probar directamente
    public static void main(String[] args) {
        try {
            // 1. Probar conexión básica
            Connection con = DataBaseConnection.conectar();
            System.out.println("✅ Conexión establecida correctamente");

            // 2. Probar consulta de citas para usuario ID 1
            String sql = "SELECT * FROM citas WHERE id_usuario = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n📋 Citas encontradas para usuario 1:");
            while(rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Fecha: " + rs.getDate("fecha") +
                        " | Hora: " + rs.getTime("hora"));
            }

            if(!rs.isBeforeFirst()) {
                System.out.println("⚠️ No se encontraron citas para el usuario 1");
            }

            con.close();
        } catch (Exception e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean registrarCita(CitaUsuario cita) {
        String sql = "INSERT INTO citas (nombre, telefono, correo, direccion, fecha, hora, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cita.getNombre());
            ps.setString(2, cita.getTelefono());
            ps.setString(3, cita.getCorreo());
            ps.setString(4, cita.getDireccion());
            ps.setDate(5, cita.getFecha());
            ps.setTime(6, cita.getHora());
            ps.setString(7, cita.getDescripcion());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
