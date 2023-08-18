package br.com.ideao.loja.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class CategoriaId implements Serializable {
    private String nome;
    private String tipo;

    public CategoriaId() {
        super();
    }

    public CategoriaId(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    
    

    
}
