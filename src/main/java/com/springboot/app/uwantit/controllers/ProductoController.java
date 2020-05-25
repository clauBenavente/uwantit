package com.springboot.app.uwantit.controllers;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.springboot.app.uwantit.models.entity.RespuestaJSON;
import com.springboot.app.uwantit.models.entity.Producto;
import com.springboot.app.uwantit.models.entity.ProductoVendidos;
import com.springboot.app.uwantit.models.entity.Usuario;
import com.springboot.app.uwantit.models.service.EnvioEmail;
import com.springboot.app.uwantit.models.service.IProductoService;
import com.springboot.app.uwantit.models.service.IUsuarioService;
import com.springboot.app.uwantit.util.paginator.PaginaRender;

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
	public String listarTodosLosProductos(Model model,
			@RequestParam(name= "pagina", defaultValue = "0") int page, 
			@RequestParam(name = "filtro", required = false) String term, 
			@RequestParam(name = "descripcion", required = false) Long descripcion) {
		long descObtenida = -1l;
		String filtro = "";
		if(term != null) {
			if(!term.isBlank()) {
				filtro = term;
			}
		}
		if(descripcion != null) {
			descObtenida = descripcion.longValue();
		}
		Pageable pageRequest = PageRequest.of(page, 6);
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("categorias", productoService.listadoCategorias());
		if(term != null && !term.isBlank()) {
			Page<Producto> producto = productoService.findByNombre(term, pageRequest);
			PaginaRender<Producto> paginaRender = new PaginaRender<>("/listar", producto);
			model.addAttribute("page", paginaRender);
			model.addAttribute("productos", producto);
			model.addAttribute("filtro", filtro);
			if(descObtenida != -1l) {
				producto = productoService.findByNombreAndCategoria(term, descObtenida, pageRequest);
				paginaRender = new PaginaRender<>("/listar", producto);
				model.addAttribute("page", paginaRender);
				model.addAttribute("selected", descObtenida);
				model.addAttribute("productos", producto);
				model.addAttribute("filtro", filtro);
			}
		}else if(descObtenida != -1l){
			Page<Producto> producto = productoService.productoPorCategoria(descObtenida, pageRequest);
			PaginaRender<Producto> paginaRender = new PaginaRender<>("/listar", producto);
			model.addAttribute("productos", producto);
			model.addAttribute("selected", descObtenida);
			model.addAttribute("page", paginaRender);
		}else {
			
			Page<Producto> producto = productoService.productosEnVenta(pageRequest);
			PaginaRender<Producto> paginaRender = new PaginaRender<>("/listar", producto);
			model.addAttribute("productos", producto);
			model.addAttribute("page", paginaRender);
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
			@RequestParam("fotos") List<MultipartFile> fotos, @RequestParam("categoria") int id,
			RedirectAttributes flash, Authentication authentication, SessionStatus status){
		
			String todasFotos = "";
				for(MultipartFile foto: fotos) {
					if(foto.isEmpty()) continue;
					Path rootPath = Paths.get("uploads").resolve(foto.getOriginalFilename());
					Path rootAbsolutePath = rootPath.toAbsolutePath();
					try {
						Files.copy(foto.getInputStream(), rootAbsolutePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
					todasFotos += foto.getOriginalFilename() + ",";
				}
				String otrasFotos = "";
				String[] todas = todasFotos.split(",");
				String fotoPrincipal = todas[0];
				List<String> resto = Arrays.asList(todas).subList(1, todas.length);
				for (String foto : resto) {
					otrasFotos += foto + ",";
				}
				producto.setFotos(otrasFotos);
				producto.setFotoPrincipal(fotoPrincipal);
			
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
		String fotoPrincipal = producto.getFotoPrincipal();
		List<String> otros = Arrays.asList(producto.getFotos().split(","));
		model.put("favorito", favorito);
		model.put("borrable", borrable);
		model.put("editable", editable);
		model.put("producto", producto);
		model.put("fotoPrincipal", fotoPrincipal);
		model.put("fotos", otros);
		model.put("titulo", producto.getNombre());
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
	
	@RequestMapping(value = "/formVendido/{idproducto}")
	public String formVendido(@PathVariable(value="idproducto") long idproducto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = usuarioService.perfilUsuario(auth.getName());
		Set<String> conversaciones = user.getEnviados().stream().map(enviado -> enviado.getRecibe().getUsername()).collect(Collectors.toSet());
		Producto producto = productoService.visualizarProducto(idproducto);
		model.addAttribute("producto", producto);
		model.addAttribute("conversaciones", conversaciones);
		model.addAttribute("titulo", "Producto vendido");
		return "formVendido";
	}
	
	@PostMapping(value = "/confirmVendido/{idproducto}")
	public String productoVendidos(@PathVariable(value="idproducto") long idproducto, String nombre, RedirectAttributes flash, Model model) {
		long iduser = usuarioService.obtenerIdUsers(nombre);
		productoService.vendido(idproducto);
		Usuario usuario = usuarioService.perfilUsuario(nombre);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario vendedor = usuarioService.perfilUsuario(auth.getName());
		productoService.confirmVendido(idproducto, usuario);
		String asunto = "Valore su experiencia con " + vendedor.getUsername();
		String mensajeInicial = "Hola " + usuario.getUsername() + " puntue su experiencia con el usuario " +
		vendedor.getUsername() + " -> http://localhost:8080/puntuar/" + vendedor.getUsername();
		this.email.sendEmail(usuario.getEmail(), asunto, mensajeInicial);
		flash.addFlashAttribute("info", "El producto ha sido vendido");
		return "redirect:/listar";
	}
	
	
	@RequestMapping(value = "/vendidos")
	public String vendidos(Model model, @RequestParam(name= "page", defaultValue = "0") int page) {
		Pageable pageRequest = PageRequest.of(page, 6);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.perfilUsuario(auth.getName());
		Page<Producto> producto = productoService.productosVendidos(usuario.getId(),pageRequest);
		PaginaRender<Producto> paginaRender = new PaginaRender<>("/vendidos", producto);
		model.addAttribute("usuario",usuario.getUsername());
		model.addAttribute("titulo", "Productos Vendidos");
		model.addAttribute("productos", producto);
		model.addAttribute("page", paginaRender);
		return "productosVendidos";
	}
	
	@RequestMapping(value = "/comprados")
	public String comprados(Model model, @RequestParam(name= "page", defaultValue = "0") int page) {
		Pageable pageRequest = PageRequest.of(page, 6);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.perfilUsuario(auth.getName());
		Page<ProductoVendidos> producto = productoService.productosComprados(usuario.getId(),pageRequest);
		PaginaRender<ProductoVendidos> paginaRender = new PaginaRender<>("/comprados", producto);
		model.addAttribute("titulo", "Productos Comprados");
	    model.addAttribute("productos",producto);
	    model.addAttribute("usuario",usuario.getUsername());
	    model.addAttribute("page", paginaRender);
		return "productosComprados";
	}
}
