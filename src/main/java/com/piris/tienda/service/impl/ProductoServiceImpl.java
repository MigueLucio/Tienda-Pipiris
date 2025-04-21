package com.piris.tienda.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.piris.tienda.model.Producto;
import com.piris.tienda.repository.ProductoRepository;
import com.piris.tienda.service.ProductoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{

	private final ProductoRepository productoRepository;
	
	public ProductoServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	@Cacheable(value = "producto")
	@Override
	public List<Producto> getAll() {
		
		return productoRepository.findAll();
		
	}

	@Cacheable(value = "producto", key = "#idProducto")
	@Override
	public Producto getById(Long idProducto) {
		
		return productoRepository.findById(idProducto)
		.orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
		
	}

	@CacheEvict(value = "producto", allEntries = true)
	@Override
	public Producto create(Producto producto) {
		
		productoRepository.save(producto);
		return producto;
		
	}

	@CachePut(value = "producto", key = "#idProducto")
	@Override
	public Producto update(Long idProducto, Producto producto) {
		
		Producto existente = productoRepository.findById(idProducto)
		.orElseThrow();
		
		existente.setModelo(producto.getModelo());
		existente.setPublico(producto.getPublico());
		existente.setNombreImagen(producto.getNombreImagen());
		existente.setCodigoBarra(producto.getCodigoBarra());
		existente.setPrecioUnitario(producto.getPrecioUnitario());
		existente.setCategoria(producto.getCategoria());
		existente.setMarca(producto.getMarca());
		existente.setInventario(producto.getInventario());
		
		return productoRepository.save(existente);
		
	}

	
	@Override
	public void delete(Long idProducto) {
		
		Producto producto = productoRepository.findById(idProducto)
		.orElseThrow();
		
		productoRepository.delete(producto);
		
	}

	@Override
	public List<Producto> getProductoByModelo(String modelo) {
		
		return productoRepository.findByModeloContainingIgnoreCase(modelo);
		
	}

}
