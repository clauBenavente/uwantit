package com.springboot.app.uwantit.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "Productos")
public class Producto {

	@Id
	@NotNull
	private Long idProducto;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	private String fotos;
	@NotEmpty
	private String localizacion;
	@NotEmpty
	private String categoriaProducto;
	@NotEmpty
	private String usuarioProducto;

	public Producto() {

	}


	public Long getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

	public String getUsuarioProducto() {
		return usuarioProducto;
	}

	public void setUsuarioProducto(String usuarioProducto) {
		this.usuarioProducto = usuarioProducto;
	}

}
