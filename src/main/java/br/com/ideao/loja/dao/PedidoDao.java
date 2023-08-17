package br.com.ideao.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.ideao.loja.dto.RelatorioDeVendas;
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

    // public List<Object[]> relatorioDeVendas() {
    //     String jpql = "SELECT produto.nome, "
    //                 + "SUM(item.quantidade), "
    //                 + "MAX(pedido.data) "
    //                 + "FROM Pedido pedido "
    //                 + "JOIN pedido.itens item "
    //                 + "JOIN item.produto produto "
    //                 + "GROUP BY produto.nome "
    //                 + "ORDER BY item.quantidade DESC";
    //     return em.createQuery(jpql, Object[].class)
    //         .getResultList();
    // }

    public List<RelatorioDeVendas> relatorioDeVendas() {
        String jpql = "SELECT NEW br.com.ideao.loja.dto.RelatorioDeVendas(" 
                    + "produto.nome, "
                    + "SUM(item.quantidade) AS quantidadeTotal, "
                    + "MAX(pedido.data)) "
                    + "FROM Pedido pedido "
                    + "JOIN pedido.itens item "
                    + "JOIN item.produto produto "
                    + "GROUP BY produto.nome "
                    + "ORDER BY quantidadeTotal DESC";
        return em.createQuery(jpql,RelatorioDeVendas.class)
            .getResultList();
    }
}
