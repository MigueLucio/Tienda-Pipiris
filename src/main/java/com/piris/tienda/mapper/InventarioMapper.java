package com.piris.tienda.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piris.tienda.dto.InventarioRequestDTO;
import com.piris.tienda.dto.InventarioResponseDTO;
import com.piris.tienda.model.Inventario;

@Mapper(componentModel = "spring")
public interface InventarioMapper {
	
    @Mapping(target = "producto", ignore = true) 
	Inventario enModelo(InventarioRequestDTO inventarioRequestDTO);
    
    @Mapping(source = "producto.idProducto", target = "productoId")
    @Mapping(source = "producto.modelo", target = "modeloProducto")
    InventarioResponseDTO enDTO(Inventario inventario);
    
}
