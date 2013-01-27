package classes;

public class ItemDePedido {
	
	private Produto produto;
	private Integer quantidade = 0;
	
	public ItemDePedido(){}
	
	public ItemDePedido(Produto produto, Integer quantidade){
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	public double totalItem(){
		double total = produto.getPreco() * this.quantidade;
		return total;
	}
	
	public void somar(int qnt){
		this.quantidade = this.quantidade + qnt;
	}

}
