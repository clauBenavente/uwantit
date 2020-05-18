package com.springboot.app.uwantit.controllers;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.uwantit.models.entity.CategoriasProducto;
import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.RespuestaJSON;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.Usuario;
import com.springboot.app.uwantit.models.service.EnvioEmail;
import com.springboot.app.uwantit.models.service.IProductoService;
import com.springboot.app.uwantit.models.service.IUsuarioService;

@Controller
@SessionAttributes("producto")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private EnvioEmail email;

	@RequestMapping(value = {"/listar","/"})
	public String listarTodosLosProductos(Model model, @RequestParam(name = "filtro", required = false) String term) {
		if(term == null || term.isBlank()) {
			model.addAttribute("titulo", "Listado de Productos");
			model.addAttribute("productos", productoService.listarProductos());
			//model.addAttribute("productos", productoService.productosParaVender());
			
		}else {
			model.addAttribute("titulo", "Listado de Productos");
			model.addAttribute("productos", productoService.findByNombre(term));
		}
		return "listar";
	}

	@GetMapping(value = "/formularioProducto")
	public String formularioProducto(Model model) {
		model.addAttribute("titulo", "Registrar producto");
		model.addAttribute("producto", new Producto());
		return "formularioProducto";
	}
	
	@RequestMapping(value = "/formularioProducto/{idproducto}")
	public String editar(@PathVariable(value="idproducto") long idproducto, Model model) {
		
		Producto producto = productoService.visualizarProducto(idproducto);
		model.addAttribute("titulo", "Editar Producto");
		model.addAttribute("producto", producto);
		return "formularioProducto";
	}
	
	@PostMapping(value = "/formProducto")
	public String procesarProducto(@Valid Producto producto, BindingResult result, Model model,
			@RequestParam("fotos") MultipartFile fotos, @RequestParam("categoria") int id,
			 RedirectAttributes flash, Authentication authentication, SessionStatus status) {

			if (!fotos.isEmpty()) {
			
			Path rootPath = Paths.get("uploads").resolve(fotos.getOriginalFilename());
			Path rootAbsolutePath = rootPath.toAbsolutePath();

			try {

				Files.copy(fotos.getInputStream(), rootAbsolutePath);
				flash.addFlashAttribute("info", "Has subido correctamente '" + fotos.getOriginalFilename() + "'");

				producto.setFotos(fotos.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = usuarioService.perfilUsuario(auth.getName());
		long idCategoria = Long.valueOf(id);
		CategoriasProducto categoriaFinal = productoService.getCategoria(idCategoria);
		producto.setCategoriaProducto(categoriaFinal);
		producto.setUsuario(user);
		productoService.insertarProducto(producto);
		status.setComplete();
		
		return "redirect:/listar";
	}

	@RequestMapping(value = "/producto/{idProducto}")
	public String visualizarProducto(@PathVariable(value = "idProducto") Long idProducto, Map<String, Object> model,
			Authentication authentication) {
		Producto producto = null;

		if (idProducto > 0) {
			producto = productoService.visualizarProducto(idProducto);
		} else {
			return "redirect:/listar";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Boolean editable = false;
		Boolean borrable = false;
		Boolean favorito = false;
		Usuario user = usuarioService.perfilUsuario(auth.getName());
		if(user != null) {
			if(user.getId() == producto.getUsuario().getId()) {
				editable = true;
				borrable = true;
			}
			if(user.getProductoFavorito().contains(producto)) {
				favorito = true;
			}
		}
		Boolean hayOfertas = !producto.getOfertas().isEmpty();
		model.put("favorito", favorito);
		model.put("borrable", borrable);
		model.put("editable", editable);
		model.put("producto", producto);
		model.put("hayOfertas", hayOfertas);
		model.put("titulo", "Vista Producto" + producto.getNombre());
		return "vistaProducto";
	}

	
	@RequestMapping(value = "/producto/eliminar/{idProducto}")
	public String eliminarProducto(@PathVariable(value = "idProducto") Long idProducto) {
		
		productoService.borrarProducto(idProducto);

		return "redirect:/listar";
	}
	
	@ResponseBody
	@RequestMapping(value= "/producto/favorito/{idProducto}")
	public RespuestaJSON guardarFavorito(@PathVariable(value = "idProducto") long id) {
		Producto producto = productoService.visualizarProducto(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.perfilUsuario(auth.getName());
		RespuestaJSON respuesta = new RespuestaJSON();
		
		if(usuario.getProductoFavorito().contains(producto)) {
			productoService.quitarFavorito(producto, usuario);
			respuesta.setEstado(false);
		}else {
			productoService.guardarFavorito(producto, usuario);
			respuesta.setEstado(true);
		}
		
		return respuesta;
	}

	@RequestMapping(value = "/pujarProducto/{idproducto}")
	public String visualizarPuja(@PathVariable(value="idproducto") long idproducto, Model model) {
		
		model.addAttribute("titulo", "Hacer propuesta");
		model.addAttribute("productoId", idproducto);
		return "formularioPuja";
	}
	
	@PostMapping("/pujar")
	public String puja(@RequestParam("idProducto") long idproducto, @RequestParam("cantidad") double puja, RedirectAttributes flash) {
		Producto producto = productoService.visualizarProducto(idproducto);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.perfilUsuario(auth.getName());
		productoService.pujarProducto(producto, usuario, puja);
		flash.addFlashAttribute("info", "Su oferta ha sido enviada, si " + producto.getUsuario().getUsername() + " acepta se pondrá en contacto con usted");
		return "redirect:/producto/" + idproducto;
	}
	
	@RequestMapping(value = "/aceptarOferta/{username}/{idProducto}")
	public String formComunicacion(@PathVariable(value="username") String interesado,
			@PathVariable(value="idProducto") long idProducto, Model model) {
		
		model.addAttribute("titulo", "Datos a enviar");
		model.addAttribute("productoId", idProducto);
		model.addAttribute("interesado", interesado);
		return "formularioComunicacion";
	}
	
	@PostMapping("/enviarEmail")
	public String enviarEmail(@RequestParam(required = false) String email, @RequestParam(required = false) String telefono, 
			@RequestParam("otro") String otro, @RequestParam("interesado") String interesado, @RequestParam("idProducto") long idProducto,
			RedirectAttributes flash) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Producto producto = productoService.visualizarProducto(idProducto);
		Usuario usuario = usuarioService.perfilUsuario(auth.getName());
		Usuario userInteresado = usuarioService.perfilUsuario(interesado);
		String asunto = usuario.getUsername() + " ha aceptado su puja por el producto " + producto.getNombre();
		String mensajeInicial = "Puede contactar con " + usuario.getUsername() + " via:\n";
		if(email != null) {
			mensajeInicial += "Via email: " + usuario.getEmail() + ".\n";
		}
		if(telefono != null) {
			mensajeInicial += "Via teléfono: " + usuario.getTelefono() + ".\n";
		}
		if(otro != null) {
			mensajeInicial += "Otras vias: " + otro + ".\n";
		}
		mensajeInicial += "Valorar a " + usuario.getUsername() + "-> http://localhost:8080/puntuar/" + usuario.getUsername();
		flash.addFlashAttribute("info", "Correo enviado a " + userInteresado.getUsername() + " correctamente.");
		this.email.sendEmail(userInteresado.getEmail(), asunto, mensajeInicial);
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/formVendido/{idproducto}")
	public String formVendido(@PathVariable(value="idproducto") long idproducto, Model model) {
		model.addAttribute("titulo", "Producto vendido");
		Producto producto = productoService.visualizarProducto(idproducto);
		model.addAttribute("producto", producto);
		return "formVendido";
	}
	
	@PostMapping(value = "/confirmVendido/{idproducto}")
	public String productoVendidos(@PathVariable(value="idproducto") long idproducto, String nombre, RedirectAttributes flash, Model model) {
		long iduser = usuarioService.obtenerIdUsers(nombre);
		productoService.vendido(idproducto);
		Usuario usuario = usuarioService.perfilUsuario(nombre);
		productoService.confirmVendido(idproducto, usuario);
		//model.addAttribute("producto", productoService.productosVendidos();
		flash.addFlashAttribute("info", "El producto ha sido vendido");
		return "redirect:/listar";
	}
	
/*
	@RequestMapping(value = "/comprados")
	public String comprados(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.perfilUsuario(auth.getName());
		model.addAttribute("titulo", "Productos Comprados");
		model.addAttribute("productos", productoService.listarProductosComprados());		
		return "productosVendidos";
	}
	
	@RequestMapping(value = "/vendidos")
	public String vendidos(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.perfilUsuario(auth.getName());
		model.addAttribute("titulo", "Productos Vendidos");
		//model.addAttribute("productos", productoService.productosVendidos(usuario.getId()));		
		return "productosVendidos";
	}
	*/
}
