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
		Statement statement = connection.createStatement();
		statement.execute("UPDATE producto SET "
			+ " nombre = '" + nombre + "'"
			+ ", descripcion = '" + descripcion + "'"
			+ ", cantidad = " + cantidad
			+ " WHERE id = " + id);
		System.out.println(nombre);
		System.out.println(id);

		int updateCount = statement.getUpdateCount();
		connection.close();
		return updateCount;
	}

	public int eliminar(Integer id) throws SQLException {
		Connection connection = new ConnectionFactory().recuperaConexion();
		Statement statement = connection.createStatement();
		statement.execute("DELETE FROM producto WHERE id = " + id);

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
		Statement statement = connection.createStatement();
		boolean result = statement.execute("SELECT id, nombre, descripcion, cantidad FROM producto");

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
		Statement statement = connection.createStatement();
		statement.execute("INSERT INTO producto (nombre, descripcion, cantidad) "
				+ " VALUES ('" + producto.get("nombre") + "','"
				+ producto.get("descripcion") + "', "
				+ producto.get("cantidad") + ")", Statement.RETURN_GENERATED_KEYS);

		ResultSet resultSet = statement.getGeneratedKeys();

		while (resultSet.next()) {
			System.out.println(String.format(
					"Fue insertado el producto de ID %d",
					resultSet.getInt(1)));
		}
	}

}
