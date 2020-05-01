package com.springboot.app.uwantit.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="favorito")
public class Favorito implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
}
