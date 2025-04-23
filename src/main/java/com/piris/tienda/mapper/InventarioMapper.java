package com.piris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piris.tienda.dto.inventario.InventarioRequestDTO;
import com.piris.tienda.dto.inventario.InventarioResponseDTO;
import com.piris.tienda.model.Inventario;

@Mapper(componentModel = "spring", uses = ProductoMapper.class)
public interface InventarioMapper {
	
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "id", ignore = true)
	Inventario enModelo(InventarioRequestDTO inventarioRequestDTO);
    
    @Mapping(target = "producto",
    		source = "producto",
    		qualifiedByName = "productoSimple")
    @Mapping(target = "talla", source = "id.talla")
    @Mapping(target = "color", source = "id.color")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "ultimaActualizacion", source = "ultimaActualizacion")
    InventarioResponseDTO enDTO(Inventario inventario);
    
}
