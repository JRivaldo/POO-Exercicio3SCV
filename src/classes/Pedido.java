package classes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Pedido {
	
	private Date data;
	private Cliente cliente;
	private Integer codPedido = 10000;
	private List<ItemDePedido> listaItens = new ArrayList<ItemDePedido>();
	private Map<Produto, ItemDePedido> mapItens = new HashMap<Produto, ItemDePedido>();
	
	public void addProduto(Produto produto){
		if(this.mapItens.containsKey(produto)){
			this.mapItens.get(produto).somar(1);
			
		}
		else{
			ItemDePedido item = new ItemDePedido(produto,1);
			this.mapItens.put(produto, item);
			this.listaItens.add(item);
			
		}
		
	}
	
	public void addProduto(Produto produto, int quantidade){
		if(this.mapItens.containsKey(produto)){
			this.mapItens.get(produto).somar(quantidade);
			
		}
		else{
			ItemDePedido item = new ItemDePedido(produto,quantidade);
			this.mapItens.put(produto, item);
			this.listaItens.add(item);
			
		}
		
	}
	
	public double totalPedido(){
		double total = 0;
		for(ItemDePedido pedidos : this.listaItens){
			total = total + pedidos.totalItem();
		}
				
		return total;
	}
	
	public List<ItemDePedido> listarItensPedido(){
		List<ItemDePedido> lista2 = this.listaItens;
		List<String> produtoNome =  new ArrayList<String>();
		
		
		for(ItemDePedido pedido: this.listaItens){
			produtoNome.add(pedido.getProduto().getNome());
		}
		
		
		Collections.sort(produtoNome);
		
		List<ItemDePedido> lista3 = new ArrayList<ItemDePedido>(this.listaItens.size());
		
		for(String produto: produtoNome){
			for(ItemDePedido pedido: lista2){
				if(produto.equals(pedido.getProduto().getNome())){
					lista3.add(pedido);
				}
			}
		}
		return lista3;
	}
	
	public List<ItemDePedido> getListaItens(){
		return this.listaItens;
	}

	public Date getData() {
		return data;
	}
	
	public String getDateAtual() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date data = new Date();
	    return dateFormat.format(data);
	}


	public void setData(String date) throws SCVException {
		try{
			DateFormat forma = new SimpleDateFormat("dd/MM/yyyy"); 
			java.util.Date dataAux = new java.util.Date(forma.parse(date).getTime()); 
			this.data = dataAux; 
		}catch(ParseException e){
			throw new SCVException("Erro no formato da data!");
		}
		

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Integer codPedido) {
		if((codPedido > 999999) || (codPedido < 0)){
			throw new SCVRuntimeException("Código de pedido inválido!");
		}
		else{
			this.codPedido = codPedido;
		}
		
	}


}
