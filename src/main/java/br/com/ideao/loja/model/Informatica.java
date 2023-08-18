package br.com.ideao.loja.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table( name = "informatica")
public class Informatica extends Produto {
    private String marca;
    private String modelo;
    
    public Informatica(){
        super();
    }

    public Informatica(String marca, String modelo){
        this.marca = marca;
        this.modelo = modelo;    
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
