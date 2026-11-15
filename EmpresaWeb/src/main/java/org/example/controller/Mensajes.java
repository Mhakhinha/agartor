package org.example.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Usamos esta clase para mostrar mensajes de información o de error
 */
public final class Mensajes {

    private Mensajes() {
    }

    /**
     * Prepara el mensaje en la petición. El reenvío a la página lo hace
     * el FrontController con el nombre de vista que devuelve este método.
     *
     * @param request petición actual
     * @param tipo    "info" o "error"
     * @param titulo  título del mensaje
     * @param texto   texto explicativo
     * @return el nombre de la vista de mensajes ("mensaje")
     */
    public static String mostrar(HttpServletRequest request,
                                 String tipo, String titulo, String texto) {
        request.setAttribute("tipo", tipo);
        request.setAttribute("titulo", titulo);
        request.setAttribute("texto", texto);
        return "mensaje";
    }
}