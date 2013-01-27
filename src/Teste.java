import io.SCVPersistenciaArquivo;
import io.SCVPersistenciaException;
import iu.OpcaoSCV;

import java.util.Date;

import javax.swing.JOptionPane;

import classes.ControleDeVendas;
import classes.ControleDeVendasException;
import classes.Pedido;
import classes.Produto;
import classes.SCVException;


public class Teste {
	
	public static void main (String [] args) throws SCVPersistenciaException, SCVException, ControleDeVendasException{
		Pedido pedido = new Pedido();
		
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();
		Produto p4 = new Produto();
		Produto p5 = new Produto();
		
		p1.setCodigo(1);
		p2.setCodigo(2);
		p3.setCodigo(3);
		p4.setCodigo(4);
		p5.setCodigo(5);
		
		p1.setNome("Manga");
		p2.setNome("Banana");
		p3.setNome("Maçã");
		p4.setNome("Uva");
		p5.setNome("Abacaxi");
		
		p1.setDescricao("alguma coisa");
		p2.setDescricao("alguma coisa");
		p3.setDescricao("alguma coisa");
		p4.setDescricao("alguma coisa");
		p5.setDescricao("alguma coisa");
		
		p1.setPreco(2.99);
		p2.setPreco(3.99);
		p3.setPreco(4.99);
		p4.setPreco(5.99);
		p5.setPreco(1.99);
		/*
		pedido.addProduto(p1, 3);
		pedido.addProduto(p2, 3);
		pedido.addProduto(p3, 3);
		pedido.addProduto(p4, 3);
		pedido.addProduto(p5);
		pedido.addProduto(p5);
		pedido.addProduto(p1,1);
		
		System.out.println("Total: " + pedido.totalPedido());
		
		List<ItemDePedido> lista3 = pedido.listarItensPedido();
		
		List<Produto> listaP = new ArrayList<Produto>();
		listaP.add(p1); listaP.add(p2);listaP.add(p3);listaP.add(p4);listaP.add(p5);
		*/
		SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
		/*
		arq.salvarProduto(p1);
		arq.salvarProduto(p2);
		arq.salvarProduto(p3);
		arq.salvarProduto(p4);
		arq.salvarProduto(p5);
		
				
		Cliente cliente = new Cliente();
		cliente.setCpf("123456");
		cliente.setNome("Karsh");
		cliente.setTelefone("88722219");
		arq.salvarCliente(cliente);
		
		pedido.setCliente(cliente);
				
		//arq.salvarPedido(pedido);
		
		//arq.salvarCodigo(arq.recuperarCodigo() + 1);
		/*
		for(ItemDePedido item: lista3){
			System.out.println("Nome: " + item.getProduto().getNome() + " | Código: " +
		item.getProduto().getCodigo() + " | Preço unitário: " + item.getProduto().getPreco() +
		 " | Quantidade: " + item.getQuantidade() + " | Total: " + item.totalItem());
		}
		*/		
		ControleDeVendas contr = new ControleDeVendas(arq.recuperarClientes(), arq.recuperarProdutos());
		OpcaoSCV op = new OpcaoSCV();	
		
	
		try{
			op.opcaoAddCiente();
		}catch(ControleDeVendasException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
		
		
	}

}
