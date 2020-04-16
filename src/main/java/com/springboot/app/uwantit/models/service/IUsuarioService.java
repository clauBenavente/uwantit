package com.springboot.app.uwantit.models.service;

import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioService {
	
	public void insertarUsuario(Usuario usuario);
	
	public void eliminarUsuario(String email);
	
	public Usuario confirmarUsuario(String email, String contrasenna);
	
}
