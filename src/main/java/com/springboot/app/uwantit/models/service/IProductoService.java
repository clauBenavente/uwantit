package com.springboot.app.uwantit.models.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;

import com.springboot.app.uwantit.models.entity.CategoriasProducto;
import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.ProductoVendidos;
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
	
	public void vendido(long idProducto);
	
	public void confirmVendido(long idProducto, Usuario usuario);
	
	public Page<Producto> productosVendidos(long iduser, Pageable pageable);
	
	public Page<ProductoVendidos> productosComprados(long iduser, Pageable pageable);
	
	public Page<Producto> productosEnVenta(Pageable pageable);
	
  	public List<Producto> productosEnVentaPerfil(long iduser);
	
}

