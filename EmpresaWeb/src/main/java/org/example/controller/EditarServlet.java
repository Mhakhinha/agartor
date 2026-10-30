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
import java.util.Locale;

/**
 * Usamos esta clase para modificar los datos de un empleado
 * El sueldo no se edita: se recalcula solo a partir de la categoría y los años trabajados
 */
@WebServlet("/editar")
public class EditarServlet extends HttpServlet {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final NominaDAO nominaDAO = new NominaDAO();

    /**
     * Muestra el formulario de edición con los datos actuales del empleado
     *
     * @param request  petición actual
     * @param response respuesta actual
     * @throws ServletException si falla el reenvío
     * @throws IOException      si falla la escritura de la respuesta
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");

        if (dni == null || dni.isBlank()) {
            Mensajes.mostrar(request, response, "error",
                    "DNI no válido", "No se ha indicado qué empleado modificar.");
            return;
        }

        try {
            Empleado empleado = empleadoDAO.obtener(dni.trim().toUpperCase());

            if (empleado == null) {
                Mensajes.mostrar(request, response, "info",
                        "Empleado no encontrado",
                        "No existe ningún empleado con el DNI " + dni.trim() + ".");
                return;
            }
            request.setAttribute("empleado", empleado);
            request.setAttribute("nomina", nominaDAO.obtener(empleado.getDni()));
            request.getRequestDispatcher("/views/editarEmpleado.jsp").forward(request, response);

        } catch (SQLException e) {
            Mensajes.mostrar(request, response, "error",
                    "Error de base de datos", "No se pudo consultar: " + e.getMessage());
        }
    }

    /**
     * Valida los datos del formulario, actualiza el empleado y recalcula su nómina
     *
     * @param request  petición actual
     * @param response respuesta actual
     * @throws ServletException si falla el reenvío
     * @throws IOException      si falla la escritura de la respuesta
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");

        try {
            if (dni == null || empleadoDAO.obtener(dni) == null) {
                Mensajes.mostrar(request, response, "info",
                        "Empleado no encontrado", "El empleado que intentas modificar no existe.");
                return;
            }
            if (nombre == null || nombre.isBlank() || !("M".equals(sexo) || "F".equals(sexo))) {
                Mensajes.mostrar(request, response, "error",
                        "Datos no válidos", "El nombre es obligatorio y el sexo debe ser M o F.");
                return;
            }

            int categoria = Integer.parseInt(request.getParameter("categoria"));
            int anyos = Integer.parseInt(request.getParameter("anyos"));

            if (categoria < 1 || categoria > 6 || anyos < 0) {
                Mensajes.mostrar(request, response, "error",
                        "Datos no válidos",
                        "La categoría debe estar entre 1 y 6 y los años no pueden ser negativos.");
                return;
            }

            // 1. se actualiza el empleado
            empleadoDAO.actualizar(new Empleado(nombre.trim(), dni, sexo, categoria, anyos));

            // 2. se recalcula el sueldo y se actualiza (o se crea) la nómina
            double sueldo = Nomina.calcularSueldo(categoria, anyos);
            Nomina nomina = new Nomina(dni, sueldo);
            if (nominaDAO.obtener(dni) == null) {
                nominaDAO.insertar(nomina);
            } else {
                nominaDAO.actualizar(nomina);
            }

            Mensajes.mostrar(request, response, "info", "Empleado modificado",
                    "Se han guardado los datos de " + nombre.trim() + ". Su nuevo sueldo es "
                            + String.format(Locale.forLanguageTag("es-ES"), "%.2f", sueldo) + " €.");

        } catch (NumberFormatException e) {
            Mensajes.mostrar(request, response, "error",
                    "Datos no válidos", "La categoría y los años trabajados deben ser números enteros.");
        } catch (SQLException e) {
            Mensajes.mostrar(request, response, "error",
                    "Error de base de datos", "No se pudo guardar: " + e.getMessage());
        }
    }
}