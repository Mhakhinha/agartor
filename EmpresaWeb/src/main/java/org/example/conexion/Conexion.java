package org.example.conexion;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        /** Mi MariaDB escucha en el puerto 4306
        La url de la pagina web http://localhost:8080/empresa/  */
        dataSource.setUrl("jdbc:mariadb://localhost:4306/empresa");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
    }

    public static Connection getConexion() throws SQLException {
        return dataSource.getConnection();
    }
}

/**
 Base de datos

 DROP DATABASE IF EXISTS empresa;

 CREATE DATABASE empresa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

 USE empresa;

 CREATE TABLE empleados (
 dni VARCHAR(9) PRIMARY KEY,
 nombre VARCHAR(100) NOT NULL,
 sexo CHAR(1) NOT NULL,
 categoria INT NOT NULL DEFAULT 1,
 anyos INT NOT NULL DEFAULT 0
 );

 CREATE TABLE nominas (
 dni VARCHAR(9) PRIMARY KEY,
 sueldo DECIMAL(10,2) NOT NULL,
 FOREIGN KEY (dni) REFERENCES empleados(dni)
 );

 INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES
 ('12345678A', 'Alejandro', 'M', 1, 2),
 ('12345678B', 'David',     'M', 2, 4),
 ('12345678C', 'Dani',      'M', 3, 6),
 ('12345678D', 'Alberta',   'F', 4, 3),
 ('12345678E', 'Christian', 'M', 5, 8),
 ('12345678F', 'Laura',     'F', 6, 9);

 -- Sueldo = base de la categoría + 100 euros por cada año trabajado
 -- Bases: cat. 1 = 1200, 2 = 1500, 3 = 1800, 4 = 2100, 5 = 2400, 6 = 2700
 INSERT INTO nominas (dni, sueldo) VALUES
 ('12345678A', 1400.00),
 ('12345678B', 1900.00),
 ('12345678C', 2400.00),
 ('12345678D', 2400.00),
 ('12345678E', 3200.00),
 ('12345678F', 3600.00);
 */