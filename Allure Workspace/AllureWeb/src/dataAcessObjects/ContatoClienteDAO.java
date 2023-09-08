package dataAcessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utilities.ConnectionFactory;

import acessoDadosBeans.ContatoClienteBean;

public class ContatoClienteDAO {
	
	private static final String Insert_Contato = "insert Contato_Cliente (Nome_Cli_Contato, Email_Cli_Contato, Assunto_Cli_Contato , Mensagem_Cli_Contato, Data_Cli_Contato) values (?,?,?,?, getdate())";

	public static void adicionarContato(ContatoClienteBean ccb){
		
		PreparedStatement statement = null;
		Connection conexao = null;
		
		try{
			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Insert_Contato);
			
			statement.setString(1, ccb.getNome_Cli_Contato());
			statement.setString(2, ccb.getEmail_Cli_Contato());
			statement.setString(3, ccb.getAssunto_Cli_Contato());
			statement.setString(4, ccb.getMensagem_Cli_Contato());		
			statement.execute();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		} finally {
			try {
				ConnectionFactory.close(null, statement, conexao);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
