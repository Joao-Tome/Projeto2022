package br.edu.toledoprudente.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import br.edu.toledoprudente.entidades.Categoria;

@Repository
public class CategoriaDAO 
	extends AbstractDao<Categoria, Integer> 
	implements ICategoriaDAO {

		public List<Categoria> findByDescricao(String descricao, int id){

			var sql = "select Categoria from Categoria where Descricao = '"+descricao+"' and id <> "+id;
			List<Categoria> list = super.createQuery(sql);
			return list;
		}

	
	
}
