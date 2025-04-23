package com.piris.tienda.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.piris.tienda.dto.ProductoRequestDTO;
import com.piris.tienda.dto.ProductoResponseDTO;
import com.piris.tienda.dto.ProductoSimpleResponseDTO;
import com.piris.tienda.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

	@Mapping(target = "categoria", ignore = true)
	@Mapping(target = "marca", ignore = true)
	Producto enModelo(ProductoRequestDTO productoRequestDTO);
	
	
	@Mapping(source = "categoria.nombre", target = "categoria")
	@Mapping(source = "marca.nombre", target = "marca")
	ProductoResponseDTO enDTO(Producto producto);
	
	
	@Named("productoSimple")
	@Mapping(source = "idProducto", target = "idProducto")
	@Mapping(source = "categoria.nombre", target = "categoria")
	@Mapping(source = "modelo", target = "modelo")
	@Mapping(source = "marca.nombre", target = "marca")
	ProductoSimpleResponseDTO enSimpleDTO(Producto producto);
	
}
