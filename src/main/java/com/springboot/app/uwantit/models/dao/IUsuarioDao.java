package com.springboot.app.uwantit.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, String>{
	
	/*@Query("select user from Usuarios user where user.email = ?1 and user.contrasenna = ?2")
	Usuario validarEmailYContrasenna(String email, String contrasenna);*/
	public Usuario findByEmail(String email);
	
	@Query("select user from Usuarios user where user.email = ?1")
	Usuario visualizarPerfil(String email);
	
}
