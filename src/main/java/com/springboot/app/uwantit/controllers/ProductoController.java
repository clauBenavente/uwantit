package com.springboot.app.uwantit.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.springboot.app.uwantit.models.service.IProductoService;

@Controller
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@RequestMapping(value = {"/listar","/"})
	public String listarTodosLosProductos(Model model) {
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productoService.listarProductos());
		return "listar";
	}

	@GetMapping(value = "/formularioProducto/{idUsername}")
	public String formularioProducto(Model model, @PathVariable(value = "idUsername") String idUsername) {
		model.addAttribute("titulo", "Registro de Producto");
		/**Cuando este buscar id quitar estos comentarios---------------
		int id =productoService.obtenerIdUsuario(idUsername)
		model.addAttribute("idUsername", id);
		 * */
		 
		model.addAttribute("producto", new Producto());
		return "formularioProducto";
	}
	
	@PostMapping(value = "/formProducto")
	public String procesarProducto(@Valid Producto producto, BindingResult result, Model model,
			@RequestParam("fotos") MultipartFile fotos, RedirectAttributes flash) {

			if (!fotos.isEmpty()) {
			
			Path directorioRecursos = Paths.get("src//main//resources//static/uploads");
			String rootPath = directorioRecursos.toFile().getAbsolutePath();

			try {

				byte[] bytes = fotos.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + fotos.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Has subido correctamente '" + fotos.getOriginalFilename() + "'");

				producto.setFotos(fotos.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		productoService.insertarProducto(producto);
		return "redirect:/listar";
	}

	@RequestMapping(value = "/producto/{idProducto}")
	public String visualizarProducto(@PathVariable(value = "idProducto") Long idProducto, Map<String, Object> model) {
		Producto producto = null;

		if (idProducto > 0) {
			producto = productoService.visualizarProducto(idProducto);
		} else {
			return "redirect:/listar";
		}
		model.put("producto", producto);
		model.put("titulo", "Vista Producto" + producto.getNombre());
		return "vistaProducto";
	}

	@RequestMapping(value = "/eliminar/producto/{idProducto}")
	public String eliminarProducto(@PathVariable(value = "idProducto") Long idProducto) {

		productoService.eliminarProductos(idProducto);

		return "redirect:/listar";
	}

	//////////////////////// NO USAR /////////////////////

	public String agregarFavorito() {
		return "";
	}

	public String visualizarFavorito() {
		return "";
	}
}
