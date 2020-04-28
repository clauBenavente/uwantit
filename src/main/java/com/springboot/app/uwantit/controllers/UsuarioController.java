package com.springboot.app.uwantit.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
//import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.Usuario;
import com.springboot.app.uwantit.models.service.IProductoService;
import com.springboot.app.uwantit.models.service.IUsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private IProductoService serviceProducto;
	
	@GetMapping(value="/form")
	public String formularioRegitro(Model model) {
		model.addAttribute("titulo", "Registro");
		model.addAttribute("usuario", new Usuario());
		return "formularioRegistro";
	}
	
	@PostMapping(value="/form")
	public String procesarRegistro(@Valid Usuario usuario, BindingResult resultado, Model model, @RequestParam("foto_perfil") MultipartFile fotoPerfil) {
		if(resultado.hasErrors()) {
			model.addAttribute("titulo", "Registro");
			return "formularioRegistro";
		}
		if(!fotoPerfil.isEmpty()) {
			Path directorioFotos = Paths.get("src//main//resources//static/uploads");
			String rootPath = directorioFotos.toFile().getAbsolutePath();
			try {
				byte [] bytes = fotoPerfil.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + fotoPerfil.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				usuario.setFotoPerfil(fotoPerfil.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		service.insertarUsuario(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping("/login")
	public String Login(Model model, Principal principal, RedirectAttributes flash) {
		if(principal != null) {
			flash.addAttribute("info", "Ya ha iniciado sesión anteriormente");
			return "redirect:/listar";
		}
		return "login";
	}
	
	
	@RequestMapping(value = "/usuario/{usuario}")
	public String verPerfil(@PathVariable(value = "usuario") String username, Model model) {
		Usuario usuario = null;
		List<Producto> listaProductos = null;
		if (username != null) {
			usuario = service.perfilUsuario(username);
			//listaProductos = serviceProducto.productosUsuario(email);
		} else {
			return "redirect:/listar";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Perfil" + usuario.getNombre());
		model.addAttribute("listado", listaProductos);
		return "vistaUsuario";
	}
	/*public void puntuarUsuario(int puntuacion) {
		
	}*/
	
}
