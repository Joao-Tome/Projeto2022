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
import br.edu.toledoprudente.dao.TipoUsuarioDAO;

import br.edu.toledoprudente.entidades.Usuario;
import br.edu.toledoprudente.entidades.tipoUsuario;
import br.edu.toledoprudente.entidades.Pessoa;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
	UsuarioDAO dao;
	@Autowired
	PessoaDAO daoPessoa;
    @Autowired
	TipoUsuarioDAO daotipoUsuario;
	
	@ModelAttribute(name="listaPessoa")
	public List<Pessoa> listaPessoa(){
		var lista = daoPessoa.findAll();
		return lista;
	}

    @ModelAttribute(name="listatipoUsuario")
	public List<tipoUsuario> listatipoUsuario(){
		var lista = daotipoUsuario.findAll();
		return lista;
	}

	@GetMapping("/cadastro")
	public String Cadastro(ModelMap model) {
		
		model.addAttribute("usuario",new Usuario());
		return "/usuario/cadastro";
		
	}

	@GetMapping("/listar")
	public String Listar(ModelMap model) {
		
		var lista = dao.findAll();
		
		model.addAttribute("listaUsuario", lista);
		
		return "/usuario/listar";
		
	}
	
	@GetMapping("/novo")
	public String Novo(ModelMap model) {
		model.addAttribute("usuario",new Usuario());
		return "/usuario/cadastro";
		
	}

	@PostMapping("/salvar")
	public String Salvar(@Valid @ModelAttribute("usuario") Usuario obj, BindingResult result, ModelMap model){
		try {

			if (result.hasErrors()){
				return "/usuario/cadastro";
			}

			if(obj.getId() == null){
				dao.save(obj);
			}else{
				dao.update(obj);
			}
		} catch (Exception e) {
			//TODO: handle exception
		}

		return "/usuario/cadastro";
	}

	@GetMapping("/alterar")
	public String Alterar(int id, ModelMap model) {

		var obj = dao.findById(id);
				
		model.addAttribute("usuario", obj);
		
		return "/usuario/cadastro";
		
	}

	@GetMapping("/excluir")
	public String Excluir(int id, ModelMap model) {

		dao.delete(id);
		
		return this.Listar(model);
		
	}
}
