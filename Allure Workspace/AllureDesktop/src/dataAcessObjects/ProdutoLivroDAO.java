package dataAcessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import utilities.ConnectionFactory;
import utilities.TableModel;
import acessoDadosBeans.ProdutoLivroBean;

public class ProdutoLivroDAO {

	private static final String InsertProdutoLivro = "Insert into Produto_Livro values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,getdate(), null, 'Ativo', ?)";

	private static final String SearchProdLivroById = "Select * from Produto_Livro where Ativo_Inativo_Status_Livro_Produto = 'Ativo' and Id_produto_livro = ?";

	private static final String verifyIfISBNExists = "Select * from Produto_Livro where ISBN_livro like ?";

	private static final String Update_ProdLivro = "update Produto_Livro set Id_Genero_fk = ?, Nome_Produto_livro = ?, ISBN_livro = ?, Autor_livro = ?, " +
	"Edicao_livro = ?, Ano_livro = ?, Preco_Livro = ?, Quantidade_Livro = ?, Id_fornec_fk = ?, Foto_livro = ? Where Id_Produto_livro = ?";

	//"Insert into Produto values (Nome_produto, Tipo_produto, preco_produto, quantidade_produto, id_fornec_fk)";

	public static void cadastrarLivroProduto(ProdutoLivroBean pl) {

		PreparedStatement statement = null;
		Connection conexao = null;
		byte[] byteArray = null;

		try {
			System.out.println(InsertProdutoLivro);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(InsertProdutoLivro);

			statement.setInt(1, pl.getId_genero_fk());
			statement.setString(2, pl.getNome_livro_prod());
			statement.setString(3, pl.getISBN_livro());
			statement.setString(4, pl.getAutor_livro());
			statement.setString(5, pl.getEdicao_livro());
			statement.setInt(6, pl.getAno_livro());
			statement.setFloat(7, pl.getPreco_livro_prod());
			statement.setInt(8, pl.getQtd_livro_prod());
			statement.setInt(9, pl.getId_fornec_fk());
			byteArray = pl.getPhoto_Book();
			statement.setBytes(10, byteArray);
			statement.setInt(11, pl.getFunc().getId_func());
			statement.execute();

			JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

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

	public static ProdutoLivroBean verificarIsbn(String Isbn) {

		PreparedStatement statement = null;
		Connection conexao = null;
		ProdutoLivroBean prod = new ProdutoLivroBean();
		try {

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(verifyIfISBNExists);
			statement.setString(1, Isbn);

			ResultSet result = statement.executeQuery();

			if(result.next()){

				prod.setISBN_livro(result.getString("ISBN_livro"));

			}

			conexao.close();
			statement = null;

		} catch (SQLException e1) {
			//return false;
		}
		return prod;

	}


	public static void alterarLivroProduto(ProdutoLivroBean pl) {

		PreparedStatement statement = null;
		Connection conexao = null;

		try {

			System.out.println(Update_ProdLivro);

			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Update_ProdLivro);

			statement.setInt(1, pl.getId_genero_fk());
			statement.setString(2, pl.getNome_livro_prod());
			statement.setString(3, pl.getISBN_livro());
			statement.setString(4, pl.getAutor_livro());
			statement.setString(5, pl.getEdicao_livro());
			statement.setInt(6, pl.getAno_livro());
			statement.setFloat(7, pl.getPreco_livro_prod());
			statement.setInt(8, pl.getQtd_livro_prod());
			statement.setInt(9, pl.getId_fornec_fk());
			statement.setBytes(10, pl.getPhoto_Book());
			statement.setInt(11, pl.getId_livro_prod());
			statement.execute();

			statement.execute();

			JOptionPane.showMessageDialog(null,
					"Produto/Livro alterado com sucesso!", "Sucesso!",
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
	}

	public static void loadLivroProdTable(JTable LoadCadastroFunc){

		String[] Colunas = new String[]{"ID","Nome","Genero", "ISBN", "Status", "Fornecedor","Quantidade","Valor Unitário"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "select * from Livro_Genero_Fornec";//VIEW
		//"SELECT dbo.Produto.*, dbo.Fornecedor.Nome_fornec FROM dbo.Fornecedor INNER JOIN dbo.Produto ON dbo.Fornecedor.ID_fornec = dbo.Produto.id_fornec_fk";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_produto_livro"), result.getString("Nome_produto_livro"),
						result.getString("Nome_genero"), result.getString("ISBN_Livro"), result.getString("Ativo_Inativo_Status_Livro_Produto"), result.getString("nome_fornec"),
						result.getString("quantidade_livro"), result.getString("preco_livro")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}

		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCadastroFunc.setModel(modelo);
	}

	public static void loadAtivoLivroProdTable(JTable LoadCadastroFunc){

		String[] Colunas = new String[]{"ID","Nome","Genero", "ISBN","Status",  "Fornecedor","Quantidade","Valor Unitário"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "select * from Livro_Genero_Fornec where Ativo_Inativo_Status_Livro_Produto = 'Ativo'";//VIEW
		//"SELECT dbo.Produto.*, dbo.Fornecedor.Nome_fornec FROM dbo.Fornecedor INNER JOIN dbo.Produto ON dbo.Fornecedor.ID_fornec = dbo.Produto.id_fornec_fk";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_produto_livro"), result.getString("Nome_produto_livro"),
						result.getString("Nome_genero"), result.getString("ISBN_Livro"), result.getString("Ativo_Inativo_Status_Livro_Produto"), result.getString("nome_fornec"),
						result.getString("quantidade_livro"), result.getString("preco_livro")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}

		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCadastroFunc.setModel(modelo);
	}

	public static void loadInativoLivroProdTable(JTable LoadCadastroFunc){

		String[] Colunas = new String[]{"ID","Nome","Genero", "ISBN","Status",  "Fornecedor","Quantidade","Valor Unitário"};
		ArrayList<Object[]> Dados = new ArrayList<Object[]>();
		String sqlCommand = "select * from Livro_Genero_Fornec where Ativo_Inativo_Status_Livro_Produto = 'Inativo'";//VIEW
		//"SELECT dbo.Produto.*, dbo.Fornecedor.Nome_fornec FROM dbo.Fornecedor INNER JOIN dbo.Produto ON dbo.Fornecedor.ID_fornec = dbo.Produto.id_fornec_fk";

		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Dados.add(new Object[]{result.getInt("ID_produto_livro"), result.getString("Nome_produto_livro"),
						result.getString("Nome_genero"), result.getString("ISBN_Livro"), result.getString("Ativo_Inativo_Status_Livro_Produto"), result.getString("nome_fornec"),
						result.getString("quantidade_livro"), result.getString("preco_livro")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}

		TableModel modelo = new TableModel(Dados,Colunas);
		LoadCadastroFunc.setModel(modelo);
	}


	public static ProdutoLivroBean buscarLivroProduto(int Id){

		ProdutoLivroBean pl = new ProdutoLivroBean();

		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(SearchProdLivroById);
			statement.setInt(1, Id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				pl.setId_livro_prod(result.getInt(1));
				pl.setId_genero_fk(result.getInt("id_genero_fk"));
				pl.setNome_livro_prod(result.getString(3));
				pl.setISBN_livro(result.getString("ISBN_Livro"));
				pl.setAutor_livro(result.getString("Autor_Livro"));
				pl.setEdicao_livro(result.getString("Edicao_Livro"));
				pl.setAno_livro(result.getInt("Ano_Livro"));
				pl.setQtd_livro_prod(result.getInt(9));
				pl.setPreco_livro_prod(result.getFloat(8));
				pl.setId_fornec_fk(result.getInt("Id_fornec_fk"));
				pl.setPhoto_Book(result.getBytes("Foto_Livro"));
				pl.setDataInsercaoProdLivro(result.getString("Data_Insercao_Produto_Livro"));
				pl.setDataResicaoProdLivro(result.getString("Data_Resicao_Produto_Livro"));
				pl.setAtivoInativoStatusProdLivro(result.getString("Ativo_Inativo_Status_Livro_Produto"));


			}

			//			if(f.getNome_prod().trim().equals("")){
			//				f.setCod_prod(0);
			//				f.setNome_prod("");
			//				f.setPreco_compra_prod(0);
			//				f.setPreco_venda_prod(0);
			//				f.setQtd_prod(0);
			//				f.setCnpj_fornec("");
			//			}

			conn.close();
			statement = null;
			result = null;

		} catch (SQLException e) {
		}

		return pl;

	}
	
	public static ArrayList<ProdutoLivroBean> ListarLivro(ProdutoLivroBean pl, String columnName, String getterName){

		ArrayList<ProdutoLivroBean> listaCampos = new ArrayList<ProdutoLivroBean>();
		String searchFunc = "SELECT        dbo.Fornecedor.*, dbo.Genero_Livro.*, dbo.Produto_Livro.* "+
		"FROM            dbo.Fornecedor INNER JOIN "+
        "dbo.Produto_Livro ON dbo.Fornecedor.Id_fornec = dbo.Produto_Livro.Id_fornec_fk INNER JOIN "+
        "dbo.Genero_Livro ON dbo.Produto_Livro.Id_Genero_fk = dbo.Genero_Livro.Id_Genero "+
"where "+columnName+" like '%"+getterName+"%'";

			
			
			
//			"SELECT        dbo.Fornecedor.*, dbo.Genero_Livro.*, dbo.Produto_Livro.*" +
//				"FROM            dbo.Fornecedor INNER JOIN"+
// "                        dbo.Produto_Livro ON dbo.Fornecedor.Id_fornec = dbo.Produto_Livro.Id_fornec_fk INNER JOIN"+
//  "                       dbo.Genero_Livro ON dbo.Produto_Livro.Id_Genero_fk = dbo.Genero_Livro.Id_Genero"+
// "where "+columnName+" like '%"+getterName+"%'";

		System.out.println(searchFunc);

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement;


		try {
			statement = conn.prepareStatement(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				
				pl.setId_livro_prod(result.getInt("Id_Produto_livro"));
				pl.setNome_livro_prod(result.getString("Nome_Produto_livro"));
				pl.setNome_Genero(result.getString("Nome_Genero"));
				pl.setISBN_livro(result.getString("ISBN_Livro"));
				pl.setNome_Fornecedor(result.getString("Nome_fornec"));
				pl.setAtivoInativoStatusProdLivro(result.getString("Ativo_Inativo_Status_Livro_Produto"));
				pl.setDataInsercaoProdLivro(result.getString("Data_Insercao_Produto_Livro"));
				pl.setQtd_livro_prod(result.getInt("Quantidade_Livro"));
				pl.setPreco_livro_prod(result.getFloat("Preco_Livro"));

				
				

				listaCampos.add(pl);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaCampos;
	}

	public static ArrayList<ProdutoLivroBean> ListarLivroAtivoInativo(ProdutoLivroBean pl, String columnName, String getterName){

		ArrayList<ProdutoLivroBean> listaCampos = new ArrayList<ProdutoLivroBean>();
		String searchFunc = "SELECT        dbo.Fornecedor.*, dbo.Genero_Livro.*, dbo.Produto_Livro.* " +
				"FROM            dbo.Fornecedor INNER JOIN "+
 "                        dbo.Produto_Livro ON dbo.Fornecedor.Id_fornec = dbo.Produto_Livro.Id_fornec_fk INNER JOIN "+
  "                       dbo.Genero_Livro ON dbo.Produto_Livro.Id_Genero_fk = dbo.Genero_Livro.Id_Genero "+
 "where "+columnName+" like '%"+getterName+"%' and Ativo_Inativo_Status_Livro_Produto = '"+pl.getAtivoInativoStatusProdLivro()+"'";

		System.out.println(searchFunc);

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement;


		try {
			statement = conn.prepareStatement(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				
				pl.setId_livro_prod(result.getInt("Id_Produto_livro"));
				pl.setNome_livro_prod(result.getString("Nome_Produto_livro"));
				pl.setNome_Genero(result.getString("Nome_Genero"));
				pl.setISBN_livro(result.getString("ISBN_Livro"));
				pl.setNome_Fornecedor(result.getString("Nome_fornec"));
				pl.setAtivoInativoStatusProdLivro(result.getString("Ativo_Inativo_Status_Livro_Produto"));
				pl.setDataInsercaoProdLivro(result.getString("Data_Insercao_Produto_Livro"));
				pl.setQtd_livro_prod(result.getInt("Quantidade_Livro"));
				pl.setPreco_livro_prod(result.getFloat("Preco_Livro"));

				
				

				listaCampos.add(pl);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaCampos;
	}

	public static void preencherTableLivroPesquisaAtivoInativo(JTable table, ProdutoLivroBean pl, String columnName, String getterName) {


		String[] Colunas = new String[]{"ID","Nome","Genero", "ISBN","Status",  "Fornecedor","Quantidade","Valor Unitário"};
		ArrayList<ProdutoLivroBean> DadosFunc = new ArrayList<ProdutoLivroBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
					+ func.getNome_func()
					+ "%' and ativo_inativo = '"
					+ func.getAtivo_inativo() + "'";*/
		// System.out.println(sql);

		DadosFunc = ProdutoLivroDAO.ListarLivroAtivoInativo(pl, columnName, getterName);

		for (ProdutoLivroBean f2 : DadosFunc) {

			Dados.add(new Object[] { f2.getId_livro_prod(), f2.getNome_livro_prod(), f2.getNome_Genero(),
					f2.getISBN_livro(), f2.getAtivoInativoStatusProdLivro(),f2.getNome_Fornecedor(),
				 f2.getQtd_livro_prod(), f2.getPreco_livro_prod() });

		}

		TableModel modelo = new TableModel(Dados, Colunas);
		table.setModel(modelo);

	} 
	
	public static void preencherTableLivroPesquisa(JTable table, ProdutoLivroBean pl, String columnName, String getterName) {


		String[] Colunas = new String[]{"ID","Nome","Genero", "ISBN","Status",  "Fornecedor","Quantidade","Valor Unitário"};
		ArrayList<ProdutoLivroBean> DadosFunc = new ArrayList<ProdutoLivroBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
					+ func.getNome_func()
					+ "%' and ativo_inativo = '"
					+ func.getAtivo_inativo() + "'";*/
		// System.out.println(sql);

		DadosFunc = ProdutoLivroDAO.ListarLivro(pl, columnName, getterName);

		for (ProdutoLivroBean f2 : DadosFunc) {

			Dados.add(new Object[] { f2.getId_livro_prod(), f2.getNome_livro_prod(), f2.getNome_Genero(),
					f2.getISBN_livro(), f2.getAtivoInativoStatusProdLivro(),f2.getNome_Fornecedor(),
				 f2.getQtd_livro_prod(), f2.getPreco_livro_prod() });

		}

		TableModel modelo = new TableModel(Dados, Colunas);
		table.setModel(modelo);

	} 


}
