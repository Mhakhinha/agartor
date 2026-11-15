package org.example.command;

import org.example.controller.Mensajes;
import org.example.model.Empleado;
import org.example.model.Nomina;
import org.example.service.EmpleadoService;
import org.example.service.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Usamos esta clase para recibir un DNI, consultar el empleado y su nómina,
 * y mostrarlo en salario.jsp (acción "salario").
 */

public class SalarioCommand implements Comando {

    private final EmpleadoService servicio;

    public SalarioCommand(EmpleadoService servicio) {
        this.servicio = servicio;
    }

    @Override
    public String ejecutar(HttpServletRequest request) throws NegocioException, SQLException {
        String dni = request.getParameter("dni");
        Empleado empleado = servicio.obtenerEmpleado(dni);
        Nomina nomina = servicio.obtenerNomina(dni);

        if (nomina == null) {
            return Mensajes.mostrar(request, "info", "Sin nómina",
                    "El empleado " + empleado.getNombre() + " no tiene nómina registrada.");
        }
        request.setAttribute("empleado", empleado);
        request.setAttribute("nomina", nomina);
        return "salario";
    }
}