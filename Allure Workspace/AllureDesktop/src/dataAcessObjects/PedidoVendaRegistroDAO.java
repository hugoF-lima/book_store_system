package dataAcessObjects;

import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.util.HashMap;

import utilities.ConnectionFactory;
import utilities.TableModel;
import acessoDadosBeans.PedidoVendaRegistroBean;
import acessoDadosBeans.ProdutoLivroBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

public class PedidoVendaRegistroDAO {

	//Item & Pedido

	public static final String InsertPedido_VendaRegistro = "INSERT INTO Pedido_VendaRegistros values(?, getdate(),?, ?, ?, ?)";
	public static final String InsertPedidoItem = "insert into PedidoItem values (?, ?, ?, ?)";
	public static final String BaixaNoEstoqueProduto = "UPDATE Produto_Livro set quantidade_livro = ? where Id_produto_livro = ?";
	public static final String Select_Produto = "Select quantidade_livro from Produto_Livro where Id_produto_livro = ?";
	public static final String Select_ID_Pedido_VendaRegistro = "Select * from Pedido_VendaRegistros";
	public static final String Pagamento_Venda = "INSERT INTO PagamentoVenda VALUES(?,?,?,?)";

	public static final String Return_VendaBoleto = "select * from CupomInfo where id_Pedido_VendaRegistro_fk_PedidoItem = ?";


	public static void addProdutoTable(JTable table, ProdutoLivroBean pr, ArrayList<ProdutoLivroBean> ListaProdutos) {

		boolean qtdChanged = false;

		@SuppressWarnings("unused")
		int index = 0;
		TableModel model = (TableModel) table.getModel();
		String[] Colunas = model.getColunas();

		@SuppressWarnings("unchecked")
		ArrayList<Object> Dados = model.getLinhas();

		try{

			if(pr.getId_fornec_fk() > 0){

				if(Dados.size() < 1){

					if(pr.getQtd_livro_prod() == 0){
						JOptionPane.showMessageDialog(null, "Produto indisponível no estoque.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}else{
						Dados.add(new Object[]{pr.getId_livro_prod(),pr.getNome_livro_prod(),pr.getQtd_venda_livro_prod(),pr.getPreco_livro_prod()});
						ListaProdutos.add(pr);
					}


				}else{

					if(pr.getQtd_livro_prod() == 0){

						JOptionPane.showMessageDialog(null, "Produto indisponível no estoque.", "Erro",
								JOptionPane.ERROR_MESSAGE);

					}else{

						if(pr.getQtd_venda_livro_prod() > 0){

							for(int i = 0; i < ListaProdutos.size(); i++){

								if(pr.getId_livro_prod() == ListaProdutos.get(i).getId_livro_prod()){

									//					System.out.println(p.getQtd_prod());
									//					System.out.println(p.getQtd_venda_prod());

									float resultado = pr.getQtd_venda_livro_prod() + ListaProdutos.get(i).getQtd_venda_livro_prod();

									if(resultado > pr.getQtd_livro_prod()){

										ListaProdutos.get(i).setQtd_venda_livro_prod(pr.getQtd_livro_prod());

										Dados.set(i, new Object[]{ListaProdutos.get(i).getId_livro_prod(),ListaProdutos.get(i).getNome_livro_prod(),
												ListaProdutos.get(i).getQtd_venda_livro_prod(),ListaProdutos.get(i).getPreco_livro_prod()});

										//						Dados.add(new Object[]{ListaProdutos.get(i).getCod_prod(),ListaProdutos.get(i).getNome_prod(),
										//								ListaProdutos.get(i).getQtd_venda_prod(),ListaProdutos.get(i).getPreco_venda_prod()});

										qtdChanged = true;

									}else{

										ListaProdutos.get(i).setQtd_venda_livro_prod(pr.getQtd_venda_livro_prod() + ListaProdutos.get(i).getQtd_venda_livro_prod());
										//Dados.remove(i);
										Dados.set(i,new Object[]{ListaProdutos.get(i).getId_livro_prod(),ListaProdutos.get(i).getNome_livro_prod(),
												ListaProdutos.get(i).getQtd_venda_livro_prod(),ListaProdutos.get(i).getPreco_livro_prod()});
										qtdChanged = true;

									}

								}

							}

							if(qtdChanged == false){
								Dados.add(new Object[]{pr.getId_livro_prod(),pr.getNome_livro_prod(),pr.getQtd_venda_livro_prod(),pr.getPreco_livro_prod()});
								ListaProdutos.add(pr);
							}
						}
					}
				}
			}

			model = new TableModel(Dados, Colunas);
			table.setModel(model);

		} catch (Exception e) {

		}

	}
	
	


	public static void RealizarVenda(PedidoVendaRegistroBean pvr) {

		int codigo = 0;

		if (verificarEstoque(pvr)) {

			try {

				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement statement = conn.prepareStatement(Select_ID_Pedido_VendaRegistro);

				ResultSet result = statement.executeQuery();

				while (result.next()) {

					codigo = result.getInt(1);

				}

				statement = conn.prepareStatement(InsertPedido_VendaRegistro);

				pvr.setId_PedidoVendaRegistro(codigo +1);

				statement.setInt(1, pvr.getId_PedidoVendaRegistro());
				statement.setFloat(2, pvr.getValor_Total());

				statement.setInt(3, pvr.getFunc().getId_func());
				statement.setInt(4, pvr.getCli().getId_cli());

				statement.setBytes(5, null);
				statement.execute();


				statement = conn.prepareStatement(InsertPedidoItem);

				//Essa parte percorre o Arraylist, para depois setar os statements
				for (int i = 0; i < pvr.getPedidoVendaItens().size(); i++) {


					statement.setInt(1, pvr.getPedidoVendaItens().get(i).getId_livro_prod());
					statement.setInt(2, pvr.getId_PedidoVendaRegistro());
					statement.setInt(3, pvr.getPedidoVendaItens().get(i).getQtd_venda_livro_prod());
					statement.setFloat(4, pvr.getPedidoVendaItens().get(i).getPreco_livro_prod());
					statement.execute();

				}

				statement = conn.prepareStatement(BaixaNoEstoqueProduto);

				for (int i = 0; i < pvr.getPedidoVendaItens().size(); i++) {

					int retorno = retornarQuantidadeNoEstoqueProduto(pvr.getPedidoVendaItens().get(i).getId_livro_prod());
					int quantidade = retorno - pvr.getPedidoVendaItens().get(i).getQtd_venda_livro_prod();

					statement.setInt(1, quantidade);
					statement.setInt(2, pvr.getPedidoVendaItens().get(i).getId_livro_prod());

					statement.execute();

				}

				statement = conn.prepareStatement(Pagamento_Venda);

				statement.setInt(1, pvr.getPagtoVenda().getId_forma_pagamento_fk());
				statement.setInt(2, pvr.getId_PedidoVendaRegistro());
				statement.setFloat(3, pvr.getPagtoVenda().getValor_pago());
				statement.setFloat(4, pvr.getPagtoVenda().getValor_troco());

				statement.execute();


				JOptionPane.showMessageDialog(null, "Venda com sucesso",
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);

				codigo++;

				String preencherRelatorio = "select * from CupomInfo where id_Pedido_VendaRegistro_fk_PedidoItem = "+codigo;

				statement = conn.prepareStatement(preencherRelatorio);
				result = statement.executeQuery();

				JRResultSetDataSource vendaRelatorio = new JRResultSetDataSource(result);

				InputStream reportStream = PedidoVendaRegistroDAO.class.getResourceAsStream("VendaReport/Venda_Report.jasper");
				
				JasperPrint vendaPrint = JasperFillManager.fillReport(reportStream, new HashMap<String, Object>(), vendaRelatorio);

				JFrame frame = new JFrame("Cupom de Vendas");
				frame.getContentPane().add(new JRViewer(vendaPrint));
				frame.pack();
				frame.setVisible(true);
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage(("/telas/SourcePics/AllureBookIcon256x256.png")));
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(196, 0, 1043, 620);
				
				pvr.setBoleto_Venda(JasperExportManager.exportReportToPdf(vendaPrint));
				String insert_Boleto = "UPDATE Pedido_VendaRegistros set boleto_venda = ? where Id_Pedido_VendaRegistro = "+codigo;
				statement = conn.prepareStatement(insert_Boleto);
				statement.setBytes(1, pvr.getBoleto_Venda());
				statement.execute();

				conn.close();
				statement = null;
				result = null;

			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao Vender ou gerar Relatório", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (JRException e) {

				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Erro ao Vender, quantidade insuficiente no estoque de produtos",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static int retornarQuantidadeNoEstoqueProduto(int codigo){

		int quantidade = 0;

		try {

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(Select_Produto);

			statement.setInt(1, codigo);

			ResultSet result = statement.executeQuery();

			while(result.next()){

				quantidade = result.getInt(1);

			}

			conn.close();
			statement = null;


		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return quantidade;
	}

	private static boolean verificarEstoque(PedidoVendaRegistroBean pvr) {

		for (int i = 0; i < pvr.getPedidoVendaItens().size(); i++) {

			int quantidade = retornarQuantidadeNoEstoqueProduto(pvr.getPedidoVendaItens().get(i).getId_livro_prod());

			if (quantidade == 0 || quantidade < 0) {
				return false;
			}

		}

		return true;

	}

	public static void carregarPedidoTable(JTable table){

		String[] Colunas = new String[] { "Cód. Produto", "Nome", "Quantidade", "Valor Unitario" };
		ArrayList<ProdutoLivroBean> DadosProduto = new ArrayList<ProdutoLivroBean>();
		TableModel model = new TableModel(DadosProduto, Colunas);

		table.setModel(model);

	}

	//-----------

	
	
	public static PedidoVendaRegistroBean retornarVendaBoleto (PedidoVendaRegistroBean pvr){

		try{
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(Return_VendaBoleto);
		statement.setInt(1, pvr.getId_PedidoVendaRegistro());
		ResultSet result = statement.executeQuery();

		JRResultSetDataSource vendaRelatorio = new JRResultSetDataSource(result);
	
		InputStream reportStream = PedidoVendaRegistroDAO.class.getResourceAsStream("VendaReport/Venda_Report.jasper");
	
		JasperPrint vendaPrint = JasperFillManager.fillReport(reportStream, new HashMap<String, Object>(), vendaRelatorio);

		JFrame frame = new JFrame("Cupom de Vendas");
		frame.getContentPane().add(new JRViewer(vendaPrint));
		frame.pack();
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PedidoVendaRegistroDAO.class.getResource("/telas/SourcePics/AllureBookIcon256x256.png")));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(196, 0, 1043, 620);
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return pvr;

	}


	public static void carregarVendasTable(JTable table){

		String[] Colunas = new String[] { "Cód. Venda", "Data da Venda", "Valor total", "Funcionario", "Cliente" };
		ArrayList<Object[]> DadosVenda = new ArrayList<Object[]>();
		String sqlCommand = "select * from VendaView"; 

		//"select * from Venda_Func_Cli";
		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlCommand);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				DadosVenda.add(new Object[]{result.getInt("ID_pedido_vendaRegistro"), result.getString("Data_Venda"),
						result.getString("Valor_Total"), result.getString("Nome_func"), result.getString("nome_cli")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}
		TableModel model = new TableModel(DadosVenda, Colunas);

		table.setModel(model);

	}

	public static void carregarPedidoItemTable(JTable table, PedidoVendaRegistroBean pvr){

		String[] Colunas = new String[] { "Nome do produto", "Gênero", "quantidade", "valor do item"};
		ArrayList<Object[]> DadosVenda = new ArrayList<Object[]>();
		String buscarVenda = //"Select * from PedidoItem where id_Pedido_VendaRegistro_fk_PedidoItem = ?";
			"select * from RelatorioVenda where id_Pedido_VendaRegistro_fk_PedidoItem = ?";//View


		try{

			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(buscarVenda);
			statement.setInt(1, pvr.getId_PedidoVendaRegistro());
			ResultSet result = statement.executeQuery();


			while(result.next()){

				//pvr.setCli(setNome_Cli.(result.getString("Nome_Cli"));

				pvr.setValor_Total(result.getFloat("valor_total"));

				DadosVenda.add(new Object[]{result.getString("Nome_produto_livro"), result.getString("Nome_Genero"),
						result.getString("quantidade"), result.getString("preco_item")});
			}

		}catch(SQLException ex){
			ex.printStackTrace();
		}
		TableModel model = new TableModel(DadosVenda, Colunas);

		table.setModel(model);

	}
	
	public static ArrayList<PedidoVendaRegistroBean> ListarVenda(PedidoVendaRegistroBean pvr, String columnName, String getterName){

		ArrayList<PedidoVendaRegistroBean> listaCampos = new ArrayList<PedidoVendaRegistroBean>();
		String searchFunc = "SELECT        dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro, dbo.Pedido_VendaRegistros.Data_venda, dbo.Pedido_VendaRegistros.valor_total, dbo.Funcionario.Nome_func, dbo.Cliente.Nome_cli "+
"FROM            dbo.Cliente INNER JOIN"+
 "                        dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN "+
  "                       dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda"+
 "where "+columnName+" like '%"+getterName+"%'";

		System.out.println(searchFunc);

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement;


		try {
			statement = conn.prepareStatement(searchFunc);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				
				pvr.setId_PedidoVendaRegistro(result.getInt("Id_Pedido_VendaRegistro"));
				pvr.setDataVenda(result.getString("Data_venda"));
				pvr.setValor_Total(result.getFloat("valor_total"));
				pvr.setNomeFuncVenda(result.getString("Nome_func"));
				pvr.setNomeCliVenda(result.getString("Nome_Cli"));
				
				
				

				listaCampos.add(pvr);
				

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaCampos;
	}

	public static void preencherTableVendaPesquisa(JTable table, PedidoVendaRegistroBean pvr, String columnName, String getterName) {


		String[] Colunas = new String[] { "Cód. Venda", "Data da Venda", "Valor total", "Funcionario", "Cliente" };
		ArrayList<PedidoVendaRegistroBean> DadosFunc = new ArrayList<PedidoVendaRegistroBean>();
		ArrayList<Object> Dados = new ArrayList<Object>();

		/*String sql = "SELECT        dbo.Funcionario.*, dbo.Cargo.descricao_cargo, dbo.Cargo.salario_cargo FROM dbo.Cargo INNER JOIN dbo.Funcionario ON dbo.Cargo.cod_cargo = dbo.Funcionario.func_cargo where nome_func like '%"
					+ func.getNome_func()
					+ "%' and ativo_inativo = '"
					+ func.getAtivo_inativo() + "'";*/
		// System.out.println(sql);

		DadosFunc = PedidoVendaRegistroDAO.ListarVenda(pvr, columnName, getterName);

		for (PedidoVendaRegistroBean f2 : DadosFunc) {

			Dados.add(new Object[] { f2.getId_PedidoVendaRegistro(), f2.getDataVenda(),f2.getValor_Total(),
					f2.getNomeFuncVenda(), f2.getNomeCliVenda()});

		}

		TableModel modelo = new TableModel(Dados, Colunas);
		table.setModel(modelo);

	} 


}


