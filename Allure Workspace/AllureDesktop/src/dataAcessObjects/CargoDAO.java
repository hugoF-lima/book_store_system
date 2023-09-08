package dataAcessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import acessoDadosBeans.CargoBean;
import utilities.ConnectionFactory;
import utilities.TableModel;

public class CargoDAO {

	private static final String SELECT_CARGO = "select * from Cargo order by ID_Cargo";

	private static final String InsertCargo = "Insert into cargo values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Ativo', getdate(), null, ?)";

	private static final String Update_Cargo = "update Cargo set Nome_Cargo = ?, Salario_Cargo = ?, Descricao_Cargo = ?, AcessoSistema_Permissao = ?, Cadastro_Consulta_Cli_Permissao = ?, " +
	"Cadastro_Consulta_Func_Permissao = ?, Cadastro_Consulta_Cargo_Permissao = ?, Cadastro_Consulta_Fornec_Permissao = ?, Cadastro_Consulta_Prod_Livro_Permissao = ?, Cadastro_Consulta_Contato_Cliente_Permissao = ?, " +
	"Vendas_Permissao = ? Where ID_Cargo = ?";

	private static final String verifyIfNomeExists = "Select * from Cargo where Nome_Cargo like ?";

	private static final String Return_Cargo = "Select * from Cargo where Id_Cargo = ?";

	private static final String verifyIfCargoExists = "Select * from Cargo where Nome_Cargo = ?";

	private static final String verifyStatusCargo = "Select Ativo_Inativo_Status_Cargo from Cargo where Id_Cargo = ?";
	
	private static final String verifyAcessSystemCargo = "Select AcessoSistema_Permissao from Cargo where Id_Cargo = ?";
	
	private static final String Activate_Cargo = "update Cargo set Data_Resicao_Cargo = null, Ativo_Inativo_Status_Cargo = 'Ativo' where ID_Cargo = ?";
	
	private static final String Deactivate_Cargo = "update Cargo set Data_Resicao_Cargo = getdate(), Ativo_Inativo_Status_Cargo = 'Inativo' where ID_Cargo = ?";
	

	public List<CargoBean> buscarCargoAtivo() {
		List<CargoBean> lista = new ArrayList<CargoBean>();

		//lista.add(new CargoInformacao(1, "Gerente"));
		//lista.add(new CargoInformacao(2, "Recepcionista"));
		//		lista.add(new Cargo(2, "Coordenador"));
		//		lista.add(new Cargo(3, "Caixa"));
		//		lista.add(new Cargo(4, "Recepcionista"));
		//		lista.add(new Cargo(5, "Analista"));

		PreparedStatement statement = null;
		Connection conexao = null;
		ResultSet result = null;
		CargoBean cargo = null;
		try {
			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(SELECT_CARGO);

			result = statement.executeQuery();
			while (result.next()) {

				cargo = getBean(result);
				lista.add(cargo);
			}

		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			try {
				ConnectionFactory.close(null, statement, conexao);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return lista;
	}


	private CargoBean getBean(ResultSet result) throws SQLException {
		return new CargoBean(result.getInt("id_cargo"), result.getString("nome_cargo"));
	}

	public static String returnAtivoInativoById(int Id_Cargo) {


		Connection conexao = null;
		//CargoBean cb = new CargoBean();

		String Ativo_Inativo_Cargo = null;

		try {

			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(verifyStatusCargo);

			statement.setInt(1, Id_Cargo);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Ativo_Inativo_Cargo = result.getString(1);

			}

			conexao.close();
			statement = null;
			result = null;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Ativo_Inativo_Cargo;
	}
	
	public static String returnAcessSystemById(int Id_Cargo) {


		Connection conexao = null;
		//CargoBean cb = new CargoBean();

		String AcessoSistema = null;

		try {

			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(verifyAcessSystemCargo);

			statement.setInt(1, Id_Cargo);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				AcessoSistema = result.getString(1);

			}

			conexao.close();
			statement = null;
			result = null;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return AcessoSistema;
	}


	public static CargoBean verificarNome(String NomeCargo) {

		PreparedStatement statement = null;
		Connection conexao = null;
		CargoBean cb = new CargoBean();
		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(verifyIfNomeExists);
			statement.setString(1, NomeCargo);

			ResultSet result = statement.executeQuery();

			if(result.next()){

				cb.setNomeCargo(result.getString("Nome_Cargo"));

			}

			conexao.close();
			statement = null;

		} catch (SQLException e1) {
			//return false;
		}
		return cb;

	}


	public static void LoadCargoTable(JTable LoadCargo){

		String[] Colunas = new String[]{"ID","Cargo","Salario","Status","Data de admissão", "N° Funcionários"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "select * from FuncCargoNumber";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_cargo"), result.getString("Nome_cargo"),
						result.getString("Salario_Cargo"), result.getString("Ativo_Inativo_Status_cargo"), result.getString("Data_Admissao_cargo"), result.getString("NumFunc")});

			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCargo.setModel(modelo);


	}
	
	public static void LoadAtivoCargoTable(JTable LoadCargo){

		String[] Colunas = new String[]{"ID","Cargo","Salario","Status","Data de admissão", "N° Funcionários"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "select * from FuncCargoNumber where Ativo_Inativo_Status_Cargo = 'Ativo'";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_cargo"), result.getString("Nome_cargo"),
						result.getString("Salario_Cargo"), result.getString("Ativo_Inativo_Status_cargo"), result.getString("Data_Admissao_cargo"), result.getString("NumFunc")});

			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCargo.setModel(modelo);


	}

	public static void LoadInativoCargoTable(JTable LoadCargo){

		String[] Colunas = new String[]{"ID","Cargo","Salario","Status","Data de admissão", "N° Funcionários"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "select * from FuncCargoNumber where Ativo_Inativo_Status_Cargo = 'Inativo'";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_cargo"), result.getString("Nome_cargo"),
						result.getString("Salario_Cargo"), result.getString("Ativo_Inativo_Status_cargo"), result.getString("Data_Admissao_cargo"), result.getString("NumFunc")});

			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCargo.setModel(modelo);


	}



	public static void cadastrarCargo(CargoBean cb) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {
			System.out.println(InsertCargo);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(InsertCargo);

			statement.setString(1, cb.getNomeCargo());
			statement.setFloat(2, cb.getSalarioCargo());
			statement.setString(3, cb.getDescricaoCargo());
			statement.setString(4, cb.getAcessoSistemaPermissao());
			statement.setString(5, cb.getCadastroConsultaCliPermissao());
			statement.setString(6, cb.getCadastroConsultaFuncPermissao());
			statement.setString(7, cb.getCadastroConsultaCargoPermissao());
			statement.setString(8, cb.getCadastroConsultaFornecPermissao());
			statement.setString(9, cb.getCadastroConsulta_Prod_Livro_Permissao());
			statement.setString(10, cb.getCadastroConsultaContatoClientePermissao());
			statement.setString(11, cb.getVendasPermissao());
			statement.setInt(12, cb.getFunc().getId_func());
			statement.execute();

			JOptionPane.showMessageDialog(null,"Cargo cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

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

	public static void alterarCargo(CargoBean cb) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			System.out.println(Update_Cargo);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Update_Cargo);

			statement.setString(1, cb.getNomeCargo());
			statement.setFloat(2, cb.getSalarioCargo());
			statement.setString(3, cb.getDescricaoCargo());
			statement.setString(4, cb.getAcessoSistemaPermissao());
			statement.setString(5, cb.getCadastroConsultaCliPermissao());
			statement.setString(6, cb.getCadastroConsultaFuncPermissao());
			statement.setString(7, cb.getCadastroConsultaCargoPermissao());
			statement.setString(8, cb.getCadastroConsultaFornecPermissao());
			statement.setString(9, cb.getCadastroConsulta_Prod_Livro_Permissao());
			statement.setString(10, cb.getCadastroConsultaContatoClientePermissao());
			statement.setString(11, cb.getVendasPermissao());
			statement.setInt(12, cb.getIdCargo());
			statement.execute();

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Cargo alterado com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

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

	public static boolean verificarCargo(CargoBean cb) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {
			System.out.println(verifyIfCargoExists);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(verifyIfCargoExists);

			statement.setString(1, cb.getNomeCargo());

			ResultSet result = statement.executeQuery();

			while(result.next()){

				cb.setNomeCargo(result.getString("Nome_Cargo"));

			}

			if(cb.getNomeCargo() != result.getString("Nome_Cargo")){
				JOptionPane.showMessageDialog(null, "Já Existe um cargo com esse nome no Banco de Dados, \n por favor, insira um outro nome!");
				return true;
			}

			conexao.close();
			statement = null;

		} catch (SQLException e1) {
			//return false;
		}
		return false;

	}

	public static CargoBean retornarCargo(CargoBean cb){

		//String sql = "Select * from Cliente where id_cli = " + c.getId_cli();

		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(Return_Cargo);
			statement.setInt(1, cb.getIdCargo());
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				//java.sql.Date dataNascCliSql = new java.sql.Date(.getDate().getTime()); 
				//java.sql.Date dataNascCadastroSql = new java.sql.Date(data_Nasc_Cli.getDate().getTime());

				cb.setIdCargo(result.getInt("ID_cargo"));
				cb.setNomeCargo(result.getString("Nome_cargo"));
				cb.setSalarioCargo(result.getFloat("Salario_Cargo"));
				cb.setDescricaoCargo(result.getString("Descricao_cargo"));
				cb.setCadastroConsultaCliPermissao(result.getString("Cadastro_Consulta_Cli_Permissao"));
				cb.setCadastroConsultaFuncPermissao(result.getString("Cadastro_Consulta_Func_Permissao"));
				cb.setCadastroConsultaCargoPermissao(result.getString("Cadastro_Consulta_Cargo_Permissao"));
				cb.setCadastroConsultaFornecPermissao(result.getString("Cadastro_Consulta_Fornec_Permissao"));
				cb.setCadastroConsulta_Prod_Livro_Permissao(result.getString("Cadastro_Consulta_Prod_Livro_Permissao"));
				cb.setCadastroConsultaContatoClientePermissao(result.getString("Cadastro_Consulta_Contato_Cliente_Permissao"));
				cb.setVendasPermissao(result.getString("Vendas_Permissao"));

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return cb;

	}

	public static void AtivarCargo(CargoBean cb){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Activate_Cargo);
			statement.setInt(1, cb.getIdCargo());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Cargo ativado com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Cargo não foi ativado com sucesso!", "Erro!",
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
	
	public static void DesativarCargo(CargoBean cb){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Deactivate_Cargo);
			statement.setInt(1, cb.getIdCargo());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Cargo deativado com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Cargo não foi deativado com sucesso!", "Erro!",
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

	

//	public static ArrayList<CargooBean> ListarFuncionario(CargooBean f, String columnName, String getterName){
//
//		ArrayList<CargooBean> listaCampos = new ArrayList<CargooBean>();
//		String searchFunc = "SELECT dbo.Funcionario.*, dbo.Cargo.Nome_Cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.ID_Cargo = dbo.Funcionario.id_cargo_fk where "+columnName+" like '%"+getterName+"%'";
//
//		System.out.println(searchFunc);
//
//		Connection conn = ConnectionFactory.getConnection();
//		PreparedStatement statement;
//
//
//		try {
//			statement = conn.prepareStatement(searchFunc);
//			ResultSet result = statement.executeQuery();
//
//			while (result.next()) {
//
//				
//				f.setId_func(result.getInt("ID_func"));
//				f.setNome_func(result.getString("Nome_func"));
//				f.setTelefone_func(result.getString("Tel_func"));
//				f.setCpf_func(result.getString("CPF_func"));
//				f.setSexo_func(result.getString("Sexo_func"));
//				/*f.setRg_func(result.getString("RG_func"));
//				f.setCep_func(result.getString("Cep_func"));
//				f.setDataNasc_func(result.getDate("Data_Nasc_func"));
//				f.setLogradouro_func(result.getString("logradouro_func"));
//				f.setNumero_func(result.getString("Numero_func"));
//				f.setComplemento_func(result.getString("Complemento_func"));
//				f.setBairro_func(result.getString("Bairro_func"));
//				f.setCidade_func(result.getString("Cidade_func"));
//				f.setEstado_func(result.getString("Estado_func"));
//				f.setSenha_func(result.getString("Senha_func"));
//				f.setFoto(result.getBytes("foto_func"));*/
//				f.setDataAdmissao_func(result.getString("Data_admissao_func"));
//				f.setAtivo_inativo_status(result.getString("ativo_inativo_status"));
//				f.setNome_cargo(result.getString("Nome_Cargo"));
//
//				
//				
//
//				listaCampos.add(f);
//				
//
//			}
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		return listaCampos;
//	}
//
//	public static ArrayList<CargooBean> ListarFuncionarioAtivoInativo(CargooBean cb, String columnName, String getterName){
//
//		ArrayList<CargooBean> listaCampos = new ArrayList<CargooBean>();
//		String searchFunc = "SELECT dbo.Funcionario.*, dbo.Cargo.Nome_Cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.ID_Cargo = dbo.Funcionario.id_cargo_fk where "+columnName+" like '%"+getterName+"%' and Ativo_Inativo_Status = '"+cb.getAtivo_inativo_status()+"'";
//
//		System.out.println(searchFunc);
//
//		Connection conn = ConnectionFactory.getConnection();
//		PreparedStatement statement;
//
//
//		try {
//			statement = conn.prepareStatement(searchFunc);
//			ResultSet result = statement.executeQuery();
//
//			while (result.next()) {
//
//				
//				cb.setId_func(result.getInt("ID_func"));
//				cb.setNome_func(result.getString("Nome_func"));
//				cb.setTelefone_func(result.getString("Tel_func"));
//				cb.setCpf_func(result.getString("CPF_func"));
//				cb.setSexo_func(result.getString("Sexo_func"));
//				/*f.setRg_func(result.getString("RG_func"));
//				f.setCep_func(result.getString("Cep_func"));
//				f.setDataNasc_func(result.getDate("Data_Nasc_func"));
//				f.setLogradouro_func(result.getString("logradouro_func"));
//				f.setNumero_func(result.getString("Numero_func"));
//				f.setComplemento_func(result.getString("Complemento_func"));
//				f.setBairro_func(result.getString("Bairro_func"));
//				f.setCidade_func(result.getString("Cidade_func"));
//				f.setEstado_func(result.getString("Estado_func"));
//				f.setSenha_func(result.getString("Senha_func"));
//				f.setFoto(result.getBytes("foto_func"));*/
//				cb.setDataAdmissao_func(result.getString("Data_admissao_func"));
//				cb.setAtivo_inativo_status(result.getString("ativo_inativo_status"));
//				cb.setNome_cargo(result.getString("Nome_Cargo"));
//
//				
//				
//
//				listaCampos.add(cb);
//				
//
//			}
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//
//		return listaCampos;
//	}
//
//	public static void preencherTableFuncionarioPesquisaAticoInativo(JTable table, CargooBean f, String columnName, String getterName) {
//
//
//		String[] Colunas = new String[] { "ID", "Nome", "Telefone", "CPF", "Sexo", "Status", "Cargo", "Data de Admissão" };
//		ArrayList<CargooBean> DadosFunc = new ArrayList<CargooBean>();
//		ArrayList<Object> Dados = new ArrayList<Object>();
//
//		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
//					+ func.getNome_func()
//					+ "%' and ativo_inativo = '"
//					+ func.getAtivo_inativo() + "'";*/
//		// System.out.println(sql);
//
//		DadosFunc = FuncionarioDAO.ListarFuncionarioAtivoInativo(f, columnName, getterName);
//
//		for (CargooBean f2 : DadosFunc) {
//
//			Dados.add(new Object[] { f2.getId_func(), f2.getNome_func(),f2.getTelefone_func(),
//					f2.getCpf_func(), f2.getSexo_func(),f2.getAtivo_inativo_status(),
//					f2.getNome_cargo(), f2.getDataAdmissao_func() });
//
//		}
//
//		TableModel modelo = new TableModel(Dados, Colunas);
//		table.setModel(modelo);
//
//	} 
//	
//	public static void preencherTableFuncionarioPesquisa(JTable table, CargooBean f, String columnName, String getterName) {
//
//
//		String[] Colunas = new String[] { "ID", "Nome", "Telefone", "CPF", "Sexo", "Status", "Cargo", "Data de Admissão" };
//		ArrayList<CargooBean> DadosFunc = new ArrayList<CargooBean>();
//		ArrayList<Object> Dados = new ArrayList<Object>();
//
//		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
//					+ func.getNome_func()
//					+ "%' and ativo_inativo = '"
//					+ func.getAtivo_inativo() + "'";*/
//		// System.out.println(sql);
//
//		DadosFunc = FuncionarioDAO.ListarFuncionario(f, columnName, getterName);
//
//		for (CargooBean f2 : DadosFunc) {
//
//			Dados.add(new Object[] { f2.getId_func(), f2.getNome_func(),f2.getTelefone_func(),
//					f2.getCpf_func(), f2.getSexo_func(),f2.getAtivo_inativo_status(),
//					f2.getNome_cargo(), f2.getDataAdmissao_func() });
//
//		}
//
//		TableModel modelo = new TableModel(Dados, Colunas);
//		table.setModel(modelo);
//
//	} 


}

