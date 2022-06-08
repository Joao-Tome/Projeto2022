package br.edu.toledoprudente.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.edu.toledoprudente.dao.ParceiroDAO;
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Parceiros;
import br.edu.toledoprudente.entidades.Usuario;

@Controller
public class HomeController {

    @Autowired
	UsuarioDAO usuarioDAO;

	@Autowired
	ParceiroDAO parceiroDAO;

	

	@ModelAttribute(name="usuariologado")
	public Usuario usuario(){
		var nome = usuarioDAO.getUsuarioLogado();
		return nome;
	}

	@ModelAttribute(name="parceiros")
	public List<Parceiros> listausuario(){
		return parceiroDAO.findAll();
	}

    @GetMapping("/login")
	public String login() {
		
		return "/login";
	}
	
	@GetMapping("/home")
	public String home(ModelMap model) {
		//model.addAttribute("usuario",dao.getUsuarioLogado().getPessoa().getPrimeiroNome() );
		return "/home";
	}
	
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("mensagem","Dados inválidos");
		return "/login";
	}

}
