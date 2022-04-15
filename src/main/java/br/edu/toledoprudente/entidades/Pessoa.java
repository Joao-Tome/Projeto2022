package br.edu.toledoprudente.entidades;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Pessoas")
public class Pessoa extends AbstractEntity<Integer>{
    @NotBlank(message = "O campo Primeiro Nome é Obrigatorio")
	@Column(name="PrimeiroNome", nullable = false, length = 80)
    private String PrimeiroNome;

    @NotBlank(message = "O campo SobreNome é Obrigatorio")
	@Column(name="SobreNome", nullable = false, length = 80)
    private String SobreNome;

    @NotBlank(message = "O campo CPF é Obrigatorio")
	@Column(name="CPF", nullable = false, length = 20)
    private String CPF;

    @OneToMany(mappedBy = "pessoa")
    private List<Usuario> listaUsuarios;

    @OneToMany(mappedBy = "pessoa")
    private List<Email> listaEmail;

    @OneToMany(mappedBy = "pessoa")
    private List<Telefone> listaTelefone;

    /**
     * @return String return the PrimeiroNome
     */
    public String getPrimeiroNome() {
        return PrimeiroNome;
    }

    /**
     * @param PrimeiroNome the PrimeiroNome to set
     */
    public void setPrimeiroNome(String PrimeiroNome) {
        this.PrimeiroNome = PrimeiroNome;
    }

    /**
     * @return String return the SobreNome
     */
    public String getSobreNome() {
        return SobreNome;
    }

    /**
     * @param SobreNome the SobreNome to set
     */
    public void setSobreNome(String SobreNome) {
        this.SobreNome = SobreNome;
    }

    /**
     * @return String return the CPF
     */
    public String getCPF() {
        return CPF;
    }

    /**
     * @param CPF the CPF to set
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }


    /**
     * @return List<Usuario> return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

}
