package com.springboot.app.uwantit.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.springboot.app.uwantit.models.entity.CategoriasProducto;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.ProductoVendidos;
import com.springboot.app.uwantit.models.entity.Usuario;

public interface IProductoDao extends PagingAndSortingRepository<Producto, Long>{
		
	@Query("SELECT c FROM CategoriasProducto c WHERE c.categoria_id = ?1")
	CategoriasProducto getCategoria(long id);
	
	@Query("select c from CategoriasProducto c")
	public List<CategoriasProducto> listarCategorias();
	
	@Query("select p from Producto p where p.categoriaProducto.categoria_id = ?1 and p.vendido = false")
	public Page<Producto> productoPorCategoria(long categoria, Pageable pageable );
	
	@Query("select p from Producto p where p.nombre like %?1% and p.vendido = false")
	public Page<Producto> findByNombre(String term, Pageable pageable);
	
	@Query("select p from Producto p where p.categoriaProducto.categoria_id = ?2 and p.vendido = false and p.nombre like %?1%")
	public Page<Producto> findByNombreAndCategoria(String term, long categoria, Pageable pageable);
	
	@Modifying
	@Query(value = "insert into favorito (usuario_id, producto_id) values (:usuario, :producto)", nativeQuery = true)
	void guardarFavorito(@Param("producto") Producto producto, @Param("usuario") Usuario usuario);
	
	@Modifying
	@Query(value = "delete from favorito where usuario_id = :usuario and producto_id = :producto", nativeQuery = true)
	void quitarFavorito(@Param("producto") Producto producto, @Param("usuario") Usuario usuario);
		
	@Modifying
	@Query(value = "delete from Producto where id_producto = ?1")
	void borrarProducto(long id);

	@Modifying
	@Query(value = "insert into producto_vendidos (producto_id_producto, usuario) values (?1, ?2)", nativeQuery = true)
	void confirmVendido(long idProducto, Usuario usuario);

	@Modifying
	@Query(value = "UPDATE Producto p SET p.vendido = true WHERE (p.id_producto = ?1)", nativeQuery = true)
	void vendido(long idProducto);

	@Query("select p from Producto p where p.vendido= true and usuario.id = ?1")
	Page<Producto> productosVendidos(long iduser, Pageable pageable);
	
  	@Query("select p from ProductoVendidos p where usuario.id = ?1")
	Page<ProductoVendidos> productosComprados(long id, Pageable pageable);
  	
  	@Query("select p from Producto p where p.vendido = false")
  	Page<Producto> productosEnVenta(Pageable pageable);
  	
  	@Query("select p from Producto p where p.vendido = false and usuario.id = ?1")
  	public List<Producto> productosEnVentaPerfil(long iduser);
  	
  	@Modifying
	@Query(value = "delete from producto_vendidos where producto_id_producto = ?1", nativeQuery = true)
	void borrarProductoVendidos(long id);
  	
}
