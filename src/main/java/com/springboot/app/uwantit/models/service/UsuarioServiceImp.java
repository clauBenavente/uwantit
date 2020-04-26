package com.springboot.app.uwantit.models.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IUsuarioDao;
import com.springboot.app.uwantit.models.entity.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional
	public void insertarUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void eliminarUsuario(String email) {
		usuarioDao.deleteById(email);
	}

	@Override
	public Usuario confirmarUsuario(String email, String contrasenna) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario perfilUsuario(String email) {
		return usuarioDao.visualizarPerfil(email);
	}

	@Override
	//terminar cuando este implementado el login en la clase usuarioController, dudas sobre el return hablar mañana!
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByEmail(email);
		return null;
	}
}
