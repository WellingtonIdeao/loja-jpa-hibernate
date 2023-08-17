package br.com.ideao.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ideao.loja.dao.CategoriaDao;
import br.com.ideao.loja.dao.ProdutoDao;
import br.com.ideao.loja.model.Categoria;
import br.com.ideao.loja.model.Produto;
import br.com.ideao.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class ConsultaDinamicaProduto {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        List<Produto> buscarComJpa = produtoDao.buscarComParametrosOpcionais("PS5", null, LocalDate.now());
        List<Produto> buscarComCriteria = produtoDao.buscarComParametrosOpcionaisComCriteria("PS5", null, LocalDate.now());
        buscarComJpa.forEach((p) -> System.out.println(p.getNome()));
        buscarComCriteria.forEach((p) -> System.out.println(p.getNome()));
        em.close();

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMÁTICA");

        Produto celular = new Produto(
            "Xiaomi Redmi",
            "Redmi 12S",
            new BigDecimal("1500"),
            celulares
        );
        Produto videogame = new Produto(
            "PS5",
            "Playstation 5",
            new BigDecimal("4000"),
            videogames
        );
        Produto macBook = new Produto(
            "MacBook",
            "MacBook pro",
            new BigDecimal("8000"),
            informatica
        );

        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        
        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macBook);
        em.getTransaction().commit();
        em.close();
    }
}
