package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Produto;

public interface IProdutoDAO {
	
	void save(Produto obj);
	
	void update(Produto obj);
	
	void delete(Integer id);
	
	Produto findById(Integer id);
	
	List<Produto> findAll();

}
