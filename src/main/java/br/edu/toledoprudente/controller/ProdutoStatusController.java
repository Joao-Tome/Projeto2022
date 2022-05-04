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

import br.edu.toledoprudente.dao.ProdutoStatusDAO;
import br.edu.toledoprudente.entidades.ProdutoStatus;
@Controller
@RequestMapping("/produtostatus")
public class ProdutoStatusController {

    @Autowired
    ProdutoStatusDAO dao;


    @GetMapping("/listar")
    public String listar(ModelMap model){
        var lista = dao.findAll();
        model.addAttribute("listaprodutostatus",lista);
        return "/produtostatus/listar";
    }

    @GetMapping("/novo")
    public String novo(ModelMap model){
        model.addAttribute("produtostatus",new ProdutoStatus());
        return "produtostatus/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produtostatus") ProdutoStatus obj, BindingResult result,  ModelMap model){
        try {

			if (result.hasErrors()){
				return "/produtostatus/cadastro";
			}

			if(obj.getId() == null){

                dao.save(obj);


			}else{
				dao.update(obj);
			}
		} catch (Exception e) {
		}
        return "/produtostatus/cadastro";
    }

    @GetMapping("/alterar")
    public String alterar(int id, ModelMap model){
        var obj = dao.findById(id);
        model.addAttribute("produtostatus",obj);
        return "/produtostatus/cadastro";
    }

    @GetMapping("/excluir")
    public String excluir(int id, ModelMap model){
        dao.delete(id);
        return this.listar(model);
    }
}
