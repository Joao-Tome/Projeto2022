package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Email;

public interface IEmailDAO {
    
    void save(Email obj);
	
	void update(Email obj);
	
	void delete(Integer id);
	
	Email findById(Integer id);
	
	List<Email> findAll();


}
