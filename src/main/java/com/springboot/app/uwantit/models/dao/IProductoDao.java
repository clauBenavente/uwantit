package com.springboot.app.uwantit.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.app.uwantit.models.entity.CategoriasProducto;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.Usuario;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	//@Query("select producto from Producto producto where producto.usuario_id = ?1")
	//List<Producto> productosUsuario(String usuario);
	
	@Query("SELECT c FROM CategoriasProducto c WHERE c.categoria_id = ?1")
	CategoriasProducto getCategoria(long id);
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	@Modifying
	@Query(value = "insert into favorito (FK_Producto, FK_Usuario) values (:producto, :usuario)", nativeQuery = true)
	void guardarFavorito(@Param("producto") long producto, @Param("usuario") long usuario);
		
}
