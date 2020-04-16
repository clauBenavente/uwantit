package com.springboot.app.uwantit.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, String>{
	
	@Query("select user from Usuarios user where user.email = ?1 and user.contrasenna = ?2")
	Usuario validarEmailYContrasenna(String email, String contrasenna);
}
