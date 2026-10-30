package laboral;

import java.util.Scanner;

public class CalculaNominas {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        int opcion;

        do {
            System.out.println();
            System.out.println("       GESTIÓN DE NÓMINAS");
            System.out.println("1. Mostrar todos los empleados");
            System.out.println("2. Mostrar salario de un empleado");
            System.out.println("3. Modificar datos de un empleado");
            System.out.println("4. Recalcular sueldo de un empleado");
            System.out.println("5. Recalcular todos los sueldos");
            System.out.println("6. Realizar copia de seguridad");
            System.out.println("0. Salir");

            System.out.print("Introduce una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {


                case 1:

                    System.out.println(
                            "Mostrando todos los empleados..."
                    );

                    break;


                case 2:

                    System.out.print(
                            "Introduce el DNI del empleado: "
                    );

                    String dniSalario = teclado.nextLine();

                    System.out.println(
                            "Mostrando salario del empleado "
                                    + dniSalario
                    );


                    break;


                case 3:

                    menuModificarEmpleado(teclado);

                    break;


                case 4:

                    System.out.print(
                            "Introduce el DNI del empleado: "
                    );

                    String dniRecalcular = teclado.nextLine();

                    System.out.println(
                            "Recalculando sueldo del empleado "
                                    + dniRecalcular
                    );


                    break;


                case 5:

                    System.out.println(
                            "Recalculando todos los sueldos..."
                    );


                    break;

                case 0:

                    System.out.println(
                            "Programa finalizado."
                    );

                    break;


                default:

                    System.out.println(
                            "Opción no válida."
                    );
            }

        } while (opcion != 0);

        teclado.close();
    }


    public static void menuModificarEmpleado(
            Scanner teclado) {

        int opcion;

        do {

            System.out.println();
            System.out.println("      MODIFICAR EMPLEADO");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar DNI");
            System.out.println("3. Modificar sexo");
            System.out.println("4. Modificar categoría");
            System.out.println("5. Modificar años trabajados");
            System.out.println("0. Volver al menú principal");

            System.out.print("Introduce una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println(
                            "Aquí modificaremos el nombre."
                    );

                    break;

                case 2:

                    System.out.println(
                            "Aquí modificaremos el DNI."
                    );

                    break;

                case 3:

                    System.out.println(
                            "Aquí modificaremos el sexo."
                    );

                    break;

                case 4:

                    System.out.println(
                            "Aquí modificaremos la categoría."
                    );

                    break;

                case 5:

                    System.out.println(
                            "Aquí modificaremos los años trabajados."
                    );

                    break;

                case 0:

                    System.out.println(
                            "Volviendo al menú principal..."
                    );

                    break;

                default:

                    System.out.println(
                            "Opción no válida."
                    );
            }

        } while (opcion != 0);
    }
}