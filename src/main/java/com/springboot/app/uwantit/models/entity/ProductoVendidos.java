package com.springboot.app.uwantit.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductoVendidos")
public class ProductoVendidos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;
	
}
