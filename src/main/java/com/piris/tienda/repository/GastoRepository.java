package com.piris.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piris.tienda.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>{

}
