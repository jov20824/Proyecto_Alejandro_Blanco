package com.spring.start.plato;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlatoController {

	@Autowired 
	PlatoDAO platoDAO;
	
	@GetMapping("/plato")
	public List<Plato> platos() {
		return (List<Plato>) platoDAO.findAll();
	}
	
	@GetMapping("/plato/{id}")
	public Optional<Plato> plato(@PathVariable long id) {
		return platoDAO.findById(id);
	}
	
	@PostMapping("/plato/save")
	public Plato savePlato(@RequestBody Plato plato) {
		return platoDAO.save(plato);
	}	
	
	@PutMapping("/plato/edit/{id}")
	public Plato editPlato(@PathVariable long id) {
		
		Optional<Plato> platin = platoDAO.findById(id);

		
		if(platin.isPresent()) {
			Plato plato = platin.get();
			return platoDAO.save(plato);
		}
		
		return null;	
		
	}
	
	@DeleteMapping("/plato/del/{id}")
	public void delPlato(@PathVariable long id) {
		platoDAO.deleteById(id);
	}
}
