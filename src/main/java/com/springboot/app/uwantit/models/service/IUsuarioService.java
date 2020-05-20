package com.springboot.app.uwantit.models.service;

import java.util.List;

import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioService {
	
	public void insertarUsuario(Usuario usuario);
	
	public void eliminarUsuario(String email);
	
	public Usuario perfilUsuario(String username);
	
	public void insertarRolUsuario(String authority, Long idUser);
	
	public void insertarPuntuacion(int puntos, long puntuado, long puntuador);
	
	public Long obtenerIdUsers(String username);
	
	public Usuario recuperarUsuario(String email);
	
	public void enviarMensaje(String mensaje, Usuario envia, Usuario recibe);
	
	public List<ComunicacionProductos> obtenerConversacion(Usuario envia, Usuario recibe);
	
}
