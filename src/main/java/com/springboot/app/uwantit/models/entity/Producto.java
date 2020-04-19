package com.springboot.app.uwantit.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private Long idProducto;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String descripcion;
	private String fotos;
	@NotEmpty
	private String localizacion;
	@NotNull
	@Column(name="precio")
	private double precio;
	@NotNull
	@Column(name="categoria_producto")
	private int categoriaProducto;
	@Column(name="usuarios_producto")
	private String usuarioProducto;

	public Producto() {

	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
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

	public int getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(int categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

	public String getUsuarioProducto() {
		return usuarioProducto;
	}

	public void setUsuarioProducto(String usuarioProducto) {
		this.usuarioProducto = usuarioProducto;
	}

}
