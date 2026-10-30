package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Usamos esta clase para mostrar mensajes de información o de error
 */
public final class Mensajes {

    private Mensajes() {
    }

    /**
     * Reenvía la petición a la página de mensajes.
     *
     * @param request  petición actual
     * @param response respuesta actual
     * @param tipo     "info" o "error"
     * @param titulo   título del mensaje
     * @param texto    texto explicativo
     * @throws ServletException si falla el reenvío
     * @throws IOException      si falla la escritura de la respuesta
     */
    public static void mostrar(HttpServletRequest request, HttpServletResponse response,
                               String tipo, String titulo, String texto)
            throws ServletException, IOException {
        request.setAttribute("tipo", tipo);
        request.setAttribute("titulo", titulo);
        request.setAttribute("texto", texto);
        request.getRequestDispatcher("/views/mensaje.jsp").forward(request, response);
    }
}