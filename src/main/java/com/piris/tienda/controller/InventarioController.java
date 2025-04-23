package com.piris.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piris.tienda.dto.inventario.InventarioRequestDTO;
import com.piris.tienda.dto.inventario.InventarioResponseDTO;
import com.piris.tienda.model.Inventario;
import com.piris.tienda.service.InventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {
	
	private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<InventarioResponseDTO>> listarInventariosDeProducto(@PathVariable Long productoId) {
    	
        return ResponseEntity.ok(inventarioService.getInventariosDeProducto(productoId));
    
    }

    @GetMapping
    public ResponseEntity<InventarioResponseDTO> obtenerInventario(
        @RequestParam Long productoId,
        @RequestParam String talla,
        @RequestParam String color) {
    	
        return ResponseEntity.ok(inventarioService.getInventario(productoId, talla, color));
    
    }

    @PatchMapping("/stock")
    public ResponseEntity<InventarioResponseDTO> actualizarStock(
        @RequestParam Long productoId,
        @RequestParam String talla,
        @RequestParam String color,
        @RequestParam int stock) {
    	
        return ResponseEntity.ok(inventarioService.actualizarStock(productoId, talla, color, stock));
    
    }

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crearInventario(@RequestBody @Valid InventarioRequestDTO inventarioRequestDTO) {
        
    	return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.crearInventario(inventarioRequestDTO));
    
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarInventario(
        @RequestParam Long productoId,
        @RequestParam String talla,
        @RequestParam String color) {
    	
        inventarioService.eliminarInventario(productoId, talla, color);
        return ResponseEntity.noContent().build();
        
    }

}
