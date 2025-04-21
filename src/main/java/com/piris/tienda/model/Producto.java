package com.piris.tienda.model;

import java.io.Serializable;
import java.util.List;

import com.piris.tienda.enumP.Publico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	
	@NotBlank
	private String modelo;
	
	@Enumerated(EnumType.STRING)
	private Publico publico;
	
	private String nombreImagen;

	@NotBlank
	@Column(unique = true)
	private String codigoBarra;
	
	@NotNull
	private double precioUnitario;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@OneToMany(mappedBy = "producto")
    private List<Inventario> inventario;
	
}