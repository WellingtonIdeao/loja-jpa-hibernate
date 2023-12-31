package br.com.ideao.loja.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class DadosPessoais  implements Serializable{
    private String nome;
    private String cpf;

    public DadosPessoais() {
        super();
    }

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    } 
}
