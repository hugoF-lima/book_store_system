package telas;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;

import acessoDadosBeans.ClienteBean;
import acessoDadosBeans.FuncionarioBean;
import acessoDadosBeans.PagamentoFormaBean;
import acessoDadosBeans.PagamentoVendaBean;
import acessoDadosBeans.PedidoVendaRegistroBean;
import acessoDadosBeans.ProdutoLivroBean;

import dataAcessObjects.PagamentoFormaDAO;
import dataAcessObjects.PedidoVendaRegistroDAO;
import dataAcessObjects.ProdutoLivroDAO;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Pedido_Info extends JDialog {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	public static FuncionarioBean func;
	public static ClienteBean cli;
	ProdutoLivroBean pl = new ProdutoLivroBean();
	ArrayList<ProdutoLivroBean> ListaProdutos = new ArrayList<ProdutoLivroBean>();
	
	//--
	JPanel PedidoPanel;
	static JLabel lblFuncionarioLogado;
	private JTextField textID_Livro_Prod;
	private JTextField textNome_Livro_Prod;
	private JTextField textPrice_Livro_Prod;
	private JTextField textItemValor_Livro_Prod;
	private JTextField textValorTotal;
	private JTextField textValorPago;
	private JTextField textValorTroco;
	JSpinner quantitySpinner_Livro_Prod;
	
//	int MaxQtdProd = 0;
//	Integer value = new Integer(0); 
//	Integer min = new Integer(0);
//	//Integer max = null;
//	Integer max = new Integer(MaxQtdProd); 
//	Integer step = new Integer(1); 
//	

	SpinnerNumberModel model;
	JButton AdicionarButton;
	static JTable PedidoTable;
	JButton RemoverItemButton;
	JButton RemoveAllButton;
	JComboBox<Object> PaymentOptionCombo;
	static JLabel lblchoosenCliente;
	//private JTextField textFieldPesquisaVenda;
	JButton FinalizarCompraButton;
	
	//--
	final JPanel pedidoRelatorio = new JPanel();
	static JTable PedidoItemTable;
	static PedidoVendaRegistroBean pvr = new PedidoVendaRegistroBean();
	JLabel lblValorTotal;
	JLabel lblFuncWhoSold;
	JLabel lblCliWhoMade;
	JButton VerBoletoButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Pedido_Info dialog = new Pedido_Info();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Pedido_Info() {
		setBounds(100, 100, 988, 536);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		PedidoPanel = new JPanel();
		getContentPane().add(PedidoPanel, "name_708121240234816");
		PedidoPanel.setLayout(null);
		pedidoRelatorio.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pedidoRelatorio);
		pedidoRelatorio.setLayout(null);
		
		/**
		 * Labels.
		 */
		JLabel lbl_Prod_Livro = new JLabel();
		lbl_Prod_Livro.setText("C\u00F3d. Produto");
		lbl_Prod_Livro.setFont(new Font("Gabriola", Font.PLAIN, 20));
		lbl_Prod_Livro.setBounds(70, 26, 126, 20);
		PedidoPanel.add(lbl_Prod_Livro);

		JLabel lblNomeLivro_Prod = new JLabel("Nome:");
		lblNomeLivro_Prod.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNomeLivro_Prod.setBounds(259, 26, 156, 20);
		PedidoPanel.add(lblNomeLivro_Prod);

		JLabel lblPrecoLivro_Prod = new JLabel("Pre\u00E7o:");
		lblPrecoLivro_Prod.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblPrecoLivro_Prod.setBounds(70, 88, 102, 20);
		PedidoPanel.add(lblPrecoLivro_Prod);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblQuantidade.setBounds(259, 88, 116, 20);
		PedidoPanel.add(lblQuantidade);

		JLabel lblValorDoItem = new JLabel("Valor do Item:");
		lblValorDoItem.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblValorDoItem.setBounds(70, 153, 126, 14);
		PedidoPanel.add(lblValorDoItem);

		/**
		 * TextFields.
		 */

		textID_Livro_Prod = new JTextField();
		textID_Livro_Prod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				pesquisarProdLivro();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarProdLivro();
			}
		});
		textID_Livro_Prod.setBounds(70, 53, 86, 20);
		PedidoPanel.add(textID_Livro_Prod);
		textID_Livro_Prod.setColumns(10);

		textNome_Livro_Prod = new JTextField();
		textNome_Livro_Prod.setEnabled(false);
		textNome_Livro_Prod.setBounds(259, 53, 139, 20);
		PedidoPanel.add(textNome_Livro_Prod);
		textNome_Livro_Prod.setColumns(10);

		textPrice_Livro_Prod = new JTextField();
		textPrice_Livro_Prod.setEnabled(false);
		textPrice_Livro_Prod.setBounds(70, 122, 86, 20);
		PedidoPanel.add(textPrice_Livro_Prod);
		textPrice_Livro_Prod.setColumns(10);

		textItemValor_Livro_Prod = new JTextField();
		textItemValor_Livro_Prod.setBounds(70, 179, 86, 20);
		PedidoPanel.add(textItemValor_Livro_Prod);
		textItemValor_Livro_Prod.setColumns(10);

		/**
		 * Spinners.
		 */

		
		Integer value = new Integer(0); 
		Integer min = new Integer(0);
		Integer max = new Integer(100); 
		Integer step = new Integer(1); 
		model = new SpinnerNumberModel(value, min, max, step); 

		//int fifty = model.getNumber().intValue(); 

		quantitySpinner_Livro_Prod = new JSpinner(model);
		quantitySpinner_Livro_Prod.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcularItemValorProd();

				if(!quantitySpinner_Livro_Prod.getValue().equals(0)){
					AdicionarButton.setEnabled(true);
				}else{
					AdicionarButton.setEnabled(false);
				}
			}
		});
		quantitySpinner_Livro_Prod.setEnabled(false);
		quantitySpinner_Livro_Prod.setBounds(259, 122, 86, 20);
		PedidoPanel.add(quantitySpinner_Livro_Prod);

		/**
		 * Buttons.
		 */

		AdicionarButton = new JButton("Adicionar");
		AdicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoVendaRegistroDAO.addProdutoTable(PedidoTable, pl, ListaProdutos);
				calcularTotal();
				limparProdInsercao();
				pl = new ProdutoLivroBean();


			}
		});
		AdicionarButton.setEnabled(false);
		AdicionarButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		AdicionarButton.setBounds(345, 179, 102, 28);
		PedidoPanel.add(AdicionarButton);

		//------------------Separator------------------------------

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(483, 6, 10, 201);
		PedidoPanel.add(separator);

		//------------------Separator------------------------------

		/**
		 * Labels.
		 */

		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento:");
		lblFormaDePagamento.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFormaDePagamento.setBounds(541, 26, 179, 20);
		PedidoPanel.add(lblFormaDePagamento);

		lblValorTotal = new JLabel("Valor total:");
		lblValorTotal.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblValorTotal.setBounds(541, 88, 91, 20);
		PedidoPanel.add(lblValorTotal);

		JLabel lblValorPago = new JLabel("Valor Pago:");
		lblValorPago.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblValorPago.setBounds(773, 26, 102, 20);
		PedidoPanel.add(lblValorPago);

		JLabel lblValorDoTroco = new JLabel("Valor do Troco:");
		lblValorDoTroco.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblValorDoTroco.setBounds(773, 84, 133, 20);
		PedidoPanel.add(lblValorDoTroco);

		/**
		 * TextFields.
		 */

		textValorTotal = new JTextField();
		textValorTotal.setBounds(541, 122, 107, 20);
		PedidoPanel.add(textValorTotal);
		textValorTotal.setColumns(10);

		textValorPago = new JTextField();
		textValorPago.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				calcularValorTroco();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				calcularValorTroco();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				calcularValorTroco();
			}
		});
		textValorPago.setBounds(773, 53, 86, 20);
		PedidoPanel.add(textValorPago);
		textValorPago.setColumns(10);

		textValorTroco = new JTextField();
		textValorTroco.setBounds(773, 122, 86, 20);
		PedidoPanel.add(textValorTroco);
		textValorTroco.setColumns(10);

		/**
		 * ComboBoxes.
		 */

		PaymentOptionCombo = new JComboBox<Object>();
		PaymentOptionCombo.setBounds(541, 53, 107, 20);
		PedidoPanel.add(PaymentOptionCombo);
		carregarComboPagtoForma();
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(53, 229, 882, 9);
		PedidoPanel.add(separator_1);

		/*PaymentOptionCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if(PaymentOptionCombo.getSelectedItem().toString().equals("Selecione...")){

					id_forma_pagamento = 0;
				}else if(PaymentOptionCombo.getSelectedItem().toString().equals("Dinheiro")){

					id_forma_pagamento = 1;
				}

			}

		});*/

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCliente.setBounds(116, 249, 79, 28);
		PedidoPanel.add(lblCliente);
		
		JLabel lblFuncionario = new JLabel("Funcionario:");
		lblFuncionario.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFuncionario.setBounds(116, 439, 111, 23);
		PedidoPanel.add(lblFuncionario);

		lblFuncionarioLogado = new JLabel("");
		lblFuncionarioLogado.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFuncionarioLogado.setBounds(219, 439, 156, 23);
		PedidoPanel.add(lblFuncionarioLogado);

		FinalizarCompraButton = new JButton("Finalizar Compra");
		FinalizarCompraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoVendaRegistroBean pvr = new PedidoVendaRegistroBean();
				PagamentoVendaBean vendaPagto = new PagamentoVendaBean();

				//ValorDinheiro
				float valorPagamento = Float.parseFloat(Pedido_Info.this.textValorPago.getText());
				float valorTroco = Float.parseFloat(Pedido_Info.this.textValorTroco.getText());
				if(valorPagamento != 0){

					vendaPagto.setId_forma_pagamento_fk(PaymentOptionCombo.getSelectedIndex());
					vendaPagto.setValor_pago(valorPagamento);
					vendaPagto.setValor_troco(valorTroco);
					//essa parte obtem os valores de func, cli que na dao são pegas o Id e logo depois, ListaProdutos
					pvr.setFunc(func);
					pvr.setCli(cli);
					pvr.setPedidoVendaItens(ListaProdutos);
					pvr.setValor_Total(obterTotal());
					pvr.setPagtoVenda(vendaPagto);
					PedidoVendaRegistroDAO.RealizarVenda(pvr);

					SystemTabs.atualizarLivroTable();
					SystemTabs.atualizarFornecTable();
					SystemTabs.atualizarFuncTable();
					SystemTabs.LoadVendasTable();
				
					LoadTablePedido();
					//Continuar daqui, pensar acerca de colocar tudo numa tela principal, ou manter os internal frames

					textValorTroco.setText("");
					textValorPago.setText("");
					textValorTotal.setText("");
					PaymentOptionCombo.setSelectedIndex(0);
				}else{

				}
			}
		});
		FinalizarCompraButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		FinalizarCompraButton.setBounds(750, 439, 156, 32);
		PedidoPanel.add(FinalizarCompraButton);

		PedidoTable = new JTable();
		PedidoTable.setBounds(50, 50, 50, 50);


		JScrollPane PedidoScroll = new JScrollPane();
		PedidoScroll.setBounds(34, 283, 867, 145);
		PedidoPanel.add(PedidoScroll);
		PedidoScroll.setViewportView(PedidoTable);

		//LoadItemPedidoTable();

		RemoverItemButton = new JButton("Remover Item");
		RemoverItemButton.setVisible(false);
		RemoverItemButton.setBounds(521, 249, 111, 23);
		PedidoPanel.add(RemoverItemButton);

		RemoveAllButton = new JButton("Remover Todos");
		RemoveAllButton.setVisible(false);
		RemoveAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		/*RemoveAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*PedidoBean pb = new PedidoBean();

				int rowSelectedPb = PedidoTable.getSelectedRow();
				int rowZeroIntValuePb = Integer.parseInt(PedidoTable.getValueAt(rowSelectedPb, 0).toString());*/

		/*PedidoVendaRegistroDAO.DeletarAllItemPedido();
				LoadItemPedidoTable();
			}
		});*/
		RemoveAllButton.setBounds(660, 249, 111, 23);
		PedidoPanel.add(RemoveAllButton);

		lblchoosenCliente = new JLabel("");
		lblchoosenCliente.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblchoosenCliente.setBounds(189, 249, 156, 23);
		PedidoPanel.add(lblchoosenCliente);


		LoadTablePedido();
		
		
		//--relatorioPedido
		JScrollPane VendaDetailsScroll = new JScrollPane();
		VendaDetailsScroll.setBounds(53, 164, 864, 149);
		pedidoRelatorio.add(VendaDetailsScroll);
		
		PedidoItemTable = new JTable();
		PedidoItemTable.setBounds(246, 104, 1, 1);
		VendaDetailsScroll.setViewportView(PedidoItemTable);
		
		JLabel lblTotalValue = new JLabel("Valor total da Compra:");
		lblTotalValue.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblTotalValue.setBounds(455, 345, 184, 22);
		pedidoRelatorio.add(lblTotalValue);
		
		JLabel lblCli = new JLabel("Cliente:");
		lblCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCli.setBounds(158, 63, 66, 22);
		pedidoRelatorio.add(lblCli);
		
		JLabel lblFunc = new JLabel("Funcion\u00E1rio:");
		lblFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFunc.setBounds(124, 109, 100, 22);
		pedidoRelatorio.add(lblFunc);
		
		lblCliWhoMade = new JLabel("");
		lblCliWhoMade.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCliWhoMade.setBounds(270, 65, 369, 19);
		pedidoRelatorio.add(lblCliWhoMade);
		
		lblFuncWhoSold = new JLabel("");
		lblFuncWhoSold.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFuncWhoSold.setBounds(270, 109, 369, 19);
		pedidoRelatorio.add(lblFuncWhoSold);
		
		lblValorTotal = new JLabel();
		lblValorTotal.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblValorTotal.setBounds(649, 345, 111, 22);
		pedidoRelatorio.add(lblValorTotal);
		
		JButton VerBoletoButton = new JButton("Ver Boleto");
		VerBoletoButton.setBounds(695, 424, 89, 23);
		pedidoRelatorio.add(VerBoletoButton);
		
		
	}
	
	private void calcularItemValorProd(){

		
		
		float preco, resultado;
		int qtd;

		int See_If_Id_Is_Not_Zero = 0;

		int QtdSpinnerConverted = (Integer)quantitySpinner_Livro_Prod.getValue();

		if(quantitySpinner_Livro_Prod.getValue().equals(0) || equals ("")){
			qtd = 0;
		}else{

			qtd = QtdSpinnerConverted;

			if(qtd > pl.getQtd_livro_prod()){

				qtd = pl.getQtd_livro_prod();
				
				quantitySpinner_Livro_Prod.setValue(String.valueOf(qtd));
				
				if(!(pl.getId_fornec_fk() ==  See_If_Id_Is_Not_Zero)){

				}else{
					JOptionPane.showMessageDialog(null, "Esse produto não possui fornecedor");
				}

			}

			pl.setQtd_venda_livro_prod(qtd);

		}
		if(textPrice_Livro_Prod.getText().isEmpty()){

		}else{
			preco = Float.parseFloat(textPrice_Livro_Prod.getText());

			resultado = qtd*preco;

			textItemValor_Livro_Prod.setText(String.valueOf(resultado));
			//    	valorTotalPagamento.setText(String.valueOf(resultado));
			//    	valor_total_field.setText(String.valueOf(resultado));
		}

	}
	
	private float obterTotal(){

		float resultado = 0;

		for(ProdutoLivroBean pr : ListaProdutos){

			resultado += pr.getQtd_venda_livro_prod() * pr.getPreco_livro_prod();

		}

		return resultado;

	}
	
	public void limparProdInsercao(){
		textID_Livro_Prod.setText("");
		textNome_Livro_Prod.setText("");
		textPrice_Livro_Prod.setText("");
		quantitySpinner_Livro_Prod.setValue(0);
		textItemValor_Livro_Prod.setText("");
	}
	
	private void calcularValorTroco(){

		try{

			float valorPagamento = Float.parseFloat(textValorPago.getText());
			float valorTotal = Float.parseFloat(textValorTotal.getText());

			if(valorPagamento > valorTotal){

				float troco = valorPagamento - valorTotal;

				textValorTroco.setText(String.valueOf(troco));

			}else{
				textValorTroco.setText("");

			}

		}catch(NumberFormatException e){

		}

	}
	
	private void calcularTotal(){

		float resultado = 0;


		for(ProdutoLivroBean pb : ListaProdutos){

			//resultado += pb.getQtd_prod() * pb.getPreco_prod();

			resultado += pb.getQtd_venda_livro_prod() * pb.getPreco_livro_prod();


			/*int QtdConvert = Integer.parseInt(textPrice_Prod.getText());
		float Total_Item_Pedido_Convert = Float.parseFloat(textItemValor_Prod.getText());

		resultado += pb.getQtdItem_Pedido(QtdConvert) * pb.getTotal_Item_Pedido(Total_Item_Pedido_Convert);
			 */
		}
		if(resultado != 0){
			textValorTotal.setText(String.valueOf(resultado));

		}
	}
	
	private void pesquisarProdLivro(){

		if (!textID_Livro_Prod.getText().trim().equals("")) {
			
			//quantitySpinner_Livro_Prod.setModel(model);
			int Id = Integer.parseInt(textID_Livro_Prod.getText()); 

			pl = ProdutoLivroDAO.buscarLivroProduto(Id);

			if(pl.getPreco_livro_prod() != 0.0){

				textNome_Livro_Prod.setText(pl.getNome_livro_prod());
				textPrice_Livro_Prod.setText(String.valueOf(pl.getPreco_livro_prod()));


			}else{

				pl.setId_fornec_fk(0);

				textNome_Livro_Prod.setText("");
				textPrice_Livro_Prod.setText("");
			}

			quantitySpinner_Livro_Prod.setEnabled(true);

		}else{
			quantitySpinner_Livro_Prod.setValue(0);
			//quantitySpinner_Livro_Prod.setModel(emptyModel);
			quantitySpinner_Livro_Prod.setEnabled(false);
			textNome_Livro_Prod.setText("");
			textPrice_Livro_Prod.setText("");

			//pr = new ProdutoBean();
		}

	}
	
	private void carregarComboPagtoForma() {
		PagamentoFormaDAO dao = new PagamentoFormaDAO();

		List<PagamentoFormaBean> lista = dao.buscarFormaPagamento();
		PaymentOptionCombo.removeAll();
		PaymentOptionCombo.addItem("Selecione ...");
		for (PagamentoFormaBean pagto : lista) {
			PaymentOptionCombo.addItem(pagto);
		}
	}
	
	public static void LoadTablePedido(){
		PedidoVendaRegistroDAO.carregarPedidoTable(PedidoTable);
	}

	
	public static void LoadVendaDetails(PedidoVendaRegistroBean pvr){
		PedidoVendaRegistroDAO.carregarPedidoItemTable(PedidoItemTable, pvr);
	}
	
	public static void setFuncionario(FuncionarioBean f) {
		func = f;
		lblFuncionarioLogado.setText(func.getNome_func());
	}

	public static void setCliente(ClienteBean c){
		cli = c;
		lblchoosenCliente.setText(cli.getNome_cli());
	}
}
