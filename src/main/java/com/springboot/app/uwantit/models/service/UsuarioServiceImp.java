package com.springboot.app.uwantit.models.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IUsuarioDao;
import com.springboot.app.uwantit.models.entity.Roles;
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
	@Transactional(readOnly = true)
	public Usuario perfilUsuario(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	//terminar cuando este implementado el login en la clase usuarioController, dudas sobre el return hablar mañana!
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for(Roles role : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, roles);
	}
}
