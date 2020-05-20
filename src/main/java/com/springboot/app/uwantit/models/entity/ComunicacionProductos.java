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
	@Column(name="id_mensaje")
	private Long idMensaje;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario envia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario recibe;
	
	private String mensaje;

	public Long getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Long idMensaje) {
		this.idMensaje = idMensaje;
	}

	public Usuario getEnvia() {
		return envia;
	}

	public void setEnvia(Usuario envia) {
		this.envia = envia;
	}

	public Usuario getRecibe() {
		return recibe;
	}

	public void setRecibe(Usuario recibe) {
		this.recibe = recibe;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
