package com.springboot.app.uwantit.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.app.uwantit.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	//@Query("select producto from producto producto where producto.usuarioProducto")
	//List<Producto> productosUsuario(String usuarioProducto);
		
}
