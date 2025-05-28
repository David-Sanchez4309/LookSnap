package Servlet;

import DAO.CitaUsuarioDAO;
import entities.CitaUsuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.mail.MessagingException;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/registrarCita")
public class RegistrarCitaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String nombre = request.getParameter("nombre");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");
            String descripcion = request.getParameter("descripcion");
            String fechaStr = request.getParameter("fecha");
            String horaStr = request.getParameter("hora");

            // Validar y ajustar hora
            if (horaStr != null && horaStr.length() == 5) {
                horaStr += ":00";  // Asegurar formato HH:mm:ss
            }

            Date fecha = Date.valueOf(fechaStr);
            Time hora = Time.valueOf(horaStr);

            CitaUsuario cita = new CitaUsuario( );
            cita.setDescripcion(descripcion);

            CitaUsuarioDAO dao = new CitaUsuarioDAO();
            boolean exito = dao.registrarCita(cita);

            if (exito) {
                String asunto = "Confirmación de Cita";
                String cuerpo = "Hola " + nombre + ",\n\nTu cita ha sido agendada para el día " + fecha + " a las " + hora +
                        ".\n\nDirección: " + direccion + "\nDescripción: " + descripcion + "\n\nGracias por agendar con nosotros.";

                try {
                    utils.EmailSender.enviarCorreoConfirmacion(correo, asunto, cuerpo);
                } catch (MessagingException me) {
                    me.printStackTrace();
                }

                response.sendRedirect("confirmacion.xhtml");
            } else {
                response.sendRedirect("error.xhtml");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.xhtml");
        }
    }

}
