package com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private Integer id;
    private String nombre;

    // Una categoria puede tener muchos produtos
    private List<Producto> productos;

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getID() {
        return this.id;
    }

    // Sobreescritura del meotodo ToString
    @Override
    public String toString() {
        return this.nombre;
    }

    public void agregar(Producto producto) {
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }

        this.productos.add(producto);
    }

    public List<Producto> getProductos() {
        return this.productos;
    }
}
