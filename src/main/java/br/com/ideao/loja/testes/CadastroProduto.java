package br.com.ideao.loja.testes;

import java.math.BigDecimal;

import br.com.ideao.loja.dao.CategoriaDao;
import br.com.ideao.loja.dao.ProdutoDao;
import br.com.ideao.loja.model.Categoria;
import br.com.ideao.loja.model.Produto;
import br.com.ideao.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroProduto {
    public static void main(String[] args) {
        Categoria informatica = new Categoria("Teste");

        Produto tablet = new Produto(
            "Tablet Samsung S6 lite 2",
            "Tablet de ótima qualidade",
            new BigDecimal("2000"), informatica
        );
        
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(informatica);
        em.flush();
        // produtoDao.cadastrar(tablet);
        em.clear();
        informatica.setNome("Eletrônicos");
        categoriaDao.atualizar(informatica);
        categoriaDao.remover(informatica);
        em.getTransaction().commit();
    }
}