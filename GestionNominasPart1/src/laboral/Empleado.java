package laboral;

public class Empleado extends Persona {


    /*
    En esta clase nos preocuparemos en controlar los datos de los empleados
    nos aseguraremos que los datos estan dentro de los limites propuestos en el enunciado.
    Tenemos varios metodos que usaremos en otras clases como incranyos o imprime.
     */

    private int categoria;
    public int anyos;


    public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) throws DatosNoCorrectosException{
        super(nombre, dni, sexo);

        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException("Los datos no son correptos ya que ha habido un problema em el apartado categoria");
        }

        if (anyos < 0) {
            throw new DatosNoCorrectosException("Los datos no son correptos ya que ha habido un problema em el apartado años");
        }
        this.categoria = categoria;
        this.anyos = anyos;
    }

    public Empleado(String nombre, String dni, String sexo) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        categoria = 1;
        anyos = 0;

    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {

        if (categoria < 1 || categoria > 10) {
            throw new DatosNoCorrectosException(
                    "La categoría debe estar entre 1 y 10");
        }

        this.categoria = categoria;
    }


    public int getAnyos() {
        return anyos;
    }

    public void incrAnyo() {
        anyos ++;
    }

    public void setAnyos(int anyos) {

        if (anyos < 0) {
            throw new DatosNoCorrectosException(
                    "Los años no pueden ser negativos");
        }

        this.anyos = anyos;
    }

    public void imprime() {
        System.out.println("Nombre: " + nombre + " ,Dni:  " + dni + " ,Sexo: " + sexo + " ,Categoria: " + categoria + " ,Años: " + anyos);
    }

}
