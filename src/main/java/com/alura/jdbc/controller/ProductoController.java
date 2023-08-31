package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.dao.ProductoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

    public int modificar(String nombre, String descripcion, Integer id, Integer cantidad) throws SQLException {
        final Connection connection = new ConnectionFactory().recuperaConexion();
        try (connection) {

            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE producto SET nombre = ?, descripcion = ?, cantidad = ? WHERE id = ?"
            );
            try (statement) {

                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);

                statement.execute();
                System.out.println(nombre);
                System.out.println(id);

                int updateCount = statement.getUpdateCount();
                connection.close();
                return updateCount;
            }
        }
    }

    public int eliminar(Integer id) throws SQLException {
        final Connection connection = new ConnectionFactory().recuperaConexion();
        try (connection) {

            final PreparedStatement statement = connection.prepareStatement("DELETE FROM producto WHERE id = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                /*
                Para saber si algo fue realmente eliminado, existe el
                siguiente metodo, el cual devuelve un int
                 */
                return statement.getUpdateCount();
            }
        }
    }

    /**
     * Le pasamos la responsabilidad del manejo de la excepcion en la funcion
     * CardarTabla de ControlDeStockFrame.java usando un Try-Catch
     *
     * @return
     * @throws SQLException
     */
    public List<Map<String, String>> listar() throws SQLException {
        final Connection connection = new ConnectionFactory().recuperaConexion();
        try (connection) {

            final PreparedStatement statement = connection.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");
            try (statement) {
                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                List<Map<String, String>> resultado = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, String> fila = new HashMap<>();
                    fila.put("id", String.valueOf(resultSet.getInt("id")));
                    fila.put("nombre", resultSet.getString("nombre"));
                    fila.put("descripcion", resultSet.getString("descripcion"));
                    fila.put("cantidad", String.valueOf(resultSet.getInt("cantidad")));
                    resultado.add(fila);
                }
                return resultado;
            }
        }
    }

    public void guardar(Producto producto) throws SQLException {
        ProductoDAO productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
        productoDAO.guardar(producto);
    }

}
