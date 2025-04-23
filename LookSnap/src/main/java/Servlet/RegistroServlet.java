package Servlet;

import DAO.usuariosDAO;
import entities.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String contrasena = request.getParameter("contrasena");

        if (nombre == null || apellido == null || correo == null || telefono == null || direccion == null || contrasena == null ||
            nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || contrasena.isEmpty()){
            response.sendRedirect("registro.html?error=campos_vacios");
        }

        usuario nuevoUsuario = new usuario(0, nombre, apellido, telefono, direccion, contrasena);

        usuariosDAO dao = new usuariosDAO();
        boolean exito = dao.registrarUsuario(nuevoUsuario);

        if(exito){
            response.sendRedirect("login.html");
        }else{
            response.sendRedirect("registro.html?error=fallo_registro");
        }
    }

}
