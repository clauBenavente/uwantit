package com.springboot.app.uwantit.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String procesarRegistro(@Valid Usuario usuario, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("titulo", "Registro");
			return "formularioRegistro";
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
