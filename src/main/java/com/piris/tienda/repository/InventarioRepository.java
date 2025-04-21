package com.piris.tienda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piris.tienda.model.Inventario;
import com.piris.tienda.model.InventarioId;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, InventarioId>{
	
	
	List<Inventario> findByIdProductoId(Long productoId);
	
	Optional<Inventario> findByIdProductoIdAndIdTallaAndIdColor(
	        Long productoId, String talla, String color
	    );
}
