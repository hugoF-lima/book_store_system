package dataAcessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import utilities.ConnectionFactory;
import utilities.TableModel;
import acessoDadosBeans.FornecedorBean;

public class FornecedorDAO {

	private static final String Insert_Fornecedor = "Insert into Fornecedor values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Ativo', getdate(), null, ?)";

	private static final String verifyIfCNPJExists = "Select * from Fornecedor where cnpj_fornec like ?";
	
	private static final String Update_Fornec = "update Fornecedor set Nome_fornec = ?, Email_fornec = ?, Tel_fornec = ?, CNPJ_fornec = ?, " +
	"CEP_fornec = ?, Logradouro_fornec = ?, Numero_fornec = ?, Complemento_fornec = ?, " +
	"Bairro_fornec = ?, Cidade_fornec = ?, Estado_fornec = ? Where ID_fornec = ?";

	private static final String return_Fornec = "Select * from Fornec_Func where id_fornec = ?";

	private static final String Select_Fornecedor = "Select * from Fornecedor order by ID_fornec";

	private static final String verifyStatusFornec = "Select Ativo_Inativo_Status_fornec from Fornecedor where Id_fornec = ?";
	
	private static final String Activate_Fornecedor = "update Fornecedor set Data_Recisao_fornec = null, Ativo_Inativo_Status_fornec = 'Ativo' where ID_fornec = ?";
	
	private static final String Deactivate_Fornecedor = "update Fornecedor set Data_Recisao_fornec = getdate(), Ativo_Inativo_Status_fornec = 'Inativo' where ID_fornec = ?";
	

	public static void cadastrarFornecedor(FornecedorBean p) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {
			System.out.println(Insert_Fornecedor);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Insert_Fornecedor);
			statement.setString(1, p.getNome_fornec());
			statement.setString(2, p.getEmail_fornec());
			statement.setString(3, p.getTel_fornec());
			statement.setString(4, p.getCnpj_fornec());
			statement.setString(5, p.getCep_fornec());
			statement.setString(6, p.getLogradouro_fornec());
			statement.setString(7, p.getNumero_fornec());
			statement.setString(8, p.getComplemento_fornec());
			statement.setString(9, p.getBairro_fornec());
			statement.setString(10, p.getCidade_fornec());
			statement.setString(11, p.getEstado_fornec());
			statement.setInt(12, p.getFunc().getId_func());
			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Fornecedor cadastrado com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

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
	
	public static FornecedorBean verificarCnpj(String cnpj) {

		PreparedStatement statement = null;
		Connection conexao = null;
		FornecedorBean fornec = new FornecedorBean();
		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(verifyIfCNPJExists);
			statement.setString(1, cnpj);

			ResultSet result = statement.executeQuery();

			if(result.next()){

				fornec.setCnpj_fornec(result.getString("Cnpj_fornec"));

			}

			conexao.close();
			statement = null;

		} catch (SQLException e1) {
			//return false;
		}
		return fornec;

	}


	public static String returnAtivoInativoFornecById(int Id_fornec) {


		Connection conexao = null;

		String Ativo_Inativo_Fornec = null;

		try {

			conexao = ConnectionFactory.getConnection();
			PreparedStatement statement = conexao.prepareStatement(verifyStatusFornec);

			statement.setInt(1, Id_fornec);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Ativo_Inativo_Fornec = result.getString(1);

			}

			conexao.close();
			statement = null;
			result = null;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Ativo_Inativo_Fornec;
	}



	public static void AlterarFornecedor(FornecedorBean p) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			System.out.println(Update_Fornec);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Update_Fornec);
			statement.setString(1, p.getNome_fornec());
			statement.setString(2, p.getEmail_fornec());
			statement.setString(3, p.getTel_fornec());
			statement.setString(4, p.getCnpj_fornec());
			statement.setString(5, p.getCep_fornec());
			statement.setString(6, p.getLogradouro_fornec());
			statement.setString(7, p.getNumero_fornec());
			statement.setString(8, p.getComplemento_fornec());
			statement.setString(9, p.getBairro_fornec());
			statement.setString(10, p.getCidade_fornec());
			statement.setString(11, p.getEstado_fornec());
			statement.setInt(12, p.getId_fornec());
			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Fornecedor alterado com sucesso!", "Sucesso!",
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
			}finally{
			}
		}
	}

	public static FornecedorBean retornarFornecedor(FornecedorBean p){


		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(return_Fornec);
			statement.setInt(1, p.getId_fornec());
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				//java.sql.Date dataNascCliSql = new java.sql.Date(.getDate().getTime()); 
				//java.sql.Date dataNascCadastroSql = new java.sql.Date(data_Nasc_Cli.getDate().getTime());

				p.setId_fornec(result.getInt("ID_fornec"));
				p.setNome_fornec(result.getString("Nome_fornec"));
				p.setEmail_fornec(result.getString("Email_fornec"));
				p.setTel_fornec(result.getString("Tel_fornec"));
				p.setCnpj_fornec(result.getString("CNPJ_fornec"));
				p.setCep_fornec(result.getString("Cep_fornec"));
				p.setLogradouro_fornec(result.getString("logradouro_fornec"));
				p.setNumero_fornec(result.getString("Numero_fornec"));
				p.setComplemento_fornec(result.getString("Complemento_fornec"));
				p.setBairro_fornec(result.getString("Bairro_fornec"));
				p.setCidade_fornec(result.getString("Cidade_fornec"));
				p.setEstado_fornec(result.getString("Estado_fornec"));
				p.setAtivo_inativo_status_fornec(result.getString("Ativo_Inativo_Status_fornec"));
				p.setData_Admissao_fornec(result.getDate("Data_Admissao_fornec"));
				p.setDataRescicao_fornec(result.getDate("Data_Recisao_fornec"));
				p.setCadastradoPorFunc_fornec(result.getString("Nome_func"));
				//c.setDataCadastro_cli(dataCadastroSql);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return p;

	}

	public static void LoadFornecedorTable(JTable LoadFornec){

		String[] Colunas = new String[]{"ID","Nome","Telefone","Status","CNPJ","CEP","Data de Admissão"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "Select ID_fornec, Nome_fornec, TEL_fornec, Ativo_Inativo_Status_fornec, CNPJ_fornec, CEP_fornec, Data_Admissao_fornec from Fornecedor";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			//PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			//ResultSet result2 = statement2.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_fornec"), result.getString("Nome_fornec"),
						result.getString("TEL_fornec"), result.getString("Ativo_Inativo_Status_fornec"), result.getString("CNPJ_fornec"),
						result.getString("CEP_fornec"), result.getString("Data_Admissao_fornec")});

			}


		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadFornec.setModel(modelo);


	}

	public static void LoadAtivoFornecedorTable(JTable LoadFornec){

		String[] Colunas = new String[]{"ID","Nome","Telefone","Status","CNPJ","CEP","Data de Admissão"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "Select ID_fornec, Nome_fornec, TEL_fornec, Ativo_Inativo_Status_fornec, CNPJ_fornec, CEP_fornec, Data_Admissao_fornec from Fornecedor where Ativo_Inativo_Status_fornec = 'Ativo'";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			//PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			//ResultSet result2 = statement2.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_fornec"), result.getString("Nome_fornec"),
						result.getString("TEL_fornec"), result.getString("Ativo_Inativo_Status_fornec"), result.getString("CNPJ_fornec"),
						result.getString("CEP_fornec"), result.getString("Data_Admissao_fornec")});

			}


		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadFornec.setModel(modelo);


	}
	
	public static void LoadInativoFornecedorTable(JTable LoadFornec){

		String[] Colunas = new String[]{"ID","Nome","Telefone","Status","CNPJ","CEP","Data de Admissão"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "Select ID_fornec, Nome_fornec, TEL_fornec, Ativo_Inativo_Status_fornec, CNPJ_fornec, CEP_fornec, Data_Admissao_fornec from Fornecedor where Ativo_Inativo_Status_fornec = 'Inativo'";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			//PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			//ResultSet result2 = statement2.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_fornec"), result.getString("Nome_fornec"),
						result.getString("TEL_fornec"), result.getString("Ativo_Inativo_Status_fornec"), result.getString("CNPJ_fornec"),
						result.getString("CEP_fornec"), result.getString("Data_Admissao_fornec")});

			}


		}catch(SQLException ex){
			ex.printStackTrace();
		}


		TableModel modelo = new TableModel(Dados,Colunas);
		LoadFornec.setModel(modelo);


	}
	
	public static ArrayList<FornecedorBean> ListarFornecedor(FornecedorBean p, String columnName, String getterName){

		ArrayList<FornecedorBean> listaCampos = new ArrayList<FornecedorBean>();
		String searchFunc = "Select ID_fornec, Nome_fornec, TEL_fornec, Ativo_Inativo_Status_fornec, CNPJ_fornec, CEP_fornec, Data_Admissao_fornec from Fornecedor where "+columnName+" like '%"+getterName+"%'";

		System.out.println(searchFunc);

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement;


		try {
			statement = conn.prepareStatement(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				
				p.setId_fornec(result.getInt("ID_fornec"));
				p.setNome_fornec(result.getString("Nome_fornec"));
				p.setTel_fornec(result.getString("Tel_fornec"));
				p.setCnpj_fornec(result.getString("Cnpj_Fornec"));
				p.setCep_fornec(result.getString("Cep_fornec"));
				p.setData_Admissao_fornec(result.getDate("Data_admissao_fornec"));
				p.setAtivo_inativo_status_fornec(result.getString("ativo_inativo_status_Fornec"));
				//p.setNome_cargo(result.getString("Nome_Cargo"));

				
				

				listaCampos.add(p);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaCampos;
	}

	public static ArrayList<FornecedorBean> ListarFornecedorAtivoInativo(FornecedorBean p, String columnName, String getterName){

		ArrayList<FornecedorBean> listaCampos = new ArrayList<FornecedorBean>();
		String searchFunc = "Select ID_fornec, Nome_fornec, TEL_fornec, Ativo_Inativo_Status_fornec, CNPJ_fornec, CEP_fornec, Data_Admissao_fornec from Fornecedor where "+columnName+" like '%"+getterName+"%' and Ativo_Inativo_Status_fornec = '"+p.getAtivo_inativo_status_fornec()+"'";

		System.out.println(searchFunc);

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement;


		try {
			statement = conn.prepareStatement(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				
				p.setId_fornec(result.getInt("ID_fornec"));
				p.setNome_fornec(result.getString("Nome_fornec"));
				p.setTel_fornec(result.getString("Tel_fornec"));
				p.setCnpj_fornec(result.getString("Cnpj_Fornec"));
				p.setCep_fornec(result.getString("Cep_fornec"));
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
				
				p.setData_Admissao_fornec(result.getDate("Data_admissao_func"));
				p.setAtivo_inativo_status_fornec(result.getString("ativo_inativo_status"));
				//p.setNome_cargo(result.getString("Nome_Cargo"));

				

				listaCampos.add(p);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listaCampos;
	}

	public static void preencherTableFornecedorPesquisaAticoInativo(JTable table, FornecedorBean f, String columnName, String getterName) {


		String[] Colunas = new String[]{"ID","Nome","Telefone","Status","CNPJ","CEP","Data de Admissão"};
		ArrayList<FornecedorBean> DadosFunc = new ArrayList<FornecedorBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
					+ func.getNome_func()
					+ "%' and ativo_inativo = '"
					+ func.getAtivo_inativo() + "'";*/
		// System.out.println(sql);

		DadosFunc = FornecedorDAO.ListarFornecedorAtivoInativo(f, columnName, getterName);

		for (FornecedorBean f2 : DadosFunc) {

			Dados.add(new Object[] { f2.getId_fornec(), f2.getNome_fornec(), f2.getTel_fornec(),
					f2.getAtivo_inativo_status_fornec(), f2.getCnpj_fornec(),f2.getCep_fornec(),
				 f2.getData_Admissao_fornec() });

		}

		TableModel modelo = new TableModel(Dados, Colunas);
		table.setModel(modelo);

	} 
	
	public static void preencherTableFuncionarioPesquisa(JTable table, FornecedorBean f, String columnName, String getterName) {


		String[] Colunas = new String[]{"ID","Nome","Telefone","Status","CNPJ","CEP","Data de Admissão"};
		ArrayList<FornecedorBean> DadosFornec = new ArrayList<FornecedorBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
					+ func.getNome_func()
					+ "%' and ativo_inativo = '"
					+ func.getAtivo_inativo() + "'";*/
		// System.out.println(sql);

		DadosFornec = FornecedorDAO.ListarFornecedor(f, columnName, getterName);

		for (FornecedorBean f2 : DadosFornec) {

			Dados.add(new Object[] { f2.getId_fornec(), f2.getNome_fornec(), f2.getTel_fornec(),
					f2.getAtivo_inativo_status_fornec(), f2.getCnpj_fornec(),f2.getCep_fornec(),
				 f2.getData_Admissao_fornec() });

		}

		TableModel modelo = new TableModel(Dados, Colunas);
		table.setModel(modelo);

	} 


	public static void carregarProductsFornecTable(JTable table, FornecedorBean p){

		String[] Colunas = new String[] { "N° Livro", "Nome", "Gênero", "Preço", "Data Inserção"};
		ArrayList<Object[]> DadosProdutoForn = new ArrayList<Object[]>();
		String sqlCommand = "Select * from Livro_Genero_Fornec where Id_fornec = ?";
			//"select Id_Produto_livro, Nome_Produto_livro, Nome_Genero, Preco_Livro, Data_Insercao_Produto_Livro  from Livro_Genero_Fornec where Id_Produto_livro = 1";//View
		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			statement.setInt(1, p.getId_fornec());
			ResultSet result = statement.executeQuery();

			while(result.next()){
				
				p.setId_fornec(result.getInt("Id_fornec"));
				p.setData_Admissao_fornec(result.getDate("Data_Admissao_Fornec"));
				p.setDataRescicao_fornec(result.getDate("Data_Recisao_Fornec"));
				p.setCadastradoPorFunc_fornec(result.getString("Nome_fornec"));
				p.setAtivo_inativo_status_fornec(result.getString("Ativo_Inativo_Status_fornec"));
				
				DadosProdutoForn.add(new Object[]{result.getString("Id_Produto_livro"), result.getString("Nome_Produto_livro"),
						result.getString("Nome_Genero"), result.getString("Preco_Livro"), result.getString("Data_Insercao_Produto_Livro")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}
		TableModel model = new TableModel(DadosProdutoForn, Colunas);

		table.setModel(model);

	}

	public static void AtivarFornecedor(FornecedorBean p){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Activate_Fornecedor);
			statement.setInt(1, p.getId_fornec());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Fornecedor ativado com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Fornecedor não foi ativado com sucesso!", "Erro!",
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
	
	public static void DesativarFornecedor(FornecedorBean p){

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Deactivate_Fornecedor);
			statement.setInt(1, p.getId_fornec());

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Fornecedor desativado com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Fornecedor não foi desativado com sucesso!", "Erro!",
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

	public List<FornecedorBean> buscarFornecedor() {
		List<FornecedorBean> lista = new ArrayList<FornecedorBean>();

		//lista.add(new CargoInformacao(1, "Gerente"));
		//lista.add(new CargoInformacao(2, "Recepcionista"));
		//lista.add(new Cargo(3, "Caixa"));
		//lista.add(new Cargo(4, "Recepcionista"));
		//lista.add(new Cargo(5, "Analista"));

		PreparedStatement statement = null;
		Connection conexao = null;
		ResultSet result = null;
		FornecedorBean fornecedor = null;

		try {
			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Select_Fornecedor);


			result = statement.executeQuery();
			while (result.next()) {
				fornecedor = getBean(result);
				lista.add(fornecedor);
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

	private FornecedorBean getBean(ResultSet result) throws SQLException {
		return new FornecedorBean(result.getInt("id_fornec"), result.getString("Nome_fornec"));
	}

}


