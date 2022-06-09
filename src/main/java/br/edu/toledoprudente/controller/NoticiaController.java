package br.edu.toledoprudente.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
    public String salvar(@Valid @ModelAttribute("noticia") Noticia obj, BindingResult result,  ModelMap model,
                         @RequestParam("file") MultipartFile file){
        try {

			if (result.hasErrors()){
				return "/noticia/cadastro";
			}else{
                if(file.isEmpty()){
                    model.addAttribute("mensagem","erro");
                    return "/noticia/cadastro";
                }else{
                    obj.setUrlImage(file.getOriginalFilename());
                    obj.setDataPub(LocalDate.now());
                    if(obj.getId() == null){
                        dao.save(obj);
                    }else{
                        dao.update(obj);
                    }
                    try {
						byte[] bytes = file.getBytes();
                        File directory = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imagens\\noticia\\");
						Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imagens\\noticia\\" + file.getOriginalFilename());
						if(!directory.exists()){
							directory.mkdirs();
						}
                        Files.write(path, bytes);

                        model.addAttribute("mensagem","success");
					} catch (Exception e) {
						model.addAttribute("mensagem","erro");
						e.printStackTrace();
					}
                }
            }

			
		} catch (Exception e) {
            model.addAttribute("mensagem","erro");
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
        try {
            dao.delete(id);
            
        } catch (Exception e) {
            model.addAttribute("mensagem","erroDelete");
        }
        return this.listar(model);
    }

    @ResponseBody
	@RequestMapping(value = "/getimagem/{nome}", method = RequestMethod.GET)
	public HttpEntity<byte[]> download(@PathVariable(value = "nome") String nome) throws IOException {
		byte[] arquivo =Files.readAllBytes( Paths.get(System.getProperty("user.dir") +"\\src\\main\\resources\\static\\imagens\\noticia\\" + nome));
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
