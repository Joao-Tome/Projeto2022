package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Noticia;

public interface INoticiaDAO {
    
    void save(Noticia obj);
	
	void update(Noticia obj);
	
	void delete(Integer id);
	
	Noticia findById(Integer id);
	
	List<Noticia> findAll();
}
