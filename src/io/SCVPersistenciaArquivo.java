package io;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import classes.Cliente;
import classes.ItemDePedido;
import classes.Pedido;
import classes.SCVException;
import classes.Produto;

public class SCVPersistenciaArquivo {
	
	List<Produto> listaProduto;
	
	static String caminhoP = "Arquivos/Produto/produto.txt";
	static String caminhoPe = "Arquivos/Pedido/pedido.txt";
	static String caminhoC = "Arquivos/Cliente/cliente.txt";
	static String caminhoCod = "Arquivos/Codigo/codigo.txt";
	
	
	public void salvarProduto(Produto produto) throws SCVPersistenciaException{
		
		boolean file = new File("Arquivos/Produto").mkdirs();
		FileWriter fw;
		PrintWriter pw;
		
		try{
			fw = new FileWriter(caminhoP, true);
			pw = new PrintWriter(fw);
						
			pw.println(produto.getNome() + ";" + produto.getCodigo() + ";" + produto.getDescricao()
						+ ";" + produto.getPreco());
			pw.close();
			
		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
	
	}
	
		
	public void salvarCliente(Cliente cliente) throws SCVPersistenciaException{
		boolean file = new File("Arquivos/Cliente").mkdirs();
		FileWriter fw;
		PrintWriter pw;
		
		try{
			fw = new FileWriter(caminhoC, true);
			pw = new PrintWriter(fw);
			
			pw.println(cliente.getCpf() + ";" + cliente.getNome() + ";" + cliente.getTelefone());
			
			pw.close();
			
		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
	}
	
	
	public void salvarPedido(Pedido pedido) throws SCVPersistenciaException{
		boolean file = new File("Arquivos/Pedido").mkdirs();
		FileWriter fw;
		PrintWriter pw;
				
		try{
			fw = new FileWriter(caminhoPe, true);
			pw = new PrintWriter(fw);
			StringBuilder sb = new StringBuilder("");
				
			for(ItemDePedido item: pedido.getListaItens()){
				sb.append(item.getProduto().getCodigo() + ":" + item.getQuantidade() + ":");
			}
			pw.println(pedido.getDateAtual() + ";" + pedido.getCliente().getCpf() + ";" +
			pedido.getCodPedido() + ";" + sb.toString());
		
			pw.close();
		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
	}
	
	
	public void salvarCodigo(int codigo) throws SCVPersistenciaException{
		boolean file = new File("Arquivos/Codigo").mkdirs();
		FileWriter fw;
		PrintWriter pw;
		
		try{
			fw = new FileWriter(caminhoCod, false);
			pw = new PrintWriter(fw);
			pw.println(codigo);
			pw.close();
			
			
		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
		
		
	}
	
	public int recuperarCodigo() throws SCVPersistenciaException{
		boolean file = new File("Arquivos/Codigo").mkdirs();
		int codigo = 0;
		
		BufferedReader reader;
		FileReader fReader;
		try{
			fReader = new FileReader(caminhoCod);
			reader = new BufferedReader(fReader);
			String line = "";
			line = reader.readLine();
			codigo = Integer.parseInt(line);
			
		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
		
		return codigo;
	}
	
	public List<Produto> recuperarProdutos() throws SCVPersistenciaException{
		boolean file = new File("Arquivos/Produto").mkdirs();
		
		List<Produto> listProdutos = new ArrayList<Produto>();
		BufferedReader reader;
		FileReader fReader;
		
		try{
			fReader = new FileReader(caminhoP);
			reader = new BufferedReader(fReader);
			String line = "";
			String[] str;
			Produto produto;
			while((line = reader.readLine()) != null){
				produto = new Produto();
				str = line.split(";");
				produto.setNome(str[0]);
				produto.setCodigo(Integer.parseInt(str[1]));
				produto.setDescricao(str[2]);
				produto.setPreco(Double.parseDouble(str[3]));
				listProdutos.add(produto);
			}
			reader.close();
			
		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
			
		return listProdutos;
	}
	
	
	public List<Cliente> recuperarClientes() throws SCVPersistenciaException{
		boolean file = new File("Arquivos/Cliente").mkdirs();
		
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		BufferedReader reader;
		FileReader fReader;
		try{
			fReader = new FileReader(caminhoC);
			reader = new BufferedReader(fReader);
			String line = "";
			String[] str;
			Cliente cliente;
			while((line = reader.readLine()) != null){
				cliente = new Cliente();
				str = line.split(";");
				cliente.setCpf(str[0]);
				cliente.setNome(str[1]);
				cliente.setTelefone(str[2]);
				listaClientes.add(cliente);
			}
			reader.close();
	
		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
		return listaClientes;
	}
	
	
	
	public List<Pedido> recuperarPedidos() throws SCVPersistenciaException{
		boolean file = new File("Arquivos/Pedido").mkdirs();
		
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		List<Produto> listaProdutos;
		List<Cliente> listaClientes;
		BufferedReader reader;
		FileReader fReader;
		
		try{
			listaProdutos = this.recuperarProdutos();
			listaClientes = this.recuperarClientes();
			fReader = new FileReader(caminhoPe);
			reader = new BufferedReader(fReader);
			String line = "";
			String[] str;
			String[] str2;
			Pedido pedido;
			while((line = reader.readLine()) != null){
				pedido = new Pedido();
				Produto p = new Produto();
				Cliente c = new Cliente();
				
				str = line.split(";");
				String data = str[0];
				String cpf = str[1];
				Integer codPedido = Integer.parseInt(str[2]);
				
				for(Cliente cliente: listaClientes){
					if(cliente.getCpf().equals(cpf)){
						c = cliente;
					}
				}
				
				pedido.setData(data);
				pedido.setCliente(c);
				pedido.setCodPedido(codPedido);
				
				str2 = str[3].split(":");
				int i = 0;
				int j = 1;
				for(int l = 0; l < str2.length / 2; l++){
					Integer codProduto = Integer.parseInt(str2[i]);
					int quantidade = Integer.parseInt(str2[j]);
					for(Produto produto: listaProdutos){
						if(produto.getCodigo() == codProduto){
							p = produto;
						}
					}
					pedido.addProduto(p, quantidade);
					i = i + 2;
					j = j + 2;
				}
				listaPedidos.add(pedido);
			}
			reader.close();

		}catch(FileNotFoundException e){
			throw new SCVPersistenciaException("Arquivo não encontrado!");
		}
		catch(IOException e){
			throw new SCVPersistenciaException("Erro interno no arquivo!");
		}
		catch(SCVException e){
			throw new SCVPersistenciaException("");
		}
		return listaPedidos;
	}

}
