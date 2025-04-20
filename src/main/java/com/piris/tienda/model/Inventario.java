package com.piris.tienda.model;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

	@EmbeddedId
	private InventarioId id;
	
	@ManyToOne
	@MapsId("productoId")
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	private int stock;
	
	private LocalDateTime ultimaActualizacion;
	
	private Double precioExtra;
	
}
