package com.springboot.app.uwantit.models.dao;


import java.util.Set;

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

	@Modifying
	@Query(value = "insert into puntuacion (puntos, puntuado_id, puntuador_id) values (:puntos, :puntuado, :puntuador)", nativeQuery = true)
	void insertarPuntuacion(@Param("puntos") int puntos, @Param("puntuado") long puntuado, @Param("puntuador") long puntuador);
		
	//Metodo de falla
	@Query("select id from Usuario u where u.username = ?1")
	Long obtenerIdUsuario(String username);
	
	@Query("select usu from Usuario usu where usu.email = ?1")
	Usuario recuperarUsuario(String email);
	
}
