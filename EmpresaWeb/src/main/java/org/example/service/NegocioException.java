package org.example.service;

public class NegocioException extends Exception {

    /**
     * Usamos esta clase para representar los errores previstos de la lógica de negocio
     * (datos no válidos, empleado no encontrado...). Lleva el tipo ("info" o "error")
     * y el título que se mostrarán al usuario; el texto es el mensaje de la excepción.
     */

    private static final long serialVersionUID = 1L;

    private final String tipo;
    private final String titulo;

    public NegocioException(String tipo, String titulo, String mensaje) {
        super(mensaje);
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public String getTipo() { return tipo; }
    public String getTitulo() { return titulo; }
}