package com.piris.tienda.service;

import java.util.List;

import com.piris.tienda.dto.inventario.InventarioRequestDTO;
import com.piris.tienda.dto.inventario.InventarioResponseDTO;
import com.piris.tienda.model.Inventario;

public interface InventarioService {
	
	List<InventarioResponseDTO> getInventariosDeProducto(Long productoId);
	InventarioResponseDTO getInventario(Long productoId, String talla, String color);
	InventarioResponseDTO actualizarStock(Long productoId, String talla, String color, int nuevoStock);
    InventarioResponseDTO crearInventario(InventarioRequestDTO inventarioRequestDTO);
    void eliminarInventario(Long productoId, String talla, String color);

}
