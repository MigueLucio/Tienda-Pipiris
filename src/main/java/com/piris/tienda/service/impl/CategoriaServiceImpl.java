package com.piris.tienda.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.piris.tienda.model.Categoria;
import com.piris.tienda.repository.CategoriaRepository;
import com.piris.tienda.service.CategoriaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;

    CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


	@Cacheable(value = "categoria")
	@Override
	public List<Categoria> getAll() {
		
		return categoriaRepository.findAll();
		
	}

	@Cacheable(value = "categoria", key = "#idCategoria")
	@Override
	public Categoria getById(Long idCategoria) {
		return categoriaRepository.findById(idCategoria)
		.orElseThrow();
	}

	@CacheEvict(value = "categoria", allEntries = true)
	@Override
	public Categoria create(Categoria categoria) {
		
		categoriaRepository.save(categoria);
		return categoria;
		
	}

	@CachePut(value = "categoria", key = "#idCategoria")
	@Override
	public Categoria update(Long idCategoria, Categoria categoria) {
		
		Categoria existente = categoriaRepository.findById(idCategoria)
		.orElseThrow();
		
		existente.setNombre(categoria.getNombre());
		
		return categoriaRepository.save(existente);

	}

	@CacheEvict(value = "categoria", key = "#idCategoria")
	@Override
	public void delete(Long idCategoria) {
		
		Categoria categoria = categoriaRepository.findById(idCategoria)
		.orElseThrow();
		
		categoriaRepository.delete(categoria);
		
	}

	@Override
	public List<Categoria> getCategoriaByNombre(String nombre) {
		
		return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
		
	}
	
}
