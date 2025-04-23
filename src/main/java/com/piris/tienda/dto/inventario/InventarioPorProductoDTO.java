package com.piris.tienda.dto.inventario;

import java.util.List;

import com.piris.tienda.dto.ProductoSimpleResponseDTO;

import lombok.Data;

@Data
public class InventarioPorProductoDTO {

	private ProductoSimpleResponseDTO producto;
	private List<InventarioVarianteDTO> inventario;
	
}
