package com.springboot.app.uwantit.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.app.uwantit.models.entity.Puntuacion;
import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	
	@Modifying
	@Query(value = "insert into authorities (authority, user_id) values (:authority, :user_id)", nativeQuery = true)
	void insertRole(@Param("authority") String role, @Param("user_id") Long userId);

	@Modifying
	@Query(value = "insert into puntuacion (puntos, id_producto, puntuado_id, puntuador_id) values (:puntos, :producto, :puntuado, :puntuador)", nativeQuery = true)
	void insertarPuntuacion(@Param("puntos") int puntos, @Param("puntuado") long puntuado,
			@Param("puntuador") long puntuador, @Param("producto") long producto);
	
	@Query("select usu from Usuario usu where usu.email = ?1")
	Usuario recuperarUsuario(String email);
	
	@Query("select p from Puntuacion p  where p.puntuador = :puntuador and p.puntuado = :puntuado and p.idProducto = :producto")
	Puntuacion haPuntuado(@Param("puntuado") Usuario puntuado, @Param("puntuador") Usuario puntuador, @Param("producto") long producto);
	
	@Modifying
	@Query(value = "delete from Usuario where id = ?1")
	void borrarUsuario(long id);
	
	@Modifying
	@Query(value = "delete from authorities where user_id = ?1", nativeQuery = true)
	void borrarAuthorities(long id);
	
	@Modifying
	@Query(value = "delete from puntuacion where puntuado_id = ?1 OR puntuador_id = ?1", nativeQuery = true)
	void borrarPuntuaciones(long id);
	
	@Modifying
	@Query(value = "delete from comunicacion where envia_id = ?1 OR recibe_id = ?1", nativeQuery = true)
	void borrarMensajes(long id);
}
