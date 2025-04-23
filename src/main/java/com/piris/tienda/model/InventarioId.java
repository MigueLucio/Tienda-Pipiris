package com.piris.tienda.model;

import java.util.Objects;


import jakarta.persistence.Embeddable;

@Embeddable
public class InventarioId {
	
	private Long productoId;
	
	private String talla;
	
	private String color;

	@Override
	public int hashCode() {
		return Objects.hash(color, productoId, talla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventarioId other = (InventarioId) obj;
		return Objects.equals(color, other.color) && Objects.equals(productoId, other.productoId)
				&& Objects.equals(talla, other.talla);
	}

	public InventarioId(Long productoId, String talla, String color) {
		this.productoId = productoId;
		this.talla = talla;
		this.color = color;
	}

	public InventarioId() {
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}