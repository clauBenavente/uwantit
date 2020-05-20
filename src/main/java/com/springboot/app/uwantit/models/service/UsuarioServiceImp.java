package com.springboot.app.uwantit.models.service;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IComunicacionDao;
import com.springboot.app.uwantit.models.dao.IUsuarioDao;
import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.Roles;
import com.springboot.app.uwantit.models.entity.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IComunicacionDao comunicacionDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insertarUsuario(Usuario usuario) {
		if(usuario.getId() != null && usuario.getId() > 0) {
			em.merge(usuario);
		}else {
			em.persist(usuario);
		}
	}
	
	@Transactional
	public void insertarRolUsuario(String authority, Long idUser) {
		usuarioDao.insertRole(authority, idUser);
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for(Roles role : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, roles);
	}

	//Metodo que falla
	@Override
	@Transactional(readOnly = true)
	public Long obtenerIdUsers(String username) {
		return usuarioDao.obtenerIdUsuario(username);
	}

	@Override
	@Transactional
	public void insertarPuntuacion(int puntos, long puntuado, long puntuador) {
		usuarioDao.insertarPuntuacion(puntos, puntuado, puntuador);
	}

	@Override
	public Usuario recuperarUsuario(String email) {
		return usuarioDao.recuperarUsuario(email);
	}
	
	@Transactional
	@Override
	public void enviarMensaje(String mensaje, Usuario envia, Usuario recibe) {
		comunicacionDao.enviarMensaje(mensaje, envia, recibe);
		
	}

	@Override
	public List<ComunicacionProductos> obtenerConversacion(Usuario envia, Usuario recibe) {
		return comunicacionDao.findByEnviaOrRecibe(envia, recibe);
		
	}
	
}
