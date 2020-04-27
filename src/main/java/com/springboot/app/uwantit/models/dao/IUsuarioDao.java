package com.springboot.app.uwantit.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, String>{
	
	public Usuario findByUsername(String username);
	
	@Query("select user from Usuarios user where user.email = ?1")
	Usuario visualizarPerfil(String email);
	
}
