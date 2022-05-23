package br.edu.toledoprudente.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;

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

    @NumberFormat(style = Style.CURRENCY, pattern = "###0.00")
	@NotNull(message = "O Campo valor é Obrigatorio")
	@Column(name="Valor", nullable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
	private BigDecimal Valor; 
	
	@NotNull(message = "O Campo Quantidade é Obrigatorio")
	@Column(name="Quantidade", nullable = false)
	private Integer Quantidade;

    @NotNull(message = "O Campo Data Validade é Obrigatorio")
    @DateTimeFormat(iso=ISO.DATE)
	@Column(name="DataValidade", nullable = false, columnDefinition = "DATE")
	private LocalDate DataValidade;

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

    @Column(name = "imagem")
    private String imagem;

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

    /**
     * @return LocalDate return the DataValidade
     */
    public LocalDate getDataValidade() {
        return DataValidade;
    }

    /**
     * @param DataValidade the DataValidade to set
     */
    public void setDataValidade(LocalDate DataValidade) {
        this.DataValidade = DataValidade;
    }

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
     * @return ProdutoStatus return the prodstatus
     */
    public ProdutoStatus getProdstatus() {
        return prodstatus;
    }

    /**
     * @param prodstatus the prodstatus to set
     */
    public void setProdstatus(ProdutoStatus prodstatus) {
        this.prodstatus = prodstatus;
    }


    /**
     * @return String return the imagem
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
