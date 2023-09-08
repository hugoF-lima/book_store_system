package acessoDadosBeans;

public class PagamentoVendaBean {

	int Id_PedidoVendaRegistro_fk;
	int Id_forma_pagamento_fk;
	float valor_pago;
	float valor_troco;
	
	
	
	public int getId_PedidoVendaRegistro_fk() {
		return Id_PedidoVendaRegistro_fk;
	}
	public void setId_PedidoVendaRegistro_fk(int id_PedidoVendaRegistro_fk) {
		Id_PedidoVendaRegistro_fk = id_PedidoVendaRegistro_fk;
	}
	public int getId_forma_pagamento_fk() {
		return Id_forma_pagamento_fk;
	}
	public void setId_forma_pagamento_fk(int id_forma_pagamento_fk) {
		Id_forma_pagamento_fk = id_forma_pagamento_fk;
	}
	public float getValor_pago() {
		return valor_pago;
	}
	
	public void setValor_pago(float valor_pago) {
		this.valor_pago = valor_pago;
	}
	
	public float getValor_troco() {
		return valor_troco;
	}
	public void setValor_troco(float valor_troco) {
		this.valor_troco = valor_troco;
	}
	@Override
	public String toString() {
		return "PagamentoVendaBean [Id_PedidoVendaRegistro_fk="
				+ Id_PedidoVendaRegistro_fk + ", Id_forma_pagamento_fk="
				+ Id_forma_pagamento_fk + ", valor_pago=" + valor_pago
				+ ", valor_troco=" + valor_troco + "]";
	}
	
	
}
