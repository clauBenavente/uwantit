package com.springboot.app.uwantit.models.entity;

import java.util.List;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="producto")
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
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
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private CategoriasProducto categoriaProducto;
	
	@ManyToMany(mappedBy = "productoFavorito", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuario> favoritos;
	
	@NotNull
	@Column(name="vendido")
	private boolean vendido;
	
	public boolean isVendido() {
		return vendido;
	}


	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Usuario usuario;
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

	public CategoriasProducto getCategoriaProducto() {
		return categoriaProducto;
	}


	public void setCategoriaProducto(CategoriasProducto categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Usuario> getFavoritos() {
		return favoritos;
	}


	public void setFavoritos(List<Usuario> favoritos) {
		this.favoritos = favoritos;
	}
	
}
