package com.piris.tienda.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEmpleado;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String dni;
	
	@Column(unique = true)
	@NotBlank
	private String codigoEmpleado;
	
	@OneToMany(mappedBy = "empleado")
	private List<Venta> ventas; 
	
	@OneToMany(mappedBy = "empleado")
	private List<Gasto> gastos;
	
}
