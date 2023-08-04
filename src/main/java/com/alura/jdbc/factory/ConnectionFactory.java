package com.alura.jdbc.factory;

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
    public Connection recuperaConexion() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
                "root",
                "root117"
        );
    }
}
