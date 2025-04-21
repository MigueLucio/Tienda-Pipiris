package com.piris.tienda.service;

import java.util.List;

import com.piris.tienda.model.Inventario;

public interface InventarioService {
	
	List<Inventario> getInventariosDeProducto(Long productoId);
    Inventario getInventario(Long productoId, String talla, String color);
    Inventario actualizarStock(Long productoId, String talla, String color, int nuevoStock);
    Inventario crearInventario(Inventario inventario);
    void eliminarInventario(Long productoId, String talla, String color);

}
