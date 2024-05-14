package com.spring.start.contiene;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.start.pedido.Pedido;
import com.spring.start.plato.Plato;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contiene {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	@JsonBackReference
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="plato_id")
	@JsonBackReference
	private Plato plato;
	
	private int cantidad;

	
}
