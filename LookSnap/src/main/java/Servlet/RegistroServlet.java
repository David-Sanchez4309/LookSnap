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
        String apellido = request.getParameter("apellido");//
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String contrasena = request.getParameter("contrasena");

        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
        System.out.println("Contraseña: " + contrasena);

        if (nombre == null || nombre.isEmpty() ||
                apellido == null || apellido.isEmpty() ||
                correo == null || correo.isEmpty() ||
                telefono == null || telefono.isEmpty() ||
                direccion == null || direccion.isEmpty() ||
                contrasena == null || contrasena.isEmpty()) {
            response.sendRedirect("registro.html?error=campos_vacios");
            return;
        }else {


        usuario nuevoUsuario = new usuario();


            usuariosDAO dao = new usuariosDAO();
            boolean exito = dao.registrarUsuario(nuevoUsuario);

            if (exito) {
                response.sendRedirect("login.xhtml");
            } else {
                response.sendRedirect("registro.html?error=fallo_registro");
            }

            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Correo: " + correo);
        }
    }

}
