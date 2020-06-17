package com.springboot.app.uwantit.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.uwantit.models.entity.RespuestaJSON;
import com.springboot.app.uwantit.models.entity.ComunicacionProductos;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.Puntuacion;
import com.springboot.app.uwantit.models.entity.Usuario;
import com.springboot.app.uwantit.models.service.EnvioEmail;
import com.springboot.app.uwantit.models.service.IProductoService;
import com.springboot.app.uwantit.models.service.IUsuarioService;

@SessionAttributes("usuario")
@Controller
public class UsuarioController {
	 
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private EnvioEmail email;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping(value="/form")
	public String formularioRegitro(Model model) {
		model.addAttribute("titulo", "Registrarse");
		model.addAttribute("usuario", new Usuario());
		return "formularioRegistro";
	}
	
	@RequestMapping(value = "/formeditar")
	public String editar(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = service.perfilUsuario(auth.getName());
		
		model.addAttribute("titulo", "Editar");
		model.addAttribute("usuario", usuario);
		return "formularioRegistro";
	}
	
	@PostMapping(value="/form")
	public String procesarRegistro(@Valid Usuario usuario, BindingResult resultado, Model model,
			@RequestParam("foto_perfil") MultipartFile fotoPerfil, RedirectAttributes flash, SessionStatus estado,
			@ModelAttribute("usuario") Usuario userEditado) {
		if(resultado.hasErrors()) {
			model.addAttribute("titulo", "Registro");
			return "formularioRegistro";
		}
		if(!fotoPerfil.isEmpty()) {
			Path rootPath = Paths.get("uploads").resolve(fotoPerfil.getOriginalFilename());
			Path rootAbsolutePath = rootPath.toAbsolutePath();
			try {
				Files.copy(fotoPerfil.getInputStream(), rootAbsolutePath);
				usuario.setFotoPerfil(fotoPerfil.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEnabled(true);
		service.insertarUsuario(usuario);
		if(userEditado.getUsername() == null){
			service.insertarRolUsuario("ROLE_USER", usuario.getId());
			flash.addFlashAttribute("success", "Usuario creado correctamente, por favor inicie sesion");
			String asunto = "Bienvenido a Uwantit";
			String mensajeInicial = "Bienvenido a " + usuario.getNombre()+ " " + usuario.getApellido() + "se acaba de registrar en Uwantit.";
			email.sendEmail(usuario.getEmail(), asunto, mensajeInicial);
		}
		estado.setComplete();
		flash.addFlashAttribute("success", "Usuario editado correctamente");
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
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
	public String verPerfil(@PathVariable(value = "usuario") String username, Model model, RedirectAttributes flash) {
		Usuario usuario = null;
		List<Producto> listaProductos = null;
		int total = 0;
		boolean esAdmin = false;
		if(service.perfilUsuario(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()
				.get(0).getAuthority().equals("ROLE_ADMIN")) {
			esAdmin = true;
		}
		if (username != null) {
			usuario = service.perfilUsuario(username);
			if(usuario == null) {
				flash.addFlashAttribute("danger", "El usuario no existe");
				return "redirect:/listar";
			}
			if(usuario.getEsPuntuado().size() > 0) {
				for (Puntuacion puntuacion : usuario.getEsPuntuado()) {
					total += puntuacion.getPuntos();
				}
				total = total / usuario.getEsPuntuado().size();
			}
			model.addAttribute("usuarioPropio", SecurityContextHolder.getContext().getAuthentication().getName().equals(usuario.getUsername()));
			model.addAttribute("admin", esAdmin);
			model.addAttribute("usuario", usuario);
			model.addAttribute("titulo", "Perfil" + usuario.getNombre());
			model.addAttribute("listado", listaProductos);
			model.addAttribute("media", total);
			model.addAttribute("productos", productoService.productosEnVentaPerfil(usuario.getId()));
		} else {
			return "redirect:/listar";
		}
		return "vistaUsuario";
	}
	
	@RequestMapping(value = "/verPerfil")
	public String verPerfilPropio(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = service.perfilUsuario(auth.getName());
		List<Producto> listaProductos = null;
		int total = 0;
		if(usuario.getEsPuntuado().size() > 0) {
			for (Puntuacion puntuacion : usuario.getEsPuntuado()) {
				total += puntuacion.getPuntos();
			}
			total = total / usuario.getEsPuntuado().size();
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Perfil" + usuario.getNombre());
		model.addAttribute("listado", listaProductos);
		model.addAttribute("media", total);
		model.addAttribute("usuarioPropio", true);
		model.addAttribute("esAdmin", false);
		model.addAttribute("productos", productoService.productosEnVentaPerfil(usuario.getId()));
		
		return "vistaUsuario";
	}
	
	@GetMapping(value = "/puntuar/{usuario}/{idProducto}")
	public String puntuar(@PathVariable(value = "usuario") String puntuado, @PathVariable long idProducto,
			Model model) {
		model.addAttribute("titulo", "Puntuacion");
		model.addAttribute("puntuado", puntuado);
		model.addAttribute("producto", idProducto);
		return "vistaPuntuar";
	}
	
	@RequestMapping(value= "/favorito")
	public String verFavoritos(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = service.perfilUsuario(auth.getName());
		List<Producto> favoritos = usuario.getProductoFavorito().stream().filter(producto -> !producto.isVendido()).collect(Collectors.toList());
		model.addAttribute("titulo", "Productos Favoritos");
		model.addAttribute("favoritos", favoritos);
		model.addAttribute("usuario", usuario);
		return "vistaFavoritos"; 
	}
	
	@PostMapping(value = "/puntuar")
	public String annadirPuntuacion(Model model, @RequestParam("puntos") int puntos, @RequestParam("puntuado") String usernamePuntuado, 
			@RequestParam long producto, RedirectAttributes flash) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario puntuador = service.perfilUsuario(auth.getName());
		Usuario puntuado = service.perfilUsuario(usernamePuntuado);
		if(service.haPuntuado(puntuado, puntuador, producto) != null) {
			flash.addFlashAttribute("danger", "No puedes puntuar dos veces una compra");
			return "redirect:/listar";
		}
		service.insertarPuntuacion(puntos, puntuado.getId(), puntuador.getId(), producto);
		flash.addFlashAttribute("info", "Puntuacion realizada");
		return "redirect:/listar";
	}
	
	@GetMapping(value="/formularioRecuperar")
	public String formularioRecuperar(Model model) {
		model.addAttribute("titulo", "Recuperar Contraseña");
		return "formularioRecuperar";
	}
	
	@PostMapping(value="/proRecuperar")
	public String proRecuperar(@RequestParam("correo") String correo, RedirectAttributes flash) {
		if(service.recuperarUsuario(correo) == null) {
			flash.addFlashAttribute("success", "No existe este usuario");
			return "redirect:/login";
		}else {
			Usuario usuario = service.recuperarUsuario(correo);
			String mensaje = "Hola usuario " + usuario.getNombre() + "se le envia este email para que recuerde que su contraseña es ->" + usuario.getPassword() + "<-";
			email.sendEmail(usuario.getEmail(), "Contraseña olvidada", mensaje);
			flash.addFlashAttribute("success", "Se le ha enviado un correo con la contraseña");
			return "redirect:/listar";
		}
		
	}
	
	@RequestMapping(value="/preguntasFrecuentes")
	public String preguntasFrecuentes() {
		return "preguntasFrecuentes";
	}
	
	@ResponseBody
	@RequestMapping(value= "/comprobarUsername/{username}")
	public RespuestaJSON comprobarUsername(@PathVariable(value = "username") String username) {
		
		RespuestaJSON respuesta = new RespuestaJSON();
		Usuario user = service.perfilUsuario(username);
		if(user != null) {
			respuesta.setEstado(true);
		}else {
			respuesta.setEstado(false);
		}
		return respuesta;
	}

	@PostMapping("/enviarMensaje")
	public String enviarMensaje(@RequestParam("destinatario") String destinatario, @RequestParam("mensaje") String mensaje,
			@RequestParam("producto") String producto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario envia = service.perfilUsuario(auth.getName());
		Usuario recibe = service.perfilUsuario(destinatario);
		service.enviarMensaje(mensaje, envia, recibe);
		if(producto.equals("")) {
			return "redirect:/verMensajes/" + destinatario;
		}
		return "redirect:/producto/" + producto;
	}
	
	@RequestMapping(value= {"/verMensajes","/verMensajes/{username}"})
	public String mensajesUsuario(Model model, @PathVariable(required = false) String username) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = service.perfilUsuario(auth.getName());
		List<ComunicacionProductos> conversacionesBBDD = service.obtenerConversacion(usuario, usuario);
		Set<String> nombres = new HashSet<>();
		List<ComunicacionProductos> chatFinal = new ArrayList<>(); 
		Boolean hayMensajes = false;
		if(username != null) {
			hayMensajes = true;
			for (ComunicacionProductos mensaje : conversacionesBBDD) {
				if(mensaje.getEnvia().getUsername().equals(username) || 
						mensaje.getRecibe().getUsername().equals(username) ) {
					chatFinal.add(mensaje);
				}
			}
		}
		for (ComunicacionProductos mensaje : conversacionesBBDD) {
			if(mensaje.getEnvia() != usuario) {
				nombres.add(mensaje.getEnvia().getUsername());
			}
			if(mensaje.getRecibe() != usuario) {
				nombres.add(mensaje.getRecibe().getUsername());
			}
			
		}
		model.addAttribute("chats", nombres);
		model.addAttribute("hayMensajes", hayMensajes);
		model.addAttribute("usuarioConversacion", username);
		model.addAttribute("chatFinal", chatFinal);
		return "vistaMensajes";
	}
	
	@GetMapping("/eliminar/usuario/{username}")
	public String eliminarUsuario(@PathVariable String username, RedirectAttributes flash) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = service.perfilUsuario(auth.getName());
		Usuario usuario = service.perfilUsuario(username);
		if(!user.getRoles().get(0).getAuthority().equals("ROLE_ADMIN")) {
			flash.addFlashAttribute("danger", "No tienes permisos para eliminar usuarios");
			return "redirect:/";
		}
		for (Producto producto : usuario.getProductos()) {
			productoService.borrarProductoVendidos(producto.getIdProducto());
			productoService.borrarProducto(producto.getIdProducto());
		}
		service.borrarUsuario(usuario.getId());
		flash.addFlashAttribute("info", "Usuario eliminado");
		return "redirect:/";
	}
}
