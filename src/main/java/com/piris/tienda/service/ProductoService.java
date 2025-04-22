package com.piris.tienda.service;

import java.util.List;

import com.piris.tienda.dto.ProductoRequestDTO;
import com.piris.tienda.dto.ProductoResponseDTO;

public interface ProductoService {
	
	List<ProductoResponseDTO> getAll();
	ProductoResponseDTO getById(Long idProducto);
	ProductoResponseDTO create(ProductoRequestDTO productoRequestDTO);
	ProductoResponseDTO update(Long idProducto, ProductoRequestDTO productoRequestDTO);
	void delete(Long idProducto);
	List<ProductoResponseDTO> getProductoByModelo(String nombre);

}
