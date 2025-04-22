package com.piris.tienda.dto;

import java.math.BigDecimal;

import com.piris.tienda.enumP.Publico;

import lombok.Data;

@Data
public class ProductoResponseDTO {

	private Long idProducto;
	private String modelo;
	private Publico publico;
	private String nombreImagen;
	private String codigoBarra;
	private BigDecimal precioUnitario;
	private String categoria;
	private String marca;
	
}
