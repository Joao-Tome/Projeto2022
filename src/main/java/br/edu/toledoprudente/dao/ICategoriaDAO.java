package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Categoria;

public interface ICategoriaDAO {

	void save(Categoria obj);
	
	void update(Categoria obj);
	
	void delete(Integer id);
	
	Categoria findById(Integer id);
	
	List<Categoria> findAll();
	
}
