package laboral.dao;

import laboral.Empleado;

import java.util.List;

public interface EmpleadoDAO {

    void insertar(Empleado empleado); //mete un empleado en la base de datos

    Empleado buscarPorDni(String dni); // busca por el DNI y devuelve el empleado

    List<Empleado> obtenerTodos(); // Devueleve todos los empleados

    void actualizar(Empleado empleado); // Modifica los datos de un empleado

    void eliminar(String dni); // Elimina un empleado

    void actualizarSueldo(String dni, double sueldo); // Actualiza el sueldo del empleado despues de calcularlo

    double obtenerSueldo(String dni); // Te encuentra el salario
}