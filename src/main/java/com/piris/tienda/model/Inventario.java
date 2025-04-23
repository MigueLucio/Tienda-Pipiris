package com.piris.tienda.model;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@NotNull
	@Min(0)
	private int stock;
	
	private LocalDateTime ultimaActualizacion;
	
	@DecimalMin("0.0")
	private Double precioExtra;
	
	//Metodo factory
	public static Inventario of(Producto producto, String talla, String color, int stock) {
		
		Inventario inv = new Inventario();
		inv.id = new InventarioId(producto.getIdProducto(),talla,color);
		inv.producto = producto;
		inv.stock = stock;
		inv.ultimaActualizacion = LocalDateTime.now();
		return inv;
		
	}
	
	//comportamiento
	public void actualizarStock(int nuevoStock) {
	
		this.stock = nuevoStock;
		this.ultimaActualizacion = LocalDateTime.now();
		
	}
	
}
