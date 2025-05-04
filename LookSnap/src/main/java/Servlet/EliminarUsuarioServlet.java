package Servlet;

import DAO.administradorDAO;
import DAO.barberoDAO;
import DAO.usuariosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String rol = request.getParameter("rol");

        boolean eliminado = false;

        switch (rol) {
            case "cliente":
                usuariosDAO uDao = new usuariosDAO();
                eliminado = uDao.eliminarUsuario(id);
                break;
            case "barbero":
                barberoDAO bDao = new barberoDAO();
                eliminado = bDao.eliminarBarbero(id);
                break;
            case "administrador":
                administradorDAO aDao = new administradorDAO();
                eliminado = aDao.eliminarAdministrador(id);
                break;
        }

        if (eliminado) {
            response.sendRedirect("ListarUsuariosServlet");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
