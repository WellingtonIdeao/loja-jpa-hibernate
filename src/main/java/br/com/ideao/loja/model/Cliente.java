package br.com.ideao.loja.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente() {
        super();
    }

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public Long getId(){
        return this.id;
    }

    public DadosPessoais getDadosPessoais() {
        return this.dadosPessoais;
    }

    public String getNome() {
        return this.dadosPessoais.getNome();
    }
}
