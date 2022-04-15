package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Pessoa;

public interface IPessoaDAO {
    
    void save(Pessoa obj);
	
	void update(Pessoa obj);
	
	void delete(Integer id);
	
	Pessoa findById(Integer id);
	
	List<Pessoa> findAll();

}
