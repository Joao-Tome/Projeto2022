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

import br.edu.toledoprudente.dao.CategoriaDAO;
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Categoria;
import br.edu.toledoprudente.entidades.Usuario;
@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaDAO dao;

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
        model.addAttribute("listacategorias",lista);
        return "/categoria/listar";
    }

    @GetMapping("/novo")
    public String novo(ModelMap model){
        model.addAttribute("categoria",new Categoria());
        return "categoria/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("categoria") Categoria obj, BindingResult result,  ModelMap model){
        try {

			if (result.hasErrors()){
                
				return "/categoria/cadastro";
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
        return "/categoria/cadastro";
    }

    @GetMapping("/alterar")
    public String alterar(int id, ModelMap model){
        var obj = dao.findById(id);
        model.addAttribute("categoria",obj);
        return "/categoria/cadastro";
    }

    @GetMapping("/excluir")
    public String excluir(int id, ModelMap model){
        try {
            dao.delete(id);
            
        } catch (Exception e) {
            model.addAttribute("mensagem","erroDelete");
        }
        return this.listar(model);
        
    }

}
