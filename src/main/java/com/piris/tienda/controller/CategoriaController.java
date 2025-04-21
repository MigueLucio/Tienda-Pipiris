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

import com.piris.tienda.model.Categoria;
import com.piris.tienda.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private final CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		
		return ResponseEntity.ok(categoriaService.getAll());
		
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<Categoria> obtenerById(@PathVariable Long idCategoria){
		
		return ResponseEntity.ok(categoriaService.getById(idCategoria));
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> crear(@RequestBody @Valid Categoria categoria){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.create(categoria));
		
	}
	
	@PutMapping("/{idCategoria}")
	public ResponseEntity<Categoria> editar(@PathVariable Long idCategoria, @RequestBody @Valid Categoria categoria){
		
		return ResponseEntity.ok(categoriaService.update(idCategoria,categoria));
		
	}
	
	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<Void> eliminar(@PathVariable Long idCategoria){
		
		categoriaService.delete(idCategoria);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Categoria>> buscarCategoriaByNombre(@RequestParam String nombre){
		
		List<Categoria> categorias = categoriaService.getCategoriaByNombre(nombre);
		
		return ResponseEntity.ok(categorias);
		
	}
	
}
