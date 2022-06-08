package br.edu.toledoprudente.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.toledoprudente.dao.NoticiaDAO;
import br.edu.toledoprudente.dao.PessoaDAO;
import br.edu.toledoprudente.dao.ProdutoDAO;
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Pessoa;
import br.edu.toledoprudente.entidades.Usuario;

@Controller
@RequestMapping("/site")
public class SiteController {
    
	@Autowired
	ProdutoDAO daoproduto;

	@Autowired
	UsuarioDAO daousuario;
	
	@Autowired
	NoticiaDAO daonoticia;


    @GetMapping("")
	public String home(ModelMap model) {
		var listaproduto = daoproduto.findAll();
		var listanoticia = daonoticia.findAll();
		
		model.addAttribute("usuario", daousuario.getUsuarioLogado());

		model.addAttribute("Produtos", listaproduto);

		model.addAttribute("noticias", listanoticia);

		return "/site/home";
	}
	
	@GetMapping("/detalhes")
	public String detalhesproduto(int id, ModelMap model){
		
		var produto = daoproduto.findById(id);

		model.addAttribute("produto", produto);

		return "/site/produtodetalhes";
	}

}
