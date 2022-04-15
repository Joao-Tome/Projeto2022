package br.edu.toledoprudente.dao;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.entidades.Email;
@Repository
public class EmailDAO extends AbstractDao<Email,Integer> implements IEmailDAO {
    
}
