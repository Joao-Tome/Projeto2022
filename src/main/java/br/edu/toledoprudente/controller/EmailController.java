package br.edu.toledoprudente.controller;

import java.util.List;

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
import br.edu.toledoprudente.dao.EmailDAO;
import br.edu.toledoprudente.entidades.Email;
import br.edu.toledoprudente.entidades.Pessoa;
import br.edu.toledoprudente.entidades.Usuario;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
	EmailDAO dao;
	@Autowired
	PessoaDAO daoPessoa;
	


	@Autowired
	UsuarioDAO usuarioDAO;

	@ModelAttribute(name="usuariologado")
	public Usuario usuario(){
		var nome = usuarioDAO.getUsuarioLogado();
		return nome;
	}

	@ModelAttribute(name="listaPessoa")
	public List<Pessoa> listaPessoa(){
		var lista = daoPessoa.findAll();
		return lista;
	}

	@GetMapping("/cadastro")
	public String Cadastro(ModelMap model) {
		
		model.addAttribute("email",new Email());
		return "/email/cadastro";
		
	}

	@GetMapping("/listar")
	public String Listar(ModelMap model) {
		
		var lista = dao.findAll();
		
		model.addAttribute("listaEmail", lista);
		
		return "/email/listar";
		
	}
	
	@GetMapping("/novo")
	public String Novo(ModelMap model) {
		model.addAttribute("email",new Email());
		return "/email/cadastro";
		
	}

	@PostMapping("/salvar")
	public String Salvar(@Valid @ModelAttribute("email") Email obj, BindingResult result, ModelMap model){
		try {

			if (result.hasErrors()){
				return "/email/cadastro";
			}

			if(obj.getId() == null){
				dao.save(obj);
			}else{
				dao.update(obj);
			}
			model.addAttribute("mensagem","success");
		} catch (Exception e) {
			model.addAttribute("mensagem","erro");
		}

		return "/email/cadastro";
	}

	@GetMapping("/alterar")
	public String Alterar(int id, ModelMap model) {

		var obj = dao.findById(id);
				
		model.addAttribute("email", obj);
		
		return "/email/cadastro";
		
	}

	@GetMapping("/excluir")
	public String Excluir(int id, ModelMap model) {

		dao.delete(id);
		
		return this.Listar(model);
		
	}
}
