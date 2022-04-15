package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Telefone;

public interface ITelefoneDAO {
    
    void save(Telefone obj);
	
	void update(Telefone obj);
	
	void delete(Integer id);
	
	Telefone findById(Integer id);
	
	List<Telefone> findAll();


}
