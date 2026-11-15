package org.example.service;

import org.example.dao.EmpleadoDAO;
import org.example.dao.NominaDAO;
import org.example.model.Empleado;
import org.example.model.Nomina;

import java.sql.SQLException;
import java.util.List;

/**
 * Usamos esta clase para reunir la lógica de negocio (patrón Service Layer).
 * Aquí se validan los datos, se normaliza el DNI y se recalcula la nómina,
 * y así los comandos no necesitan saber que existen dos DAO distintos.
 * No usa HttpServletRequest, por lo que no depende del servidor web.
 */
public class EmpleadoService {

    // acceso a los datos de las tablas empleados y nominas
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final NominaDAO nominaDAO = new NominaDAO();

    /**
     * Obtiene todos los empleados ordenados por nombre
     *
     * @return devuelve la lista de empleados (vacía si no hay ninguno)
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public List<Empleado> listar() throws SQLException {
        return empleadoDAO.listar();
    }

    /**
     * Busca empleados por los criterios indicados (los vacíos se ignoran)
     *
     * @param nombre    el nombre
     * @param dni       el DNI
     * @param sexo      si es masculino "M" o femenino "F"
     * @param categoria la categoría, como texto
     * @param anyos     los años trabajados, como texto
     * @return devuelve la lista de empleados que cumplen todos los criterios
     * @throws NegocioException si la categoría o los años no son números enteros
     * @throws SQLException     nos saldrá si en algún momento falla la consulta SQL
     */
    public List<Empleado> buscar(String nombre, String dni, String sexo,
                                 String categoria, String anyos) throws NegocioException, SQLException {
        try {
            return empleadoDAO.buscar(nombre, dni, sexo, categoria, anyos);
        } catch (NumberFormatException e) {
            throw numerosNoValidos();
        }
    }

    /**
     * Obtiene un empleado a partir de su DNI
     *
     * @param dni DNI del empleado
     * @return devuelve el empleado
     * @throws NegocioException si el DNI está vacío o no existe ese empleado
     * @throws SQLException     nos saldrá si en algún momento falla la consulta SQL
     */
    public Empleado obtenerEmpleado(String dni) throws NegocioException, SQLException {
        String dniLimpio = normalizarDni(dni);
        Empleado empleado = empleadoDAO.obtener(dniLimpio);
        if (empleado == null) {
            throw new NegocioException("info", "Empleado no encontrado",
                    "No existe ningún empleado con el DNI " + dniLimpio + ".");
        }
        return empleado;
    }

    /**
     * Obtiene la nómina de un empleado a partir de su DNI
     *
     * @param dni DNI del empleado
     * @return devuelve la nómina o null si el empleado no tiene
     * @throws NegocioException si el DNI está vacío
     * @throws SQLException     nos saldrá si en algún momento falla la consulta SQL
     */
    public Nomina obtenerNomina(String dni) throws NegocioException, SQLException {
        return nominaDAO.obtener(normalizarDni(dni));   // null si no tiene
    }

    /**
     * Valida los datos del formulario, actualiza el empleado y recalcula su nómina.
     * El sueldo no se edita: se calcula a partir de la categoría y los años trabajados
     *
     * @param dni          DNI del empleado
     * @param nombre       el nombre nuevo
     * @param sexo         "M" o "F"
     * @param categoriaTxt la categoría nueva, como texto (de 1 a 6)
     * @param anyosTxt     los años trabajados, como texto
     * @return devuelve el sueldo nuevo
     * @throws NegocioException si los datos no son válidos o el empleado no existe
     * @throws SQLException     nos saldrá si en algún momento falla la consulta SQL
     */
    public double modificar(String dni, String nombre, String sexo,
                            String categoriaTxt, String anyosTxt) throws NegocioException, SQLException {

        // 1. se comprueba que el empleado existe
        String dniLimpio = normalizarDni(dni);
        if (empleadoDAO.obtener(dniLimpio) == null) {
            throw new NegocioException("info", "Empleado no encontrado",
                    "El empleado que intentas modificar no existe.");
        }

        // 2. se validan el nombre y el sexo
        if (nombre == null || nombre.isBlank() || !("M".equals(sexo) || "F".equals(sexo))) {
            throw new NegocioException("error", "Datos no válidos",
                    "El nombre es obligatorio y el sexo debe ser M o F.");
        }

        // 3. se convierten a número la categoría y los años, y se comprueban sus rangos
        int categoria;
        int anyos;
        try {
            categoria = Integer.parseInt(categoriaTxt.trim());
            anyos = Integer.parseInt(anyosTxt.trim());
        } catch (NumberFormatException | NullPointerException e) {
            throw numerosNoValidos();
        }
        if (categoria < 1 || categoria > 6 || anyos < 0) {
            throw new NegocioException("error", "Datos no válidos",
                    "La categoría debe estar entre 1 y 6 y los años no pueden ser negativos.");
        }

        // 4. se actualiza el empleado
        empleadoDAO.actualizar(new Empleado(nombre.trim(), dniLimpio, sexo, categoria, anyos));

        // 5. se recalcula el sueldo y se actualiza (o se crea) la nómina
        double sueldo = Nomina.calcularSueldo(categoria, anyos);
        Nomina nomina = new Nomina(dniLimpio, sueldo);
        if (nominaDAO.obtener(dniLimpio) == null) {
            nominaDAO.insertar(nomina);
        } else {
            nominaDAO.actualizar(nomina);
        }
        return sueldo;
    }

    /**
     * Comprueba que el DNI no esté vacío y lo deja sin espacios y en mayúsculas
     *
     * @param dni DNI tal y como llega del formulario
     * @return devuelve el DNI limpio
     * @throws NegocioException si el DNI es nulo o está vacío
     */
    private String normalizarDni(String dni) throws NegocioException {
        if (dni == null || dni.isBlank()) {
            throw new NegocioException("error", "DNI no válido", "Debes indicar el DNI del empleado.");
        }
        return dni.trim().toUpperCase();
    }

    /**
     * Crea el error que se muestra cuando la categoría o los años no son números enteros
     *
     * @return devuelve la excepción lista para lanzarla
     */
    private NegocioException numerosNoValidos() {
        return new NegocioException("error", "Datos no válidos",
                "La categoría y los años trabajados deben ser números enteros.");
    }
}