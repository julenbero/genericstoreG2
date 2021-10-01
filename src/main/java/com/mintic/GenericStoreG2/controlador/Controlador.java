package com.mintic.GenericStoreG2.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mintic.GenericStoreG2.interfaces.IUsuario;
import com.mintic.GenericStoreG2.model.Usuario;


@Controller
public class Controlador {

	@Autowired
	private IUsuario data;
	
	@GetMapping("/listarusuarios")
	public String listarusuarios(Model model) {
		List<Usuario> usuarios = (List<Usuario>)data.findAll();
		model.addAttribute("usuarios", usuarios);
		return "listarusuarios";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuarios";
	}
	
	@PostMapping("/guardarusuario")
	public String guardarUsuario(Usuario u) {
		data.save(u);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editarusuario/{cedula}")
    public String editarUsuario(@PathVariable Long cedula, Model model) {
    	Optional<Usuario> usuario = data.findById(cedula);
    	model.addAttribute("usuario",usuario);
    	return "usuarios";
    }
	
	@GetMapping("/borrarusuario/{cedula}")
    public String borrarUsuario(@PathVariable Long cedula) {
    	data.deleteById(cedula);
    	return "redirect:/usuarios";
    }
}
