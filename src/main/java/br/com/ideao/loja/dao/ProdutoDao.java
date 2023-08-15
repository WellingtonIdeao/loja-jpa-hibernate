package br.com.ideao.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.ideao.loja.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return this.em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        TypedQuery<Produto> query = this.em.createQuery(jpql, Produto.class);
        return query.getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        // position parameter
        // String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
        // TypedQuery<Produto> query = this.em.createQuery(jpql, Produto.class)
        //                         .setParameter(1, nome);
        
        // named parameter
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        TypedQuery<Produto> query = this.em.createQuery(jpql, Produto.class)
                                .setParameter("nome", nome);
        
        return query.getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        TypedQuery<Produto> query = this.em.createQuery(jpql, Produto.class)
                                .setParameter("nome", nome);

        return query.getResultList();
    }

    public List<BigDecimal> buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.nome FROM Produto p WHERE p.nome = :nome";
        TypedQuery<BigDecimal> query = this.em.createQuery(jpql, BigDecimal.class)
                                .setParameter("nome", nome);

        // return query.getSingleResult();                        
        return query.getResultList();
    }
}
