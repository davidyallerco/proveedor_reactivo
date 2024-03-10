package com.example.proveedor_reactivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.example.proveedor_reactivo.model.Pais;
import com.example.proveedor_reactivo.service.PaisService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//aplicando ResponseEntity

@RestController
@RequestMapping("/pais2")
public class Pais2Controller {

	@Autowired
	private PaisService paisService;

	// *************** Read ****************************

	// http://localhost:8034/pais/listar
	@GetMapping("/listar")
	public ResponseEntity<Flux<Pais>> buscarTodos() {
		return new ResponseEntity<>(paisService.buscarTodos(),HttpStatus.OK) ;
	}
	
	// http://localhost:8034/pais/listar/B
	@GetMapping("/listar/{palabra}")
	public  ResponseEntity<Flux<Pais>> buscarPaisesQueInicienCon(@PathVariable String palabra) {
		return new ResponseEntity<>(paisService.buscarPaisesQueInicienCon(palabra),HttpStatus.OK) ;
	}

	// http://localhost:8030/proveedor/pais/2
	@GetMapping("/{id}")
	public ResponseEntity<Mono<Pais>> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<>(paisService.buscarPorId(id),HttpStatus.OK) ;
	}

	// http://localhost:8030/proveedor/pais/buscar/2
//	@GetMapping(path = "/buscar/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
//	public Pais buscarPorId2(@PathVariable("id") Long id) {
//		return paisService.buscarPorIdOptional(id).orElse(null);
//	}

	// http://localhost:8030/proveedor/pais/2
	@GetMapping("/buscar")
	public Mono<Pais> buscarPorId3(@RequestParam Long id) {
		return paisService.buscarPorId(id);
	}

	// ************** Create *********************
	
	//http://localhost:8030/proveedor/pais/agregar/EEUU/Washinton/US
	@GetMapping("/agregar/{nombrePais}/{capital}/{codigo}"/* path="", consumes={MediaType.APPLICATION_JSON_VALUE} */)
	public Mono<Pais> agregar(
			@PathVariable("nombrePais") String nombrePais, 
			@PathVariable("capital") String capital,
			@PathVariable("codigo") String codigo
			){
		return paisService.agregar(nombrePais, capital,codigo);
	}

	//http://localhost:8030/proveedor/pais/crear
	/** ingresar en body>raw> json : {
	"pais":"EEUU","capital":"Washinton","codigo":"US"} **/
	@PostMapping("/crear")
	public ResponseEntity<Mono<Pais>> add(@RequestBody Pais pais) {
		return new ResponseEntity<>( paisService.crear(pais), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<Mono<Void>> guardar(@RequestBody Pais pais) {
		return new ResponseEntity<>(paisService.guardar(pais), HttpStatus.OK);
	}
	// ************** Update *******************
	
	//  http://localhost:8030/proveedor/pais/3
	/**  
	{
	"pais":"PaisNuevo",
    "capital":"CapitalNueva",
    "codigo":"CP"
	}
	**/
	@PutMapping("/{id}")
	public Mono<Pais> actualizar(@PathVariable("id") Long id, @RequestBody Pais pais) {
		pais.setId(id);
		return paisService.actualizar(pais);
	}
	
	@PutMapping("/actualizar")
	public Mono<ResponseEntity<Pais>> actualizar(@RequestParam Long id,@RequestParam String capital) {
		return paisService.actualizar(id, capital)
				.map(p-> new ResponseEntity<>(p, HttpStatus.OK))
				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
	}

	// ************* Delete *****************************

	// http://localhost:8034/pais/eliminar/1
	@DeleteMapping("/eliminar/{id}")
	public Mono<Void> eliminar(@PathVariable Long id) {
		return paisService.eliminar(id);
	}

	// http://localhost:8034/pais/eliminar2/9L
	@DeleteMapping("/eliminar2/{id}")
	public Mono<ResponseEntity<Pais>> eliminarPais(@PathVariable("id") Long id) {
		return paisService.eliminarPais(id)  //Mono<Pais>
				.map(p-> new ResponseEntity<>(p, HttpStatus.OK))
				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));//Mono<ResponseEntity<Pais>>
		
		
	}
	

}
