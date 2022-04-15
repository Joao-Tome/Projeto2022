package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.tipoUsuario;

public interface ITipoUsuarioDAO {

    void save(tipoUsuario obj);
	
	void update(tipoUsuario obj);
	
	void delete(Integer id);
	
	tipoUsuario findById(Integer id);
	
	List<tipoUsuario> findAll();
    
}
