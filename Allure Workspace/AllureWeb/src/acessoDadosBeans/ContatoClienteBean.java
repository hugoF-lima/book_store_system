package acessoDadosBeans;

public class ContatoClienteBean {
	
	private Long id_Cli_Contato;
	private String nome_Cli_Contato;
	private String email_Cli_Contato;
	private String assunto_Cli_Contato;
	private String mensagem_Cli_Contato;
	
	
	public Long getId_Cli_Contato() {
		return id_Cli_Contato;
	}
	public void setId_Cli_Contato(Long id_Cli_Contato) {
		this.id_Cli_Contato = id_Cli_Contato;
	}
	public String getNome_Cli_Contato() {
		return nome_Cli_Contato;
	}
	public void setNome_Cli_Contato(String nome_Cli_Contato) {
		this.nome_Cli_Contato = nome_Cli_Contato;
	}
	public String getEmail_Cli_Contato() {
		return email_Cli_Contato;
	}
	public void setEmail_Cli_Contato(String email_Cli_Contato) {
		this.email_Cli_Contato = email_Cli_Contato;
	}
	public String getAssunto_Cli_Contato() {
		return assunto_Cli_Contato;
	}
	public void setAssunto_Cli_Contato(String assunto_Cli_Contato) {
		this.assunto_Cli_Contato = assunto_Cli_Contato;
	}
	public String getMensagem_Cli_Contato() {
		return mensagem_Cli_Contato;
	}
	public void setMensagem_Cli_Contato(String mensagem_Cli_Contato) {
		this.mensagem_Cli_Contato = mensagem_Cli_Contato;
	}

}
