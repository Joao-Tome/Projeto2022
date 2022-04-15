package br.edu.toledoprudente.entidades;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TipoUsuario")
public class tipoUsuario extends AbstractEntity<Integer>{
    @NotBlank(message = "O campo Decrição é Obrigatorio")
	@Column(name="Descricao", nullable = false, length = 80)
    private String Descricao;

    @OneToMany(mappedBy = "tipousuario")
    private List<Usuario> listaUsuarios;


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
