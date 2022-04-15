package br.edu.toledoprudente.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Usuario")
public class Usuario extends AbstractEntity<Integer>{

    @NotBlank(message = "O campo Login é Obrigatorio")
	@Column(name="Login", nullable = false, length = 80)
    private String Login;

    @NotBlank(message = "O campo Senha é Obrigatorio")
	@Column(name="Senha", nullable = false, length = 80)
    private String Senha;
    
    @NotNull(message = "O campo TipoUsuario é Obrigatorio")
	@ManyToOne
	@JoinColumn(name="IdTipoUsuario")
    private tipoUsuario tipousuario;
    
    @NotNull(message = "O campo Pessoa é Obrigatorio")
	@ManyToOne
	@JoinColumn(name="IdPessoa")
    private Pessoa pessoa;



    /**
     * @return String return the Login
     */
    public String getLogin() {
        return Login;
    }

    /**
     * @param Login the Login to set
     */
    public void setLogin(String Login) {
        this.Login = Login;
    }

    /**
     * @return String return the Senha
     */
    public String getSenha() {
        return Senha;
    }

    /**
     * @param Senha the Senha to set
     */
    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    /**
     * @return tipoUsuario return the tipousuario
     */
    public tipoUsuario getTipousuario() {
        return tipousuario;
    }

    /**
     * @param tipousuario the tipousuario to set
     */
    public void setTipousuario(tipoUsuario tipousuario) {
        this.tipousuario = tipousuario;
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
