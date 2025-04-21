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

import com.piris.tienda.model.Producto;
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
	public ResponseEntity<List<Producto>> listarProductos(){
		
		return ResponseEntity.ok(productoService.getAll());
		
	}
	
	@GetMapping("/{idProducto}")
	public ResponseEntity<Producto> obtenerById(@PathVariable Long idProducto){
		
		return ResponseEntity.ok(productoService.getById(idProducto));
		
	}
	
	@PostMapping
	public ResponseEntity<Producto> crear(@RequestBody @Valid Producto producto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.create(producto));
		
	}
	
	@PutMapping("/{idProducto}")
	public ResponseEntity<Producto> editar(@PathVariable Long idProducto, @RequestBody @Valid Producto producto){
		
		return ResponseEntity.ok(productoService.update(idProducto, producto));
		
	}
	
	@DeleteMapping("/{idProducto}")
	public ResponseEntity<Producto> eliminar(@PathVariable Long idProducto){
		
		productoService.delete(idProducto);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Producto>> buscarProductoByModelo(@RequestParam String modelo){
		
		List<Producto> productos = productoService.getProductoByModelo(modelo);
		return ResponseEntity.ok(productos);
		
	}
	

}
