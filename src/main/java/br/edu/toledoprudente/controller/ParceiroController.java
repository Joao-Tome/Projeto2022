package br.edu.toledoprudente.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.edu.toledoprudente.dao.ParceiroDAO;
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Parceiros;
import br.edu.toledoprudente.entidades.Usuario;

@Controller
@RequestMapping("/parceiro")
public class ParceiroController {
    @Autowired
    ParceiroDAO dao;

    @Autowired
    UsuarioDAO usuarioDAO;

    @ModelAttribute(name = "usuariologado")
    public Usuario usuario() {
        var nome = usuarioDAO.getUsuarioLogado();
        return nome;
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        var lista = dao.findAll();
        model.addAttribute("listaParceiro", lista);
        return "/parceiro/listar";
    }

    @GetMapping("/novo")
    public String novo(ModelMap model) {
        model.addAttribute("parceiro", new Parceiros());
        return "parceiro/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("parceiro") Parceiros obj, BindingResult result, ModelMap model,
            @RequestParam("file") MultipartFile file) {
        try {

            if (result.hasErrors()) {
                return "/parceiro/cadastro";
            } else {
                if (file.isEmpty()) {
                    model.addAttribute("mensagem", "erro");
                    return "/noticia/cadastro";
                } else {
                    obj.setUrlImage(file.getOriginalFilename());
                    if (obj.getId() == null) {
                        dao.save(obj);
                    } else {
                        dao.update(obj);
                    }
                    model.addAttribute("mensagem", "success");

                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imagens\\" + file.getOriginalFilename());
                    Files.write(path, bytes);

                    model.addAttribute("mensagem","success");
                }
            }

        } catch (Exception e) {
            model.addAttribute("mensagem", "erro");
        }
        return "/parceiro/cadastro";
    }

    @GetMapping("/alterar")
    public String alterar(int id, ModelMap model) {
        var obj = dao.findById(id);
        model.addAttribute("parceiro", obj);
        return "/parceiro/cadastro";
    }

    @GetMapping("/excluir")
    public String excluir(int id, ModelMap model) {
        dao.delete(id);
        return this.listar(model);
    }

    @ResponseBody
	@RequestMapping(value = "/getimagem/{nome}", method = RequestMethod.GET)
	public HttpEntity<byte[]> download(@PathVariable(value = "nome") String nome) throws IOException {
		byte[] arquivo =Files.readAllBytes( Paths.get(System.getProperty("user.dir") +"\\src\\main\\resources\\static\\imagens\\" + nome));
		HttpHeaders httpHeaders = new HttpHeaders();
		switch (nome.substring(nome.lastIndexOf(".") + 1).toUpperCase()) {
		case "JPG":
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);break;
		case "GIF":
		httpHeaders.setContentType(MediaType.IMAGE_GIF); break;
		case "PNG":
		httpHeaders.setContentType(MediaType.IMAGE_PNG); break;
		default:
		break;
		} httpHeaders.setContentLength(arquivo.length);
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
		return entity;}
}
