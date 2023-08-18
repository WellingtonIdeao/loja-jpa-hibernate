package br.com.ideao.loja.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

    @EmbeddedId
    private CategoriaId id;
    
    public Categoria() {
        super();
    }
    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "xpto");
    }

    public String getNome() {
        return this.id.getNome();
    }
    
}
