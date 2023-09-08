package dataAcessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import acessoDadosBeans.ContatoClienteBean;

import utilities.ConnectionFactory;
import utilities.TableModel;

public class ContatoOnlineDAO {

	private static final String LoadOnlineContact = "select * from Contato_Cliente";
	private static final String LoadOnlineContactById = "select * from Contato_Cliente where Id_Cli_Contato = ?";



	public static void LoadClienteTable(JTable LoadCadastroCli){

		String[] Columns = new String[]{"Cód.","Nome","Email","Assunto","Mensagem","Data Contato"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();


		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(LoadOnlineContact);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_cli_Contato"), result.getString("Nome_cli_Contato"), result.getString("Email_cli_Contato")
						,result.getString("Assunto_cli_Contato"), result.getString("Mensagem_cli_Contato"), result.getString("Data_Cli_Contato")});

				//DateFormat.getInstance().format(result.getString("data_Cadastro_cli"))});

			}


		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Columns);
		LoadCadastroCli.setModel(modelo);
	}
	
	public static ContatoClienteBean retornarContato(ContatoClienteBean ccb){

		//String sql = "Select * from Cliente where id_cli = " + c.getId_cli();

		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(LoadOnlineContactById);
			statement.setLong(1, ccb.getId_Cli_Contato());
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				//java.sql.Date dataNascCliSql = new java.sql.Date(.getDate().getTime()); 
				//java.sql.Date dataNascCadastroSql = new java.sql.Date(data_Nasc_Cli.getDate().getTime());

				ccb.setId_Cli_Contato(result.getLong("ID_cli_Contato"));
				ccb.setNome_Cli_Contato(result.getString("Nome_cli_Contato"));
				ccb.setEmail_Cli_Contato(result.getString("Email_cli_Contato"));
				ccb.setAssunto_Cli_Contato(result.getString("Assunto_cli_Contato"));
				ccb.setMensagem_Cli_Contato(result.getString("Mensagem_cli_Contato"));
				ccb.setData_Cli_Contato(result.getString("Data_Cli_Contato"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ccb;

	}

}