package com.piris.tienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.piris.tienda.dto.ProductoRequestDTO;
import com.piris.tienda.dto.ProductoResponseDTO;
import com.piris.tienda.mapper.ProductoMapper;
import com.piris.tienda.model.Categoria;
import com.piris.tienda.model.Marca;
import com.piris.tienda.model.Producto;
import com.piris.tienda.repository.CategoriaRepository;
import com.piris.tienda.repository.MarcaRepository;
import com.piris.tienda.repository.ProductoRepository;
import com.piris.tienda.service.ProductoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{

	private final ProductoRepository productoRepository;
	private final ProductoMapper productoMapper;
	private final CategoriaRepository categoriaRepository;
	private final MarcaRepository marcaRepository;
	
	
	
	public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper,
			CategoriaRepository categoriaRepository, MarcaRepository marcaRepository) {
		this.productoRepository = productoRepository;
		this.productoMapper = productoMapper;
		this.categoriaRepository = categoriaRepository;
		this.marcaRepository = marcaRepository;
	}

	@Cacheable(value = "producto")
	@Override
	public List<ProductoResponseDTO> getAll() {
		
		return productoRepository.findAll()
				.stream()
				.map(productoMapper::enDTO)
				.collect(Collectors.toList());
		
	}

	@Cacheable(value = "producto", key = "#idProducto")
	@Override
	public ProductoResponseDTO getById(Long idProducto) {
		
		Producto producto = productoRepository.findById(idProducto)
		.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		ProductoResponseDTO productoDTO = productoMapper.enDTO(producto);
		
		return productoDTO;
		
	}

	@CacheEvict(value = "producto", allEntries = true)
	@Override
	public ProductoResponseDTO create(ProductoRequestDTO productoRequestDTO) {
		
		Producto producto = productoMapper.enModelo(productoRequestDTO);
		
		Categoria categoria = categoriaRepository.findById(productoRequestDTO.getCategoria())
				.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		Marca marca = marcaRepository.findById(productoRequestDTO.getMarca())
				.orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
		
		producto.setCategoria(categoria);
		producto.setMarca(marca);
		
		Producto creado = productoRepository.save(producto);
		
		return productoMapper.enDTO(creado);
		
	}

	
	@CachePut(value = "producto", key = "#idProducto")
	@Override
	public ProductoResponseDTO update(Long idProducto, ProductoRequestDTO productoRequestDTO) {
		
		Producto existente = productoRepository.findById(idProducto)
		.orElseThrow();
		
		existente.setModelo(productoRequestDTO.getModelo());
		existente.setPublico(productoRequestDTO.getPublico());
		existente.setNombreImagen(productoRequestDTO.getNombreImagen());
		existente.setCodigoBarra(productoRequestDTO.getCodigoBarra());
		existente.setPrecioUnitario(productoRequestDTO.getPrecioUnitario());
		
		Categoria categoria = categoriaRepository.findById(productoRequestDTO.getCategoria())
				.orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
		
		Marca marca = marcaRepository.findById(productoRequestDTO.getMarca())
				.orElseThrow(() -> new EntityNotFoundException("Marca no encontrada"));
		
		existente.setCategoria(categoria);
		existente.setMarca(marca);

		Producto actualizado = productoRepository.save(existente);
		
		return productoMapper.enDTO(actualizado);
		
	}

	
	@Override
	public void delete(Long idProducto) {
		
		Producto producto = productoRepository.findById(idProducto)
		.orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
		
		productoRepository.delete(producto);
		
	}

	@Override
	public List<ProductoResponseDTO> getProductoByModelo(String modelo) {
		
		return productoRepository.findByModeloContainingIgnoreCase(modelo)
				.stream()
				.map(productoMapper::enDTO)
				.collect(Collectors.toList());
	}

}
