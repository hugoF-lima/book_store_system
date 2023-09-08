package acessoDadosBeans;

import java.util.Arrays;
import java.util.Date;

public class FuncionarioBean {
	
	int id_func;
	String nome_func;
	String email_func;
	String telefone_func;
	String cpf_func;
	String sexo_func;
	String rg_func;
	Date dataNasc_func;
	String cep_func;
	String logradouro_func;
	String numero_func;
	String complemento_func;
	String bairro_func;
	String cidade_func;
	String estado_func;
	String senha_func;
	String ativo_inativo_status;
	byte[] foto;
	String dataAdmissao_func;
	Date dataRecisao_func;
	private int id_cargo_fk;
	private String nome_cargo;
	//private CargoBean cargo;
	
	String column_func;
	String textPesq_func;
	
	
	
	public int getId_func() {
		return id_func;
	}
	public void setId_func(int id_func) {
		this.id_func = id_func;
	}
	public String getNome_func() {
		return nome_func;
	}
	public void setNome_func(String nome_func) {
		this.nome_func = nome_func;
	}
	public String getEmail_func() {
		return email_func;
	}
	public void setEmail_func(String email_func) {
		this.email_func = email_func;
	}
	public String getTelefone_func() {
		return telefone_func;
	}
	public void setTelefone_func(String telefone_func) {
		this.telefone_func = telefone_func;
	}
	public String getCpf_func() {
		return cpf_func;
	}
	public void setCpf_func(String cpf_func) {
		this.cpf_func = cpf_func;
	}
	public String getSexo_func() {
		return sexo_func;
	}
	public void setSexo_func(String sexo_func) {
		this.sexo_func = sexo_func;
	}
	public String getRg_func() {
		return rg_func;
	}
	public void setRg_func(String rg_func) {
		this.rg_func = rg_func;
	}
	public Date getDataNasc_func() {
		return dataNasc_func;
	}
	public void setDataNasc_func(Date dataNasc_func) {
		this.dataNasc_func = dataNasc_func;
	}
	public String getCep_func() {
		return cep_func;
	}
	public void setCep_func(String cep_func) {
		this.cep_func = cep_func;
	}
	public String getLogradouro_func() {
		return logradouro_func;
	}
	public void setLogradouro_func(String logradouro_func) {
		this.logradouro_func = logradouro_func;
	}
	public String getNumero_func() {
		return numero_func;
	}
	public void setNumero_func(String numero_func) {
		this.numero_func = numero_func;
	}
	public String getComplemento_func() {
		return complemento_func;
	}
	public void setComplemento_func(String complemento_func) {
		this.complemento_func = complemento_func;
	}
	public String getBairro_func() {
		return bairro_func;
	}
	public void setBairro_func(String bairro_func) {
		this.bairro_func = bairro_func;
	}
	public String getCidade_func() {
		return cidade_func;
	}
	public void setCidade_func(String cidade_func) {
		this.cidade_func = cidade_func;
	}
	public String getEstado_func() {
		return estado_func;
	}
	public void setEstado_func(String estado_func) {
		this.estado_func = estado_func;
	}
	public String getSenha_func() {
		return senha_func;
	}
	public void setSenha_func(String senha_func) {
		this.senha_func = senha_func;
	}
	public String getAtivo_inativo_status() {
		return ativo_inativo_status;
	}
	public void setAtivo_inativo_status(String ativo_inativo_status) {
		this.ativo_inativo_status = ativo_inativo_status;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getDataAdmissao_func() {
		return dataAdmissao_func;
	}
	public void setDataAdmissao_func(String dataAdmissao_func) {
		this.dataAdmissao_func = dataAdmissao_func;
	}
	public Date getDataRecisao_func() {
		return dataRecisao_func;
	}
	public void setDataRecisao_func(Date dataRecisao_func) {
		this.dataRecisao_func = dataRecisao_func;
	}
	public int getId_cargo_fk() {
		return id_cargo_fk;
	}
	public void setId_cargo_fk(int id_cargo_fk) {
		this.id_cargo_fk = id_cargo_fk;
	}
	public String getNome_cargo() {
		return nome_cargo;
	}
	public void setNome_cargo(String nome_cargo) {
		this.nome_cargo = nome_cargo;
	}
	public String getColumn_func() {
		return column_func;
	}
	public void setColumn_func(String column_func) {
		this.column_func = column_func;
	}
	public String getTextPesq_func() {
		return textPesq_func;
	}
	public void setTextPesq_func(String textPesq_func) {
		this.textPesq_func = textPesq_func;
	}
	


	@Override
	public String toString() {
		return "FuncionarioBean [id_func=" + id_func + ", nome_func="
				+ nome_func + ", email_func=" + email_func + ", telefone_func="
				+ telefone_func + ", cpf_func=" + cpf_func + ", sexo_func="
				+ sexo_func + ", rg_func=" + rg_func + ", dataNasc_func="
				+ dataNasc_func + ", cep_func=" + cep_func
				+ ", logradouro_func=" + logradouro_func + ", numero_func="
				+ numero_func + ", complemento_func=" + complemento_func
				+ ", bairro_func=" + bairro_func + ", cidade_func="
				+ cidade_func + ", estado_func=" + estado_func
				+ ", senha_func=" + senha_func + ", ativo_inativo_status="
				+ ativo_inativo_status + ", foto=" + Arrays.toString(foto)
				+ ", dataAdmissao_func=" + dataAdmissao_func
				+ ", dataRecisao_func=" + dataRecisao_func + ", id_cargo_fk="
				+ id_cargo_fk + ", nome_cargo=" + nome_cargo + ", column_func="
				+ column_func + ", textPesq_func=" + textPesq_func + "]";
	}

	
	

	

}
