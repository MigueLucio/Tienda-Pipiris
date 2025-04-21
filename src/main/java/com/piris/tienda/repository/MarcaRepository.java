package com.piris.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piris.tienda.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{

	List<Marca> findByNombreContainingIgnoreCase(String nombre);
	
}
