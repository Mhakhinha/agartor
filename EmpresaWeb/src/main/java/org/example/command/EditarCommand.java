package org.example.command;

import org.example.service.EmpleadoService;
import org.example.service.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Usamos esta clase para mostrar el formulario de edición con los datos actuales
 * del empleado en editarEmpleado.jsp (acción "editar").
 * Solo consulta los datos; los cambios se guardan después en GuardarCommand.
 */
public class EditarCommand implements Comando {

    // el servicio al que se le piden el empleado y su nómina
    private final EmpleadoService servicio;

    /**
     * Con este constructor la ComandoFactory le pasa el servicio que debe usar
     *
     * @param servicio servicio con la lógica de negocio de empleados y nóminas
     */
    public EditarCommand(EmpleadoService servicio) {
        this.servicio = servicio;
    }

    /**
     * Obtiene el empleado y su nómina a partir del DNI recibido y los deja en la petición
     * para que el formulario se rellene con los datos actuales
     *
     * @param request petición actual (debe traer el parámetro "dni")
     * @return devuelve el nombre de la vista del formulario ("editarEmpleado")
     * @throws NegocioException si el DNI está vacío o no existe ese empleado
     * @throws SQLException     nos saldrá si en algún momento falla la consulta SQL
     */
    @Override
    public String ejecutar(HttpServletRequest request) throws NegocioException, SQLException {
        String dni = request.getParameter("dni");
        request.setAttribute("empleado", servicio.obtenerEmpleado(dni));
        request.setAttribute("nomina", servicio.obtenerNomina(dni));
        return "editarEmpleado";
    }
}