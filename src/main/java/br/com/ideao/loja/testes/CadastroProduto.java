package br.com.ideao.loja.testes;

import java.math.BigDecimal;

import br.com.ideao.loja.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CadastroProduto {
    public static void main(String[] args) {
        Produto tablet = new Produto();
        tablet.setNome("Tablet Samsung S6 lite");
        tablet.setDescricao("Tablet de ótima qualidade");
        tablet.setPreco(new BigDecimal("2000"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
        EntityManager em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(tablet);
        em.getTransaction().commit();
        em.close();    
    }
}