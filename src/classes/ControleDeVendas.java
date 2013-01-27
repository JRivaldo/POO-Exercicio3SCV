package classes;

import io.SCVPersistenciaArquivo;
import io.SCVPersistenciaException;
import iu.EntradasUsuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControleDeVendas {
	
	private List<Pedido> listaPedidos;
	private Set<Cliente> clientes;
	private Set<Produto> produtos;
	
	
	public ControleDeVendas(){
		this.listaPedidos = new ArrayList<Pedido>();
		this.clientes = new HashSet<Cliente>();
		this.produtos = new HashSet<Produto>();
				
	}
	
	public ControleDeVendas(List<Cliente> clientes,List<Produto> produtos){
		this.clientes = new HashSet<Cliente>(clientes);
		this.produtos = new HashSet<Produto>(produtos);
	}
	
	public int gerarCodigo() throws ControleDeVendasException {
		int codigo = 0;
		try{
			SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
			codigo = arq.recuperarCodigo() + 1;
			arq.salvarCodigo(codigo);
		}catch(SCVPersistenciaException e){
			throw new ControleDeVendasException(e.getMessage());
		}
		
				
		return codigo;
	}
	
	
	public Pedido novoPedido(Cliente cliente) throws ControleDeVendasException{
		Pedido pedido = new Pedido();
		
		try{
			SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
			pedido.setCodPedido(this.gerarCodigo());
			pedido.setCliente(cliente);
			
			EntradasUsuario entrada = new EntradasUsuario();
			List<ItemDePedido> listaItem = entrada.entradaAddProduto();
			for(ItemDePedido item: listaItem){
				pedido.addProduto(item.getProduto(), item.getQuantidade());
			}
			arq.salvarPedido(pedido);
	
		}catch(SCVException e){
			throw new ControleDeVendasException(e.getMessage());
		}catch(SCVPersistenciaException e){
			throw new ControleDeVendasException(e.getMessage());
		}

		return pedido;
	}
	
	
	public List<Pedido> listarPedido() throws ControleDeVendasException{
		SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
		try{
			this.listaPedidos = arq.recuperarPedidos();
		}catch(SCVPersistenciaException e){
			throw new ControleDeVendasException(e.getMessage());
		}
		
		return this.listaPedidos;
	}
	
	
	public List<Pedido> listarPedido(String data) throws ControleDeVendasException{
		SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
		List<Pedido> list1 = new ArrayList<Pedido>();
		boolean existe = false;
		try{
			for(Pedido pedido: arq.recuperarPedidos()){
				if(pedido.getDateAtual().equals(data)){
					list1.add(pedido);
					existe = true;
				}
			}
			if(existe == false){
				throw new ControleDeVendasException("Pedidos não encontrado, verifique se a data está correta!");
			}
			
		}catch(SCVPersistenciaException e){
			throw new ControleDeVendasException(e.getMessage());
		}
		
		return list1;
	}
	
	
	public List<Pedido> listarPedido(Cliente cliente) throws ControleDeVendasException{
		SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
		List<Pedido> list1 = new ArrayList<Pedido>();
		boolean existe = false;
		try{
			for(Pedido pedido: arq.recuperarPedidos()){
				if(pedido.getCliente().getCpf().equals(cliente.getCpf())){
					list1.add(pedido);			
					existe = true;
				}
			}
			if(existe == false){
				throw new ControleDeVendasException("Pedidos não encontrado!");
			}
		}catch(SCVPersistenciaException e){
			throw new ControleDeVendasException(e.getMessage());
		}
		
		return list1;
	}
	
	public void addProduto(Produto produto) throws ControleDeVendasException{
			
			try{
				SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
				
				for(Produto produtos: arq.recuperarProdutos()){
					if((produtos.getCodigo() == produto.getCodigo()) || produtos.getNome().equals(produto.getNome())){
						throw new ControleDeVendasException("O produto já existe!");
					}
				}
				this.produtos.add(produto);
					
				arq.salvarProduto(produto);

			}catch(SCVPersistenciaException e){
				throw new ControleDeVendasException(e.getMessage());
			}
		
	}
	
	public void addCliente(Cliente cliente) throws ControleDeVendasException{
		try{
			SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
			
			for(Cliente clientes: arq.recuperarClientes()){
				if((clientes.getCpf().equals(cliente.getCpf()))){
					throw new ControleDeVendasException("O cliente já existe!");
				}
			}
			this.clientes.add(cliente);
				
			arq.salvarCliente(cliente);

		}catch(SCVPersistenciaException e){
			throw new ControleDeVendasException(e.getMessage());
		}
	}
	
	public Cliente getCliente(String cpf) throws ControleDeVendasException{
		Cliente cliente = new Cliente();
		boolean verifica = false;
		try{
			SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
			for(Cliente c: arq.recuperarClientes()){
				if(c.getCpf().equals(cpf)){
					cliente = c;
					verifica = true;
				}
			}
			if(verifica == false){
				throw new ControleDeVendasException("Cliente não encontrado!");
			}
			
		}catch(SCVPersistenciaException e){
			throw new ControleDeVendasException(e.getMessage());
		}
		
		return cliente;
	}

}
