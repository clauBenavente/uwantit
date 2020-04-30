package com.springboot.app.uwantit.models.service;


import java.util.List;

import com.springboot.app.uwantit.models.entity.Producto;


public interface IProductoService {

	public void insertarProducto(Producto producto);
	
	public List<Producto> listarProductos();
	
	public Producto visualizarProducto(Long idProducto);
	
	public void eliminarProductos(Long idProducto);
	
	public Producto agregarFavorito(Long idProducto);
	
	//public List<Producto> productosUsuario(String usuario);
}

