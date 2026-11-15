package org.example.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Usamos esta clase como filtro (patrón Intercepting Filter) para fijar la codificación
 * UTF-8 en todas las peticiones antes de que lleguen al FrontController.
 * Así no hace falta repetir setCharacterEncoding en cada clase y los acentos
 * y las eñas se guardan bien en la base de datos.
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Se ejecuta una vez, cuando Tomcat arranca el filtro. No necesitamos configurar nada
     *
     * @param filterConfig configuración del filtro (no se usa)
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Fija UTF-8 en la petición y la deja pasar al siguiente elemento de la cadena
     * (el FrontController). Tiene que hacerse antes de leer cualquier parámetro,
     * por eso lo hacemos aquí, al principio de todo
     *
     * @param request  petición actual
     * @param response respuesta actual
     * @param chain    cadena de filtros; con chain.doFilter la petición sigue su camino
     * @throws IOException      si falla la lectura o escritura de la petición
     * @throws ServletException si falla el procesamiento de la petición
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    /**
     * Se ejecuta una vez, cuando Tomcat para la aplicación. No hay nada que liberar
     */
    @Override
    public void destroy() {
    }
}