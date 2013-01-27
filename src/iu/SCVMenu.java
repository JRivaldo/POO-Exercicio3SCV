package iu;

import classes.ControleDeVendasException;

public class SCVMenu {
	
	public static void main(String args[]){
		boolean fim = false;
		boolean fim2 = false;
		boolean fim3 = false;
		boolean fim4 = false;
		OpcaoSCV op = new OpcaoSCV();
		String entr;
		String cancel;
		
			while(fim == false){
				fim2 = false;
				fim3 = false;
				fim4 = false;
				entr = op.entrada("MENU SCV\n\n1- Pedidos.\n2- Clientes.\n3- Produto.\n4- sair.");
				cancel = entr;
				try{
					if(cancel == null){
						op.mensagem("Programa cancelado!");
						fim = true;
						fim2 = true;
						fim3 = true;
						fim4 = true;
						break;
					}
					else if(entr.equals("1")){
						while(fim2 == false){
							entr = op.entrada("MENU PEDIDOS\n\n1- Adicionar Pedidos.\n2- Listar Pedidos.\n3- " +
									"Listar Pedidos(Data)\n4- Listar Pedidos(Cliente).\n5- sair.");
							cancel = entr;
							if(cancel == null){
								op.mensagem("Opção cancelada!");
								fim2 = true;
								break;
							}
							else if(entr.equals("1")){
								op.opcaoNovoPedido();
							}
							else if(entr.equals("2")){
								op.opcaoListarPedidos();
							}
							else if(entr.equals("3")){
								op.opcaoListarPedidosData();
							}
							else if(entr.equals("4")){
								op.opcaoListarPedidosCliente();
							}
							else if(entr.equals("5")){
								fim2 = true;
							}
							else{
								op.mensagem("Opção inválida!");
							}
						}
					}
					else if(entr.equals("2")){
						while(fim3 == false){
							entr = op.entrada("MENU CLIENTES\n\n1- Adicionar Clientes.\n2- Ver Clientes.\n3- " +
									"Sair");
							cancel = entr;
							if(cancel == null){
								op.mensagem("Opção cancelada!");
								fim3 = true;
								break;
							}
							else if(entr.equals("1")){
								op.opcaoAddCiente();
							}
							else if(entr.equals("2")){
								op.opcaoGetCliente();
							}
							else if(entr.equals("3")){
								fim3 = true;
							}
							
							else{
								op.mensagem("Opção inválida!");
							}
						}
					}
					else if(entr.equals("3")){
						while(fim4 == false){
							entr = op.entrada("MENU PRODUTOS\n\n1- Adicionar Produtos.\n2- Sair");
							cancel = entr;
							if(cancel == null){
								op.mensagem("Opção cancelada!");
								fim4 = true;
								break;
							}
							else if(entr.equals("1")){
								op.opcaoAddProduto();
							}
							else if(entr.equals("2")){
								fim4 = true;
							}
							else{
								op.mensagem("Opção inválida!");
							}
						}
					}
					else if(entr.equals("4")){
						op.mensagem("Programa Finalizado!");
						fim = true;
						fim2 = true;
						fim3 = true;
						fim4 = true;
					}
					else{
						op.mensagem("Opção inválida!");
					}
				}catch(ControleDeVendasException e){
					op.mensagem(e.getMessage());
				}
				
				
			}
		
		
	}
	

}
