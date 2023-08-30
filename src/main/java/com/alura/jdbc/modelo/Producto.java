package com.alura.jdbc.modelo;

public class Producto {

    /**
     * Este modelo representa a la tabla en la aplicacion Java.
     * Debe de tener los mismos campos de la tabla
     */

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;

    // Metodo Constructor con parametros
    public Producto(String nombre, String descripcion, Integer cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
          "{id: %s, nombre: %s, descripcion: %s, cantidad: %d}",
                this.id,
                this.nombre,
                this.descripcion,
                this.cantidad
        );
    }
}
