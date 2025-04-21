package com.piris.tienda.service;

import java.util.List;

import com.piris.tienda.model.Marca;

public interface MarcaService {

	List<Marca> getAll();
	Marca getById(Long idMarca);
	Marca create(Marca marca);
	Marca update(Long idMarca, Marca marca);
	void delete(Long idMarca);
	List<Marca> getMarcaByNombre(String nombre);
	
}
