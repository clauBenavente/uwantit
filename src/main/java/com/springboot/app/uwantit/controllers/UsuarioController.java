package com.springboot.app.uwantit.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.app.uwantit.models.entity.Usuario;
import com.springboot.app.uwantit.models.service.IUsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		service.insertarUsuario(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping(value="/login")
	public String formularioLogin(Model model) {
		model.addAttribute("titulo", "Login");
		return "formularioLogin";
	}
	
	@PostMapping(value="/login")
	public String confirmarLogin(@RequestParam("email") String email, 
			@RequestParam("password") String contrasenna, 
			Model model) {
		if(service.confirmarUsuario(email, contrasenna) instanceof Usuario) {
			return "redirect:/listar";
		}else {
			model.addAttribute("titulo", "Login");
			return "formularioLogin";		}
	}
	
	/*public void puntuarUsuario(int puntuacion) {
		
	}*/
	
}
