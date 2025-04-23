package DAO;

import entities.CitaUsuario;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CitaUsuarioDAO {
    public List<CitaUsuario> listarCitasConUsuarios(String fecha) {
        List<CitaUsuario> lista = new ArrayList<>();
        String sql = "SELECT u.nombre, u.telefono, u.correo, u.direccion, c.fecha, c.hora " +
                "FROM usuarios u JOIN citas c ON u.id = c.id_usuario WHERE c.fecha = ?";

        try (Connection con = DataBaseConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(fecha));  // Asegúrate de pasar la fecha correctamente

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("✅ Conexión a la base de datos establecida correctamente.\n");

                while (rs.next()) {
                    CitaUsuario cu = new CitaUsuario();
                    cu.setNombre(rs.getString("nombre"));
                    cu.setTelefono(rs.getString("telefono"));
                    cu.setCorreo(rs.getString("correo"));
                    cu.setDireccion(rs.getString("direccion"));
                    cu.setFecha(rs.getDate("fecha"));
                    cu.setHora(rs.getTime("hora"));
                    lista.add(cu);

                    // Mostrar datos por consola
                    System.out.println("Nombre: " + cu.getNombre());
                    System.out.println("Teléfono: " + cu.getTelefono());
                    System.out.println("Correo: " + cu.getCorreo());
                    System.out.println("Dirección: " + cu.getDireccion());
                    System.out.println("Fecha: " + cu.getFecha());
                    System.out.println("Hora: " + cu.getHora());
                    System.out.println("---------------------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // 🔽 Método main para probar directamente
    public static void main(String[] args) {
        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        String fecha = "2025-04-23";  // Puedes probar con una fecha específica
        List<CitaUsuario> lista = dao.listarCitasConUsuarios(fecha);

        System.out.println("\n📋 Total de citas encontradas: " + lista.size());
    }
}
