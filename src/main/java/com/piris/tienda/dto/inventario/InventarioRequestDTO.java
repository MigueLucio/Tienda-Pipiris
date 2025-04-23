package com.piris.tienda.dto.inventario;

import lombok.Data;

@Data
public class InventarioRequestDTO {
	
	private Long productoId;
	private String talla;
	private String color;
	private int stock;

}
