package com.springboot.app.uwantit.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, String>{
	
	public Usuario findByUsername(String username);
	
	@Modifying
	@Query(value = "insert into authorities (authority, user_id) values (:authority, :user_id)", nativeQuery = true)
	void insertRole(@Param("authority") String role, @Param("user_id") Long userId);
		
	//Metodo de falla
	@Query("select id from users where users.username = ?1")
	Long obtenerIdUsuario(String username);
}
