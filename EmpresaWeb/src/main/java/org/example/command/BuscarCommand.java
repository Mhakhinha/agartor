package org.example.command;

import org.example.controller.Mensajes;
import org.example.model.Empleado;
import org.example.service.EmpleadoService;
import org.example.service.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Usamos esta clase para buscar empleados por cualquiera de sus datos y mostrarlos
 * en resultados.jsp (acción "buscar").
 */

public class BuscarCommand implements Comando {

    private final EmpleadoService servicio;

    public BuscarCommand(EmpleadoService servicio) {
        this.servicio = servicio;
    }

    @Override
    public String ejecutar(HttpServletRequest request) throws NegocioException, SQLException {
        List<Empleado> empleados = servicio.buscar(
                request.getParameter("nombre"),
                request.getParameter("dni"),
                request.getParameter("sexo"),
                request.getParameter("categoria"),
                request.getParameter("anyos"));

        if (empleados.isEmpty()) {
            return Mensajes.mostrar(request, "info",
                    "Sin resultados", "Ningún empleado cumple los criterios de búsqueda.");
        }
        request.setAttribute("empleados", empleados);
        return "resultados";
    }
}