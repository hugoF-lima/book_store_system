package acessoDadosBeans;

import java.util.Date;

public class FornecedorBean {
	
	int Id_fornec ;
	String Nome_fornec ;
	String email_fornec; 
	String tel_fornec ;
	String cnpj_fornec ;
	String cep_fornec ;
	String logradouro_fornec ;
	String numero_fornec ;
	String complemento_fornec ;
	String bairro_fornec ;
	String cidade_fornec ;
	String estado_fornec ;
	String ativo_inativo_status_fornec;
	Date Data_Admissao_fornec;
	Date dataRescicao_fornec;
	
	String CadastradoPorFunc_fornec;
	
	FuncionarioBean func;
	
	public FornecedorBean(int id_fornec, String nome_fornec) {
		super();
		this.Id_fornec = id_fornec;
		this.Nome_fornec = nome_fornec;
		this.func = new FuncionarioBean();

	}
	
	@Override
	public String toString() {
		return Nome_fornec;
	}
	

	
	public int getId_fornec() {
		return Id_fornec;
	}

	public void setId_fornec(int id_fornec) {
		Id_fornec = id_fornec;
	}

	public String getNome_fornec() {
		return Nome_fornec;
	}

	public void setNome_fornec(String nome_fornec) {
		Nome_fornec = nome_fornec;
	}

	public String getEmail_fornec() {
		return email_fornec;
	}

	public void setEmail_fornec(String email_fornec) {
		this.email_fornec = email_fornec;
	}

	public String getTel_fornec() {
		return tel_fornec;
	}

	public void setTel_fornec(String tel_fornec) {
		this.tel_fornec = tel_fornec;
	}

	public String getCnpj_fornec() {
		return cnpj_fornec;
	}

	public void setCnpj_fornec(String cnpj_fornec) {
		this.cnpj_fornec = cnpj_fornec;
	}

	public String getCep_fornec() {
		return cep_fornec;
	}

	public void setCep_fornec(String cep_fornec) {
		this.cep_fornec = cep_fornec;
	}

	public String getLogradouro_fornec() {
		return logradouro_fornec;
	}

	public void setLogradouro_fornec(String logradouro_fornec) {
		this.logradouro_fornec = logradouro_fornec;
	}

	public String getNumero_fornec() {
		return numero_fornec;
	}

	public void setNumero_fornec(String numero_fornec) {
		this.numero_fornec = numero_fornec;
	}

	public String getComplemento_fornec() {
		return complemento_fornec;
	}

	public void setComplemento_fornec(String complemento_fornec) {
		this.complemento_fornec = complemento_fornec;
	}

	public String getBairro_fornec() {
		return bairro_fornec;
	}

	public void setBairro_fornec(String bairro_fornec) {
		this.bairro_fornec = bairro_fornec;
	}

	public String getCidade_fornec() {
		return cidade_fornec;
	}

	public void setCidade_fornec(String cidade_fornec) {
		this.cidade_fornec = cidade_fornec;
	}

	public String getEstado_fornec() {
		return estado_fornec;
	}

	public void setEstado_fornec(String estado_fornec) {
		this.estado_fornec = estado_fornec;
	}

	public String getAtivo_inativo_status_fornec() {
		return ativo_inativo_status_fornec;
	}

	public void setAtivo_inativo_status_fornec(String ativo_inativo_status_fornec) {
		this.ativo_inativo_status_fornec = ativo_inativo_status_fornec;
	}

	public Date getData_Admissao_fornec() {
		return Data_Admissao_fornec;
	}

	public void setData_Admissao_fornec(Date data_Admissao_fornec) {
		Data_Admissao_fornec = data_Admissao_fornec;
	}

	public Date getDataRescicao_fornec() {
		return dataRescicao_fornec;
	}

	public void setDataRescicao_fornec(Date dataRescicao_fornec) {
		this.dataRescicao_fornec = dataRescicao_fornec;
	}

	public String getCadastradoPorFunc_fornec() {
		return CadastradoPorFunc_fornec;
	}

	public void setCadastradoPorFunc_fornec(String cadastradoPor_fornec) {
		CadastradoPorFunc_fornec = cadastradoPor_fornec;
	}

	public FuncionarioBean getFunc() {
		return func;
	}

	public void setFunc(FuncionarioBean func) {
		this.func = func;
	}

	public FornecedorBean(){
		
	}
	
	

}
