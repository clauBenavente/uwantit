package com.springboot.app.uwantit.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 40, unique = true)
	private String username;
	
	@NotBlank
	@Column(length = 60)
	private String password;

	private boolean enabled;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Roles> roles;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "puntuador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Puntuacion> Puntua;
	
	@OneToMany(mappedBy = "puntuado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Puntuacion> esPuntuado;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellido;
	
	@NotBlank
	@Pattern(regexp = "(\\+34|0034|34)?[ -]*(6|7|9)([0-9]){2}[ -]?(([0-9]){2}[ -]?([0-9]){2}[ -]?([0-9]){2}|([0-9]){3}[ -]?([0-9]){3})", message = "El número indicado no es un número de telefono")
	private String telefono;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String direccion;
	
	@Column(name = "foto_perfil")
	private String fotoPerfil;

	public Usuario() {

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Puntuacion> getPuntua() {
		return Puntua;
	}

	public void setPuntua(List<Puntuacion> puntua) {
		Puntua = puntua;
	}

	public List<Puntuacion> getEsPuntuado() {
		return esPuntuado;
	}

	public void setEsPuntuado(List<Puntuacion> esPuntuado) {
		this.esPuntuado = esPuntuado;
	}
	
	
}
