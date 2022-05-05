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

import br.edu.toledoprudente.dao.ParceiroDAO;
import br.edu.toledoprudente.entidades.Parceiros;
@Controller
@RequestMapping("/parceiro")
public class ParceiroController {
    @Autowired
    ParceiroDAO dao;


    @GetMapping("/listar")
    public String listar(ModelMap model){
        var lista = dao.findAll();
        model.addAttribute("listaParceiro",lista);
        return "/parceiro/listar";
    }

    @GetMapping("/novo")
    public String novo(ModelMap model){
        model.addAttribute("parceiro",new Parceiros());
        return "parceiro/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("parceiro") Parceiros obj, BindingResult result,  ModelMap model){
        try {

			if (result.hasErrors()){
				return "/parceiro/cadastro";
			}

			if(obj.getId() == null){

                dao.save(obj);


			}else{
				dao.update(obj);
			}
		} catch (Exception e) {
		}
        return "/parceiro/cadastro";
    }

    @GetMapping("/alterar")
    public String alterar(int id, ModelMap model){
        var obj = dao.findById(id);
        model.addAttribute("parceiro",obj);
        return "/parceiro/cadastro";
    }

    @GetMapping("/excluir")
    public String excluir(int id, ModelMap model){
        dao.delete(id);
        return this.listar(model);
    }
}
