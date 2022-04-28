package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Parceiros;

public interface IParceiroDAO {

    void save(Parceiros obj);
	
	void update(Parceiros obj);
	
	void delete(Integer id);
	
	Parceiros findById(Integer id);
	
	List<Parceiros> findAll();

}
