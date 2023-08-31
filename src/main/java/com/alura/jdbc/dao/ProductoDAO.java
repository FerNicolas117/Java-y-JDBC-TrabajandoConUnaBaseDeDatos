package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Producto;

import java.sql.*;

public class ProductoDAO {

    final private Connection connection;

    // Metodo constructor que recibe una conexion
    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardar(Producto producto) throws SQLException {
        try (connection) {
            connection.setAutoCommit(false);

            final PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO producto (nombre, descripcion, cantidad) "
                            + " VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutaRegistro(producto, statement);
                connection.commit();
                System.out.println("COMMIT!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK!");
                connection.rollback();
            }
        }
    }

    private static void ejecutaRegistro(Producto producto, PreparedStatement statement) throws SQLException {
		/*if (cantidad < 50) {
			throw new RuntimeException("Error!");
		}*/
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, producto.getCantidad());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();
        try (resultSet) {
            while (resultSet.next()) {
                producto.setId(resultSet.getInt(1));
                System.out.printf("Fue insertado el producto %s%n", producto);
            }
        }
    }

}
