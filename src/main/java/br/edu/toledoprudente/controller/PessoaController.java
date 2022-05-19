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

import br.edu.toledoprudente.dao.PessoaDAO;
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Pessoa;
import br.edu.toledoprudente.entidades.Usuario;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
	PessoaDAO dao;
	

	@Autowired
	UsuarioDAO usuarioDAO;

	@ModelAttribute(name="usuariologado")
	public Usuario usuario(){
		var nome = usuarioDAO.getUsuarioLogado();
		return nome;
	}

	@GetMapping("/cadastro")
	public String Cadastro(ModelMap model) {
		
		model.addAttribute("pessoa",new Pessoa());
		return "/pessoa/cadastro";
		
	}

	@GetMapping("/listar")
	public String Listar(ModelMap model) {
		
		var lista = dao.findAll();
		
		model.addAttribute("listaPessoa", lista);
		
		return "/pessoa/listar";
		
	}
	
	@GetMapping("/novo")
	public String Novo(ModelMap model) {
		model.addAttribute("pessoa",new Pessoa());
		return "/pessoa/cadastro";
		
	}

	@PostMapping("/salvar")
	public String Salvar(@Valid @ModelAttribute("pessoa") Pessoa obj, BindingResult result, ModelMap model){
		try {

			if (result.hasErrors()){
				return "/pessoa/cadastro";
			}

			if(obj.getId() == null){
				dao.save(obj);
			}else{
				dao.update(obj);
			}
		} catch (Exception e) {
		}

		return "/pessoa/cadastro";
	}

	@GetMapping("/alterar")
	public String Alterar(int id, ModelMap model) {

		var obj = dao.findById(id);
				
		model.addAttribute("pessoa", obj);
		
		return "/pessoa/cadastro";
		
	}

	@GetMapping("/excluir")
	public String Excluir(int id, ModelMap model) {

		dao.delete(id);
		
		return this.Listar(model);
		
	}
    
}
