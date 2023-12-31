package br.com.ideao.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import br.com.ideao.loja.dao.CategoriaDao;
import br.com.ideao.loja.dao.ClienteDao;
import br.com.ideao.loja.dao.PedidoDao;
import br.com.ideao.loja.dao.ProdutoDao;
import br.com.ideao.loja.dto.RelatorioDeVendas;
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
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
        em.close();
        System.out.println(pedido.getCliente().getNome());
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

        Cliente cliente = new Cliente("Wellington", "12345678901");
       
        Pedido pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(3, pedido, celular));
        pedido.addItem(new ItemPedido(10, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.addItem(new ItemPedido(2, pedido2, macBook));

        
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);
        
        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macBook);
        
        clienteDao.cadastrar(cliente);
        
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);
       
        em.getTransaction().commit();
        
        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: "+ totalVendido);

        List<RelatorioDeVendas> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);
       
        em.close();
    }
}
