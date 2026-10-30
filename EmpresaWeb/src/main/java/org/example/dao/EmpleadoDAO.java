package org.example.dao;

import org.example.conexion.Conexion;
import org.example.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Usamos esta clase para el acceso a los datos de la tabla empleados
 */
public class EmpleadoDAO {

    /**
     * Con esto obtenemos todos los empleados ordenados por nombre.
     *
     * @return devuelve la lista de empleados (si no hay ninguno la devuelve vacía)
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public List<Empleado> listar() throws SQLException {
        String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados ORDER BY nombre";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                empleados.add(crearEmpleado(rs));
            }
        }
        return empleados;
    }

    /**
     * Con este método obtenemos un empleado usando su DNI
     *
     * @param dni DNI del empleado
     * @return devuelve el empleado o null si no existe
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public Empleado obtener(String dni) throws SQLException {
        String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE dni = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearEmpleado(rs);
                }
            }
        }
        return null;
    }

    /**
     * Busca empleados al rrellenar todos los criterios indicados.
     * Los criterios vacíos o nulos se ignoran
     *
     * @param nombre    el nombre
     * @param dni       el DNI
     * @param sexo      si es masculino "M" o femenino "F"
     * @param categoria la categoria
     * @param anyos     los años trabajados
     * @return Devuelve la lista de empleados que cumplen todos los criterios
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public List<Empleado> buscar(String nombre, String dni, String sexo,
                                 String categoria, String anyos) throws SQLException {

        StringBuilder sql = new StringBuilder(
                "SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (nombre != null && !nombre.isBlank()) {
            sql.append(" AND nombre LIKE ?");
            parametros.add("%" + nombre.trim() + "%");
        }
        if (dni != null && !dni.isBlank()) {
            sql.append(" AND dni LIKE ?");
            parametros.add("%" + dni.trim() + "%");
        }
        if (sexo != null && !sexo.isBlank()) {
            sql.append(" AND sexo = ?");
            parametros.add(sexo.trim());
        }
        if (categoria != null && !categoria.isBlank()) {
            sql.append(" AND categoria = ?");
            parametros.add(Integer.parseInt(categoria.trim()));
        }
        if (anyos != null && !anyos.isBlank()) {
            sql.append(" AND anyos = ?");
            parametros.add(Integer.parseInt(anyos.trim()));
        }
        sql.append(" ORDER BY nombre");

        List<Empleado> empleados = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(crearEmpleado(rs));
                }
            }
        }
        return empleados;
    }

    /**
     * Inserta un empleado nuevo
     *
     * @param e empleado que se instertara
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public void insertar(Empleado e) throws SQLException {
        String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getSexo());
            ps.setInt(4, e.getCategoria());
            ps.setInt(5, e.getAnyos());
            ps.executeUpdate();
        }
    }

    /**
     * Actualiza los datos de un empleado existente buscado por su DNI
     *
     * @param e empleado que se actualizara
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public void actualizar(Empleado e) throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getSexo());
            ps.setInt(3, e.getCategoria());
            ps.setInt(4, e.getAnyos());
            ps.setString(5, e.getDni());
            ps.executeUpdate();
        }
    }

    /**
     * Elimina un empleado. Antes hay que borrar su nómina con
     * {@link NominaDAO#eliminar(String)}, porque la tabla nominas
     * depende de empleados.
     *
     * @param dni DNI del empleado a eliminar
     * @throws SQLException si falla el borrado
     */
    public void eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM empleados WHERE dni = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }

    /**
     * Con este método creamos un empleado a partir de la fila actual del ResultSet.
     */
    private Empleado crearEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getString("nombre"),
                rs.getString("dni"),
                rs.getString("sexo"),
                rs.getInt("categoria"),
                rs.getInt("anyos")
        );
    }
}