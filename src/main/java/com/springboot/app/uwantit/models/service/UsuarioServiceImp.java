package com.springboot.app.uwantit.models.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IUsuarioDao;
import com.springboot.app.uwantit.models.entity.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService{
	
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
	public Usuario confirmarUsuario(String email, String contrasenna) {
		return usuarioDao.validarEmailYContrasenna(email, contrasenna);
			
		
	}
}
