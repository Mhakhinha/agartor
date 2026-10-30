package org.example.controller;

import org.example.dao.EmpleadoDAO;
import org.example.dao.NominaDAO;
import org.example.model.Empleado;
import org.example.model.Nomina;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Usamos esta clase para recibir un DNI, consultar el empleado y su nómina, y mostrarlo en salario.jsp
 */
@WebServlet("/salario")
public class SalarioServlet extends HttpServlet {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final NominaDAO nominaDAO = new NominaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");

        if (dni == null || dni.isBlank()) {
            Mensajes.mostrar(request, response, "error",
                    "DNI no válido", "Debes introducir el DNI del empleado.");
            return;
        }

        try {
            dni = dni.trim().toUpperCase();
            Empleado empleado = empleadoDAO.obtener(dni);
            Nomina nomina = nominaDAO.obtener(dni);

            if (empleado == null) {
                Mensajes.mostrar(request, response, "info",
                        "Empleado no encontrado",
                        "No existe ningún empleado con el DNI " + dni + ".");
                return;
            }
            if (nomina == null) {
                Mensajes.mostrar(request, response, "info",
                        "Sin nómina",
                        "El empleado " + empleado.getNombre() + " no tiene nómina registrada.");
                return;
            }
            request.setAttribute("empleado", empleado);
            request.setAttribute("nomina", nomina);
            request.getRequestDispatcher("/views/salario.jsp").forward(request, response);

        } catch (SQLException e) {
            Mensajes.mostrar(request, response, "error",
                    "Error de base de datos", "No se pudo consultar: " + e.getMessage());
        }
    }
}