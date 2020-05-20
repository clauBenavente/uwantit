package com.springboot.app.uwantit.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.Usuario;

public interface IComunicacionDao extends JpaRepository<ComunicacionProductos, Long>{

	@Modifying
	@Query(value = "insert into comunicacion (mensaje, envia_id, recibe_id) values (:mensaje, :envia, :recibe)", nativeQuery = true)
	void enviarMensaje(@Param("mensaje") String mensaje, @Param("envia") Usuario envia, @Param("recibe") Usuario recibe);

	List<ComunicacionProductos> findByEnviaOrRecibe(Usuario envia, Usuario recibe);
	
}
