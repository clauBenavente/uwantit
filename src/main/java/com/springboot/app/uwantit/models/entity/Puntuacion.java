package com.springboot.app.uwantit.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="puntuacion")
public class Puntuacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_puntuacion")
	private Long idPuntuacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario puntuador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario puntuado;
	
	private int puntos;

	public Usuario getPuntuador() {
		return puntuador;
	}

	public void setPuntuador(Usuario puntuador) {
		this.puntuador = puntuador;
	}

	public Usuario getPuntuado() {
		return puntuado;
	}

	public void setPuntuado(Usuario puntuado) {
		this.puntuado = puntuado;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
