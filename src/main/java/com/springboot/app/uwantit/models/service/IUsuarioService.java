package com.springboot.app.uwantit.models.service;

import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioService {
	
	public void insertarUsuario(Usuario usuario);
	
	public void eliminarUsuario(String email);
	
	public Usuario perfilUsuario(String email);
	
	public void insertarRolUsuario(String authority, Long idUser);
	
	public Long obtenerIdUsers(String username);
}
