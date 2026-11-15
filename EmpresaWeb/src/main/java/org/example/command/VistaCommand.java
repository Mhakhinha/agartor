package org.example.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Usamos esta clase para las acciones que solo muestran una página sin consultar nada,
 * como los formularios de búsqueda. Devuelve el nombre de la vista que se le indica.
 */

public class VistaCommand implements Comando {

    private final String vista;

    public VistaCommand(String vista) {
        this.vista = vista;
    }

    @Override
    public String ejecutar(HttpServletRequest request) {
        return vista;
    }
}