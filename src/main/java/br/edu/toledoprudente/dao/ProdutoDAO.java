package br.edu.toledoprudente.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.entidades.Produto;

@Repository
public class ProdutoDAO 
	extends AbstractDao<Produto, Integer>
	implements IProdutoDAO {

		public List<Produto> getdestaque(){
			List<Produto> lista = this.createQuery("select p from Produto p where p.prodstatus = 2");
			return lista.isEmpty() ? null : lista;
		}

		public List<Produto> getnodestaque(){
			List<Produto> lista = this.createQuery("select p from Produto p where p.prodstatus != 2 and p.prodstatus != 4");
			return lista.isEmpty() ? null : lista;
		}
}
