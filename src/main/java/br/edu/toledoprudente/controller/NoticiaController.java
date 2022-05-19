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
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Noticia;
import br.edu.toledoprudente.entidades.Usuario;
@Controller
@RequestMapping("/noticia")
public class NoticiaController {
    @Autowired
    NoticiaDAO dao;


    @Autowired
	UsuarioDAO usuarioDAO;

	@ModelAttribute(name="usuariologado")
	public Usuario usuario(){
		var nome = usuarioDAO.getUsuarioLogado();
		return nome;
	}

    @GetMapping("/listar")
    public String listar(ModelMap model){
        var lista = dao.findAll();
        model.addAttribute("listanoticia",lista);
        return "/noticia/listar";
    }

    @GetMapping("/novo")
    public String novo(ModelMap model){
        model.addAttribute("noticia",new Noticia());
        return "noticia/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("noticia") Noticia obj, BindingResult result,  ModelMap model){
        try {

			if (result.hasErrors()){
				return "/noticia/cadastro";
			}

			if(obj.getId() == null){

                dao.save(obj);


			}else{
				dao.update(obj);
			}
		} catch (Exception e) {
		}
        return "/noticia/cadastro";
    }

    @GetMapping("/alterar")
    public String alterar(int id, ModelMap model){
        var obj = dao.findById(id);
        model.addAttribute("noticia",obj);
        return "/noticia/cadastro";
    }

    @GetMapping("/excluir")
    public String excluir(int id, ModelMap model){
        dao.delete(id);
        return this.listar(model);
    }
}
