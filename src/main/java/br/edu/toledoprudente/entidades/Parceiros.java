package br.edu.toledoprudente.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Parceiros")
public class Parceiros extends AbstractEntity<Integer>{
    
    @NotBlank(message = "O campo Nome é Obrigatorio")
	@Column(name="Nome", nullable = false, length = 80)
    private String Nome;
    
    @NotBlank(message = "O campo CNPJ é Obrigatorio")
	@Column(name="CNPJ", nullable = false, length = 80)
    private String CNPJ;

    @NotBlank(message = "O campo Descricao é Obrigatorio")
	@Column(name="Descricao", nullable = false, length = 80)
    private String Descricao;

    


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
     * @return String return the CNPJ
     */
    public String getCNPJ() {
        return CNPJ;
    }

    /**
     * @param CNPJ the CNPJ to set
     */
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

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

}
