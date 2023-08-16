package br.com.ideao.loja.dao;

import br.com.ideao.loja.model.Pedido;
import jakarta.persistence.EntityManager;

public class PedidoDao {
    
    private EntityManager em;

    public PedidoDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }
    
}
