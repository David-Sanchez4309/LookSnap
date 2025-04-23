package Servlet;

import DAO.CitaUsuarioDAO;
import com.google.gson.Gson;
import entities.CitaUsuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarCitas")
public class ListarCitasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String fecha = request.getParameter("fecha"); // Puedes usar esto si filtras por fecha más adelante

        if (fecha == null || fecha.isEmpty()) {
            response.getWriter().print("Fecha no proporcionada.");
            return;
        }

        CitaUsuarioDAO dao = new CitaUsuarioDAO();
        List<CitaUsuario> citas = dao.listarCitasConUsuarios(fecha);  // Pasar fecha al DAO

        String json = new Gson().toJson(citas);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
