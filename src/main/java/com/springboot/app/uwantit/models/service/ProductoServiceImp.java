package com.springboot.app.uwantit.models.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IProductoDao;
import com.springboot.app.uwantit.models.entity.CategoriasProducto;
import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.ProductoVendidos;
import com.springboot.app.uwantit.models.entity.Usuario;

@Service
public class ProductoServiceImp implements IProductoService{

	@Autowired
	private IProductoDao productoDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insertarProducto(Producto producto) {
		if(producto.getIdProducto() != null && producto.getIdProducto() > 0) {
			em.merge(producto);
		}else {
			em.persist(producto);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Producto visualizarProducto(Long idProducto) {
		return productoDao.findById(idProducto).get();
	}

	@Override
	public CategoriasProducto getCategoria(long id) {
		return productoDao.getCategoria(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findByNombre(String term,  Pageable pageable) {
		return productoDao.findByNombre(term, pageable);
	}

	@Transactional
	@Override
	public void guardarFavorito(Producto producto, Usuario usuario) {
		productoDao.guardarFavorito(producto, usuario);
		
	}

	@Transactional
	@Override
	public void quitarFavorito(Producto producto, Usuario usuario) {
		productoDao.quitarFavorito(producto, usuario);
		
	}
	
	@Transactional
	@Override
	public void borrarProducto(long id) {
		productoDao.borrarProducto(id);
		
	}
	@Transactional
	@Override
	public void vendido(long idProducto) {
		productoDao.vendido( idProducto);
		
	}
	@Transactional
	@Override
	public void confirmVendido(long idProducto, Usuario usuario) {
		productoDao.confirmVendido(idProducto, usuario);
		
	}
	@Override
	@Transactional(readOnly = true)
	public Page<Producto> productosVendidos(long iduser, Pageable pageable) {
		return productoDao.productosVendidos(iduser, pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ProductoVendidos> productosComprados(long iduser, Pageable pageable) {
		return productoDao.productosComprados(iduser, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> productosEnVenta(Pageable pageable) {
		return productoDao.productosEnVenta(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
  	public List<Producto> productosEnVentaPerfil(long iduser){
		return productoDao.productosEnVentaPerfil(iduser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoriasProducto> listadoCategorias() {
		
		return productoDao.listarCategorias();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> productoPorCategoria(long categoria, Pageable pageable) {
		
		return productoDao.productoPorCategoria(categoria, pageable);
	}

	@Override
	public Page<Producto> findByNombreAndCategoria(String term, long categoria, Pageable pageable) {
		return productoDao.findByNombreAndCategoria(term, categoria, pageable);
	}

	@Override
	public boolean existe(long id) {
		return productoDao.existsById(id);
	}

	@Transactional
	@Override
	public void borrarProductoVendidos(long id) {
		productoDao.borrarProductoVendidos(id);
	}

}
	
