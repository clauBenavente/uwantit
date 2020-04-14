package com.springboot.app.uwantit.models.service;



import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.uwantit.models.dao.IUsuarioDao;
import com.springboot.app.uwantit.models.entity.Usuario;

public class UsuarioServiceImp implements IUsuarioService{

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
	public boolean confirmarUsuario(String email, String contrasenna) {
		usuarioDao.findById(email);
		return false;
	}

}
