package org.example.command;

import org.example.controller.Mensajes;
import org.example.model.Empleado;
import org.example.service.EmpleadoService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Usamos esta clase para consultar todos los empleados y mostrarlos en listado.jsp
 * (acción "listar").
 */

public class ListarCommand implements Comando {

    private final EmpleadoService servicio;

    public ListarCommand(EmpleadoService servicio) {
        this.servicio = servicio;
    }

    @Override
    public String ejecutar(HttpServletRequest request) throws SQLException {
        List<Empleado> empleados = servicio.listar();
        if (empleados.isEmpty()) {
            return Mensajes.mostrar(request, "info",
                    "Sin resultados", "Todavía no hay empleados en la base de datos.");
        }
        request.setAttribute("empleados", empleados);
        return "listado";
    }
}