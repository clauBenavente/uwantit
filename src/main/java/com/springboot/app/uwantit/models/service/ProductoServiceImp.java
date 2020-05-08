package com.springboot.app.uwantit.models.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IProductoDao;
import com.springboot.app.uwantit.models.entity.CategoriasProducto;
import com.springboot.app.uwantit.models.entity.Producto;
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
	public List<Producto> listarProductos() {
		
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto visualizarProducto(Long idProducto) {
		return productoDao.findById(idProducto).get();
	}

	@Override
	@Transactional
	public void eliminarProductos(Long idProducto) {
		productoDao.deleteById(idProducto);
		
	}

	@Override
	public CategoriasProducto getCategoria(long id) {
		return productoDao.getCategoria(id);
	}

	@Override
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}

	@Transactional
	@Override
	public void guardarFavorito(Producto producto, Usuario usuario) {
		productoDao.guardarFavorito(producto, usuario);
		
	}

}
