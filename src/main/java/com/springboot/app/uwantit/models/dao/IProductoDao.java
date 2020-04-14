package com.springboot.app.uwantit.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.uwantit.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

	
}
