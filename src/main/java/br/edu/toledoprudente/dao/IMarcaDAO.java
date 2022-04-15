package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Marca;

public interface IMarcaDAO {
    
    void save(Marca obj);
	
	void update(Marca obj);
	
	void delete(Integer id);
	
	Marca findById(Integer id);
	
	List<Marca> findAll();

}
