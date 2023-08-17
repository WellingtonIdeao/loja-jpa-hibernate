package br.com.ideao.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import br.com.ideao.loja.dao.CategoriaDao;
import br.com.ideao.loja.dao.ProdutoDao;
import br.com.ideao.loja.model.Categoria;
import br.com.ideao.loja.model.Produto;
import br.com.ideao.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto produto = produtoDao.buscarPorId(1l);
        // List<Produto> produtos = produtoDao.buscarTodos();
        // List<Produto> produtos = produtoDao.buscarPorNome("Tablet Samsung S6 Lite");
        List<Produto> produtos = produtoDao.buscarPorNomeDaCategoria("Informática");

        System.out.println(produto.getNome());

        produtos.forEach(p -> System.out.println(p.getNome()));

        List<BigDecimal> precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Tablet Samsung S6 Lite");
        System.err.println("Preço do produto: "+ precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria informatica = new Categoria("Informática");

        Produto tablet = new Produto(
            "Tablet Samsung S6 lite",
            "Tablet de ótima qualidade",
            new BigDecimal("2000"), informatica
        );
        
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(tablet);
        // categoriaDao.atualizar(informatica);
        // categoriaDao.remover(informatica);
        em.getTransaction().commit();
        em.close();
    }
}