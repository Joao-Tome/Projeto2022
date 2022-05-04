package br.edu.toledoprudente.entidades;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Categorias")
public class Categoria extends AbstractEntity<Integer> {
	@NotBlank(message = "O campo Descrição é Obrigatorio")
	@Column(name="Descricao", nullable = false, length = 80)
	private String Descricao;
	
	@OneToMany(mappedBy = "categoria")
	private List<Produto> listaProduto;

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
}
