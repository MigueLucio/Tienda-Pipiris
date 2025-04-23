package com.piris.tienda.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.piris.tienda.dto.inventario.InventarioRequestDTO;
import com.piris.tienda.dto.inventario.InventarioResponseDTO;
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
	
	private final InventarioRepository inventarioRepo;
	private final ProductoRepository productoRepo;
	private final InventarioMapper inventarioMapper;
	
	public InventarioServiceImpl(InventarioRepository inventarioRepo, ProductoRepository productoRepo,
			InventarioMapper inventarioMapper) {
		this.inventarioRepo = inventarioRepo;
		this.productoRepo = productoRepo;
		this.inventarioMapper = inventarioMapper;
	}

	@Override
	public List<InventarioResponseDTO> getInventariosDeProducto(Long productoId) {
		
		List<Inventario> inventarios = inventarioRepo.findByIdProductoId(productoId);
		return	inventarios.stream()
				.map(inventarioMapper::enDTO)
				.collect(Collectors.toList());
				
	}

	@Override
	public InventarioResponseDTO getInventario(Long productoId, String talla, String color) {

		Inventario inv = inventarioRepo.findByIdProductoIdAndIdTallaAndIdColor(productoId, talla, color)
        .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado"));
		
		return inventarioMapper.enDTO(inv);
	}

	@Override
	public InventarioResponseDTO crearInventario(InventarioRequestDTO requestDTO) {
		
		Producto p = productoRepo.findById(requestDTO.getProductoId())
				.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		Inventario inv = Inventario.of(p, requestDTO.getTalla(), requestDTO.getColor(), requestDTO.getStock());
		
		inv = inventarioRepo.save(inv);
		
		return inventarioMapper.enDTO(inv);
		
	}
	
	@Override
	public InventarioResponseDTO actualizarStock(Long productoId, String talla, String color, int nuevoStock) {
		
		Inventario inv = findInventario(productoId, talla, color);
		inv.setStock(nuevoStock);
		inv.setUltimaActualizacion(LocalDateTime.now());
		inv = inventarioRepo.save(inv);
		
		return inventarioMapper.enDTO(inv);
		
	}

	@Override
	public void eliminarInventario(Long productoId, String talla, String color) {
		
		InventarioId id = new InventarioId(productoId, talla, color);
        inventarioRepo.deleteById(id);
		
	}
	
	//METODOS INTERNOS
	
	//METODO PARA BUSCAR EL INVENTARIO
	private Inventario findInventario(Long productoId, String talla, String color) {
	    return inventarioRepo.findByIdProductoIdAndIdTallaAndIdColor(productoId, talla, color)
	        .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado"));
	}
	

}
