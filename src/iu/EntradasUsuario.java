package iu;

import io.SCVPersistenciaArquivo;
import io.SCVPersistenciaException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import classes.ItemDePedido;
import classes.Produto;
import classes.SCVException;

public class EntradasUsuario {
	
	
	public String lerEntrada(String msg){
		String entr = JOptionPane.showInputDialog(msg);
	
		return entr;
	}
	
	public void mostrarMensagem(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
	
	
	public List<ItemDePedido> entradaAddProduto() throws SCVException{
		List<ItemDePedido> listaItem = new ArrayList<ItemDePedido>();
		boolean fim = false;
		boolean fim2 = false;
		boolean codExist = false;
		String entr = "";
		String cancel = "";
		Integer cod;
		Produto prod = new Produto();
		ItemDePedido item = new ItemDePedido();
		int quant;
		
		try{
			SCVPersistenciaArquivo arq = new SCVPersistenciaArquivo();
			List<Produto> listaProdutos = arq.recuperarProdutos();
			
			while(fim == false){
				fim2 = false;
				entr = lerEntrada("Informe o código do produto:");
				cancel = entr;
				item = new ItemDePedido();
				if(cancel == null){
					mostrarMensagem("Opção cancelada!");
					fim = true;
					fim2 = true;
					break;
				}
				else{
					cod = Integer.parseInt(entr);
					for(Produto produto: listaProdutos){
						if(cod == produto.getCodigo()){
							prod = produto;
							codExist = true;
						}
					}
				}
				if(codExist == true){
					entr = lerEntrada("Informe a quantidade:");
					cancel = entr;
					if(cancel == null){
						mostrarMensagem("Opção cancelada!");
						fim = true;
						fim2 = true;
						break;
					}
					else{
						quant = Integer.parseInt(entr);
						item.setProduto(prod);
						item.setQuantidade(quant);
						listaItem.add(item);
						System.out.println(listaItem);
					}
				}
				else{
					mostrarMensagem("Código de produto inválido!");
				}
				while(fim2 == false){

					entr = lerEntrada("Deseja continuar adicionando produtos ao pedido?\n\n1- Sim.\n2- Não.");
					cancel = entr;
					if(cancel == null){
						mostrarMensagem("Opção cancelada!");
						fim = true;
						fim2 = true;
						break;
					}
					else if(entr.equals("1")){
						fim2 = true;
					}
					else if(entr.equals("2")){
						fim = true;
						fim2 = true;
					}
					else{
						mostrarMensagem("Opção inválida!");
					}
				}
			}
			
		}catch(SCVPersistenciaException e){
			throw new SCVException(e.getMessage());
		}catch(NumberFormatException e){
			throw new SCVException(e.getMessage());
		}
		
		return listaItem;
	}

		
	
}
