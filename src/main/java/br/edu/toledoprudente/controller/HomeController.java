package br.edu.toledoprudente.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.toledoprudente.dao.UsuarioDAO;

@Controller
public class HomeController {

    @Autowired
    private UsuarioDAO dao;

    @GetMapping("/login")
	public String login() {
		
		return "/login";
	}
	
	@GetMapping("/home")
	public String home(ModelMap model) {
		model.addAttribute("usuario",dao.getUsuarioLogado().getPessoa().getPrimeiroNome() );
		return "/home";
	}
	
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("mensagem","Dados inválidos");
		return "/login";
	}

}
