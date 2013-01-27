package classes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProdutoPerecivel extends Produto{
	
	private Date validade;

	public Date getValidade() {
		return validade;
	}

	public void setValidade(String data) throws ParseException {
		DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date validade = format1.parse(data);
		this.validade = validade;
	}
	
	

}
