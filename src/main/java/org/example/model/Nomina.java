package org.example.model;

/**
 * Representa la nómina de un empleado.
 * Se relaciona con Empleado mediante el DNI.
 */
public class Nomina {

    // sueldo base de cada categoría (la categoría 1 es la posición 0)
    private static final double[] BASE_POR_CATEGORIA = {1200, 1500, 1800, 2100, 2400, 2700};

    // euros que se suman por cada año trabajado
    private static final double EXTRA_POR_ANYO = 100;

    private String dni;
    private double sueldo;

    public Nomina() {
    }

    public Nomina(String dni, double sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    /**
     * Calcula el sueldo sumando la base de la categoría y 100 euros por cada año trabajado
     *
     * @param categoria la categoría del empleado (de 1 a 6)
     * @param anyos     los años trabajados
     * @return devuelve el sueldo calculado
     * @throws IllegalArgumentException nos saldrá si la categoría no existe
     */
    public static double calcularSueldo(int categoria, int anyos) {
        if (categoria < 1 || categoria > BASE_POR_CATEGORIA.length) {
            throw new IllegalArgumentException("Categoría no válida: " + categoria);
        }
        return BASE_POR_CATEGORIA[categoria - 1] + anyos * EXTRA_POR_ANYO;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }
}