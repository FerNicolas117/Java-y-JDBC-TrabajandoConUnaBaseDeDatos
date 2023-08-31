package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    /**
     * DesignPattern, es un patron de disenio llamando Factory Method,
     * tiene como objetivo encapsular el codigo de creacion de un objeto especifico
     * centralizando la logica en un solo punto
     * @return
     * @throws SQLException
     */

    private final DataSource dataSource;

    public ConnectionFactory() {
        var pooledDataSource = new ComboPooledDataSource(); // Clase de c3p0
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("root117");

        // Cantidad MAX de conexiones abiertas
        pooledDataSource.setMaxPoolSize(10);

        this.dataSource = pooledDataSource;
    }

    public Connection recuperaConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
