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

}
