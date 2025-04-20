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
@Table(name = "gasto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gasto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idGastos;
	
	private String descripcion;
	
	private double gasto;
	
	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;

}