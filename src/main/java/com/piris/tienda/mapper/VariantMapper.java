package com.piris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piris.tienda.dto.inventario.InventarioVarianteDTO;
import com.piris.tienda.model.Inventario;

@Mapper(componentModel = "spring")
public interface VariantMapper {

	@Mapping(source = "id.talla", target = "talla")
	@Mapping(source = "id.color", target = "color")
	@Mapping(source = "stock", target = "stock")
	@Mapping(source = "ultimaActualizacion", target = "ultimaActualizacion")
	InventarioVarianteDTO enVariante(Inventario inv);

}
