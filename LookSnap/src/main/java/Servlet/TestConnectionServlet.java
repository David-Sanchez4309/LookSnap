package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DataBaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/TestConnection")
public class TestConnectionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DataBaseConnection.conectar()) {
            if (conn != null) {
                response.getWriter().println("Conexión exitosa a la base de datos.");
            } else {
                response.getWriter().println("Conexión fallida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al conectar: " + e.getMessage());
        }
    }
}
