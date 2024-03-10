package com.example.proveedor_reactivo.model;

import java.io.Serializable;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "paises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String pais;
	
	private String capital;
	
	private String codigo;

}
