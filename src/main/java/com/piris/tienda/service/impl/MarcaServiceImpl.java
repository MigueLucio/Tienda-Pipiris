package com.piris.tienda.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.piris.tienda.model.Marca;
import com.piris.tienda.repository.MarcaRepository;
import com.piris.tienda.service.MarcaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

	private final MarcaRepository marcaRepository;

	public MarcaServiceImpl(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}

	@Cacheable(value = "marca")
	@Override
	public List<Marca> getAll() {
		
		return marcaRepository.findAll();

	}

	@Cacheable(value = "marca", key = "#idMarca")
	@Override
	public Marca getById(Long idMarca) {

		return marcaRepository.findById(idMarca)
		.orElseThrow();

	}
	
	@CacheEvict(value = "marca", allEntries = true)
	@Override
	public Marca create(Marca marca) {
		
		marcaRepository.save(marca);
		return marca;
		
	}

	@CachePut(value = "marca", key = "#idMarca")
	@Override
	public Marca update(Long idMarca, Marca marca) {

		Marca existente = marcaRepository.findById(idMarca)
		.orElseThrow();
		
		existente.setNombre(marca.getNombre());
		existente.setContacto(marca.getContacto());
		existente.setDireccion(marca.getDireccion());
		
		return marcaRepository.save(existente);
		
	}

	@CacheEvict(value = "marca", key = "#idMarca")
	@Override
	public void delete(Long idMarca) {
		
		Marca marca = marcaRepository.findById(idMarca)
		.orElseThrow();
		
		marcaRepository.delete(marca);
		
	}

	@Override
	public List<Marca> getMarcaByNombre(String nombre) {

		return marcaRepository.findByNombreContainingIgnoreCase(nombre);

	}
	
	
	
	
}
