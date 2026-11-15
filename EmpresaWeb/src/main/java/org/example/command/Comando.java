package org.example.command;

import org.example.service.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Patrón Command: usamos esta interfaz para que cada acción de la aplicación
 * (listar, buscar, editar...) sea un objeto con un único método.
 */

@FunctionalInterface
public interface Comando {

    /**
     * Ejecuta la acción: lee los parámetros, llama al servicio y deja los datos en la petición
     *
     * @param request petición actual
     * @return el nombre de la vista a mostrar (sin ruta ni extensión), por ejemplo "listado"
     * @throws NegocioException si hay un error previsto (datos no válidos, no encontrado...)
     * @throws SQLException     nos saldrá si en algún momento falla la consulta SQL
     */

    String ejecutar(HttpServletRequest request) throws NegocioException, SQLException;
}