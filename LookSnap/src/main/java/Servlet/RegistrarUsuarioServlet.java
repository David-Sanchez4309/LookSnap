package Servlet;

import DAO.administradorDAO;
import DAO.barberoDAO;
import DAO.usuariosDAO;
import entities.administrador;
import entities.barbero;
import entities.usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegistrarUsuarioServlet")
public class RegistrarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rol = request.getParameter("rol");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String contrasena = request.getParameter("contrasena");

        boolean registrado = false;

        switch (rol) {
            case "cliente":
                String direccion = request.getParameter("direccion");

                usuario cliente = new usuario();
                cliente.setNombre(nombre);
                cliente.setCorreo(correo);
                cliente.setTelefono(telefono);
                cliente.setDireccion(direccion);
                cliente.setContrasena(contrasena);

                usuariosDAO usuarioDao = new usuariosDAO();
                registrado = usuarioDao.registrarUsuario(cliente);
                break;

            case "barbero":
                String especialidad = request.getParameter("especialidad");

                barbero b = new barbero();
                b.setNombre(nombre);
                b.setCorreo(correo);
                b.setTelefono(telefono);
                b.setEspecialidad(especialidad);
                b.setContrasena(contrasena);

                barberoDAO barberoDao = new barberoDAO();
                registrado = barberoDao.registrarBarbero(b);
                break;

            case "administrador":
                administrador admin = new administrador();
                admin.setNombre(nombre);
                admin.setCorreo(correo);
                admin.setTelefono(telefono);
                admin.setContrasena(contrasena);

                administradorDAO adminDao = new administradorDAO();
                registrado = adminDao.registrarAdministrador(admin);
                break;
        }

        if (registrado) {
            response.sendRedirect("login.xhtml");
        } else {
            response.sendRedirect("usuarios/registroFallido.jsp");
        }
    }
}
