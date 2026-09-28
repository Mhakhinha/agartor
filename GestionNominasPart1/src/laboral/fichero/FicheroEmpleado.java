package laboral.fichero;

import laboral.Empleado;

import java.io.*;

public class FicheroEmpleado {

    public static void leerEmpleados(String nombreFichero) {

        try (BufferedReader bufreader = new BufferedReader(new FileReader(nombreFichero))) {

            String linea;

            while ((linea = bufreader.readLine()) != null) {

                String[] frase = linea.split(";");

                String nombre = frase[0];
                String dni = frase[1];
                String sexo = frase[2];
                int categoria = Integer.parseInt(frase[3]);
                int anyos = Integer.parseInt(frase[4]);

                Empleado empleado = new Empleado(
                        nombre,
                        dni,
                        sexo,
                        categoria,
                        anyos
                );

                empleado.imprime();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al leer el fichero: " + e.getMessage()
            );
        }
    }

        public void escribirSalario (String nombreFichero, Empleado emp,double sueldo){


            try (PrintWriter pw =
                         new PrintWriter(
                                 new FileWriter(nombreFichero, true))) {

                pw.println(
                        emp.dni + ";" + sueldo
                );

            } catch (IOException e) {

                System.out.println(
                        "Error al escribir el fichero: " + e.getMessage()
                );
        }
    }
}
