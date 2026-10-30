package org.example.dao;

import org.example.conexion.Conexion;
import org.example.model.Nomina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase da el acceso a los datos de la tabla nominas
 */
public class NominaDAO {

    /**
     * Obtiene todas las nóminas ordenadas por DNI
     *
     * @return devuelve la lista de nóminas (la devuelve vacía si no hay ninguna)
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public List<Nomina> listar() throws SQLException {
        String sql = "SELECT dni, sueldo FROM nominas ORDER BY dni";
        List<Nomina> nominas = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                nominas.add(crearNomina(rs));
            }
        }
        return nominas;
    }

    /**
     * Obtiene la nómina de un empleado a partir de su DNI
     *
     * @param dni DNI del empleado
     * @return devuelve la nómina o null si el empleado no tiene
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public Nomina obtener(String dni) throws SQLException {
        String sql = "SELECT dni, sueldo FROM nominas WHERE dni = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearNomina(rs);
                }
            }
        }
        return null;
    }

    /**
     * Inserta una nómina nueva
     * Es importante que el empleado con ese DNI deba existir ya
     *
     * @param n la nómina que se va a insertar
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public void insertar(Nomina n) throws SQLException {
        String sql = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, n.getDni());
            ps.setDouble(2, n.getSueldo());
            ps.executeUpdate();
        }
    }

    /**
     * Actualiza el sueldo de una nómina existente
     *
     * @param n la ómina con el sueldo nuevo
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public void actualizar(Nomina n) throws SQLException {
        String sql = "UPDATE nominas SET sueldo = ? WHERE dni = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, n.getSueldo());
            ps.setString(2, n.getDni());
            ps.executeUpdate();
        }
    }

    /**
     * Elimina la nómina de un empleado.
     *
     * @param dni DNI del empleado
     * @throws SQLException nos saldrá si en algún momento falla la consulta SQL
     */
    public void eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM nominas WHERE dni = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }

    /**
     * Crea una nómina a partir de la fila actual del ResultSet.
     */
    private Nomina crearNomina(ResultSet rs) throws SQLException {
        return new Nomina(rs.getString("dni"), rs.getDouble("sueldo"));
    }
}