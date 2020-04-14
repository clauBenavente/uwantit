package com.springboot.app.uwantit.models.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IProductoDao;
import com.springboot.app.uwantit.models.entity.Producto;

public class ProductoServiceImp implements IProductoService{

	@Autowired
	private IProductoDao productoDao;
	
	@Override
	public void insertarProducto(Producto producto) {
		productoDao.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> listarProductos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto visualizarProducto(Long idProducto) {
		// TODO Auto-generated method stub
		return productoDao.findById(idProducto).get();
	}

	@Override
	@Transactional
	public void eliminarProductos(Long idProducto) {
		productoDao.deleteById(idProducto);
		
	}

	@Override
	public Producto agregarFavorito(Long idProducto) {
		// TODO Auto-generated method stub
		return null;
	}

}
