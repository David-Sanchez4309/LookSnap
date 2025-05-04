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
import java.util.List;

@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        usuariosDAO usuarioDao = new usuariosDAO();
        barberoDAO barberoDao = new barberoDAO();
        administradorDAO adminDao = new administradorDAO();

        List<usuario> clientes = usuarioDao.obtenerTodos();
        List<barbero> barberos = barberoDao.obtenerTodos();
        List<administrador> admins = adminDao.obtenerTodos();

        request.setAttribute("clientes", clientes);
        request.setAttribute("barberos", barberos);
        request.setAttribute("administradores", admins);

        request.getRequestDispatcher("usuarios/listarUsuarios.jsp").forward(request, response);
        System.out.println("Clientes encontrados: " + clientes.size());
        System.out.println("Barberos encontrados: " + barberos.size());
        System.out.println("Administradores encontrados: " + admins.size());

    }

}
