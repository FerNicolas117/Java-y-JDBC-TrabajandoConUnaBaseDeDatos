package com.alura.jdbc.dao;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoDAO {

    final private Connection connection;

    // Metodo constructor que recibe una conexion
    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardar(Producto producto) {
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO producto (nombre, descripcion, cantidad) "
                            + " VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutaRegistro(producto, statement);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
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

    public List<Producto> listar() {
        List<Producto> resultado = new ArrayList<>();

        final Connection connection = new ConnectionFactory().recuperaConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");

            try (statement) {
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        Producto fila = new Producto(resultSet.getInt("ID"),
                                resultSet.getString("nombre"),
                                resultSet.getString("descripcion"),
                                resultSet.getInt("cantidad")
                                );
                        resultado.add(fila);
                    }
                }
            }
            return resultado;
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE producto SET nombre = ?, descripcion = ?, cantidad = ? WHERE id = ?"
            );

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM producto WHERE id = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                // Para saber si algo fue realmente eliminado, existe el siguiente metodo, el cual devuelve un int
                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
