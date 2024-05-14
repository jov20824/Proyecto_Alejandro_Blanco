package com.spring.start.plato;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.start.contiene.Contiene;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Plato {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	private String descripcion;
	
	private float precio;
	
	private String foto;
	

	@OneToMany(mappedBy="plato")
	@Cascade(CascadeType.ALL)
	@JsonManagedReference
	private Set<Contiene> contiene = new HashSet<Contiene>();
	
	
}
