package org.example.controller;

import org.example.command.ComandoFactory;
import org.example.service.NegocioException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Patrón Front Controller: usamos esta clase como único punto de entrada de la aplicación.
 * Todas las peticiones llegan a /controlador con el parámetro "accion". No tiene lógica
 * de negocio: pide el comando a ComandoFactory, lo ejecuta y reenvía a la vista que
 * este indique. También captura los errores en un solo sitio.
 * Las vistas están en WEB-INF para que no se pueda acceder a ellas directamente.
 */

@WebServlet("/controlador")
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String PREFIJO_VISTAS = "/WEB-INF/views/";
    private static final String SUFIJO_VISTAS = ".jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesar(request, response);
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null || accion.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String vista;
        try {
            vista = ComandoFactory.obtener(accion).ejecutar(request);

        } catch (NegocioException e) {
            vista = Mensajes.mostrar(request, e.getTipo(), e.getTitulo(), e.getMessage());

        } catch (SQLException e) {
            log("Error de base de datos en la acción '" + accion + "'", e);
            vista = Mensajes.mostrar(request, "error", "Error de base de datos",
                    "No se pudo completar la operación. Inténtalo de nuevo más tarde.");
        }

        request.getRequestDispatcher(PREFIJO_VISTAS + vista + SUFIJO_VISTAS).forward(request, response);
    }
}