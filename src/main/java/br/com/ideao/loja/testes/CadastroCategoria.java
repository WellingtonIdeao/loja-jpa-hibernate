package br.com.ideao.loja.testes;

import br.com.ideao.loja.model.Categoria;
import br.com.ideao.loja.model.CategoriaId;
import br.com.ideao.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroCategoria {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        Categoria categoria = em.find(Categoria.class, new CategoriaId("CELULARES", "xpto"));
        em.close();
        System.out.println(categoria.getNome());
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(celulares);
        em.getTransaction().commit();
        em.close(); 
    }
}
