package acessoDadosBeans;

public class PagamentoFormaBean {
	
	private int id_FormaPagamento;
	private String nome_Pagamento;
	
	public PagamentoFormaBean(int id_FormaPagamento, String nome_Pagamento){
		super();
		this.id_FormaPagamento = id_FormaPagamento;
		this.nome_Pagamento = nome_Pagamento;
		
	}
	
	@Override
	public String toString() {
		return nome_Pagamento;
	}

	public int getId_FormaPagamento() {
		return id_FormaPagamento;
	}

	public void setId_FormaPagamento(int id_Genero) {
		this.id_FormaPagamento = id_Genero;
	}

	public String getNome_Pagamento() {
		return nome_Pagamento;
	}

	public void setNome_Pagamento(String nome_Genero) {
		nome_Pagamento = nome_Genero;
	}
	
	public PagamentoFormaBean(){
		
	}

	/*@Override
	public String toString() {
		return "PagamentoFormaBean [id_FormaPagamento=" + id_FormaPagamento
				+ ", nome_Pagamento=" + nome_Pagamento + "]";
	}*/

}
