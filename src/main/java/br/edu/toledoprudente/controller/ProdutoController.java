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

import br.edu.toledoprudente.dao.CategoriaDAO;
import br.edu.toledoprudente.dao.ProdutoDAO;
import br.edu.toledoprudente.entidades.Categoria;
import br.edu.toledoprudente.entidades.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoDAO dao;
	@Autowired
	CategoriaDAO daoCategoria;
	
	@ModelAttribute(name="listaCategoria")
	public List<Categoria> listarCategoria(){
		var lista = daoCategoria.findAll();
		return lista;
	}

	@GetMapping("/cadastro")
	public String Cadastro(ModelMap model) {
		
		model.addAttribute("produto",new Produto());
		return "/produto/cadastro";
		
	}

	@GetMapping("/listar")
	public String Listar(ModelMap model) {
		
		var lista = dao.findAll();
		
		model.addAttribute("listaProduto", lista);
		
		return "/produto/listar";
		
	}
	
	@GetMapping("/novo")
	public String Novo(ModelMap model) {
		model.addAttribute("produto",new Produto());
		return "/produto/cadastro";
		
	}

	@PostMapping("/salvar")
	public String Salvar(@Valid @ModelAttribute("produto") Produto obj, BindingResult result, ModelMap model){
		try {

			if (result.hasErrors()){
				return "/produto/cadastro";
			}

			if(obj.getId() == null){
				dao.save(obj);
			}else{
				dao.update(obj);
			}
		} catch (Exception e) {
			//TODO: handle exception
		}

		return "/produto/cadastro";
	}

	@GetMapping("/alterar")
	public String Alterar(int id, ModelMap model) {

		var obj = dao.findById(id);
				
		model.addAttribute("produto", obj);
		
		return "/produto/cadastro";
		
	}

	@GetMapping("/excluir")
	public String Excluir(int id, ModelMap model) {

		dao.delete(id);
		
		return this.Listar(model);
		
	}
}
