package com.spring.start.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {

	@Autowired
	ClienteDAO clienteDAO;
	
	@GetMapping("/cliente")
	public ModelAndView clientes() {

		ModelAndView model = new ModelAndView();
		model.setViewName("clientes.html");
		List<Cliente> clientes = (List<Cliente>) clienteDAO.findAll();
		model.addObject("clientes", clientes);
		
		return model;
	}
}
