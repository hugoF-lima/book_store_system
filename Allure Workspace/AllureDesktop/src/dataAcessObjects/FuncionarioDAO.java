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
import acessoDadosBeans.CargoBean;
import acessoDadosBeans.FuncionarioBean;

public class FuncionarioDAO {

	private static final String Insert_Funcionario = "Insert into Funcionario Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Ativo', ?, getdate(), null, 'Offline', ?)";

	private static final String verifyIfCPFExists = "Select * from Funcionario where CPF_func like ?";

	private static final String Login_Funcionario = "Select * from Func_Cargo where Nome_Func = ? and senha_Func = ?";

	private static final String Update_Funcionario = "update Funcionario set Nome_Func = ?, Email_Func = ?, Tel_Func = ?, CPF_Func = ?, Sexo_Func = ?, RG_Func = ?, Data_Nasc_Func = ?, CEP_Func = ?, Logradouro_Func = ?, " +
	"Numero_Func = ?, Complemento_Func = ?, Bairro_Func = ?, Cidade_Func = ?, Estado_Func = ?, Senha_func = ?, foto_func = ?, Id_Cargo_fk = ? Where ID_Func = ?";


	private static final String Activate_Func = "update Funcionario set Data_Recisao_func = null, Ativo_Inativo_Status = 'Ativo' where ID_func = ?";

	private static final String Return_Func = "Select * from Funcionario where id_func = ?";


	public static void cadastrarFuncionario(FuncionarioBean f) {

		PreparedStatement statement = null;
		Connection conexao = null;
		byte[] byteArray = null; 

		try {
			System.out.println(Insert_Funcionario);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Insert_Funcionario);

			statement.setString(1, f.getNome_func());
			statement.setString(2, f.getEmail_func());
			statement.setString(3, f.getTelefone_func());
			statement.setString(4, f.getCpf_func());
			statement.setString(5, f.getSexo_func());
			statement.setString(6, f.getRg_func());
			statement.setDate(7, (Date) f.getDataNasc_func());
			statement.setString(8, f.getCep_func());
			statement.setString(9, f.getLogradouro_func());
			statement.setString(10, f.getNumero_func());
			statement.setString(11, f.getComplemento_func());
			statement.setString(12, f.getBairro_func());
			statement.setString(13, f.getCidade_func());
			statement.setString(14, f.getEstado_func());
			statement.setString(15, f.getSenha_func());
			byteArray = f.getFoto();
			statement.setBytes(16, byteArray);
			statement.setInt(17, f.getId_cargo_fk());
			//statement.setInt(18, f.getFunc().getId_func());
			statement.execute();

			JOptionPane.showMessageDialog(null,"Funcionario(a) cadastrado(a) com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

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

	public static FuncionarioBean verificarCpf(String cpf) {

		PreparedStatement statement = null;
		Connection conexao = null;
		FuncionarioBean func = new FuncionarioBean();
		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(verifyIfCPFExists);
			statement.setString(1, cpf);

			ResultSet result = statement.executeQuery();

			if(result.next()){

				func.setCpf_func(result.getString("CPF_func"));

			}

			conexao.close();
			statement = null;

		} catch (SQLException e1) {
			//return false;
		}
		return func;

	}




	public static void AlterarFuncionario(FuncionarioBean f) {

		PreparedStatement statement = null;
		Connection conexao = null;
		byte[] byteArray = null; 

		try {

			System.out.println(Update_Funcionario);
			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Update_Funcionario);
			statement.setString(1, f.getNome_func());
			statement.setString(2, f.getEmail_func());
			statement.setString(3, f.getTelefone_func());
			statement.setString(4, f.getCpf_func());
			statement.setString(5, f.getSexo_func());
			statement.setString(6, f.getRg_func());
			statement.setDate(7, (Date) f.getDataNasc_func());
			statement.setString(8, f.getCep_func());
			statement.setString(9, f.getLogradouro_func());
			statement.setString(10, f.getNumero_func());
			statement.setString(11, f.getComplemento_func());
			statement.setString(12, f.getBairro_func());
			statement.setString(13, f.getCidade_func());
			statement.setString(14, f.getEstado_func());
			statement.setString(15, f.getSenha_func());
			byteArray = f.getFoto();
			statement.setBytes(16, byteArray);
			statement.setInt(17, f.getId_cargo_fk());
			statement.setInt(18, f.getId_func());
			statement.execute();

			JOptionPane.showMessageDialog(null, "Funcionario(a) alterado(a) com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
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
	}



	public static FuncionarioBean retornarFuncionario(FuncionarioBean f){

		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(Return_Func);
			statement.setInt(1, f.getId_func());
			ResultSet result = statement.executeQuery();

			while (result.next()) {


				f.setId_func(result.getInt("ID_func"));
				f.setNome_func(result.getString("Nome_func"));
				f.setEmail_func(result.getString("Email_func"));
				f.setTelefone_func(result.getString("Tel_func"));
				f.setCpf_func(result.getString("CPF_func"));
				f.setSexo_func(result.getString("Sexo_func"));
				f.setRg_func(result.getString("RG_func"));
				f.setDataNasc_func(result.getDate("Data_Nasc_func"));
				f.setCep_func(result.getString("Cep_func"));
				f.setLogradouro_func(result.getString("logradouro_func"));
				f.setNumero_func(result.getString("Numero_func"));
				f.setComplemento_func(result.getString("Complemento_func"));
				f.setBairro_func(result.getString("Bairro_func"));
				f.setCidade_func(result.getString("Cidade_func"));
				f.setEstado_func(result.getString("Estado_func"));
				f.setSenha_func(result.getString("Senha_func"));
				f.setFoto(result.getBytes("foto_func"));
				f.setId_cargo_fk(result.getInt("Id_Cargo_fk"));
				
				f.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status"));
				f.setDataAdmissao_func(result.getString("Data_Admissao_func"));
				f.setDataRecisao_func(result.getDate("Data_Recisao_func"));


			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return f;

	}

	public static void LoadFuncionarioTable(JTable LoadCadastroFunc){

		String[] Colunas = new String[]{"ID","Nome","Telefone","CPF", "Sexo", "Status", "Cargo", "Data de Admissão"};
		ArrayList<Object> Dados = new ArrayList<Object>();
		String sqlCommand = "Select ID_Func, Nome_func, Tel_func, CPF_func, Sexo_func, Ativo_Inativo_Status, Nome_Cargo, Data_Admissao_func from Func_Cargo";
		//"SELECT dbo.Funcionario.*, dbo.Cargo.Nome_Cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.ID_Cargo = dbo.Funcionario.id_cargo_fk";


		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			//PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			//ResultSet result2 = statement2.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_func"), result.getString("Nome_func"),
						result.getString("TEL_func"), result.getString("CPF_func"),
						result.getString("Sexo_func"), result.getString("Ativo_Inativo_Status"), result.getString("nome_Cargo"), result.getString("Data_Admissao_func")});

			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCadastroFunc.setModel(modelo);


	}

	public static void LoadAtivoFuncionarioTable(JTable LoadCadastroFunc){

		String[] Colunas = new String[]{"ID","Nome","Telefone","CPF","Sexo", "Status", "Cargo", "Data de Admissão"};
		ArrayList<Object> Dados = new ArrayList<Object>();
		String sqlCommand = "Select ID_Func, Nome_func, Tel_func, CPF_func, Sexo_func, Ativo_Inativo_Status, Nome_Cargo, Data_Admissao_func from Func_Cargo where ativo_inativo_status = 'Ativo'";
		//"Select ID_func, Nome_func, Tel_func, Email_func, CPF_func, Sexo_func, RG_func, Id_Cargo_fk, Data_Admissao_func from Funcionario";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			//PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			//ResultSet result2 = statement2.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_func"), result.getString("Nome_func"),
						result.getString("TEL_func"), result.getString("CPF_func"),
						result.getString("Sexo_func"), result.getString("Ativo_Inativo_Status"), result.getString("nome_Cargo"), result.getString("Data_Admissao_func")});

			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCadastroFunc.setModel(modelo);


	}

	public static void LoadInativoFuncionarioTable(JTable LoadCadastroFunc){

		String[] Colunas = new String[]{"ID","Nome","Telefone","CPF","Sexo", "Status", "Cargo", "Data de Admissão"};
		ArrayList<Object> Dados = new ArrayList<Object>();
		String sqlCommand = "Select ID_Func, Nome_func, Tel_func, CPF_func, Sexo_func, Ativo_Inativo_Status, Nome_Cargo, Data_Admissao_func from Func_Cargo where ativo_inativo_status = 'Inativo'";
		//"Select ID_func, Nome_func, Tel_func, Email_func, CPF_func, Sexo_func, RG_func, Id_Cargo_fk, Data_Admissao_func from Funcionario";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			//PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			//ResultSet result2 = statement2.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_func"), result.getString("Nome_func"),
						result.getString("TEL_func"), result.getString("CPF_func"),
						result.getString("Sexo_func"), result.getString("Ativo_Inativo_Status"), result.getString("nome_Cargo"), result.getString("Data_Admissao_func")});

			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCadastroFunc.setModel(modelo);


	}
	
	public static void LoadInativoVacationFuncionarioTable(JTable LoadCadastroFunc){

		String[] Colunas = new String[]{"ID","Nome","Telefone","CPF","Sexo", "Status", "Cargo", "Data de Admissão"};
		ArrayList<Object> Dados = new ArrayList<Object>();
		String sqlCommand = "Select ID_Func, Nome_func, Tel_func, CPF_func, Sexo_func, Ativo_Inativo_Status, Nome_Cargo, Data_Admissao_func from Func_Cargo where ativo_inativo_status = 'em Recesso'";
		//"Select ID_func, Nome_func, Tel_func, Email_func, CPF_func, Sexo_func, RG_func, Id_Cargo_fk, Data_Admissao_func from Funcionario";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			//PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			//ResultSet result2 = statement2.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_func"), result.getString("Nome_func"),
						result.getString("TEL_func"), result.getString("CPF_func"),
						result.getString("Sexo_func"), result.getString("Ativo_Inativo_Status"), result.getString("nome_Cargo"), result.getString("Data_Admissao_func")});

			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCadastroFunc.setModel(modelo);


	}

	public static void desativarFuncionarioVacation(FuncionarioBean f){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			String Deactivate_Func = "update Funcionario set Data_Recisao_func = getdate(), Ativo_Inativo_Status = 'em Recesso' where ID_func = ?";

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Deactivate_Func);
			statement.setInt(1, f.getId_func());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Recessão concebida com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Recessão não foi concebida com sucesso!", "Erro!",
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


	public static void desativarFuncionario(FuncionarioBean f){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			String Deactivate_Func = "update Funcionario set Data_Recisao_func = getdate(), Ativo_Inativo_Status = 'Inativo' where ID_func = ?";

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Deactivate_Func);
			statement.setInt(1, f.getId_func());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Funcionario(a) desativado(a) com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Funcionario(a) não foi desativado(a) com sucesso!", "Erro!",
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


	public static void AtivarFuncionario(FuncionarioBean f){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Activate_Func);
			statement.setInt(1, f.getId_func());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Funcionario(a) ativado(a) com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Funcionario(a) não foi ativado(a) com sucesso!", "Erro!",
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
	
	public static ArrayList<FuncionarioBean> ListarFuncionario(FuncionarioBean f, String columnName, String getterName){

		ArrayList<FuncionarioBean> listaCampos = new ArrayList<FuncionarioBean>();
		String searchFunc = "SELECT dbo.Funcionario.*, dbo.Cargo.Nome_Cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.ID_Cargo = dbo.Funcionario.id_cargo_fk where "+columnName+" like '%"+getterName+"%'";

		System.out.println(searchFunc);

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement;


		try {
			statement = conn.prepareStatement(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				
				f.setId_func(result.getInt("ID_func"));
				f.setNome_func(result.getString("Nome_func"));
				f.setTelefone_func(result.getString("Tel_func"));
				f.setCpf_func(result.getString("CPF_func"));
				f.setSexo_func(result.getString("Sexo_func"));
				/*f.setRg_func(result.getString("RG_func"));
				f.setCep_func(result.getString("Cep_func"));
				f.setDataNasc_func(result.getDate("Data_Nasc_func"));
				f.setLogradouro_func(result.getString("logradouro_func"));
				f.setNumero_func(result.getString("Numero_func"));
				f.setComplemento_func(result.getString("Complemento_func"));
				f.setBairro_func(result.getString("Bairro_func"));
				f.setCidade_func(result.getString("Cidade_func"));
				f.setEstado_func(result.getString("Estado_func"));
				f.setSenha_func(result.getString("Senha_func"));
				f.setFoto(result.getBytes("foto_func"));*/
				f.setDataAdmissao_func(result.getString("Data_admissao_func"));
				f.setAtivo_inativo_status(result.getString("ativo_inativo_status"));
				f.setNome_cargo(result.getString("Nome_Cargo"));

				
				

				listaCampos.add(f);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaCampos;
	}

	public static ArrayList<FuncionarioBean> ListarFuncionarioAtivoInativo(FuncionarioBean f, String columnName, String getterName){

		ArrayList<FuncionarioBean> listaCampos = new ArrayList<FuncionarioBean>();
		String searchFunc = "SELECT dbo.Funcionario.*, dbo.Cargo.Nome_Cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.ID_Cargo = dbo.Funcionario.id_cargo_fk where "+columnName+" like '%"+getterName+"%' and Ativo_Inativo_Status = '"+f.getAtivo_inativo_status()+"'";

		System.out.println(searchFunc);

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement;


		try {
			statement = conn.prepareStatement(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				
				f.setId_func(result.getInt("ID_func"));
				f.setNome_func(result.getString("Nome_func"));
				f.setTelefone_func(result.getString("Tel_func"));
				f.setCpf_func(result.getString("CPF_func"));
				f.setSexo_func(result.getString("Sexo_func"));
				/*f.setRg_func(result.getString("RG_func"));
				f.setCep_func(result.getString("Cep_func"));
				f.setDataNasc_func(result.getDate("Data_Nasc_func"));
				f.setLogradouro_func(result.getString("logradouro_func"));
				f.setNumero_func(result.getString("Numero_func"));
				f.setComplemento_func(result.getString("Complemento_func"));
				f.setBairro_func(result.getString("Bairro_func"));
				f.setCidade_func(result.getString("Cidade_func"));
				f.setEstado_func(result.getString("Estado_func"));
				f.setSenha_func(result.getString("Senha_func"));
				f.setFoto(result.getBytes("foto_func"));*/
				f.setDataAdmissao_func(result.getString("Data_admissao_func"));
				f.setAtivo_inativo_status(result.getString("ativo_inativo_status"));
				f.setNome_cargo(result.getString("Nome_Cargo"));

				
				

				listaCampos.add(f);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listaCampos;
	}

	public static void preencherTableFuncionarioPesquisaAticoInativo(JTable table, FuncionarioBean f, String columnName, String getterName) {


		String[] Colunas = new String[] { "ID", "Nome", "Telefone", "CPF", "Sexo", "Status", "Cargo", "Data de Admissão" };
		ArrayList<FuncionarioBean> DadosFunc = new ArrayList<FuncionarioBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
					+ func.getNome_func()
					+ "%' and ativo_inativo = '"
					+ func.getAtivo_inativo() + "'";*/
		// System.out.println(sql);

		DadosFunc = FuncionarioDAO.ListarFuncionarioAtivoInativo(f, columnName, getterName);

		for (FuncionarioBean f2 : DadosFunc) {

			Dados.add(new Object[] { f2.getId_func(), f2.getNome_func(),f2.getTelefone_func(),
					f2.getCpf_func(), f2.getSexo_func(),f2.getAtivo_inativo_status(),
					f2.getNome_cargo(), f2.getDataAdmissao_func() });

		}

		TableModel modelo = new TableModel(Dados, Colunas);
		table.setModel(modelo);

	} 
	
	public static void preencherTableFuncionarioPesquisa(JTable table, FuncionarioBean f, String columnName, String getterName) {


		String[] Colunas = new String[] { "ID", "Nome", "Telefone", "CPF", "Sexo", "Status", "Cargo", "Data de Admissão" };
		ArrayList<FuncionarioBean> DadosFunc = new ArrayList<FuncionarioBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
					+ func.getNome_func()
					+ "%' and ativo_inativo = '"
					+ func.getAtivo_inativo() + "'";*/
		// System.out.println(sql);

		DadosFunc = FuncionarioDAO.ListarFuncionario(f, columnName, getterName);

		for (FuncionarioBean f2 : DadosFunc) {

			Dados.add(new Object[] { f2.getId_func(), f2.getNome_func(),f2.getTelefone_func(),
					f2.getCpf_func(), f2.getSexo_func(),f2.getAtivo_inativo_status(),
					f2.getNome_cargo(), f2.getDataAdmissao_func() });

		}

		TableModel modelo = new TableModel(Dados, Colunas);
		table.setModel(modelo);

	} 



	public static boolean LogIntoSystem(FuncionarioBean f, CargoBean cb){

		boolean loginStatus = false;



		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(Login_Funcionario);

			statement.setString(1, f.getNome_func());
			statement.setString(2, f.getSenha_func());

			ResultSet result = statement.executeQuery();

			while(result.next()){


				f.setId_func(result.getInt("Id_func"));
				f.setNome_func(result.getString("Nome_func"));
				f.setEmail_func(result.getString("Email_func"));
				f.setTelefone_func(result.getString("Tel_func"));
				f.setCpf_func(result.getString("CPF_func"));
				f.setSexo_func(result.getString("Sexo_func"));
				f.setRg_func(result.getString("RG_func"));
				f.setDataNasc_func(result.getDate("Data_Nasc_func"));
				f.setCep_func(result.getString("Cep_func"));
				f.setLogradouro_func(result.getString("logradouro_func"));
				f.setNumero_func(result.getString("Numero_func"));
				f.setComplemento_func(result.getString("Complemento_func"));
				f.setBairro_func(result.getString("Bairro_func"));
				f.setCidade_func(result.getString("Cidade_func"));
				f.setEstado_func(result.getString("Estado_func"));
				f.setId_cargo_fk(result.getInt("Id_Cargo_fk"));
				f.setSenha_func(result.getString("senha_func"));
				f.setFoto(result.getBytes("foto_func"));
				f.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status"));

				cb.setNomeCargo(result.getString("Nome_Cargo"));
				cb.setAcessoSistemaPermissao(result.getString("AcessoSistema_Permissao"));
				cb.setCadastroConsultaCliPermissao(result.getString("Cadastro_Consulta_Cli_Permissao"));
				cb.setCadastroConsultaFuncPermissao(result.getString("Cadastro_Consulta_Func_Permissao"));
				cb.setCadastroConsultaCargoPermissao(result.getString("Cadastro_Consulta_Cargo_Permissao"));
				cb.setCadastroConsultaFornecPermissao(result.getString("Cadastro_Consulta_Fornec_Permissao"));
				cb.setCadastroConsulta_Prod_Livro_Permissao(result.getString("Cadastro_Consulta_Prod_Livro_Permissao"));
				cb.setCadastroConsultaContatoClientePermissao(result.getString("Cadastro_Consulta_Contato_Cliente_Permissao"));
				cb.setVendasPermissao(result.getString("Vendas_Permissao"));
				
				//cb.setAtivo


			}
			if(f.getId_func() != 0){
				if(f.getAtivo_inativo_status().equals("Ativo") && cb.getAcessoSistemaPermissao().equals("Yes")){
					loginStatus = true;	
				}else{
					JOptionPane.showMessageDialog(null, "Você não tem permissão para fazer login no Sistema!");
					loginStatus = false;
				}
			}else{
				JOptionPane.showMessageDialog(null,"Usuário e/ou senha incorreto(s)", "Erro", JOptionPane.ERROR_MESSAGE);
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

	public static void carregarVendasFuncTable(JTable table, FuncionarioBean f){

		String[] Colunas = new String[] { "Cód. Venda", "Data", "Valor Total", "Cliente"};
		ArrayList<Object[]> DadosVenda = new ArrayList<Object[]>();
		String sqlCommand = "select * from Venda_Func_Cli where id_Func = ?";//View
		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			statement.setInt(1, f.getId_func());
			ResultSet result = statement.executeQuery();

			while(result.next()){

//				f.setDataAdmissao_func(result.getString("Data_Admissao_Func"));
//				f.setAtivo_inativo_status(result.getString("Ativo_Inativo_Status"));

				DadosVenda.add(new Object[]{result.getString("ID_pedido_vendaRegistro"), result.getString("Data_Venda"),
						result.getString("Valor_Total"), result.getString("Nome_Cli")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}
		TableModel model = new TableModel(DadosVenda, Colunas);

		table.setModel(model);

	}

}




