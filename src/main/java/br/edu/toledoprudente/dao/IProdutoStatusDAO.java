package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.ProdutoStatus;

public interface IProdutoStatusDAO {
    
    void save(ProdutoStatus obj);
	
	void update(ProdutoStatus obj);
	
	void delete(Integer id);
	
	ProdutoStatus findById(Integer id);
	
	List<ProdutoStatus> findAll();


}
