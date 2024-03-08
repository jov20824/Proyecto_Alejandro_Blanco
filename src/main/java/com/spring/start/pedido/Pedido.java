package com.spring.start.pedido;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.start.cliente.Cliente;
import com.spring.start.contiene.Contiene;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String horaRealizado;
	
	private String horaReserva;
	
	
	@OneToMany(mappedBy="pedido")
	@Cascade(CascadeType.ALL)
	@JsonManagedReference
	private Set<Contiene> contiene = new HashSet<Contiene>();
	
	@ManyToOne
	@JoinColumn(name="FK_CLIENTE")
	private Cliente cliente;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHoraRealizado() {
		return horaRealizado;
	}

	public void setHoraRealizado(String horaRealizado) {
		this.horaRealizado = horaRealizado;
	}

	public String getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}

	public Set<Contiene> getContiene() {
		return contiene;
	}

	public void setContiene(Set<Contiene> contiene) {
		this.contiene = contiene;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
