package com.springboot.app.uwantit.models.service;

import java.util.List;

import com.springboot.app.uwantit.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	public Producto findOne(long idProducto);
	
}