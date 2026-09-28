package laboral;

public class Persona {

    /*
    En esta clase nos preocuparemos en controlar los datos de las personas.
    Tendremos un metodo que imprima los datos
    */

    public String nombre, dni, sexo;

    public Persona(String nombre, String dni, String sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }


    public Persona(String nombre, String sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void imprime() {
        System.out.println("El nombre es " + nombre + " y tu dni es " + dni);
    }

}
