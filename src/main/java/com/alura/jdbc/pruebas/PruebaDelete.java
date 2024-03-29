package com.alura.jdbc.pruebas;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaDelete {
    public static void main(String[] args) throws SQLException {

        Connection connection = new ConnectionFactory().recuperaConexion();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM producto WHERE id = " + 99);

		/*
		Para saber si algo fue realmente eliminado, existe el
		siguiente metodo, el cual devuelve un int
		 */
        System.out.println(statement.getUpdateCount());
    }
}
