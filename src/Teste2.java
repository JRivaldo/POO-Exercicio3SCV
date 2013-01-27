import io.SCVPersistenciaArquivo;
import io.SCVPersistenciaException;

import java.text.ParseException;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import classes.Cliente;
import classes.ItemDePedido;
import classes.Pedido;
import classes.Produto;


public class Teste2 {
	
	public static void main (String args[]) throws SCVPersistenciaException, ParseException{
		SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
		List<Cliente> listaClientes = arq.recuperarClientes();
		List<Produto> listaProdutos = arq.recuperarProdutos();
		List<Pedido> listaPedidos = arq.recuperarPedidos();
		
		String mostrar1 = "";
		String mostrar2 = "";
		String mostrar3 = "";
		String mostrar4 = "";
		for(Produto produto: listaProdutos){
			mostrar1 = mostrar1 + produto.getNome() + " : " + produto.getPreco() + " | ";
			
		}
		for(Cliente cliente: listaClientes){
			mostrar2 = mostrar2 + cliente.getNome() + "\n";
		}
		for(Pedido pedido: listaPedidos){
			
			for(ItemDePedido item: pedido.getListaItens()){
				mostrar4 = mostrar4 + item.getProduto().getNome() + " : " + item.getQuantidade() + " : ";
					
			}
			mostrar3 = mostrar3 + pedido.getDateAtual() + " ; " + pedido.getCliente().getNome() + " ; " +
					pedido.getCodPedido() + " ; " + mostrar4 + "\n";
		}
		
		System.out.println(mostrar1);
		System.out.println(mostrar2);
		System.out.println(mostrar3);
	}

}
