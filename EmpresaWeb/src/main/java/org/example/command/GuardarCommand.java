package org.example.command;

import org.example.controller.Mensajes;
import org.example.service.EmpleadoService;
import org.example.service.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Usamos esta clase para guardar los cambios del formulario de edición (acción "guardar").
 * Solo funciona por POST. El sueldo no se edita: se recalcula en EmpleadoService
 * a partir de la categoría y los años trabajados.
 */

public class GuardarCommand implements Comando {

    private final EmpleadoService servicio;

    public GuardarCommand(EmpleadoService servicio) {
        this.servicio = servicio;
    }

    @Override
    public String ejecutar(HttpServletRequest request) throws NegocioException, SQLException {
        if (!"POST".equals(request.getMethod())) {
            return Mensajes.mostrar(request, "error", "Petición no válida",
                    "Esta acción solo se puede realizar enviando el formulario.");
        }

        String nombre = request.getParameter("nombre");
        double sueldo = servicio.modificar(
                request.getParameter("dni"),
                nombre,
                request.getParameter("sexo"),
                request.getParameter("categoria"),
                request.getParameter("anyos"));

        return Mensajes.mostrar(request, "info", "Empleado modificado",
                "Se han guardado los datos de " + nombre.trim() + ". Su nuevo sueldo es "
                        + String.format(Locale.forLanguageTag("es-ES"), "%.2f", sueldo) + " €.");
    }
}