package acessoDadosBeans;

public class CargoBean {

	private int idCargo;
	private String nomeCargo;
	float salarioCargo;
	String descricaoCargo;

	String acessoSistemaPermissao;
	String cadastroConsultaCliPermissao;
	String cadastroConsultaFuncPermissao;
	String cadastroConsultaCargoPermissao;
	String cadastroConsultaFornecPermissao;
	String cadastroConsultaContatoClientePermissao;
	String cadastroConsulta_Prod_Livro_Permissao;
	String VendasPermissao;
	
	String Ativo_inativo_status_Cargo;
	
	FuncionarioBean func;
	
	public CargoBean(int id, String nome) {
		super();
		this.idCargo = id;
		this.nomeCargo = nome;
		this.func = new FuncionarioBean();
	}
	
	@Override
	public String toString() {
		return nomeCargo;
	}
	
	public int getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(int id) {
		this.idCargo = id;
	}
	public String getNomeCargo() {
		return nomeCargo;
	}
	public void setNomeCargo(String nome) {
		this.nomeCargo = nome;
	}
	
	public float getSalarioCargo() {
		return salarioCargo;
	}
	public void setSalarioCargo(float salario) {
		this.salarioCargo = salario;
	}
	public String getDescricaoCargo() {
		return descricaoCargo;
	}
	public void setDescricaoCargo(String descricao) {
		this.descricaoCargo = descricao;
	}
	
	public String getAcessoSistemaPermissao() {
		return acessoSistemaPermissao;
	}

	public void setAcessoSistemaPermissao(String acessoSistemaPermissao) {
		this.acessoSistemaPermissao = acessoSistemaPermissao;
	}

	public String getCadastroConsultaCliPermissao() {
		return cadastroConsultaCliPermissao;
	}

	public void setCadastroConsultaCliPermissao(String cadastroConsultaCliPermissao) {
		this.cadastroConsultaCliPermissao = cadastroConsultaCliPermissao;
	}

	public String getCadastroConsultaFuncPermissao() {
		return cadastroConsultaFuncPermissao;
	}

	public void setCadastroConsultaFuncPermissao(
			String cadastroConsultaFuncPermissao) {
		this.cadastroConsultaFuncPermissao = cadastroConsultaFuncPermissao;
	}

	public String getCadastroConsultaCargoPermissao() {
		return cadastroConsultaCargoPermissao;
	}

	public void setCadastroConsultaCargoPermissao(
			String cadastroConsultaCargoPermissao) {
		this.cadastroConsultaCargoPermissao = cadastroConsultaCargoPermissao;
	}

	public String getCadastroConsultaFornecPermissao() {
		return cadastroConsultaFornecPermissao;
	}

	public void setCadastroConsultaFornecPermissao(
			String cadastroConsultaFornecPermissao) {
		this.cadastroConsultaFornecPermissao = cadastroConsultaFornecPermissao;
	}

	public String getCadastroConsulta_Prod_Livro_Permissao() {
		return cadastroConsulta_Prod_Livro_Permissao;
	}

	public void setCadastroConsulta_Prod_Livro_Permissao(
			String cadastroConsulta_Prod_Livro_Permissao) {
		this.cadastroConsulta_Prod_Livro_Permissao = cadastroConsulta_Prod_Livro_Permissao;
	}

	public String getCadastroConsultaContatoClientePermissao() {
		return cadastroConsultaContatoClientePermissao;
	}

	public void setCadastroConsultaContatoClientePermissao(
			String cadastroConsultaContatoClientePermissao) {
		this.cadastroConsultaContatoClientePermissao = cadastroConsultaContatoClientePermissao;
	}

	public String getVendasPermissao() {
		return VendasPermissao;
	}

	public void setVendasPermissao(String controleVendasPermissao) {
		this.VendasPermissao = controleVendasPermissao;
	}

	public String getAtivo_inativo_status_Cargo() {
		return Ativo_inativo_status_Cargo;
	}

	public void setAtivo_inativo_status_Cargo(String ativo_inativo_status_Cargo) {
		Ativo_inativo_status_Cargo = ativo_inativo_status_Cargo;
	}

	public FuncionarioBean getFunc() {
		return func;
	}

	public void setFunc(FuncionarioBean func) {
		this.func = func;
	}

	public CargoBean(){
		
	}
	
}
