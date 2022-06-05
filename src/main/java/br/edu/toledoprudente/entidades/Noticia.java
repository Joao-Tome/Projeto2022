package br.edu.toledoprudente.entidades;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="Noticia")
public class Noticia extends AbstractEntity<Integer>{
    @NotBlank(message = "O campo Titulo é Obrigatorio")
	@Column(name="Titulo", nullable = false, length = 80)
    private String Titulo;

    @NotBlank(message = "O campo Conteudo é Obrigatorio")
	@Column(name="Conteudo", columnDefinition="TEXT")
    private String Conteudo;

    // @NotNull(message = "O Campo Data Validade é Obrigatorio")
    @DateTimeFormat(iso=ISO.DATE)
    @Column(name="DataPub", nullable = false, columnDefinition = "DATE")
    private LocalDate dataPub;

	@Column(name="urlImage", length = 255)
    private String urlImage;


    /**
     * @return String return the Titulo
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * @param Titulo the Titulo to set
     */
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /**
     * @return String return the Conteudo
     */
    public String getConteudo() {
        return Conteudo;
    }

    /**
     * @param Conteudo the Conteudo to set
     */
    public void setConteudo(String Conteudo) {
        this.Conteudo = Conteudo;
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


    /**
     * @return LocalDate return the dataPub
     */
    public LocalDate getDataPub() {
        return dataPub;
    }

    /**
     * @param dataPub the dataPub to set
     */
    public void setDataPub(LocalDate dataPub) {
        this.dataPub = dataPub;
    }

}
