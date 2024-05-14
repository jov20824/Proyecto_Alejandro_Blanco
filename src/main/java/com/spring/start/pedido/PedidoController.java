package com.spring.start.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.start.cliente.ClienteDAO;
import com.spring.start.contiene.Contiene;
import com.spring.start.contiene.ContieneDAO;
import com.spring.start.plato.PlatoDAO;

@RestController
public class PedidoController {

	@Autowired PedidoDAO pedidoDAO;
	@Autowired PlatoDAO platoDAO;
	@Autowired ClienteDAO clienteDAO;
	@Autowired ContieneDAO contieneDAO;
	
	@GetMapping("/pedido")
	public List<Pedido> pedidos() {
		return (List<Pedido>) pedidoDAO.findAll();
	}
	
	@GetMapping("/pedido/{id}")
	public Pedido pedido(@PathVariable long id) {
		return pedidoDAO.findById(id).get();
	}
	
	@GetMapping("/pedido/add")
	public Pedido addPedido() {
		return new Pedido();
	}	
	
	@PostMapping("/pedido/save")
	public Pedido formPedido(@ModelAttribute Pedido pedido) {
		Pedido pedidoGuardado = pedidoDAO.save(pedido);
		
		for (Contiene contiene: pedido.getContiene()) {
	        contiene.setPedido(pedidoGuardado);
	        contieneDAO.save(contiene);
	    }
		
		return pedidoGuardado;
	}	
	
	@PutMapping("/pedido/edit/{id}")
	public Pedido editPedido(@PathVariable long id) {
		Optional<Pedido> platin = pedidoDAO.findById(id);
		
		if(platin.isPresent()) {
			Pedido pedido = platin.get();
			pedidoDAO.save(pedido);
			return pedido;
		}
		else return null;
	}
	
	@DeleteMapping("/pedido/del/{id}")
	public void delPedido(@PathVariable long id) {
		pedidoDAO.deleteById(id);
	}
}
