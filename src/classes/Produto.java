package classes;

public class Produto {
	
	private String nome;
	private Integer codigo = 0;
	private String descricao;
	private double preco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		if((codigo > 999999) || (codigo < 0)){
			throw new SCVRuntimeException("Código de pedido inválido!");
		}
		else{
			this.codigo = codigo;
		}
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	

}
