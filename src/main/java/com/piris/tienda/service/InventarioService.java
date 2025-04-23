package com.piris.tienda.service;

import com.piris.tienda.dto.inventario.InventarioPorProductoDTO;
import com.piris.tienda.dto.inventario.InventarioRequestDTO;
import com.piris.tienda.dto.inventario.InventarioResponseDTO;

public interface InventarioService {
	
	InventarioPorProductoDTO getInventariosDeProducto(Long productoId);
	InventarioResponseDTO getInventario(Long productoId, String talla, String color);
	InventarioResponseDTO actualizarStock(Long productoId, String talla, String color, int nuevoStock);
    InventarioResponseDTO crearInventario(InventarioRequestDTO inventarioRequestDTO);
    void eliminarInventario(Long productoId, String talla, String color);

}
