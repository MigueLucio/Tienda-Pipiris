package com.piris.tienda.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InventarioResponseDTO {

	 private Long productoId;
	 private String modeloProducto;
	 private String talla;
	 private String color;
	 private int stock;
	 private LocalDateTime ultimaActualizacion;
	    
}
