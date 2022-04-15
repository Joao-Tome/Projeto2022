package br.edu.toledoprudente.entidades;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Marcas")
public class Marca extends AbstractEntity<Integer> {
    @NotBlank(message = "O campo Nome é Obrigatorio")
	@Column(name="Nome", nullable = false, length = 80)
	private String Nome;
	
	@OneToMany(mappedBy = "marca")
	private List<Produto> listaProduto;

    /**
     * @return String return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return List<Produto> return the listaProduto
     */
    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    /**
     * @param listaProduto the listaProduto to set
     */
    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

}
