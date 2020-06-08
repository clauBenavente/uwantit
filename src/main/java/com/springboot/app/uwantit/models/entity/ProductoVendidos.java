package com.springboot.app.uwantit.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="producto_vendidos")
public class ProductoVendidos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ProductoVendidos() {

	}
	
	
}
