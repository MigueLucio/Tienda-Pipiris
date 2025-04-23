package com.piris.tienda.dto;

import lombok.Data;

@Data
public class ProductoSimpleResponseDTO {
	
	private Long idProducto;
	private String categoria;
	private String modelo;
	private String marca;

}
