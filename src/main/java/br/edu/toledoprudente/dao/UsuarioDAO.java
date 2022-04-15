package br.edu.toledoprudente.dao;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.entidades.Usuario;

@Repository
public class UsuarioDAO extends AbstractDao<Usuario,Integer> implements IUsuarioDAO {
    
}
