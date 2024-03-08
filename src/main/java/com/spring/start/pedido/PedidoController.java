package com.spring.start.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.start.cliente.ClienteDAO;
import com.spring.start.contiene.Contiene;
import com.spring.start.contiene.ContieneDAO;
import com.spring.start.plato.PlatoDAO;

@Controller
public class PedidoController {

	@Autowired PedidoDAO pedidoDAO;
	@Autowired PlatoDAO platoDAO;
	@Autowired ClienteDAO clienteDAO;
	@Autowired ContieneDAO contieneDAO;
	
	@GetMapping("/pedido")
	public ModelAndView tutorias() {

		ModelAndView model = new ModelAndView();
		model.setViewName("pedidos");
		
		List<Pedido> pedidos = (List<Pedido>) pedidoDAO.findAll();
		model.addObject("pedidos", pedidos);
		
		return model;
	}
	

	
	
	
	@GetMapping("/pedido/{id}")
	public ModelAndView pedido(@PathVariable long id) {
		
		Pedido pedido = pedidoDAO.findById(id).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("pedido");
		model.addObject("pedido", pedido);
		
		return model;
	}
	
	@GetMapping("/pedido/add")
	public ModelAndView addPlan() {
				
		ModelAndView model = new ModelAndView();
		Pedido pedido = new Pedido();
		model.addObject("pedido", pedido);
		model.addObject("clientes",clienteDAO.findAll());
		model.addObject("platos",platoDAO.findAll());
		model.setViewName("formPedido");
		
		return model;
	}	
	
	@PostMapping("/pedido/save")
	public ModelAndView formTutoria(@ModelAttribute Pedido pedido) {
		
		Pedido pedidoGuardado = pedidoDAO.save(pedido);
		
		for (Contiene contiene: pedido.getContiene()) {
	        contiene.setPedido(pedidoGuardado);
	        contieneDAO.save(contiene);
	    }
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/pedido");	
		
		return model;
	}	
	

	
	@GetMapping("/pedido/edit/{id}")
	public ModelAndView editPlan(@PathVariable long id) {
				
		ModelAndView model = new ModelAndView();
		
		Optional<Pedido> platin = pedidoDAO.findById(id);
		
		if(platin.isPresent()) {
			Pedido pedido = platin.get();
			model.addObject("pedido", pedido);
			model.addObject("clientes",clienteDAO.findAll());
			model.addObject("platos",platoDAO.findAll());
			model.setViewName("formPedido");
			pedidoDAO.save(pedido);
		}
		else model.setViewName("redirect:/pedido");	
		
		return model;
	}
	
	@GetMapping("/pedido/del/{id}")
	public ModelAndView delPlan(@PathVariable long id) {
				
		pedidoDAO.deleteById(id);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/pedido");
		
		return model;
	}
}
