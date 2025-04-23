package com.piris.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piris.tienda.dto.ProductoRequestDTO;
import com.piris.tienda.dto.ProductoResponseDTO;
import com.piris.tienda.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductoResponseDTO>> listarProductos(){
		
		return ResponseEntity.ok(productoService.getAll());
		
	}
	
	@GetMapping("/{idProducto}")
	public ResponseEntity<ProductoResponseDTO> obtenerById(@PathVariable Long idProducto){
		
		return ResponseEntity.ok(productoService.getById(idProducto));
		
	}
	
	@PostMapping
	public ResponseEntity<ProductoResponseDTO> crear(@RequestBody @Valid ProductoRequestDTO productoRequestDTO){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.create(productoRequestDTO));
		
	}
	
	@PutMapping("/{idProducto}")
	public ResponseEntity<ProductoResponseDTO> editar(@PathVariable Long idProducto, @RequestBody @Valid ProductoRequestDTO productoRequestDTO){
		
		return ResponseEntity.ok(productoService.update(idProducto, productoRequestDTO));
		
	}
	
	@DeleteMapping("/{idProducto}")
	public ResponseEntity<ProductoResponseDTO> eliminar(@PathVariable Long idProducto){
		
		productoService.delete(idProducto);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductoResponseDTO>> buscarProductoByModelo(@RequestParam String modelo){
		
		List<ProductoResponseDTO> productos = productoService.getProductoByModelo(modelo);
		return ResponseEntity.ok(productos);
		
	}
	
}
