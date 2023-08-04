package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer id, Integer cantidad) throws SQLException {
		Connection connection = new ConnectionFactory().recuperaConexion();
		PreparedStatement statement = connection.prepareStatement(
				"UPDATE producto SET nombre = ?, descripcion = ?, cantidad = ? WHERE id = ?"
		);

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

	public int eliminar(Integer id) throws SQLException {
		Connection connection = new ConnectionFactory().recuperaConexion();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM producto WHERE id = ?");
		statement.setInt(1, id);
		statement.execute();

		/*
		Para saber si algo fue realmente eliminado, existe el
		siguiente metodo, el cual devuelve un int
		 */
		return statement.getUpdateCount();
	}

	/**
	 * Le pasamos la responsabilidad del manejo de la excepcion en la funcion
	 * CardarTabla de ControlDeStockFrame.java usando un Try-Catch
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, String>> listar() throws SQLException {
		Connection connection = new ConnectionFactory().recuperaConexion();
		PreparedStatement statement = connection.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");
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

		connection.close();
		return resultado;
	}

    public void guardar(Map<String, String> producto) throws SQLException {
		Connection connection = new ConnectionFactory().recuperaConexion();
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO producto (nombre, descripcion, cantidad) "
						+ " VALUES (?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, producto.get("nombre"));
		statement.setString(2, producto.get("descripcion"));
		statement.setInt(3, Integer.valueOf(producto.get("cantidad")));
		statement.execute();

		ResultSet resultSet = statement.getGeneratedKeys();

		while (resultSet.next()) {
			System.out.println(String.format(
					"Fue insertado el producto de ID %d",
					resultSet.getInt(1)));
		}
	}

}
