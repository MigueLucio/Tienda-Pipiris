package com.piris.tienda.service;

import java.util.List;

import com.piris.tienda.model.Producto;

public interface ProductoService {
	
	List<Producto> getAll();
	Producto getById(Long idProducto);
	Producto create(Producto producto);
	Producto update(Long idProducto, Producto producto);
	void delete(Long idProducto);
	List<Producto> getProductoByModelo(String nombre);

}
