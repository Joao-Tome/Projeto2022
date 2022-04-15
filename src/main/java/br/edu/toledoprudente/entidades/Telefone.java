package br.edu.toledoprudente.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Telefone")
public class Telefone extends AbstractEntity<Integer> {

    @NotBlank(message = "O campo Tipo é Obrigatorio")
	@Column(name="Tipo", nullable = false, length = 80)
    private String Tipo;

    @NotBlank(message = "O campo telefone é Obrigatorio")
	@Column(name="Email", nullable = false, length = 80)
    private String Telefone;

    @NotNull(message = "O campo Pessoa é Obrigatorio")
	@ManyToOne
	@JoinColumn(name="IdPessoa")
    private Pessoa pessoa;

    /**
     * @return String return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return String return the Telefone
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * @param Telefone the Telefone to set
     */
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    /**
     * @return Pessoa return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
