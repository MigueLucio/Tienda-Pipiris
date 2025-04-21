package com.piris.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piris.tienda.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

}
