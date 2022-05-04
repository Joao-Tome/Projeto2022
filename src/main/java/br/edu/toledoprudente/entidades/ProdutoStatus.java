package br.edu.toledoprudente.entidades;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="ProdutoStatus")
public class ProdutoStatus extends AbstractEntity<Integer>{
    @NotBlank(message = "O campo Status é Obrigatorio")
	@Column(name="Status", nullable = false, length = 80)
	private String Status;
	
	@OneToMany(mappedBy = "prodstatus")
	private List<Produto> listaProduto;

    /**
     * @return String return the Nome
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setStatus(String status) {
        this.Status = status;
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
