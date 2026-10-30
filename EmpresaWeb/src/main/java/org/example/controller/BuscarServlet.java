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
 * Usamos esta clase para buscar empleados por cualquiera de sus datos y mostrarlos en resultados.jsp
 */
@WebServlet("/buscar")
public class BuscarServlet extends HttpServlet {

    private final EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Empleado> empleados = dao.buscar(
                    request.getParameter("nombre"),
                    request.getParameter("dni"),
                    request.getParameter("sexo"),
                    request.getParameter("categoria"),
                    request.getParameter("anyos"));

            if (empleados.isEmpty()) {
                Mensajes.mostrar(request, response, "info",
                        "Sin resultados", "Ningún empleado cumple los criterios de búsqueda.");
                return;
            }
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("/views/resultados.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            Mensajes.mostrar(request, response, "error",
                    "Datos no válidos",
                    "La categoría y los años trabajados deben ser números enteros.");
        } catch (SQLException e) {
            Mensajes.mostrar(request, response, "error",
                    "Error de base de datos", "No se pudo consultar: " + e.getMessage());
        }
    }
}