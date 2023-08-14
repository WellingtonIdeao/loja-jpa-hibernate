package br.com.ideao.loja.testes;

import java.math.BigDecimal;

import br.com.ideao.loja.dao.ProdutoDao;
import br.com.ideao.loja.model.Produto;
import br.com.ideao.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroProduto {
    public static void main(String[] args) {
        Produto tablet = new Produto();
        tablet.setNome("Tablet Samsung S6 lite");
        tablet.setDescricao("Tablet de ótima qualidade");
        tablet.setPreco(new BigDecimal("2000"));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();
        
        produtoDao.cadastrar(tablet);
        em.getTransaction().commit();
        em.close();    
    }
}