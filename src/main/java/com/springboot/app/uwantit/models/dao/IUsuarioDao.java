package com.springboot.app.uwantit.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.uwantit.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, String>{

}
