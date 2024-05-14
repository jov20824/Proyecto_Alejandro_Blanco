package com.spring.start.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

	@Autowired
	ClienteDAO clienteDAO;
	
	@GetMapping("/cliente")
	public List<Cliente> clientes() {

		List<Cliente> clientes = (List<Cliente>) clienteDAO.findAll();
		
		return clientes;
	}
}
