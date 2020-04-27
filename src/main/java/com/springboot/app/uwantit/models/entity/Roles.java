package com.springboot.app.uwantit.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table("rol_usuario")
public class Roles {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_rol_usuario;
	
	private String descripcion;

	public Long getId_rol_usuario() {
		return id_rol_usuario;
	}

	public void setId_rol_usuario(Long id_rol_usuario) {
		this.id_rol_usuario = id_rol_usuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
