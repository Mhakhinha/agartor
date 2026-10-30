package laboral.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =  "jdbc:mariadb://localhost:3306/empresa";

    private static  final String USUARIO = "root";

    private static final String PASSWORD = "1234";

    public static Connection conect()throws SQLException{
        return DriverManager.getConnection(
                URL,USUARIO,PASSWORD
        );
    }
}
