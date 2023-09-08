package acessoDadosBeans;


import java.util.Date;

public class ClienteBean {
	//continuar daqui... criar um getter e um setter para coluna e texto digitado com relação a pesquisa
	int id_cli;
	String nome_cli;
	String email_cli;
	String telefone_cli;
	String cpf_cli;
	String sexo_cli;
	String rg_cli;
	String cep_cli;
	Date dataNasc_cli;
	String logradouro_cli;
	String numero_cli;
	String complemento_cli;
	String bairro_cli;
	String cidade_cli;
	String estado_cli;
	String dataCadastro_cli; 
	String ativo_inativo_status;
	int id_forma_pagamento_fk;
	FuncionarioBean func;

	String column_cli;
	String textPesq_cli;

	String cadastradoPor;
	String dataRecisaoCli;

	public ClienteBean(){
		this.func = new FuncionarioBean();
	}

	public int getId_cli() {
		return id_cli;
	}

	public void setId_cli(int id_cli) {
		this.id_cli = id_cli;
	}

	public String getNome_cli() {
		return nome_cli;
	}

	public void setNome_cli(String nome_cli) {
		this.nome_cli = nome_cli;
	}

	public String getEmail_cli() {
		return email_cli;
	}

	public void setEmail_cli(String email_cli) {
		this.email_cli = email_cli;
	}

	public String getTelefone_cli() {
		return telefone_cli;
	}

	public void setTelefone_cli(String telefone_cli) {
		this.telefone_cli = telefone_cli;
	}

	public String getCpf_cli() {
		return cpf_cli;
	}

	public void setCpf_cli(String cpf_cli) {
		this.cpf_cli = cpf_cli;
	}

	public String getSexo_cli() {
		return sexo_cli;
	}

	public void setSexo_cli(String sexo_cli) {
		this.sexo_cli = sexo_cli;
	}

	public String getRg_cli() {
		return rg_cli;
	}

	public void setRg_cli(String rg_cli) {
		this.rg_cli = rg_cli;
	}

	public String getCep_cli() {
		return cep_cli;
	}

	public void setCep_cli(String cep_cli) {
		this.cep_cli = cep_cli;
	}

	public Date getDataNasc_cli() {
		return dataNasc_cli;
	}

	public void setDataNasc_cli(Date dataNasc_cli) {
		this.dataNasc_cli = dataNasc_cli;
	}

	public String getLogradouro_cli() {
		return logradouro_cli;
	}

	public void setLogradouro_cli(String logradouro_cli) {
		this.logradouro_cli = logradouro_cli;
	}

	public String getNumero_cli() {
		return numero_cli;
	}

	public void setNumero_cli(String numero_cli) {
		this.numero_cli = numero_cli;
	}

	public String getComplemento_cli() {
		return complemento_cli;
	}

	public void setComplemento_cli(String complemento_cli) {
		this.complemento_cli = complemento_cli;
	}

	public String getBairro_cli() {
		return bairro_cli;
	}

	public void setBairro_cli(String bairro_cli) {
		this.bairro_cli = bairro_cli;
	}

	public String getCidade_cli() {
		return cidade_cli;
	}

	public void setCidade_cli(String cidade_cli) {
		this.cidade_cli = cidade_cli;
	}

	public String getEstado_cli() {
		return estado_cli;
	}

	public void setEstado_cli(String estado_cli) {
		this.estado_cli = estado_cli;
	}

	public String getDataCadastro_cli() {
		return dataCadastro_cli;
	}

	public void setDataCadastro_cli(String dataCadastro_cli) {
		this.dataCadastro_cli = dataCadastro_cli;
	}

	public String getAtivo_inativo_status() {
		return ativo_inativo_status;
	}

	public void setAtivo_inativo_status(String ativo_inativo_status) {
		this.ativo_inativo_status = ativo_inativo_status;
	}

	public int getId_forma_pagamento_fk() {
		return id_forma_pagamento_fk;
	}

	public void setId_forma_pagamento_fk(int id_forma_pagamento_fk) {
		this.id_forma_pagamento_fk = id_forma_pagamento_fk;
	}

	public FuncionarioBean getFunc() {
		return func;
	}

	public void setFunc(FuncionarioBean func) {
		this.func = func;
	}

	public String getColumn_cli() {
		return column_cli;
	}

	public void setColumn_cli(String column_cli) {
		this.column_cli = column_cli;
	}

	public String getTextPesq_cli() {
		return textPesq_cli;
	}

	public void setTextPesq_cli(String textPesq_cli) {
		this.textPesq_cli = textPesq_cli;
	}

	public String getCadastradoPor() {
		return cadastradoPor;
	}

	public void setCadastradoPor(String cadastradoPor) {
		this.cadastradoPor = cadastradoPor;
	}

	public String getDataRecisaoCli() {
		return dataRecisaoCli;
	}

	public void setDataRecisaoCli(String dataRecisaoCli) {
		this.dataRecisaoCli = dataRecisaoCli;
	}

	@Override
	public String toString() {
		return "ClienteBean [id_cli=" + id_cli + ", nome_cli=" + nome_cli
		                     + ", email_cli=" + email_cli + ", telefone_cli="
		                     + telefone_cli + ", cpf_cli=" + cpf_cli + ", sexo_cli="
		                     + sexo_cli + ", rg_cli=" + rg_cli + ", cep_cli=" + cep_cli
		                     + ", dataNasc_cli=" + dataNasc_cli + ", logradouro_cli="
		                     + logradouro_cli + ", numero_cli=" + numero_cli
		                     + ", complemento_cli=" + complemento_cli + ", bairro_cli="
		                     + bairro_cli + ", cidade_cli=" + cidade_cli
		                     + ", estado_cli=" + estado_cli + ", dataCadastro_cli="
		                     + dataCadastro_cli + ", ativo_inativo_status="
		                     + ativo_inativo_status + ", id_forma_pagamento_fk="
		                     + id_forma_pagamento_fk + ", func=" + func
		                     + ", column_cli=" + column_cli + ", textPesq_cli="
		                     + textPesq_cli + "]";
	}








}
