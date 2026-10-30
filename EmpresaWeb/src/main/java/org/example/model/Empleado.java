package org.example.model;

/**
 * Representa a un empleado de la empresa.
 * El sueldo se guarda aparte, en la clase Nomina.
 */
public class Empleado {

    private String nombre;
    private String dni;
    private String sexo;
    private int categoria;
    private int anyos;

    // constructor vacío
    public Empleado() {
    }

    // constructor con sus atributos
    public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    // getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }
}