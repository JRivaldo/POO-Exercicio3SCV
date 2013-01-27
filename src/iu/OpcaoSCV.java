package iu;

import java.util.List;

import javax.swing.JOptionPane;

import classes.Cliente;
import classes.ControleDeVendas;
import classes.ControleDeVendasException;
import classes.ItemDePedido;
import classes.Pedido;
import classes.Produto;

public class OpcaoSCV {
	
	public String entrada(String msg){
		String entr = JOptionPane.showInputDialog(msg);
		return entr;
	}
	
	public void mensagem(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void opcaoNovoPedido() throws ControleDeVendasException{
		try{
			StringBuffer sb = new StringBuffer("");
			ControleDeVendas controle = new ControleDeVendas();
			String entr = entrada("OPÇÃO NOVO PEDIDO \n\nInforme o CPF do cliente:");
			Cliente cliente = controle.getCliente(entr);
			Pedido pedido = controle.novoPedido(cliente);
			if(pedido != null){
				for(ItemDePedido item: pedido.getListaItens()){
					sb.append("Produto: " + item.getProduto().getNome() + " | Preço unitário: " + item.getProduto().getPreco() +
							" | Quantidade: " + item.getQuantidade() + 
							" | Total: " + item.totalItem() + " R$.\n");
				}
				mensagem("Nome do Cliente: " + pedido.getCliente().getNome() + "\nCódigo do pedido: " + pedido.getCodPedido()
						+ "\n\n" + sb.toString() + "\nValor total do pedido: " + pedido.totalPedido() + " R$.");
			}
		}catch(ControleDeVendasException e){
			mensagem(e.getMessage());
		}
		
	}
	
	public void opcaoListarPedidos() throws ControleDeVendasException{
		StringBuffer sb = new StringBuffer("");
		StringBuffer sb2 = new StringBuffer("");
		try{
			ControleDeVendas controle = new ControleDeVendas();
			List<Pedido> listPed = controle.listarPedido();
			
			for(Pedido pedido: listPed){
				sb2 = new StringBuffer("");
				for(ItemDePedido item: pedido.getListaItens()){
					sb2.append("Produto: " + item.getProduto().getNome() + " | Preço unitário: " + item.getProduto().getPreco() +
							" | Quantidade: " + item.getQuantidade() + 
							" | Total: " + item.totalItem() + " R$.\n");
				}
				sb.append("Data do pedido: " + pedido.getDateAtual() + " | Nome do Cliente: " + pedido.getCliente().getNome() 
						+ " | Código do pedido: " + pedido.getCodPedido()
						+ "\n" + sb2.toString() + "Valor total do pedido: " + pedido.totalPedido() + " R$.\n\n");
			}
			mensagem(sb.toString());
		}catch(ControleDeVendasException e){
			mensagem(e.getMessage());
		}
	}
	
	public void opcaoListarPedidosData() throws ControleDeVendasException{
		StringBuffer sb = new StringBuffer("");
		StringBuffer sb2 = new StringBuffer("");
		try{
			ControleDeVendas controle = new ControleDeVendas();
			String entr = entrada("Informe a data: ");
			List<Pedido> listPed = controle.listarPedido(entr);
			
			for(Pedido pedido: listPed){
				sb2 = new StringBuffer("");
				for(ItemDePedido item: pedido.getListaItens()){
					sb2.append("Produto: " + item.getProduto().getNome() + " | Preço unitário: " + item.getProduto().getPreco() +
							" | Quantidade: " + item.getQuantidade() + 
							" | Total: " + item.totalItem() + " R$.\n");
				}
				sb.append("Data do pedido: " + pedido.getDateAtual() + " | Nome do Cliente: " + pedido.getCliente().getNome() 
						+ " | Código do pedido: " + pedido.getCodPedido()
						+ "\n" + sb2.toString() + "Valor total do pedido: " + pedido.totalPedido() + " R$.\n\n");	
					
				
			}
			
			mensagem(sb.toString());
		}catch(ControleDeVendasException e){
			mensagem(e.getMessage());
		}
	}
	
	
	public void opcaoListarPedidosCliente() throws ControleDeVendasException{
		StringBuffer sb = new StringBuffer("");
		StringBuffer sb2 = new StringBuffer("");
		try{
			ControleDeVendas controle = new ControleDeVendas();
			
			String entr = entrada("Informe o CPF do cliente: ");
			Cliente cliente = controle.getCliente(entr);
			List<Pedido> listPed = controle.listarPedido(cliente);
			for(Pedido pedido: listPed){
				sb2 = new StringBuffer("");
				for(ItemDePedido item: pedido.getListaItens()){
					sb2.append("Produto: " + item.getProduto().getNome() + " | Preço unitário: " + item.getProduto().getPreco() +
							" | Quantidade: " + item.getQuantidade() + 
							" | Total: " + item.totalItem() + " R$.\n");
				}
				sb.append("Data do pedido: " + pedido.getDateAtual() + " | Nome do Cliente: " + pedido.getCliente().getNome() 
						+ " | Código do pedido: " + pedido.getCodPedido()
						+ "\n" + sb2.toString() + "Valor total do pedido: " + pedido.totalPedido() + " R$.\n\n");
								
			}
			
			mensagem(sb.toString());
		}catch(ControleDeVendasException e){
			mensagem(e.getMessage());
		}
	}
	
	public void opcaoAddProduto() throws ControleDeVendasException{
		Produto produto = new Produto();
		ControleDeVendas controle = new ControleDeVendas();
		boolean fim = false;
		boolean fim2 = false;
		boolean fim3 = false;
		String cancel = "";
		String entr = "";
		Integer cod;
		String nome;
		String descri;
		double preco;
		try{
			while(fim == false){
				fim2 = false;
				fim3 = false;
				entr = entrada("Informe um código para o produto: ");
				cancel = entr;
				if(cancel == null){
					mensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					fim3 = true;
					break;
				}
				cod = Integer.parseInt(entr);
				entr = entrada("Informe um nome para o produto: ");
				cancel = entr;
				if(cancel == null){
					mensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					fim3 = true;
					break;
				}
				nome = entr;
				entr = entrada("Informe uma descrição para o produto: ");
				cancel = entr;
				if(cancel == null){
					mensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					fim3 = true;
					break;
				}
				descri = entr;
				entr = entrada("Informe um preço para o produto: ");
				cancel = entr;
				if(cancel == null){
					mensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					fim3 = true;
					break;
				}
				
				preco = Double.parseDouble(entr);
				produto.setCodigo(cod); produto.setDescricao(descri); produto.setNome(nome); produto.setPreco(preco);
				while(fim2 == false){
					entr = entrada("Nome: " + produto.getNome() + " | Código: " + produto.getCodigo() + " | Descrição: "
							+ produto.getDescricao() + " | Preço: " + produto.getPreco() + "\n\n Deseja salvar esse produto?"
							+ "\n1- Sim.\n2- Não.");
					cancel = entr;
					if(cancel == null){
						mensagem("Opção cancelada!");
						fim = true;
						fim2 = true;
						fim3 = true;
						break;
					}
					else if(entr.equals("1")){
						controle.addProduto(produto);
						mensagem("Produto Adicionado Com Sucesso!");
						fim2 = true;
					}
					else if(entr.equals("2")){
						fim2 = true;
						
					}else{
						mensagem("Opção inválida!");
					}
				}
				
				while(fim3 == false){
					entr = entrada("Deseja adicionar outro produto?\n1- Sim.\n2- Não. ");
					cancel = entr;
					if(cancel == null){
						mensagem("Opção cancelada!");
						fim = true;
						fim2 = true;
						fim3 = true;
						break;
					}
					else if(entr.equals("1")){
						fim3 = true;
					}
					else if(entr.equals("2")){
						mensagem("Opção Finalizada!");
						fim = true;
						fim2 = true;
						fim3 = true;
						
					}else{
						mensagem("Opção inválida!");
					}
				}
		
			}
			
		}catch(ControleDeVendasException e){
			mensagem(e.getMessage());
		}catch(NumberFormatException e){
			throw new ControleDeVendasException(e.getMessage());
		}

	}

	public void opcaoAddCiente() throws ControleDeVendasException{
		Cliente cliente = new Cliente();
		ControleDeVendas controle = new ControleDeVendas();
		boolean fim = false;
		boolean fim2 = false;
		boolean fim3 = false;
		String cancel = "";
		String entr = "";
		String cpf;
		String nome;
		String fone;
		try{
			while(fim == false){
				fim2 = false;
				fim3 = false;
				entr = entrada("Informe um CPF para o cliente: ");
				cancel = entr;
				if(cancel == null){
					mensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					fim3 = true;
					break;
				}
				cpf = entr;
				entr = entrada("Informe um nome para o cliente: ");
				cancel = entr;
				if(cancel == null){
					mensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					fim3 = true;
					break;
				}
				nome = entr;
				entr = entrada("Informe um telefone para o cliente: ");
				cancel = entr;
				if(cancel == null){
					mensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					fim3 = true;
					break;
				}
				fone = entr;
								
				cliente.setCpf(cpf); cliente.setNome(nome); cliente.setTelefone(fone);
				while(fim2 == false){
					entr = entrada("Nome: " + cliente.getNome() + " | CPF: " + cliente.getCpf() + " | Telefone: "
							+ cliente.getTelefone() + "\n\n Deseja salvar esse cliente?"
							+ "\n1- Sim.\n2- Não.");
					cancel = entr;
					if(cancel == null){
						mensagem("Opção cancelada!");
						fim = true;
						fim2 = true;
						fim3 = true;
						break;
					}
					else if(entr.equals("1")){
						controle.addCliente(cliente);
						mensagem("Cliente Adicionado Com Sucesso!");
						fim2 = true;
					}
					else if(entr.equals("2")){
						fim2 = true;
						
					}else{
						mensagem("Opção inválida!");
					}
				}
				
				while(fim3 == false){
					entr = entrada("Deseja adicionar outro cliente?\n1- Sim.\n2- Não. ");
					cancel = entr;
					if(cancel == null){
						mensagem("Opção cancelada!");
						fim = true;
						fim2 = true;
						fim3 = true;
						break;
					}
					else if(entr.equals("1")){
						fim3 = true;
					}
					else if(entr.equals("2")){
						mensagem("Opção Finalizada!");
						fim = true;
						fim2 = true;
						fim3 = true;
						
					}else{
						mensagem("Opção inválida!");
					}
				}
		
			}
			
		}catch(ControleDeVendasException e){
			mensagem(e.getMessage());
		}

	}
	
	public void opcaoGetCliente(){
		StringBuffer sb = new StringBuffer("");
		try{
			ControleDeVendas controle = new ControleDeVendas();
			
			String entr = entrada("Informe o CPF do cliente: ");
			Cliente cliente = controle.getCliente(entr);
			sb.append("Nome: " + cliente.getNome() + " | CPF: " + cliente.getCpf() + " | Telefone: "
					+ cliente.getTelefone());
										
			
			mensagem(sb.toString());
		}catch(ControleDeVendasException e){
			mensagem(e.getMessage());
		}
	}
	
}
