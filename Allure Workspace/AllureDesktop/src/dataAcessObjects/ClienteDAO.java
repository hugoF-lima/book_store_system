package dataAcessObjects;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import utilities.ConnectionFactory;
import utilities.TableModel;
import acessoDadosBeans.ClienteBean;

public class ClienteDAO {


	private static final String Insert_Cliente = "Insert Cliente Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), 'Ativo', null, ?)";

	private static final String LogBalcaoClienteAs_Standard = "Select * from Cliente where ID_cli = '1'";

	private static final String verifyIfCPFExistsCli = "Select * from Cliente where CPF_cli like ?";

	private static final String Return_Cli = "Select * from Func_Cli where id_cli = ?";

	private static final String Update_Cliente = "update Cliente set Nome_Cli = ?, Email_Cli = ?, Tel_Cli = ?, CPF_cli = ?, Sexo_Cli = ?, " +
	"RG_Cli = ?, Data_Nasc_cli = ?, CEP_Cli = ?, Logradouro_cli = ?, Numero_cli = ?, Complemento_cli = ?, " +
	"Bairro_cli = ?, Cidade_cli = ?, Estado_cli = ? Where ID_cli = ?";

	private static final String Activate_Cli = "Update Cliente set Data_Resicao_cli = null, Ativo_Inativo_Status_Cli = 'Ativo' where Id_cli = ?";

	private static final String Deactivate_Cli = "Update Cliente set Data_Resicao_Cli = getdate(), Ativo_Inativo_Status_Cli = 'Inativo' where Id_cli = ?";


	public static void cadastrarCliente(ClienteBean c) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {
			System.out.println(Insert_Cliente);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Insert_Cliente);

			statement.setString(1, c.getNome_cli());
			statement.setString(2, c.getEmail_cli());
			statement.setString(3, c.getTelefone_cli());
			statement.setString(4, c.getCpf_cli());
			statement.setString(5, c.getSexo_cli());
			statement.setString(6, c.getRg_cli());
			statement.setDate(7, (Date) c.getDataNasc_cli());
			statement.setString(8, c.getCep_cli());
			statement.setString(9, c.getLogradouro_cli());
			statement.setString(10, c.getNumero_cli());
			statement.setString(11, c.getComplemento_cli());
			statement.setString(12, c.getBairro_cli());
			statement.setString(13, c.getCidade_cli());
			statement.setString(14, c.getEstado_cli());
			statement.setInt(15, c.getFunc().getId_func());
			statement.execute();

			JOptionPane.showMessageDialog(null,"Cliente cadastrado(a) com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Falha ao Cadastrar, verifique se os campos estão preenchidos corretamente!"
					+ "\n             Caso o erro persistir, contate os desenvolvedores do sistema!",
					"Erro!", JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e1);
		} finally {
			try {
				ConnectionFactory.close(null, statement, conexao);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	public static ClienteBean verificarCpfCli(String cpf) {

		PreparedStatement statement = null;
		Connection conexao = null;
		ClienteBean cli = new ClienteBean();
		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(verifyIfCPFExistsCli);
			statement.setString(1, cpf);

			ResultSet result = statement.executeQuery();

			if(result.next()){

				cli.setCpf_cli(result.getString("CPF_cli"));

			}

			conexao.close();
			statement = null;

		} catch (SQLException e1) {
			//return false;
		}
		return cli;

	}



	public static void alterarCliente(ClienteBean c) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			System.out.println(Update_Cliente);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Update_Cliente);

			statement.setString(1, c.getNome_cli());
			statement.setString(2, c.getEmail_cli());
			statement.setString(3, c.getTelefone_cli());
			statement.setString(4, c.getCpf_cli());
			statement.setString(5, c.getSexo_cli());
			statement.setString(6, c.getRg_cli());
			statement.setDate(7, (Date) c.getDataNasc_cli());
			statement.setString(8, c.getCep_cli());
			statement.setString(9, c.getLogradouro_cli());
			statement.setString(10, c.getNumero_cli());
			statement.setString(11, c.getComplemento_cli());
			statement.setString(12, c.getBairro_cli());
			statement.setString(13, c.getCidade_cli());
			statement.setString(14, c.getEstado_cli());
			statement.setInt(15, c.getId_cli());
			statement.execute();

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Cliente alterado(a) com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane
			.showMessageDialog(
					null,
					"Falha ao alterar, verifique se os campos estão preenchidos corretamente \n se o erro persistir, contate os desenvolvedores para resolver esse problema",
					"Erro!", JOptionPane.ERROR_MESSAGE);

			throw new RuntimeException(e1);

		} finally {
			try {
				ConnectionFactory.close(null, statement, conexao);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/*
		 * public static void PesquisarCliente(ClienteInformacao c){
		 * 
		 * public List<ClienteInformacao> buscarNomeCli() {
		 * List<ClienteInformacao> listaPesquisa = new
		 * ArrayList<ClienteInformacao>();
		 * 
		 * PreparedStatement statement = null; Connection conexao = null;
		 * ResultSet result = null; ClienteInformacao cliente = null;
		 * 
		 * 
		 * try{ 
		 * String //BUSCAR_NOME_CLI = "select * from Cliente where upper(Nome_Cli) like UPPER (?)";{
		 *        BUSCAR_NOME_CLI = "SELECT * FROM Cliente where Nome_Cli like '%?%'";
		 * 
		 * 

		 /*conexao = ConnectionFactory.getConnection(); statement =
		  conexao.prepareStatement(BUSCAR_NOME_CLI); Nome_cli = "%" +
		  c.setNome_cli + "%"; statement.setString(1, c.getNome_cli());
		  System.out.println(BUSCAR_CAMPOS_CLI);*/
		/*  
		 * result = statement.executeQuery(); ClienteInformacao c = new
		 * ClienteInformacao(); while (result.next()) {
		 * c.setID_cli(result.getInt("ID_cli"));
		 * c.setNome_cli(result.getString("Nome_cli"));
		 * c.setEmail_cli(result.getString("Email_cli"));
		 * c.setTelefone_cli(result.getString("Tel_cli"));
		 * c.setCpf_cli(result.getString("Cpf_cli"));
		 * c.setRG_Funci(result.getString("RG_cli"));
		 * listaPesquisa.add(cliente); }
		 * 
		 * }catch (SQLException e11) { throw new RuntimeException(e11); }
		 * finally { try { ConnectionFactory.close(null, statement, conexao); }
		 * catch (Exception e) { throw new RuntimeException(e); } }
		 * 
		 * return listaPesquisa;
		 */

	}

	/*public static ArrayList<ClienteInformacao> ListarFuncionarioNome(String sql) {

		ArrayList<Funcionario> listaCampos = new ArrayList<Funcionario>();

		try {

		String BUSCAR_NOME_CLI = "SELECT * FROM Cliente where Nome_Cli like '%?%'";

			Connection conn = Conexao.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Funcionario f = new Funcionario();

				f.setBairro_func(result.getString("bairro_func"));
				f.setCep_func(result.getString("cep_func"));
				f.setCidade_func(result.getString("cidade_func"));
				f.setCod_func(result.getInt("cod_func"));
				f.setCpf_func(result.getString("cpf_func"));
				f.setEndereco_func(result.getString("endereco_func"));
				f.setEstado_func(result.getString("estado_func"));
				f.setRg_func(result.getString("rg_func"));
				f.setNome_func(result.getString("nome_func"));
				f.setNum_casa_func(result.getString("num_casa_func"));
				f.setSexo_func(result.getString("sexo_func"));
				f.setTelefone_func(result.getString("telefone_func"));
				f.setUsuario(result.getString("usuario"));
				f.setSenha(result.getString("senha"));
				f.setCargo(result.getInt("func_cargo"));
				f.setDesc_cargo(result.getString("descricao_cargo"));
				f.setData_admissao(result.getDate("data_admiss"));
				f.setData_resicao(result.getDate("data_resicao"));
				f.setAtivo_inativo(result.getString("ativo_inativo"));

				listaCampos.add(f);

			}

			conn.close();
			statement = null;
			result = null;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCampos;
	}*/
	//the following method is duplicated, with boolean instead
	public static boolean LogCliPedido(ClienteBean c){


		boolean loginStatus = false;



		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(Return_Cli);

			statement.setInt(1, c.getId_cli());

			ResultSet result = statement.executeQuery();

			while(result.next()){


				c.setId_cli(result.getInt("ID_cli"));
				c.setNome_cli(result.getString("Nome_cli"));
				c.setEmail_cli(result.getString("Email_cli"));
				c.setTelefone_cli(result.getString("Tel_cli"));
				c.setCpf_cli(result.getString("CPF_cli"));
				c.setSexo_cli(result.getString("Sexo_cli"));
				c.setRg_cli(result.getString("RG_cli"));
				c.setCep_cli(result.getString("Cep_cli"));
				c.setDataNasc_cli(result.getDate("Data_Nasc_cli"));
				c.setLogradouro_cli(result.getString("logradouro_cli"));
				c.setNumero_cli(result.getString("Numero_cli"));
				c.setComplemento_cli(result.getString("Complemento_cli"));
				c.setBairro_cli(result.getString("Bairro_cli"));
				c.setCidade_cli(result.getString("Cidade_cli"));
				c.setEstado_cli(result.getString("Estado_cli"));
				c.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status_Cli"));


			}
			if(c.getId_cli() != 0){
				loginStatus = true;	
			}else{
				JOptionPane.showMessageDialog(null,"Cliente não inserido", "Erro", JOptionPane.ERROR_MESSAGE);
				loginStatus = false;
			}

			conn.close();
			statement = null;

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorretos", "Erro",JOptionPane.ERROR_MESSAGE);

			loginStatus = false;
		}

		return loginStatus;
	}


	public static ClienteBean retornarCliente(ClienteBean c){

		//String sql = "Select * from Cliente where id_cli = " + c.getId_cli();

		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(Return_Cli);
			statement.setInt(1, c.getId_cli());
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				//java.sql.Date dataNascCliSql = new java.sql.Date(.getDate().getTime()); 
				//java.sql.Date dataNascCadastroSql = new java.sql.Date(data_Nasc_Cli.getDate().getTime());

				c.setId_cli(result.getInt("ID_cli"));
				c.setNome_cli(result.getString("Nome_cli"));
				c.setEmail_cli(result.getString("Email_cli"));
				c.setTelefone_cli(result.getString("Tel_cli"));
				c.setCpf_cli(result.getString("CPF_cli"));
				c.setSexo_cli(result.getString("Sexo_cli"));
				c.setRg_cli(result.getString("RG_cli"));
				c.setCep_cli(result.getString("Cep_cli"));
				c.setDataNasc_cli(result.getDate("Data_Nasc_cli"));
				c.setLogradouro_cli(result.getString("logradouro_cli"));
				c.setNumero_cli(result.getString("Numero_cli"));
				c.setComplemento_cli(result.getString("Complemento_cli"));
				c.setBairro_cli(result.getString("Bairro_cli"));
				c.setCidade_cli(result.getString("Cidade_cli"));
				c.setEstado_cli(result.getString("Estado_cli"));
				
				c.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status_Cli"));
				c.setCadastradoPor(result.getString("nome_func"));
				c.setDataCadastro_cli(result.getString("data_Cadastro_cli"));
				c.setDataRecisaoCli(result.getString("Data_Resicao_Cli"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return c;

	}

	public static void LoadClienteTable(JTable LoadCadastroCli){

		String[] Columns = new String[]{"Cód.","Nome","Telefone","CPF","Sexo","CEP","Status","Data de Cadastro"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String FillClienteTable = "select * from Func_Cli where Id_cli >1";//View

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(FillClienteTable);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_cli"), result.getString("Nome_cli"),result.getString("Tel_cli")
						,result.getString("CPF_cli"), result.getString("Sexo_cli"), result.getString("CEP_cli")
						,result.getString("Ativo_Inativo_Status_Cli"),result.getString("Data_Cadastro_cli")});

				//DateFormat.getInstance().format(result.getString("data_Cadastro_cli"))});

			}


		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Columns);
		LoadCadastroCli.setModel(modelo);
	}

	public static void LoadAtivoClienteTable(JTable LoadCadastroCli){

		String[] Columns = new String[]{"Cód.","Nome","Telefone","CPF","Sexo","CEP","Status","Data de Cadastro"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String FillClienteTable = "select * from Func_Cli where Ativo_Inativo_Status_Cli = 'Ativo' and Id_Cli >1";//View

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(FillClienteTable);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_cli"), result.getString("Nome_cli"),
						result.getString("Tel_cli"), result.getString("CPF_cli"), result.getString("Sexo_cli"), result.getString("CEP_cli")
						,result.getString("Ativo_Inativo_Status_Cli"),result.getString("Data_Cadastro_cli")});

				//DateFormat.getInstance().format(result.getString("data_Cadastro_cli"))});

			}


		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Columns);
		LoadCadastroCli.setModel(modelo);
	}

	public static void LoadInativoClienteTable(JTable LoadCadastroCli){

		String[] Columns = new String[]{"Cód.","Nome","Telefone","CPF","Sexo","CEP","Status","Data de Cadastro"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String FillClienteTable = "select * from Func_Cli where Ativo_Inativo_Status_Cli = 'Inativo' and Id_Cli >1";//View

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(FillClienteTable);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_cli"), result.getString("Nome_cli")
						,result.getString("Tel_cli"), result.getString("CPF_cli"), result.getString("Sexo_cli"), result.getString("CEP_cli")
						,result.getString("Ativo_Inativo_Status_Cli"),result.getString("Data_Cadastro_cli")});

				//DateFormat.getInstance().format(result.getString("data_Cadastro_cli"))});

			}


		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Columns);
		LoadCadastroCli.setModel(modelo);
	}

	public static void desativarCliente(ClienteBean c){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Deactivate_Cli);
			statement.setInt(1, c.getId_cli());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Cliente(a) desativado(a) com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Cliente(a) não foi desativado(a) com sucesso!", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e1);
		} finally {
			try {
				ConnectionFactory.close(null, statement, conexao);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}	


	public static void AtivarCliente(ClienteBean c){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Activate_Cli);
			statement.setInt(1, c.getId_cli());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Cliente(a) ativado(a) com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Cliente(a) não foi ativado(a) com sucesso!", "Erro!",
					JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException(e1);
		} finally {
			try {
				ConnectionFactory.close(null, statement, conexao);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}



	//	public static ClienteBean PesquisarClienteNome(ClienteBean c){
	//
	//		String //"Select * from Cliente where id_cli = " + c.getId_cli();
	//		BUSCAR_NOME_CLI = "SELECT * FROM Cliente where Nome_Cli like '%"+c.getNome_cli()+"%'";
	//
	//		try {
	//
	//			Connection conn = ConnectionFactory.getConnection();
	//			PreparedStatement statement = conn.prepareStatement(BUSCAR_NOME_CLI);
	//			ResultSet result = statement.executeQuery();
	//
	//			while (result.next()) {
	//
	//				//java.sql.Date dataNascCliSql = new java.sql.Date(.getDate().getTime()); 
	//				//java.sql.Date dataNascCadastroSql = new java.sql.Date(data_Nasc_Cli.getDate().getTime());
	//
	//				c.setId_cli(result.getInt("ID_cli"));
	//				c.setNome_cli(result.getString("Nome_cli"));
	//				c.setEmail_cli(result.getString("Email_cli"));
	//				c.setTelefone_cli(result.getString("Tel_cli"));
	//				c.setCpf_cli(result.getString("CPF_cli"));
	//				c.setSexo_cli(result.getString("Sexo_cli"));
	//				c.setRg_cli(result.getString("RG_cli"));
	//				c.setCep_cli(result.getString("Cep_cli"));
	//				c.setDataNasc_cli(result.getDate("Data_Nasc_cli"));
	//				c.setLogradouro_cli(result.getString("logradouro_cli"));
	//				c.setNumero_cli(result.getString("Numero_cli"));
	//				c.setComplemento_cli(result.getString("Complemento_cli"));
	//				c.setBairro_cli(result.getString("Bairro_cli"));
	//				c.setCidade_cli(result.getString("Cidade_cli"));
	//				c.setEstado_cli(result.getString("Estado_cli"));
	//				//c.setDataCadastro_cli(dataCadastroSql);
	//
	//			}
	//
	//		} catch (SQLException ex) {
	//			ex.printStackTrace();
	//		}
	//
	//		return c;
	//
	//	}

	public static ArrayList<ClienteBean> ListarCliente(ClienteBean c, String columnName, String getterName) {

		ArrayList<ClienteBean> FieldsList = new ArrayList<ClienteBean>();
		String searchFunc = "SELECT        dbo.Pedido_VendaRegistros.*, dbo.Funcionario.*, dbo.Cliente.* "+
		"FROM            dbo.Cliente INNER JOIN "+
		"                        dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN "+
		"                       dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda "+
		"where "+columnName+" like '%"+getterName+"%' and iD_cli > 1";

		try {

			Connection conn = ConnectionFactory.getConnection();

			PreparedStatement statement = conn.prepareStatement(searchFunc);
			System.out.println(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				c.setId_cli(result.getInt("ID_cli"));
				c.setNome_cli(result.getString("Nome_cli"));
				c.setEmail_cli(result.getString("Email_cli"));
				c.setTelefone_cli(result.getString("Tel_cli"));
				c.setCpf_cli(result.getString("CPF_cli"));
				c.setSexo_cli(result.getString("Sexo_cli"));
				c.setRg_cli(result.getString("RG_cli"));
				c.setCep_cli(result.getString("Cep_cli"));
				c.setDataNasc_cli(result.getDate("Data_Nasc_cli"));
				c.setLogradouro_cli(result.getString("logradouro_cli"));
				c.setNumero_cli(result.getString("Numero_cli"));
				c.setComplemento_cli(result.getString("Complemento_cli"));
				c.setBairro_cli(result.getString("Bairro_cli"));
				c.setCidade_cli(result.getString("Cidade_cli"));
				c.setEstado_cli(result.getString("Estado_cli"));
				c.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status_Cli"));
				c.setDataCadastro_cli(result.getString("Data_Cadastro_cli"));

				FieldsList.add(c);

			}

			conn.close();
			statement = null;
			result = null;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return FieldsList;
	}

	public static ArrayList<ClienteBean> ListarClienteAtivoInativo(ClienteBean c, String columnName, String getterName) {

		ArrayList<ClienteBean> FieldsList = new ArrayList<ClienteBean>();
		String searchFunc = "SELECT        dbo.Pedido_VendaRegistros.*, dbo.Funcionario.*, dbo.Cliente.* "+
		"FROM            dbo.Cliente INNER JOIN "+
		" dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN "+
		" dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda "+
		"where "+columnName+" like '%"+getterName+"%' and iD_cli > 1 and Ativo_Inativo_Status_cli = '"+c.getAtivo_inativo_status()+"'";

		try {

			Connection conn = ConnectionFactory.getConnection();

			PreparedStatement statement = conn.prepareStatement(searchFunc);
			System.out.println(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {


				c.setId_cli(result.getInt("ID_cli"));
				c.setNome_cli(result.getString("Nome_cli"));
				c.setEmail_cli(result.getString("Email_cli"));
				c.setTelefone_cli(result.getString("Tel_cli"));
				c.setCpf_cli(result.getString("CPF_cli"));
				c.setSexo_cli(result.getString("Sexo_cli"));
				c.setRg_cli(result.getString("RG_cli"));
				c.setCep_cli(result.getString("Cep_cli"));
				c.setDataNasc_cli(result.getDate("Data_Nasc_cli"));
				c.setLogradouro_cli(result.getString("logradouro_cli"));
				c.setNumero_cli(result.getString("Numero_cli"));
				c.setComplemento_cli(result.getString("Complemento_cli"));
				c.setBairro_cli(result.getString("Bairro_cli"));
				c.setCidade_cli(result.getString("Cidade_cli"));
				c.setEstado_cli(result.getString("Estado_cli"));
				c.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status_Cli"));
				c.setDataCadastro_cli(result.getString("Data_Cadastro_cli"));

				FieldsList.add(c);

			}

			conn.close();
			statement = null;
			result = null;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return FieldsList;
	}


	//	public static void FillUpCliTableSearch(JTable table,ClienteBean c) {
	//
	//		
	//		
	//		if (c.getAtivo_inativo_status().equals("Inativo")) {
	//
	//			String[] Colunas = new String[] { "N° Funcionário", "Nome", "Sexo",
	//					"Cidade", "Bairro", "CEP", "Endereço", "Numero", "Estado",
	//					"RG", "CPF", "Telefone", "Usuario", "Senha", "Cargo",
	//					"Data Admissão", "Data Resição", "Status" };
	//			ArrayList<ClienteBean> DadosCli = new ArrayList();
	//			ArrayList Dados = new ArrayList<Object>();
	//
	//			String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
	//					+ c.getNome_func()
	//					+ "%' and ativo_inativo = '"
	//					+ c.getAtivo_inativo() + "'";
	//			// System.out.println(sql);
	//
	//			DadosCli = FuncionarioDAO.ListarFuncionarioNome(sql);
	//
	//			for (Funcionario f : DadosCli) {
	//
	//				Dados.add(new Object[] { f.getCod_func(), f.getNome_func(),
	//						f.getSexo_func(), f.getCidade_func(),
	//						f.getBairro_func(), f.getCep_func(),
	//						f.getEndereco_func(), f.getNum_casa_func(),
	//						f.getEstado_func(), f.getRg_func(), f.getCpf_func(),
	//						f.getTelefone_func(), f.getUsuario(), f.getSenha(),
	//						f.getDesc_cargo(),
	//						DateFormat.getInstance().format(f.getData_admissao()),
	//						DateFormat.getInstance().format(f.getData_resicao()),
	//						f.getAtivo_inativo() });
	//
	//			}
	//
	//			TableModel modelo = new TableModel(Dados, Colunas);
	//			table.setModel(modelo);
	//
	//		} else {
	//			String[] Colunas = new String[] { "N° Funcionário", "Nome", "Sexo",
	//					"Cidade", "Bairro", "CEP", "Endereço", "Numero", "Estado",
	//					"RG", "CPF", "Telefone", "Usuario", "Senha", "Cargo",
	//					"Data Admissão", "Status" };
	//			ArrayList<Funcionario> DadosFunc = new ArrayList();
	//			ArrayList Dados = new ArrayList<Object>();
	//
	//			String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
	//					+ c.getNome_func()
	//					+ "%' and ativo_inativo = '"
	//					+ c.getAtivo_inativo() + "'";
	//			// System.out.println(sql);
	//
	//			DadosFunc = FuncionarioDAO.ListarFuncionarioNome(sql);
	//
	//			for (Funcionario f : DadosFunc) {
	//
	//				Dados.add(new Object[] { f.getCod_func(), f.getNome_func(),
	//						f.getSexo_func(), f.getCidade_func(),
	//						f.getBairro_func(), f.getCep_func(),
	//						f.getEndereco_func(), f.getNum_casa_func(),
	//						f.getEstado_func(), f.getRg_func(), f.getCpf_func(),
	//						f.getTelefone_func(), f.getUsuario(), f.getSenha(),
	//						f.getDesc_cargo(),
	//						DateFormat.getInstance().format(f.getData_admissao()),
	//						f.getAtivo_inativo() });
	//
	//			}
	//
	//			TableModel modelo = new TableModel(Dados, Colunas);
	//			table.setModel(modelo);
	//
	//		}
	//
	//	}

	public static boolean LogBalcaoCliStandard(ClienteBean c){


		boolean loginStatus = false;



		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(LogBalcaoClienteAs_Standard);

			ResultSet result = statement.executeQuery();

			while(result.next()){


				c.setId_cli(result.getInt("ID_cli"));
				c.setNome_cli(result.getString("Nome_cli"));
				c.setEmail_cli(result.getString("Email_cli"));
				c.setTelefone_cli(result.getString("Tel_cli"));
				c.setCpf_cli(result.getString("CPF_cli"));
				c.setSexo_cli(result.getString("Sexo_cli"));
				c.setRg_cli(result.getString("RG_cli"));
				c.setCep_cli(result.getString("Cep_cli"));
				c.setDataNasc_cli(result.getDate("Data_Nasc_cli"));
				c.setLogradouro_cli(result.getString("logradouro_cli"));
				c.setNumero_cli(result.getString("Numero_cli"));
				c.setComplemento_cli(result.getString("Complemento_cli"));
				c.setBairro_cli(result.getString("Bairro_cli"));
				c.setCidade_cli(result.getString("Cidade_cli"));
				c.setEstado_cli(result.getString("Estado_cli"));


			}
			if(c.getId_cli() != 0){
				loginStatus = true;	
			}else{
				JOptionPane.showMessageDialog(null,"Cliente balcão não logado", "Erro", JOptionPane.ERROR_MESSAGE);
				loginStatus = false;
			}

			conn.close();
			statement = null;

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorretos", "Erro",JOptionPane.ERROR_MESSAGE);

			loginStatus = false;
		}

		return loginStatus;
	}

	public static void carregarVendasCliTable(JTable table, ClienteBean c){

		String[] Colunas = new String[] { "Cód. Venda", "Data", "Valor Total", "Funcionário"};
		ArrayList<Object[]> DadosVenda = new ArrayList<Object[]>();
		String sqlCommand = "select * from Venda_Func_Cli where id_Cli = ?";//View
		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			statement.setInt(1, c.getId_cli());
			ResultSet result = statement.executeQuery();

			while(result.next()){

				//c.setValor_Total(result.getFloat("Valor_Total"));
				c.setDataCadastro_cli(result.getString("Data_Cadastro_cli"));
				c.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status_cli"));
				c.setCadastradoPor(result.getString("Nome_func"));
				c.setDataRecisaoCli(result.getString("Data_Resicao_Cli"));


				DadosVenda.add(new Object[]{result.getString("ID_pedido_vendaRegistro"), result.getString("Data_Venda"),
						result.getString("Valor_Total"), result.getString("Nome_Func")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}
		TableModel model = new TableModel(DadosVenda, Colunas);

		table.setModel(model);

	}

	//	public static ArrayList<ClienteBean> ListarCliente(ClienteBean c){
	//
	//		ArrayList<ClienteBean> listaCampos = new ArrayList<ClienteBean>();
	//		String searchCli_Ativo_Inativo = //"SELECT dbo.Funcionario.*, dbo.Cargo.Nome_Cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.ID_Cargo = dbo.Funcionario.id_cargo_fk where "+f.getColumn_func()+" like '%"+f.getNome_func()+"%' and Ativo_Inativo_Status = '"+f.getAtivo_inativo_status()+"'";
	//			"SELECT dbo.Cliente.ID_cli, dbo.Cliente.Nome_cli, dbo.Cliente.Email_cli, dbo.Cliente.Tel_cli, dbo.Cliente.CPF_cli, dbo.Cliente.Sexo_cli, dbo.Cliente.CEP_cli, dbo.Funcionario.ID_func, dbo.Funcionario.Nome_func, " +
	//			"dbo.Cliente.Data_Cadastro_cli FROM dbo.Cliente INNER JOIN dbo.Funcionario ON dbo.Cliente.Id_func_fk_cli = dbo.Funcionario.ID_func where "+c.getColumn_cli()+" like '%"+c.getTextPesq_cli()+"%' and Ativo_Inativo_Status = '"+c.getAtivo_inativo_status()+"'";
	//
	//		//String searchFuncName = "SELECT dbo.Funcionario.*, dbo.Cargo.Nome_Cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.ID_Cargo = dbo.Funcionario.id_cargo_fk where nome_func like '%"+f.getNome_func()+"%'";
	//
	//		System.out.println(searchCli_Ativo_Inativo);
	//
	//		Connection conn = ConnectionFactory.getConnection();
	//		PreparedStatement statement;
	//
	//
	//
	//
	//		try {
	//			statement = conn.prepareStatement(searchCli_Ativo_Inativo);
	//			ResultSet result = statement.executeQuery();
	//
	//			while (result.next()) {
	//
	//				c.setId_cli(result.getInt("ID_cli"));
	//				c.setNome_cli(result.getString("Nome_cli"));
	//				c.setEmail_cli(result.getString("Email_cli"));
	//				c.setTelefone_cli(result.getString("Tel_cli"));
	//				c.setCpf_cli(result.getString("CPF_cli"));
	//				c.setSexo_cli(result.getString("Sexo_cli"));
	//				c.setCep_cli(result.getString("Cep_cli"));
	//				//c.setFunc().setNome
	//				c.setDataCadastro_cli(result.getString("Data_Cadastro_cli"));
	//
	//
	//
	//				listaCampos.add(c);
	//
	//			}
	//
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//		return listaCampos;
	//	}

	public static void preencherTableClientePesquisa(JTable table, ClienteBean c, String columnName, String getterName) {


		String[] Columns = new String[]{"Cód.","Nome","Telefone","CPF","Sexo","CEP","Status","Data de Cadastro"};
		ArrayList<ClienteBean> DadosCli = new ArrayList<ClienteBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		DadosCli = ClienteDAO.ListarCliente(c, columnName, getterName);

		for (ClienteBean c2 : DadosCli) {

			Dados.add(new Object[] { c2.getId_cli(), c2.getNome_cli(),c2.getTelefone_cli(),
					c2.getCpf_cli(), c2.getSexo_cli(), c2.getCep_cli(),
					c2.getAtivo_inativo_status(), c2.getDataCadastro_cli()});

		}

		TableModel modelo = new TableModel(Dados, Columns);
		table.setModel(modelo);

	} 

	public static void preencherTableClientePesquisaAtivoInativo(JTable table, ClienteBean c, String columnName, String getterName) {


		String[] Columns = new String[]{"Cód.","Nome","Telefone","CPF","Sexo","CEP","Status","Data de Cadastro"};
		ArrayList<ClienteBean> DadosCli = new ArrayList<ClienteBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		DadosCli = ClienteDAO.ListarClienteAtivoInativo(c, columnName, getterName);

		for (ClienteBean c2 : DadosCli) {

			Dados.add(new Object[] { c2.getId_cli(), c2.getNome_cli(),c2.getTelefone_cli(),
					c2.getCpf_cli(), c2.getSexo_cli(), c2.getCep_cli(),
					c2.getAtivo_inativo_status(), c2.getDataCadastro_cli()});

		}

		TableModel modelo = new TableModel(Dados, Columns);
		table.setModel(modelo);

	} 

}

