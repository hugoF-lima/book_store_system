package acessoDadosBeans;

import java.util.ArrayList;
import java.util.Arrays;

public class PedidoVendaRegistroBean {
	
	int Id_PedidoVendaRegistro;
	ArrayList<ProdutoLivroBean> PedidoVendaItens;
	FuncionarioBean func;
	ClienteBean cli;
	PagamentoVendaBean PagtoVenda;
	float valor_Total;
	byte[] boleto_Venda;
	
	String dataVenda;
	String NomeFuncVenda;
	String NomeCliVenda;
	
	
	public PedidoVendaRegistroBean(){
		this.PedidoVendaItens = new ArrayList<ProdutoLivroBean>();
		this.func = new FuncionarioBean();
		this.cli = new ClienteBean();
	}

	public int getId_PedidoVendaRegistro() {
		return Id_PedidoVendaRegistro;
	}

	public void setId_PedidoVendaRegistro(int id_PedidoVendaRegistro) {
		Id_PedidoVendaRegistro = id_PedidoVendaRegistro;
	}

	public ArrayList<ProdutoLivroBean> getPedidoVendaItens() {
		return PedidoVendaItens;
	}

	public void setPedidoVendaItens(ArrayList<ProdutoLivroBean> pedidoVendaItens) {
		PedidoVendaItens = pedidoVendaItens;
	}

	public FuncionarioBean getFunc() {
		return func;
	}

	public void setFunc(FuncionarioBean func) {
		this.func = func;
	}

	public ClienteBean getCli() {
		return cli;
	}

	public void setCli(ClienteBean cli) {
		this.cli = cli;
	}

	public PagamentoVendaBean getPagtoVenda() {
		return PagtoVenda;
	}

	public void setPagtoVenda(PagamentoVendaBean pagtoVenda) {
		PagtoVenda = pagtoVenda;
	}

	public float getValor_Total() {
		return valor_Total;
	}

	public void setValor_Total(float valor_Total) {
		this.valor_Total = valor_Total;
	}



	public byte[] getBoleto_Venda() {
		return boleto_Venda;
	}

	public void setBoleto_Venda(byte[] boleto_Venda) {
		this.boleto_Venda = boleto_Venda;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getNomeFuncVenda() {
		return NomeFuncVenda;
	}

	public void setNomeFuncVenda(String nomeFuncVenda) {
		NomeFuncVenda = nomeFuncVenda;
	}

	public String getNomeCliVenda() {
		return NomeCliVenda;
	}

	public void setNomeCliVenda(String nomeCliVenda) {
		NomeCliVenda = nomeCliVenda;
	}

	@Override
	public String toString() {
		return "PedidoVendaRegistroBean [Id_PedidoVendaRegistro="
				+ Id_PedidoVendaRegistro + ", PedidoVendaItens="
				+ PedidoVendaItens + ", func=" + func + ", cli=" + cli
				+ ", PagtoVenda=" + PagtoVenda + ", valor_Total=" + valor_Total
				+ ", boleto_Venda=" + Arrays.toString(boleto_Venda) + "]";
	}

	



}
