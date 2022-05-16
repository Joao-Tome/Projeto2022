package br.edu.toledoprudente.dao;

import java.util.List;

import br.edu.toledoprudente.entidades.Usuario;

public interface IUsuarioDAO {

    void save(Usuario obj);
	
	void update(Usuario obj);
	
	void delete(Integer id);
	
	Usuario findById(Integer id);
	
	List<Usuario> findAll();

	public Usuario findByUserName(String username);
}
