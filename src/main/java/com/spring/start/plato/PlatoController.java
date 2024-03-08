package com.spring.start.plato;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlatoController {

	@Autowired PlatoDAO platoDAO;
	
	@GetMapping("/plato")
	public ModelAndView tutorias() {

		ModelAndView model = new ModelAndView();
		model.setViewName("platos");
		
		List<Plato> platos = (List<Plato>) platoDAO.findAll();
		model.addObject("platos", platos);
		
		return model;
	}
	

	
	
	
	@GetMapping("/plato/{id}")
	public ModelAndView tutoria(@PathVariable long id) {
		
		Plato plato = platoDAO.findById(id).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("plato");
		model.addObject("plato", plato);
		
		return model;
	}
	
	@GetMapping("/plato/add")
	public ModelAndView addPlan() {
				
		ModelAndView model = new ModelAndView();
		Plato plato = new Plato();
		model.addObject("plato", plato);
		
		model.setViewName("formPlato");
		
		return model;
	}	
	
	@PostMapping("/plato/save")
	public ModelAndView formTutoria(@ModelAttribute Plato plato) {
	

		
		Plato platoGuardado = platoDAO.save(plato);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/plato");	
		
		return model;
	}	
	

	
	@GetMapping("/plato/edit/{id}")
	public ModelAndView editPlan(@PathVariable long id) {
				
		ModelAndView model = new ModelAndView();
		
		Optional<Plato> platin = platoDAO.findById(id);
		
		if(platin.isPresent()) {
			Plato plato = platin.get();
			model.addObject("plato", plato);
			model.setViewName("formPlato");
			platoDAO.save(plato);
		}
		else model.setViewName("redirect:/plato");	
		
		return model;
	}
	
	@GetMapping("/plato/del/{id}")
	public ModelAndView delPlan(@PathVariable long id) {
				
		platoDAO.deleteById(id);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/plato");
		
		return model;
	}
}
