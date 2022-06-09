package br.edu.toledoprudente.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

import br.edu.toledoprudente.dao.CategoriaDAO;
import br.edu.toledoprudente.dao.MarcaDAO;
import br.edu.toledoprudente.dao.ProdutoDAO;
import br.edu.toledoprudente.dao.ProdutoStatusDAO;
import br.edu.toledoprudente.dao.UsuarioDAO;
import br.edu.toledoprudente.entidades.Categoria;
import br.edu.toledoprudente.entidades.Marca;
import br.edu.toledoprudente.entidades.Produto;
import br.edu.toledoprudente.entidades.ProdutoStatus;
import br.edu.toledoprudente.entidades.Usuario;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	

	@Autowired
	ProdutoDAO dao;
	@Autowired
	CategoriaDAO daoCategoria;

	@Autowired
	MarcaDAO daomarca;

	@Autowired
	ProdutoStatusDAO daoStatusProd;

	@Autowired
	UsuarioDAO usuarioDAO;

	@ModelAttribute(name="usuariologado")
	public Usuario usuario(){
		var nome = usuarioDAO.getUsuarioLogado();
		return nome;
	}

	@ModelAttribute(name="listaCategoria")
	public List<Categoria> listarCategoria(){
		var lista = daoCategoria.findAll();
		return lista;
	}

	@ModelAttribute(name="listarMarcas")
	public List<Marca> listarMarcas(){
		var lista = daomarca.findAll();
		return lista;
	}

	@ModelAttribute(name="listarProdStatus")
	public List<ProdutoStatus> listarProdStatus(){
		var lista = daoStatusProd.findAll();
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
	public String Salvar(@Valid @ModelAttribute("produto") Produto obj, BindingResult result, ModelMap model,
						 @RequestParam("file") MultipartFile file){
		try {

			if (result.hasErrors()){
				return "/produto/cadastro";
			}else{
				if(file.isEmpty()){
					model.addAttribute("mensagem","O arquivo é Obrigatorio");
					model.addAttribute("retorno",true);
					return "/produto/cadastro";
				}else{
					obj.setImagem(file.getOriginalFilename());
					if(obj.getId() == null){
						dao.save(obj);
					}else{
						dao.update(obj);
					}
					
					try {
						byte[] bytes = file.getBytes();
						Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imagens\\" + file.getOriginalFilename());
						Files.write(path, bytes);

						model.addAttribute("mensagem","success");
					} catch (Exception e) {
						model.addAttribute("mensagem","erro");
						e.printStackTrace();
					}


					model.addAttribute("validacao","produto Enviado Com Sucesso");
				}
			}

			
		} catch (Exception e) {
			model.addAttribute("mensagem","erro");
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

		try {
            dao.delete(id);
            
        } catch (Exception e) {
            model.addAttribute("mensagem","erroDelete");
        }
        return this.Listar(model);
		
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
		return entity;
	}

}
