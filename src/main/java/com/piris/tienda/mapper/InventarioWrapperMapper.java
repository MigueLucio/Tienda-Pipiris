package com.piris.tienda.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piris.tienda.dto.inventario.InventarioPorProductoDTO;
import com.piris.tienda.model.Inventario;

@Mapper(componentModel = "spring", uses = {InventarioMapper.class, ProductoMapper.class})
public interface InventarioWrapperMapper {
	
	@Mapping(target = "producto",
			source = "inventarios[0].producto",
			qualifiedByName = "productoSimple")
	@Mapping(target = "variantes",
			source = "inventarios")
	InventarioPorProductoDTO paraDto(List<Inventario> inventarios);

}
