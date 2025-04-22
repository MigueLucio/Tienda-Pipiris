package com.piris.tienda.dto;

import java.math.BigDecimal;

import com.piris.tienda.enumP.Publico;
import lombok.Data;

@Data
public class ProductoRequestDTO {

	private String modelo;
	private Publico publico;
	private String nombreImagen;
	private String codigoBarra;
	private BigDecimal precioUnitario;
	private Long categoria;
	private Long marca;
	
}
