package com.springboot.app.uwantit.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comunicacion")
public class ComunicacionProductos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_propuesta")
	private Long idPropuesta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario interesado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;
	
	private double propuesta;

	public Long getIdPropuesta() {
		return idPropuesta;
	}

	public void setIdPropuesta(Long idPropuesta) {
		this.idPropuesta = idPropuesta;
	}

	public Usuario getInteresado() {
		return interesado;
	}

	public void setInteresado(Usuario interesado) {
		this.interesado = interesado;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public double getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(double propuesta) {
		this.propuesta = propuesta;
	}
	
	
}
