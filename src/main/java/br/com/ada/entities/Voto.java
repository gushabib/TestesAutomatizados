package br.com.ada.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Voto {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private Long numeroCandidato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumeroCandidato() {
        return numeroCandidato;
    }

    public void setNumeroCandidato(Long numeroCandidato) {
        this.numeroCandidato = numeroCandidato;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", numeroCandidato=").append(numeroCandidato);
        sb.append('}');
        return sb.toString();
    }
}
