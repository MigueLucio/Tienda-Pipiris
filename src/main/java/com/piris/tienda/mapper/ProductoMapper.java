package com.piris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piris.tienda.dto.ProductoRequestDTO;
import com.piris.tienda.dto.ProductoResponseDTO;
import com.piris.tienda.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

	@Mapping(target = "categoria", ignore = true)
	@Mapping(target = "marca", ignore = true)
	Producto enModelo(ProductoRequestDTO productoRequestDTO);
	
	
	@Mapping(source = "categoria.nombre", target = "categoria")
	@Mapping(source = "marca.nombre", target = "marca")
	ProductoResponseDTO enDTO(Producto producto);
	
}
