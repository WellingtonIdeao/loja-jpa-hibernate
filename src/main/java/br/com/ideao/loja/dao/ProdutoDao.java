package br.com.ideao.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ideao.loja.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

    // public List<Produto> buscarPorNomeDaCategoria(String nome) {
    //     String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
    //     TypedQuery<Produto> query = this.em.createQuery(jpql, Produto.class)
    //                             .setParameter("nome", nome);

    //     return query.getResultList();
    // }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        TypedQuery<Produto> query =
                this.em.createNamedQuery("Produto.produtoPorCategoria", Produto.class)
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

    public List<Produto> buscarComParametrosOpcionais(String nome, BigDecimal preco, LocalDate dataCadastro){
        String jpql = "SELECT p FROM Produto p WHERE 1=1";
        
        if (nome != null && !nome.trim().isEmpty()) {
            jpql += " AND p.nome = :nome";
        }
        if (preco != null) {
            jpql += " AND p.preco = :preco";
        }
        if (dataCadastro != null) {
            jpql += " AND p.dataCadastro = :dataCadastro";
        }

        TypedQuery<Produto> query = this.em.createQuery(jpql, Produto.class);
        
        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);    
        }
        if (preco != null) {
            query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }
        return query.getResultList();
    }

    public List<Produto> buscarComParametrosOpcionaisComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){
        
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);
        query.select(from);
        
        Predicate filtros = builder.and();

        if (nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);
        return this.em.createQuery(query).getResultList();
    }    
}
