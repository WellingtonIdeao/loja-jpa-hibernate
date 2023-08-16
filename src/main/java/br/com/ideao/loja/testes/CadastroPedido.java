package br.com.ideao.loja.testes;

import java.math.BigDecimal;

import br.com.ideao.loja.dao.CategoriaDao;
import br.com.ideao.loja.dao.ClienteDao;
import br.com.ideao.loja.dao.PedidoDao;
import br.com.ideao.loja.dao.ProdutoDao;
import br.com.ideao.loja.model.Categoria;
import br.com.ideao.loja.model.Cliente;
import br.com.ideao.loja.model.ItemPedido;
import br.com.ideao.loja.model.Pedido;
import br.com.ideao.loja.model.Produto;
import br.com.ideao.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class CadastroPedido {
    
    public static void main(String[] args) {
       popularBancoDeDados();
       EntityManager em = JPAUtil.getEntityManager();
       ProdutoDao produtoDao = new ProdutoDao(em);
       ClienteDao clienteDao = new ClienteDao(em);
       PedidoDao pedidoDao = new PedidoDao(em);

       Produto produto = produtoDao.buscarPorId(1l);
       Cliente cliente = clienteDao.buscarPorId(1l); 
       
       Pedido pedido = new Pedido(cliente);
       pedido.addItem(new ItemPedido(1, pedido, produto));
      
       em.getTransaction().begin();
       pedidoDao.cadastrar(pedido);
       em.getTransaction().commit();
       em.close();

       
    }

     private static void popularBancoDeDados() {
        Categoria informatica = new Categoria("Informática");

        Produto tablet = new Produto(
            "Tablet Samsung S6 lite",
            "Tablet de ótima qualidade",
            new BigDecimal("2000"), informatica
        );

        Cliente cliente = new Cliente("Wellington", "12345678901");
        
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(tablet);
        clienteDao.cadastrar(cliente);
        
        em.getTransaction().commit();
        em.close();
    }
}
