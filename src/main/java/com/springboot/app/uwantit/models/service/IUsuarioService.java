package com.springboot.app.uwantit.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.Puntuacion;
import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioService {
	
	public void insertarUsuario(Usuario usuario);
	
	public Usuario perfilUsuario(String username);
	
	public void insertarRolUsuario(String authority, Long idUser);
	
	public void insertarPuntuacion(int puntos, long puntuado, long puntuador, long producto);
	
	public Puntuacion haPuntuado(Usuario puntuado, Usuario puntuador, long producto);
	
	public Usuario recuperarUsuario(String email);
	
	public void enviarMensaje(String mensaje, Usuario envia, Usuario recibe);
	
	public List<ComunicacionProductos> obtenerConversacion(Usuario envia, Usuario recibe);
	
	public void borrarUsuario(long id);
	
	
}
