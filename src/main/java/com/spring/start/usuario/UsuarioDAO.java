package com.spring.start.usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);
}
