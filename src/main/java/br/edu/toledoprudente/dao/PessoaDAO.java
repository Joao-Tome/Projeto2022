package br.edu.toledoprudente.dao;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.entidades.Pessoa;

@Repository
public class PessoaDAO extends AbstractDao<Pessoa,Integer> implements IPessoaDAO {
    
}
