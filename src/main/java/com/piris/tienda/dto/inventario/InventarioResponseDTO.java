package com.piris.tienda.dto.inventario;

import java.time.LocalDateTime;

import com.piris.tienda.dto.ProductoSimpleResponseDTO;

import lombok.Data;

@Data
public class InventarioResponseDTO {

	 private ProductoSimpleResponseDTO producto;
	 private String talla;
	 private String color;
	 private int stock;
	 private LocalDateTime ultimaActualizacion;
	    
}
