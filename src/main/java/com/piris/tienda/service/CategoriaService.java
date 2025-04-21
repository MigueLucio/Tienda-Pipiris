package com.piris.tienda.service;

import java.util.List;

import com.piris.tienda.model.Categoria;

public interface CategoriaService {
	
	List<Categoria> getAll();
	Categoria getById(Long idCategoria);
	Categoria create(Categoria categoria);
	Categoria update(Long idCategoria, Categoria categoria);
	void delete(Long idCategoria);
	List<Categoria> getCategoriaByNombre(String nombre);

}
