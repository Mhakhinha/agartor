package laboral;

public class Nomina {

    /*
    En esta clase nos preocuparemos en controlar los datos de las nominas,
    nos aseguraremos de sacar el sueldo de cada empleado segun la formula propuesta en el enunciado.
    */

    private static final int SUELDO_BASE[] =
            {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

    public double sueldo(Empleado e) {
        return SUELDO_BASE[e.getCategoria() - 1]
                + 5000 * e.getAnyos();
    }


}
