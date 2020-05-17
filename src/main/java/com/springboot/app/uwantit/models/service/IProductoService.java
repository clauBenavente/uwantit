package com.springboot.app.uwantit.models.service;


import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;

import com.springboot.app.uwantit.models.entity.CategoriasProducto;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.Usuario;


public interface IProductoService {

	public void insertarProducto(Producto producto);
	
	public List<Producto> listarProductos();
	
	public Producto visualizarProducto(Long idProducto);
	
	public void borrarProducto(long id);
	
	public CategoriasProducto getCategoria(long id);
	
	public List<Producto> findByNombre(String term);
	
	public void guardarFavorito(Producto producto, Usuario usuario);
	
	public void quitarFavorito(Producto producto, Usuario usuario);
	
	public void pujarProducto(Producto producto, Usuario usuario, double propuesta);

	public List<Producto> listarProductosVendidos(Authentication auth);

	public void productoVendidos(long idProducto, Long iduser);
	
}

