package org.example.controller;

import org.example.dao.EmpleadoDAO;
import org.example.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Usamos esta clase para consultar todos los empleados y mostrarlos en listado.jsp
 */
@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

    private final EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Empleado> empleados = dao.listar();

            if (empleados.isEmpty()) {
                Mensajes.mostrar(request, response, "info",
                        "Sin resultados", "Todavía no hay empleados en la base de datos.");
                return;
            }
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("/views/listado.jsp").forward(request, response);

        } catch (SQLException e) {
            Mensajes.mostrar(request, response, "error",
                    "Error de base de datos", "No se pudo consultar: " + e.getMessage());
        }
    }
}