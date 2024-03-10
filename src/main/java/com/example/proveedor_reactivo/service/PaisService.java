package com.example.proveedor_reactivo.service;




import com.example.proveedor_reactivo.model.Pais;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaisService {

	// Read
	public Flux<Pais> buscarTodos();
	
	public Flux<Pais> buscarPaisesQueInicienCon(String palabra);

	public Mono<Pais> buscarPorId(Long id);

	//public Optional<Pais> buscarPorIdOptional(Long id);

	// Create
	public Mono<Pais> crear(Pais pais);
	
	public Mono<Pais> agregar(String nombrePais, String capital, String codigo);

	public Mono<Void> guardar(Pais pais);

	// Update
	public Mono<Pais> actualizar(Pais pais);
	
	public Mono<Pais> actualizar(Long id, String capital);

	// Delete
	public Mono<Void> eliminar(Long id);

	public Mono<Pais> eliminarPais(Long id);

	public Mono<Pais> eliminadoLogico(Long id);

}
