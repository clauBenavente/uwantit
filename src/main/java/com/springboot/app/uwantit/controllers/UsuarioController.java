package com.springboot.app.uwantit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String procesarRegistro(Usuario usuario) {
		service.insertarUsuario(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping(value="/login")
	public String formularioLogin(Model model) {
		model.addAttribute("titulo", "Login");
		return "formularioLogin";
	}
	
	@PostMapping(value="/login")
	public String confirmarLogin() {
		//service.confirmarUsuario(usuario);
		return "redirect:/listar";
	}
	
	/*public void puntuarUsuario(int puntuacion) {
		
	}*/
	
}
