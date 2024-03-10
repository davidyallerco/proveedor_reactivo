package com.example.proveedor_reactivo.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proveedor_reactivo.model.Pais;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class PaisServiceImpl implements PaisService{
	
	private static List<Pais> paises = new ArrayList<>(List.of(
			new Pais(1L,"Bolivia", "La Paz", "BO"),
			new Pais(2L,"Perú", "Lima", "PE"),
			new Pais(3L,"Argentina", "Buenos Aires", "AR"),
			new Pais(4L,"Brazil", "Brasilia", "BR"),
			new Pais(5L,"Colombia", "Bogotá", "CO"),
			new Pais(6L,"México", "Ciudad de México", "MX"),
			new Pais(7L,"Chile", "Santiago", "CL"),
			new Pais(8L,"Venezuela", "Caracas", "VE"),
			new Pais(9L,"Ecuador", "Quito", "EC")));

	

	

	
	
	
	

	//********* listar ************************
	
	@Override
	public Flux<Pais> buscarTodos() {
		// TODO Auto-generated method stub
		return Flux.fromIterable(paises)
				.delayElements(Duration.ofMillis(500));
	}
	
	@Override
	public Flux<Pais> buscarPaisesQueInicienCon(String palabra){
		return buscarTodos()
				.filter(p -> p.getPais().startsWith(palabra));
	}

	@Override
	public Mono<Pais> buscarPorId(Long id) {
		return buscarTodos() //Flux<Pais>
				.filter(p->p.getId()== id)//Flux<Pais>
				.next();//Mono<Pais>
				//.switchIfEmpty(Mono.just(new Pais()));//en caso este vacio devolver otro valor  por defecto
	}

	//************crear ********************
	@Override
	public Mono<Pais> crear(Pais pais) {
		return null;
		
	}

	@Override
	public Mono<Pais> agregar(String nombrePais, String capital, String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> guardar(Pais pais) {
		return buscarPorId(pais.getId())//Mono<Pais>
				.switchIfEmpty(Mono.just(pais).map(p->{
					paises.add(pais);
					return p;
				}))//Mono<Pais>
				.then();//Mono<Void>
	}
	
	//**********actualizar ***************
	
	@Override
	public Mono<Pais> actualizar(Pais pais) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Mono<Pais> actualizar(Long id, String capital) {
		return buscarPorId(id)//Mono<Pais>
				.map(p->{
					p.setCapital(capital);
					return p;
				});//Mono<Pais>
	}

	
	


	//*************Eliminar****************
	

	@Override
	public Mono<Void> eliminar(Long id) {
		 //este codigo me funciona , lo saque de chatgpt
		 return Mono.fromRunnable(() -> paises.removeIf(pais -> pais.getId().equals(id)));
	
	}

	@Override
	public Mono<Pais> eliminarPais(Long id) {
		return buscarPorId(id)
				.map(p->{
					paises.removeIf(q->q.getId()== id);
					return p;
				});//Mono<Pais>
				//.switchIfEmpty(null));
	}

	@Override
	public Mono<Pais> eliminadoLogico(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
