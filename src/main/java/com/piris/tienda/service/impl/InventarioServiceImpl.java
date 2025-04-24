package com.piris.tienda.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.piris.tienda.dto.ProductoSimpleResponseDTO;
import com.piris.tienda.dto.inventario.InventarioPorProductoDTO;
import com.piris.tienda.dto.inventario.InventarioRequestDTO;
import com.piris.tienda.dto.inventario.InventarioResponseDTO;
import com.piris.tienda.dto.inventario.InventarioVarianteDTO;
import com.piris.tienda.mapper.InventarioMapper;
import com.piris.tienda.mapper.InventarioWrapperMapper;
import com.piris.tienda.mapper.ProductoMapper;
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
	private final ProductoMapper productoMapper;
	private final InventarioWrapperMapper inventarioWrapperMapper;
	
	public InventarioServiceImpl(InventarioRepository inventarioRepo, ProductoRepository productoRepo,
			InventarioMapper inventarioMapper, ProductoMapper productoMapper, InventarioWrapperMapper inventarioWrapperMapper) {
		this.inventarioRepo = inventarioRepo;
		this.productoRepo = productoRepo;
		this.inventarioMapper = inventarioMapper;
		this.productoMapper = productoMapper;
		this.inventarioWrapperMapper = inventarioWrapperMapper;
	}

	@Override
	public InventarioPorProductoDTO getInventariosDeProducto(Long productoId) {
		
		List<Inventario> invs = inventarioRepo.findByIdProductoId(productoId);
		
		if(invs.isEmpty()) 
			throw new EntityNotFoundException("No hay inventario para el producto con id " + productoId );
		
		return inventarioWrapperMapper.paraDto(invs);
		
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
