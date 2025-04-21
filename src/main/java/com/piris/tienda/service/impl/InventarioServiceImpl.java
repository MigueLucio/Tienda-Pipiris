package com.piris.tienda.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.piris.tienda.model.Inventario;
import com.piris.tienda.model.InventarioId;
import com.piris.tienda.model.Producto;
import com.piris.tienda.repository.InventarioRepository;
import com.piris.tienda.repository.ProductoRepository;
import com.piris.tienda.service.InventarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioServiceImpl implements InventarioService{

	private final InventarioRepository inventarioRepository;
	private final ProductoRepository productoRepository;
	
	public InventarioServiceImpl(InventarioRepository inventarioRepository, ProductoRepository productoRepository) {
		this.inventarioRepository = inventarioRepository;
		this.productoRepository = productoRepository;
	}
	
	
	@Override
	public List<Inventario> getInventariosDeProducto(Long productoId) {
		
		return inventarioRepository.findByIdProductoId(productoId);
		
	}

	@Override
	public Inventario getInventario(Long productoId, String talla, String color) {

		return inventarioRepository.findByIdProductoIdAndIdTallaAndIdColor(productoId, talla, color)
	            .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado"));
	}

	@Override
	public Inventario crearInventario(Inventario inventario) {
		
		Long productoId = inventario.getProducto().getIdProducto();
		
		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		inventario.setProducto(producto);
		inventario.setUltimaActualizacion(LocalDateTime.now());
		
		return inventarioRepository.save(inventario);
		
	}
	
	@Override
	public Inventario actualizarStock(Long productoId, String talla, String color, int nuevoStock) {
		
		Inventario inventario = getInventario(productoId, talla, color);
        inventario.setStock(nuevoStock);
        inventario.setUltimaActualizacion(LocalDateTime.now());
        return inventarioRepository.save(inventario);
        
	}

	@Override
	public void eliminarInventario(Long productoId, String talla, String color) {
		
		InventarioId id = new InventarioId(productoId, talla, color);
        inventarioRepository.deleteById(id);
		
	}

}
