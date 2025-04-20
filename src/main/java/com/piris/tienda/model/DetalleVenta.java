package com.piris.tienda.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDetalleVenta;
	
	private int cantidad;
	
	private String talla;
	
	private String color;
	
	private double precioUnitario;
	
	private double precioSubtotal;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "venta_id")
	private Venta venta;
	
	public double calcularSubtotal() {
		return cantidad * precioUnitario;
	}
	
}
