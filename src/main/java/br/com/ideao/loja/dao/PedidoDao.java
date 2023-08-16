package br.com.ideao.loja.dao;

import java.math.BigDecimal;

import br.com.ideao.loja.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PedidoDao {
    
    private EntityManager em;

    public PedidoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }
    
    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        TypedQuery<BigDecimal> query = this.em.createQuery(jpql, BigDecimal.class);
        return query.getSingleResult();
    }
}
