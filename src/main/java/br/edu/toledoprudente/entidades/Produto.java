package br.edu.toledoprudente.entidades;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Produtos")
public class Produto 
	extends AbstractEntity<Integer> {
	@NotBlank(message = "O campo descrição é Obrigatorio")
	@Column(name="Descricao", nullable = false, length = 200)
	private String Descricao;

	@NotBlank(message = "O campo nome é Obrigatorio")
	@Column(name="Nome", nullable = false, length = 80)
	private String Nome;

	@NotNull(message = "O Campo valor é Obrigatorio")
	@Column(name="Valor", nullable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
	private BigDecimal Valor; 
	
	@NotNull(message = "O Campo Quantidade é Obrigatorio")
	@Column(name="Quantidade", nullable = false)
	private Integer Quantidade;
	/*
	@Column(name="DataValidade", nullable = false, columnDefinition = "DATE")
	private LocalDate DataValidade;
*/
	@NotNull(message = "O campo Categoria é Obrigatorio")
	@ManyToOne
	@JoinColumn(name="IdCategoria")
	private Categoria categoria;

	@NotNull(message = "O campo Marca é Obrigatorio")
	@ManyToOne
	@JoinColumn(name="IdMarca")
	private Marca marca;

	@NotNull(message = "O campo Status é Obrigatorio")
	@ManyToOne
	@JoinColumn(name="IdProdutoStatus")
	private ProdutoStatus prodstatus;

	@Column(name="urlImage", nullable = false, length = 255)
	private String urlImage;

    /**
     * @return String return the Descricao
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * @param Descricao the Descricao to set
     */
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

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
     * @return BigDecimal return the Valor
     */
    public BigDecimal getValor() {
        return Valor;
    }

    /**
     * @param Valor the Valor to set
     */
    public void setValor(BigDecimal Valor) {
        this.Valor = Valor;
    }

    /**
     * @return Integer return the Quantidade
     */
    public Integer getQuantidade() {
        return Quantidade;
    }

    /**
     * @param Quantidade the Quantidade to set
     */
    public void setQuantidade(Integer Quantidade) {
        this.Quantidade = Quantidade;
    }

    // /**
    //  * @return LocalDate return the DataValidade
    //  */
    // public LocalDate getDataValidade() {
    //     return DataValidade;
    // }

    // /**
    //  * @param DataValidade the DataValidade to set
    //  */
    // public void setDataValidade(LocalDate DataValidade) {
    //     this.DataValidade = DataValidade;
    // }

    /**
     * @return Categoria return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return Marca return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }


    /**
     * @return ProdutoStatus return the status
     */
    public ProdutoStatus getStatus() {
        return prodstatus;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ProdutoStatus status) {
        this.prodstatus = status;
    }


    /**
     * @return String return the urlImage
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * @param urlImage the urlImage to set
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}
