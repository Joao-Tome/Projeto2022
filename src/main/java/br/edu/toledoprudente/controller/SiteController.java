package br.edu.toledoprudente.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.toledoprudente.dao.NoticiaDAO;
import br.edu.toledoprudente.dao.ParceiroDAO;
import br.edu.toledoprudente.dao.PessoaDAO;
import br.edu.toledoprudente.dao.ProdutoDAO;
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Parceiros;
import br.edu.toledoprudente.entidades.Pessoa;
import br.edu.toledoprudente.entidades.Produto;
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

	@Autowired
	PessoaDAO daopessoa;

	@Autowired
	ParceiroDAO daoparceiro;


	@ModelAttribute(name="usuariologado")
	public Usuario usuarioLogado(){
		return daousuario.getUsuarioLogado();
	}

	@ModelAttribute(name="parceiros")
	public List<Parceiros> listausuario(){
		return daoparceiro.findAll();
	}

    @GetMapping("")
	public String home(ModelMap model) {
		List<Produto> listaprodutodestaque = daoproduto.getdestaque();
		List<Produto> listaprodutotodos = daoproduto.getnodestaque();
		
		List<Produto> listaproduto = new ArrayList<Produto>();
		listaproduto.addAll(listaprodutodestaque);
		listaproduto.addAll(listaprodutotodos);

		var listanoticia = daonoticia.findAll();

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




	@PostMapping("/salvarSitePessoa")
	public String salvarPessoaPeloSite(@Valid @ModelAttribute("pessoa") Pessoa obj, BindingResult result, ModelMap model){
		
		try {

			if (result.hasErrors()){
				return "/site/cadastroPessoa";
			}

			if(obj.getId() == null){
				daopessoa.save(obj);
			}else{
				daopessoa.update(obj);
			}
			model.addAttribute("mensagem","success");

			model.addAttribute("pessoa",obj);
			model.addAttribute("usuario", new Usuario());
			return "/site/CadastroUsuario";
		} catch (Exception e) {
			model.addAttribute("mensagem","erro");
		}

		return "/site/cadastroPessoa";
		
	}

	@GetMapping("/cadastrarSitePessoa")
	public String cadastrarPessoaPeloSite(ModelMap model){
		model.addAttribute("pessoa", new Pessoa());
		return "/site/cadastroPessoa";
		
	}


	@PostMapping("/salvarSiteUsuario")
	public String salvarUsuarioPeloSite(@Valid @ModelAttribute("usuario") Usuario obj, BindingResult result, ModelMap model){
		
		try {

			if (result.hasErrors()){
				return "/site/CadastroUsuario";
			}
			
			String senha = obj.getSenha();
			obj.setSenha(new BCryptPasswordEncoder().encode(senha));

			if(obj.getId() == null){
				daousuario.save(obj);
			}else{
				daousuario.update(obj);
			}
			model.addAttribute("mensagem","success");

			model.addAttribute("pessoa",obj);
			return "/login";
		} catch (Exception e) {
			model.addAttribute("mensagem","erro");
		}

		return "/site/CadastroUsuario";
		
	}

	@GetMapping("/cadastrarSiteUsuario")
	public String cadastrarUsuarioPeloSite(ModelMap model){
		model.addAttribute("usuario", new Usuario());
		return "/site/cadastroUsuario";
		
	}



}
