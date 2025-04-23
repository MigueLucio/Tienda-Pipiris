package com.piris.tienda.dto.inventario;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InventarioVarianteDTO {

	private String talla;
	private String color;
	private int stock;
	private LocalDateTime ultimaActualizacion;
	
}
