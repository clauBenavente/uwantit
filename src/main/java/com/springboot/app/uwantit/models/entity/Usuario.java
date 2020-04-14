package com.springboot.app.uwantit.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity(name = "Usuarios")
public class Usuario {
	@NotBlank
	private String login;
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellido;
	@NotBlank
	private String contrasenna;
	@NotNull
    //@Pattern(regexp = "[6]{1}[0-9]{8}", message="El número indicado no es un número de telefono")
	private int telefono;
	@Id
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String direccion;
	@NotBlank
	@Column(name="foto_perfil")
	private String fotoPerfil;
	@Column(name="rol_usuario")
	private int rolUsuario;

	public Usuario() {

	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenna() {
		return contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public int getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(int rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

}
