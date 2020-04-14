package com.springboot.app.uwantit.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.service.IProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value="/listar")
	public String listarTodosLosProductos(Model model) {
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productoService.findAll());
		return "listar";
	}

	@GetMapping(value="/formularioProducto")
	public String formularioProducto(Model model) {
		model.addAttribute("titulo", "Registro de Producto");
		model.addAttribute("producto", new Producto());
		return "formularioProducto";
	}
	/**
	  @PostMapping(value="/formProducto")
	public String procesarProducto(@Valid Producto producto, bindingResult result) {
		if(result.hasErrors()){
			model.addAttribute("titulo", "Registro de Producto");
			return "formularioProducto";
		}
		return "redirect:/listar";
	}
	 */
	@PostMapping(value="/formProducto")
	public String procesarProducto(Producto producto) {
		return "redirect:/listar";
	}
	
	@RequestMapping(value="/producto/{idProducto}")
	public String visualizarProducto(@PathVariable(value="idProducto") int idProducto, Map<String, Object> model) {
		Producto producto = null;
		
		if(idProducto > 0) {
			producto = productoService.findOne(idProducto);
		}else {
			return "redirect:/listar"; 
		}
		model.put("producto", producto);
		model.put("titulo", "Vista Producto");
		return "vistaProducto";
	}
	
	//////////////////////// NO USAR /////////////////////

	public String agregarFavorito() {
		return "";
	}
	public String visualizarFavorito() {
		return "";
	}
}
