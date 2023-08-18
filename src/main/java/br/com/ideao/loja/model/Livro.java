package br.com.ideao.loja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Livro extends Produto {
    private String autor;
    @Column(name="numero_de_paginas")
    private Integer numeroDePaginas;
    
    public Livro(){
        super();
    }

    public Livro(String autor, Integer numeroDePaginas){
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumeroDePaginas() {
        return this.numeroDePaginas;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }
}
