package br.edu.toledoprudente.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Email")
public class Email extends AbstractEntity<Integer> {
    @NotBlank(message = "O campo Tipo é Obrigatorio")
	@Column(name="Tipo", nullable = false, length = 80)
    private String Tipo;

    @NotBlank(message = "O campo Email é Obrigatorio")
	@Column(name="Email", nullable = false, length = 80)
    private String Email;

    @NotNull(message = "O campo Pessoa é Obrigatorio")
	@ManyToOne
	@JoinColumn(name="IdPessoa")
    private Pessoa pessoa;


    /**
     * @return String return the tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.Email = email;
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
