package com.piris.tienda.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.piris.tienda.dto.InventarioRequestDTO;
import com.piris.tienda.dto.InventarioResponseDTO;
import com.piris.tienda.mapper.InventarioMapper;
import com.piris.tienda.mapper.InventarioMapperImpl;
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

    private final InventarioMapperImpl inventarioMapperImpl;

	private final InventarioRepository inventarioRepo;
	private final ProductoRepository productoRepo;
	private final InventarioMapper inventarioMapper;
	
	public InventarioServiceImpl(InventarioRepository inventarioRepo, ProductoRepository productoRepo,
			InventarioMapper inventarioMapper, InventarioMapperImpl inventarioMapperImpl) {
		this.inventarioRepo = inventarioRepo;
		this.productoRepo = productoRepo;
		this.inventarioMapper = inventarioMapper;
		this.inventarioMapperImpl = inventarioMapperImpl;
	}

	@Override
	public List<Inventario> getInventariosDeProducto(Long productoId) {
		
		return inventarioRepo.findByIdProductoId(productoId);
		
	}

	@Override
	public Inventario getInventario(Long productoId, String talla, String color) {

		return inventarioRepo.findByIdProductoIdAndIdTallaAndIdColor(productoId, talla, color)
	            .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado"));
	}

	@Override
	public InventarioResponseDTO crearInventario(InventarioRequestDTO requestDTO) {
		
		Producto p = productoRepo.findById(requestDTO.getProductoId())
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		
		Inventario inv = Inventario.of(p, requestDTO.getTalla(), requestDTO.getColor(), requestDTO.getStock());
		
		Inventario creado = inventarioRepo.save(inv);
		
		return inventarioMapper.enDTO(creado);
		
	}
	
	@Override
	public Inventario actualizarStock(Long productoId, String talla, String color, int nuevoStock) {
		
		Inventario inventario = getInventario(productoId, talla, color);
        inventario.setStock(nuevoStock);
        inventario.setUltimaActualizacion(LocalDateTime.now());
        return inventarioRepo.save(inventario);
        
	}

	@Override
	public void eliminarInventario(Long productoId, String talla, String color) {
		
		InventarioId id = new InventarioId(productoId, talla, color);
        inventarioRepo.deleteById(id);
		
	}

}
