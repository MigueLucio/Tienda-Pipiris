package com.piris.tienda.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;
	
	private LocalDateTime fechaVenta;
	
	private double precioTotal;

	private String estado;
	
	private String tipoPago;
	
	private boolean ventaConComprobante;
	
	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private List<DetalleVenta> detalleVentas;
	
	@OneToOne(mappedBy = "venta",cascade = CascadeType.ALL)
	private Comprobante comprobante;
	
}