package telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utilities.PesquisaDados;
import acessoDadosBeans.CargoBean;
import acessoDadosBeans.ClienteBean;
import acessoDadosBeans.ContatoClienteBean;
import acessoDadosBeans.FornecedorBean;
import acessoDadosBeans.FuncionarioBean;
import acessoDadosBeans.PedidoVendaRegistroBean;
import acessoDadosBeans.ProdutoLivroBean;


import dataAcessObjects.CargoDAO;
import dataAcessObjects.ClienteDAO;
import dataAcessObjects.ContatoOnlineDAO;
import dataAcessObjects.FornecedorDAO;
import dataAcessObjects.FuncionarioDAO;
import dataAcessObjects.PedidoVendaRegistroDAO;
import dataAcessObjects.ProdutoLivroDAO;

import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

public class SystemTabs extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	//Beans initialization
	public static ClienteBean cli;
	public static FuncionarioBean func;
	public static CargoBean cargo = new CargoBean();
	PedidoVendaRegistroBean pvr = new PedidoVendaRegistroBean();
	ClienteBean c = new ClienteBean();
	FuncionarioBean f = new FuncionarioBean();
	FornecedorBean p = new FornecedorBean();
	ProdutoLivroBean pl = new ProdutoLivroBean();

	//TabbedPane initialization
	static JTabbedPane SystemTabPanel;

	//JPanels initialization
	private JPanel VendasPanel;
	private JPanel ClientePanel;
	private JPanel FuncionarioPanel;
	private JPanel CargoPanel;
	private JPanel FornecedorPanel;
	private JPanel LivroPanel;
	private JPanel ContatoOnlinePanel;

	//VendasPanel component initialization
	private static JTable VendasTable;
	private JPanel SubVendasPanel;
	private JButton RelatorioButtonVenda;

	//ClientePanel component initialization
	private ButtonGroup masc_fem_cli;
	JComboBox<Object> statusComboCli;

	JPanel SubClientePanel;
	JTextField formattedTextPesquisa_Cli;
	private JButton RelatorioButtonCli;
	static JTable clienteCadastro;
	private JPopupMenu popupClienteCadastroOptions;
	private JMenuItem RelatorioOptionCli;
	private JMenuItem novoPedidoOptionCli;
	private JMenuItem AtivarClienteOption;
	private JMenuItem DesativarClienteOption;
	private ButtonGroup masc_fem_cli_pesquisa;

	//FuncPanel component initialization

	JTextField formattedTextPesquisaFunc;


	private JComboBox<Object> statusCombo;
	JPopupMenu popupFuncionarioCadastroOptions;
	private JMenuItem DetalhesRelatorioOptionFunc;
	JMenuItem RecessoFuncOption;
	JMenuItem DesativarFuncOption;
	private JButton RelatorioButtonFunc;
	static JTable funcionarioCadastro;
	
	//CargoPanel component initializaiton

	JPanel SubCargoPanel;
	JFormattedTextField formattedTextPesquisaCargo;
	static JTable cargoCadastro;
	JPopupMenu CargoOptionsMenu;
	JMenuItem AtivarCargoOption;
	JMenuItem DesativarCargoOption;
	//

	JPopupMenu FornecOptionsMenu;
	JPanel SubFornecPanel_2;
	JTextField formattedTextPesquisa_Fornec;
	private JButton DetalhesButtonFornec;
	static JTable fornecCadastro;
	private JLabel lblFiltrarPorStatus;
	JComboBox<Object> statusComboFornec;
	private JButton btnNovoCliente;
	private JButton NewFuncButton;
	private JMenuItem AtivarFuncOption;
	private JButton NewFornecButton;
	private JSeparator separator_5;
	JButton NovoCargoButton;
	JMenuItem DesativarFornecOption;
	JMenuItem AtivarFornecOption;


	JComboBox<Object> statusComboCargo;

	//LivroPanel

	JComboBox<Object> statusComboLivro;
	JTextField formattedPesquisaLivro;
	JPanel Sub_Livro_Panel;
	static JTable LivroCadastro;
	JButton NewBookButton;
	private JButton btnVerificarVendasBalco;
	private JPanel SubContatoOnlinePanel;
	private JTextField textField;
	private static JTable ContatoOnlineTable;

	//Contato
	JScrollPane ContatoScroll;
	private JButton btnAtualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemTabs frame = new SystemTabs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public SystemTabs() {
		setBounds(196, 0, 1043, 620);
		getContentPane().setLayout(null);


		SystemTabPanel = new JTabbedPane(JTabbedPane.TOP);
		/*CadastroConsultaTabPanel.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
		  protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
		  });*/
		SystemTabPanel.setBounds(0, 0, 1037, 592);
		getContentPane().add(SystemTabPanel);

		/**
		 * Some other utilities.
		 */
		
		
		VendasPanel = new JPanel();
		SystemTabPanel.addTab("Vendas", null, VendasPanel, null);
		VendasPanel.setLayout(null);

		SubVendasPanel = new JPanel();
		SubVendasPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(VendasTable.getSelectedRowCount() >0){
					VendasTable.clearSelection();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(VendasTable.getSelectedRowCount() >0){
					VendasTable.clearSelection();
				}

			}
		});
		SubVendasPanel.setBounds(24, 42, 957, 471);
		SubVendasPanel.setBackground(new Color(135,206,250,100));
		VendasPanel.add(SubVendasPanel);
		SubVendasPanel.setLayout(null);

		JButton NovoPedidoButton = new JButton("Nova Venda/Pedido");
		NovoPedidoButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Pedido_Info pi = new Pedido_Info();
				pi.setTitle("Nova Venda");
				pi.setVisible(true);
				pi.setFuncionario(func);
				pi.setCliente(cli);
			}
		});
		NovoPedidoButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		NovoPedidoButton.setBounds(749, 9, 168, 28);
		SubVendasPanel.add(NovoPedidoButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 58, 937, 10);
		SubVendasPanel.add(separator);

		JScrollPane VendasScroll = new JScrollPane();
		VendasScroll.setBounds(36, 125, 897, 253);
		SubVendasPanel.add(VendasScroll);

		VendasTable = new JTable();
		VendasTable.setBounds(50, 50, 50, 50);
		VendasScroll.setViewportView(VendasTable);

		VendasTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
					boolean rowsAreSelected = VendasTable.getSelectedRowCount() > 0;


					RelatorioButtonVenda.setEnabled(rowsAreSelected);
				}
			}
		});

		RelatorioButtonVenda = new JButton("Relat\u00F3rio");
		RelatorioButtonVenda.setFont(new Font("Gabriola", Font.PLAIN, 20));
		RelatorioButtonVenda.setEnabled(false);
		RelatorioButtonVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RelatorioVendaAction(e);
			}
		});
		RelatorioButtonVenda.setBounds(36, 403, 96, 28);
		SubVendasPanel.add(RelatorioButtonVenda);
		LoadVendasTable();


		//-------------\/------------ClientePanel elements-----------\/------------------------

		ClientePanel = new JPanel();
		SystemTabPanel.addTab("Clientes", null, ClientePanel, null);
		ClientePanel.setPreferredSize(new Dimension(999, 901));
		ClientePanel.setOpaque(false);
		ClientePanel.setLayout(null);

		masc_fem_cli = new ButtonGroup();
		masc_fem_cli.clearSelection();


		//-------------/\------------SubClientePanel elements-----------/\------------------------

		//-------------\/------------SubClientePanel_2 elements-----------\/------------------------

		SubClientePanel = new JPanel();
		SubClientePanel.setBounds(24, 42, 957, 471);
		ClientePanel.add(SubClientePanel);
		SubClientePanel.setBackground(new Color(135,206,250,100));
		SubClientePanel.setLayout(null);

		SubClientePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(clienteCadastro.getSelectedRowCount() >0){
					clienteCadastro.clearSelection();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(clienteCadastro.getSelectedRowCount() >0){
					clienteCadastro.clearSelection();
				}
			}
		});


		/**
		 * Labels.
		 */

		JLabel lblPesquisaCli = new JLabel("Pesquisa (Nome):");
		lblPesquisaCli.setBounds(251, 79, 170, 23);
		SubClientePanel.add(lblPesquisaCli);
		lblPesquisaCli.setFont(new Font("Gabriola", Font.PLAIN, 25));

		JLabel lblFiltrarPorStatusCli = new JLabel("Filtrar por Status:");
		lblFiltrarPorStatusCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFiltrarPorStatusCli.setBounds(547, 402, 153, 28);
		SubClientePanel.add(lblFiltrarPorStatusCli);


		/**
		 * FormattedFields.
		 */

		formattedTextPesquisa_Cli = new JTextField();
		formattedTextPesquisa_Cli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PesquisaDados.PesqNomeCli(c, statusComboCli, clienteCadastro, formattedTextPesquisa_Cli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				PesquisaDados.PesqNomeCli(c, statusComboCli, clienteCadastro, formattedTextPesquisa_Cli);
			}
		});
		formattedTextPesquisa_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		formattedTextPesquisa_Cli.setBounds(431, 81, 186, 20);
		SubClientePanel.add(formattedTextPesquisa_Cli);


		masc_fem_cli_pesquisa = new ButtonGroup();
		masc_fem_cli_pesquisa.clearSelection();

		/**
		 * ComboBoxes.
		 */

		statusComboCli = new JComboBox<Object>();
		statusComboCli.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "Ativo", "Inativo"}));
		statusComboCli.setBounds(720, 408, 125, 18);
		SubClientePanel.add(statusComboCli);

		statusComboCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(statusComboCli.getSelectedItem().toString().equals("Selecione...")){
					ClienteDAO.LoadClienteTable(clienteCadastro);
				}else if(statusComboCli.getSelectedItem().toString().equals("Ativo")){
					ClienteDAO.LoadAtivoClienteTable(clienteCadastro);
				}else if(statusComboCli.getSelectedItem().toString().equals("Inativo")){
					ClienteDAO.LoadInativoClienteTable(clienteCadastro);
				}
			}
		});

		/**
		 * ScrollPanes, Tables and PopupMenus.
		 */



		JScrollPane clienteScroll = new JScrollPane();
		clienteScroll.setBounds(36, 125, 897, 253);
		clienteScroll.setOpaque(false);
		SubClientePanel.add(clienteScroll);

		clienteCadastro = new JTable();
		clienteCadastro.setBounds(50, 50, 50, 50);
		clienteCadastro.setOpaque(false);
		clienteCadastro.setShowGrid(true);
		clienteCadastro.getSelectionModel().clearSelection();
		clienteScroll.setViewportView(clienteCadastro);

		clienteCadastro.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
					boolean rowsAreSelected = clienteCadastro.getSelectedRowCount() > 0;


					RelatorioButtonCli.setEnabled(rowsAreSelected);
				}
			}
		});

		clienteCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {

					// get row that pointer is over
					int row = clienteCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (clienteCadastro.isRowSelected(row)) {
						popupClienteCadastroOptions.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {

					// get row that pointer is over
					int row = clienteCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (clienteCadastro.isRowSelected(row)) {
						popupClienteCadastroOptions.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					EditarCliMouseEvent(e);
				}
			}
		});

		popupClienteCadastroOptions = new JPopupMenu();
		addPopup(clienteScroll, popupClienteCadastroOptions);
		popupClienteCadastroOptions.setBounds(0, 0, 200, 50);

		novoPedidoOptionCli = new JMenuItem("Novo Pedido");
		novoPedidoOptionCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteBean c = new ClienteBean();

				int rowSelectedCli = clienteCadastro.getSelectedRow();
				int rowZeroIntValueCli = Integer.parseInt(clienteCadastro.getValueAt(rowSelectedCli, 0).toString());


				c.setId_cli(rowZeroIntValueCli);

				//c = ClienteDAO.retornarCliente(c);

				//cv.setVisible(true);
				Pedido_Info pi = new Pedido_Info();

				if(ClienteDAO.LogCliPedido(c) == true){
					if(!c.getAtivo_inativo_status().equals("Ativo")){

						int dialogButton = JOptionPane.showConfirmDialog(null, "O cliente se encontra Inativo. Para atribuir uma venda  a \n esse cliente, é necesário que ele esteja Ativo \n Deseja Ativar o Cliente?", null, 0);  

						if (dialogButton == JOptionPane.YES_OPTION) {  
							c.setId_cli(rowZeroIntValueCli);
							ClienteDAO.AtivarCliente(c);
							atualizarClienteTable();
							TelaPrincipal.setClienteBalcao(c);
							Pedido_Info.setCliente(c);
							Pedido_Info.setFuncionario(func);
							pi.setVisible(true);
							//return;  
						} else if(dialogButton == JOptionPane.NO_OPTION){  
							remove(dialogButton);

						}
					}else{
						TelaPrincipal.setClienteBalcao(c);
						Pedido_Info.setCliente(c);
						Pedido_Info.setFuncionario(func);
						pi.setVisible(true);
					}
				}
			}
		});
		popupClienteCadastroOptions.add(novoPedidoOptionCli);

		JSeparator separatorPop = new JSeparator();
		popupClienteCadastroOptions.add(separatorPop);

		AtivarClienteOption = new JMenuItem("Ativar Cliente");
		AtivarClienteOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtivarCliActionPerformed(e);
			}
		});
		popupClienteCadastroOptions.add(AtivarClienteOption);

		DesativarClienteOption = new JMenuItem("Desativar Cliente");
		popupClienteCadastroOptions.add(DesativarClienteOption);
		DesativarClienteOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesativarCliActionPerformed(e);
			}
		});

		JSeparator separator_7 = new JSeparator();
		popupClienteCadastroOptions.add(separator_7);

		RelatorioOptionCli = new JMenuItem("Exibir Relat\u00F3rio");
		popupClienteCadastroOptions.add(RelatorioOptionCli);

		RelatorioOptionCli.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				RelatorioCliActionPerformed(arg0);

			}});

		RelatorioButtonCli = new JButton("Relat\u00F3rio");
		RelatorioButtonCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioCliActionPerformed(e);
			}
		});
		RelatorioButtonCli.setEnabled(false);
		RelatorioButtonCli.setFont(new Font("Gabriola", Font.PLAIN, 20));
		RelatorioButtonCli.setBounds(36, 403, 96, 28);
		SubClientePanel.add(RelatorioButtonCli);



		ClienteDAO.LoadClienteTable(clienteCadastro);

		btnNovoCliente = new JButton("Novo(a) Cliente");
		btnNovoCliente.setFont(new Font("Gabriola", Font.PLAIN, 20));
		btnNovoCliente.setBounds(781, 11, 136, 28);
		SubClientePanel.add(btnNovoCliente);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 60, 937, 10);
		SubClientePanel.add(separator_1);

		btnVerificarVendasBalco = new JButton("Verificar Cliente Balc\u00E3o");
		btnVerificarVendasBalco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioCliBalcaoActionPerformed(e);
			}
		});
		btnVerificarVendasBalco.setFont(new Font("Gabriola", Font.PLAIN, 20));
		btnVerificarVendasBalco.setBounds(148, 402, 199, 27);
		SubClientePanel.add(btnVerificarVendasBalco);

		btnNovoCliente.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				ClienteCadastro_Info ci = new ClienteCadastro_Info();
				ci.setVisible(true);
				ci.setTitle("Novo Cliente");
				ci.CadastroClientePanel.setVisible(true);
				ci.setFuncionario(func);
				ci.verifyFields();
			}
		});


		//------------------------------------------------------------------------------------

		FuncionarioPanel = new JPanel();
		SystemTabPanel.addTab("Funcionários", null, FuncionarioPanel, null);
		FuncionarioPanel.setPreferredSize(new Dimension(999, 901));
		FuncionarioPanel.setOpaque(false);
		FuncionarioPanel.setLayout(null);

		//-------------------------SubFuncionarioPanel_2 elements---------------------------------------------------------------------------


		JPanel SubFuncionarioPanel_2 = new JPanel();
		SubFuncionarioPanel_2.setBounds(24, 42, 957, 471);
		SubFuncionarioPanel_2.setBackground(new Color(135,206,250,100));
		FuncionarioPanel.add(SubFuncionarioPanel_2);
		SubFuncionarioPanel_2.setLayout(null);

		SubFuncionarioPanel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(funcionarioCadastro.getSelectedRowCount() >0){
					funcionarioCadastro.clearSelection();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(funcionarioCadastro.getSelectedRowCount() >0){
					funcionarioCadastro.clearSelection();
				}
			}
		});

		/**
		 * Labels.
		 */
		JLabel lblPesquisaFunc = new JLabel("Pesquisa(Nome):");
		lblPesquisaFunc.setBounds(230, 79, 147, 23);
		SubFuncionarioPanel_2.add(lblPesquisaFunc);
		lblPesquisaFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));

		lblFiltrarPorStatus = new JLabel("Filtrar por Status:");
		lblFiltrarPorStatus.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFiltrarPorStatus.setBounds(547, 402, 153, 28);
		SubFuncionarioPanel_2.add(lblFiltrarPorStatus);

		/**
		 * Formatted Fields.
		 */

		formattedTextPesquisaFunc = new JTextField();
		formattedTextPesquisaFunc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PesquisaDados.PesqNomeFunc(f, statusCombo, funcionarioCadastro, formattedTextPesquisaFunc);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				PesquisaDados.PesqNomeFunc(f, statusCombo, funcionarioCadastro, formattedTextPesquisaFunc);
			}
		});
		formattedTextPesquisaFunc.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		formattedTextPesquisaFunc.setBounds(378, 81, 186, 20);
		SubFuncionarioPanel_2.add(formattedTextPesquisaFunc);



		/**
		 * ComboBoxes.
		 */

		statusCombo = new JComboBox<Object>();
		statusCombo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		statusCombo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "Ativo", "Inativo", "em Recesso"}));
		statusCombo.setBounds(720, 408, 125, 18);
		SubFuncionarioPanel_2.add(statusCombo);

		statusCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(statusCombo.getSelectedItem().toString().equals("Selecione...")){
					FuncionarioDAO.LoadFuncionarioTable(funcionarioCadastro);
				}else if(statusCombo.getSelectedItem().toString().equals("Ativo")){
					FuncionarioDAO.LoadAtivoFuncionarioTable(funcionarioCadastro);
				}else if(statusCombo.getSelectedItem().toString().equals("Inativo")){
					FuncionarioDAO.LoadInativoFuncionarioTable(funcionarioCadastro);
				}else if(statusCombo.getSelectedItem().toString().equals("em Recesso")){
					FuncionarioDAO.LoadInativoVacationFuncionarioTable(funcionarioCadastro);
				}
			}
		});

		/**
		 * ScrollPanes and tables.
		 */

		JScrollPane FuncionarioScroll = new JScrollPane();
		FuncionarioScroll.setBounds(36, 125, 897, 253);
		SubFuncionarioPanel_2.add(FuncionarioScroll);

		funcionarioCadastro = new JTable();
		funcionarioCadastro.setBounds(50, 50, 50, 50);

		funcionarioCadastro.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
					boolean rowsAreSelected = funcionarioCadastro.getSelectedRowCount() > 0;

					RelatorioButtonFunc.setEnabled(rowsAreSelected);
				}
			}
		});
		FuncionarioScroll.setViewportView(funcionarioCadastro);

		funcionarioCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {

					int rowSelection = funcionarioCadastro.getSelectedRow();
					String FuncInfoStatus = funcionarioCadastro.getValueAt(rowSelection, 5).toString();
					System.out.println(FuncInfoStatus);

					boolean rowIsInactive = FuncInfoStatus.toString().equals("Inativo");
					boolean rowIsActive = FuncInfoStatus.toString().equals("Ativo");
					boolean rowIsRecessed = FuncInfoStatus.toString().equals("em Recesso");

					DesativarFuncOption.setEnabled(rowIsActive);
					AtivarFuncOption.setEnabled(rowIsInactive || rowIsRecessed);
					RecessoFuncOption.setEnabled(rowIsActive);

					// get row that pointer is over
					int row = funcionarioCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (funcionarioCadastro.isRowSelected(row)) {
						popupFuncionarioCadastroOptions.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {

					int rowSelection = funcionarioCadastro.getSelectedRow();
					String FuncInfoStatus = funcionarioCadastro.getValueAt(rowSelection, 5).toString();

					boolean rowIsInactive = FuncInfoStatus.toString().equals("Inativo");
					boolean rowIsActive = FuncInfoStatus.toString().equals("Ativo");
					boolean rowIsRecessed = FuncInfoStatus.toString().equals("em Recesso");

					DesativarFuncOption.setEnabled(rowIsActive);
					AtivarFuncOption.setEnabled(rowIsInactive || rowIsRecessed);
					RecessoFuncOption.setEnabled(rowIsActive);


					// get row that pointer is over
					int row = funcionarioCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (funcionarioCadastro.isRowSelected(row)) {
						popupFuncionarioCadastroOptions.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					EditarFuncActionPerformed(e);
				}
			}
		});

		popupFuncionarioCadastroOptions = new JPopupMenu();
		addPopup(FuncionarioScroll, popupFuncionarioCadastroOptions);
		popupFuncionarioCadastroOptions.setBounds(0, 0, 200, 50);

		AtivarFuncOption = new JMenuItem("Ativar Funcion\u00E1rio");
		popupFuncionarioCadastroOptions.add(AtivarFuncOption);

		AtivarFuncOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AtivarFuncActionPerformed(e);
			}

		});

		DesativarFuncOption = new JMenuItem("Desativar Funcion\u00E1rio");
		popupFuncionarioCadastroOptions.add(DesativarFuncOption);

		DesativarFuncOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DesativarActionFuncPerformed(e);
			}

		});

		RecessoFuncOption = new JMenuItem("Conceber Recessão");
		popupFuncionarioCadastroOptions.add(RecessoFuncOption);

		RecessoFuncOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RecessoActionFuncPerformed(e);
			}

		});

		JSeparator separator_3 = new JSeparator();
		popupFuncionarioCadastroOptions.add(separator_3);

		DetalhesRelatorioOptionFunc = new JMenuItem("Exibir Relat\u00F3rio");
		popupFuncionarioCadastroOptions.add(DetalhesRelatorioOptionFunc);

		DetalhesRelatorioOptionFunc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				RelatorioFuncActionPerformed(arg0);
			}
		});

		RelatorioButtonFunc = new JButton("Relat\u00F3rio");
		RelatorioButtonFunc.setFont(new Font("Gabriola", Font.PLAIN, 20));
		RelatorioButtonFunc.setEnabled(false);
		RelatorioButtonFunc.setBounds(36, 403, 96, 28);
		SubFuncionarioPanel_2.add(RelatorioButtonFunc);

		RelatorioButtonFunc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				RelatorioFuncActionPerformed(arg0);
			}
		});

		FuncionarioDAO.LoadFuncionarioTable(funcionarioCadastro);

		NewFuncButton = new JButton("Novo(a) Funcion\u00E1rio(a)");
		NewFuncButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		NewFuncButton.setBounds(720, 11, 197, 28);
		SubFuncionarioPanel_2.add(NewFuncButton);

		separator_5 = new JSeparator();
		separator_5.setBounds(10, 60, 937, 10);
		SubFuncionarioPanel_2.add(separator_5);
		NewFuncButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncCadastro_Info fci = new FuncCadastro_Info();
				fci.setVisible(true);
				fci.setTitle("Novo Funcionário");
				//fci.ClienteInfoTabPanel.setVisible(false);
				fci.CadastroFuncPanel.setVisible(true);
			}
		});

		//------------------------------------------------------------------------------------	   
		//--------------\/----------CargoPanel elements---------\/---------------------------------------------------    		
		CargoPanel = new JPanel();
		SystemTabPanel.addTab("Cargos", null, CargoPanel, null);
		CargoPanel.setLayout(null);

		SubCargoPanel = new JPanel();
		SubCargoPanel.setBounds(24, 42, 957, 471);
		CargoPanel.add(SubCargoPanel);
		SubCargoPanel.setBackground(new Color(135,206,250,100));
		SubCargoPanel.setLayout(null);

		SubCargoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(cargoCadastro.getSelectedRowCount() >0){
					cargoCadastro.clearSelection();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(cargoCadastro.getSelectedRowCount() >0){
					cargoCadastro.clearSelection();
				}
			}
		});

		/**
		 * Buttons.
		 */

		NovoCargoButton = new JButton("Novo Cargo");
		NovoCargoButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				CargoCadastro_Info CargCad = new CargoCadastro_Info();
				CargCad.setTitle("Novo Cargo");
				CargCad.setVisible(true);
				CargCad.CadastroCargoPanel.setVisible(true);
				CargCad.setFuncionario(func);
			}
		});
		NovoCargoButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		NovoCargoButton.setBounds(781, 11, 136, 28);
		SubCargoPanel.add(NovoCargoButton);

		//------------------------------------------------------------------------------------   		

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 60, 937, 10);
		SubCargoPanel.add(separator_2);

		//------------------------------------------------------------------------------------

		/**
		 * labels.
		 */

		JLabel lblPesquisaCargo = new JLabel("Pesquisa (Nome):");
		lblPesquisaCargo.setVisible(false);
		lblPesquisaCargo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblPesquisaCargo.setBounds(239, 81, 142, 23);
		SubCargoPanel.add(lblPesquisaCargo);

		JLabel lblFiltrarPorStatusCargo = new JLabel("Filtrar por Status:");
		lblFiltrarPorStatusCargo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFiltrarPorStatusCargo.setBounds(547, 402, 153, 28);
		SubCargoPanel.add(lblFiltrarPorStatusCargo);


		statusComboCargo = new JComboBox<Object>();
		statusComboCargo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		statusComboCargo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "Ativo", "Inativo"}));
		statusComboCargo.setBounds(720, 408, 125, 18);
		SubCargoPanel.add(statusComboCargo);

		statusComboCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(statusComboCargo.getSelectedItem().toString().equals("Selecione...")){
					CargoDAO.LoadCargoTable(cargoCadastro);
				}else if(statusComboCargo.getSelectedItem().toString().equals("Ativo")){
					CargoDAO.LoadAtivoCargoTable(cargoCadastro);
				}else if(statusComboCargo.getSelectedItem().toString().equals("Inativo")){
					CargoDAO.LoadInativoCargoTable(cargoCadastro);
				}
			}
		});


		/**
		 * formatted fields.
		 */

		formattedTextPesquisaCargo = new JFormattedTextField();
		formattedTextPesquisaCargo.setVisible(false);
		formattedTextPesquisaCargo.setBounds(406, 81, 186, 20);
		SubCargoPanel.add(formattedTextPesquisaCargo);


		/**
		 * ScrollPanes, tables and PopupMenus.
		 */

		JScrollPane cargoScroll = new JScrollPane();
		cargoScroll.setBounds(36, 125, 897, 253);
		cargoScroll.setOpaque(false);
		SubCargoPanel.add(cargoScroll);

		cargoCadastro = new JTable();
		cargoCadastro.setBounds(50, 50, 50, 50);
		cargoCadastro.setOpaque(false);
		cargoCadastro.setShowGrid(true);
		cargoCadastro.getSelectionModel().clearSelection();
		cargoScroll.setViewportView(cargoCadastro);
		//atualizarCargoTable();
		CargoDAO.LoadCargoTable(cargoCadastro);

		//		cargoCadastro.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		//		{
		//			@Override
		//			public void valueChanged(ListSelectionEvent e)
		//			{
		//				if (!e.getValueIsAdjusting())
		//				{
		//					boolean rowsAreSelected = cargoCadastro.getSelectedRowCount() > 0;
		//
		//					//EditarButtonCargo.setEnabled(rowsAreSelected);
		//					
		//				}
		//			}
		//		});

		cargoCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {

					int rowSelection = cargoCadastro.getSelectedRow();
					String CargoInfoStatus = cargoCadastro.getValueAt(rowSelection, 3).toString();

					boolean rowIsInactive = CargoInfoStatus.toString().equals("Inativo");
					boolean rowIsActive = CargoInfoStatus.toString().equals("Ativo");

					DesativarCargoOption.setEnabled(rowIsActive);
					AtivarCargoOption.setEnabled(rowIsInactive);

					// get row that pointer is over
					int row = cargoCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (cargoCadastro.isRowSelected(row)) {
						CargoOptionsMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {

					int rowSelection = cargoCadastro.getSelectedRow();
					String CargoInfoStatus = cargoCadastro.getValueAt(rowSelection, 3).toString();

					boolean rowIsInactive = CargoInfoStatus.toString().equals("Inativo");
					boolean rowIsActive = CargoInfoStatus.toString().equals("Ativo");

					DesativarCargoOption.setEnabled(rowIsActive);
					AtivarCargoOption.setEnabled(rowIsInactive);


					// get row that pointer is over
					int row = cargoCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (cargoCadastro.isRowSelected(row)) {
						CargoOptionsMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					EditarCargoMouseEvent(e);
				}
			}
		});

		CargoOptionsMenu = new JPopupMenu();
		addPopup(cargoScroll, CargoOptionsMenu);

		AtivarCargoOption = new JMenuItem("Ativar Cargo");
		AtivarCargoOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtivarCargoActionPerformed(e);
			}
		});
		CargoOptionsMenu.add(AtivarCargoOption);

		DesativarCargoOption = new JMenuItem("Desativar Cargo");
		DesativarCargoOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesativarCargoActionPerformed(e);
			}
		});
		CargoOptionsMenu.add(DesativarCargoOption);


		//--------------/\----------CargoPanel elements---------/\--------------------------------------------------- 		
		//------------------------------------------------------------------------------------	  


		FornecedorPanel = new JPanel();
		FornecedorPanel.setPreferredSize(new Dimension(999, 901));
		FornecedorPanel.setOpaque(false);
		FornecedorPanel.setLayout(null);
		SystemTabPanel.addTab("Fornecedores", null, FornecedorPanel, null);

		//-------------------------SubFornecedorPanel_2 elements-----------------------------------

		SubFornecPanel_2 = new JPanel();
		SubFornecPanel_2.setBounds(24, 42, 957, 471);
		SubFornecPanel_2.setBackground(new Color(135,206,250,100));
		FornecedorPanel.add(SubFornecPanel_2);


		SubFornecPanel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(fornecCadastro.getSelectedRowCount() >0){
					fornecCadastro.clearSelection();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(fornecCadastro.getSelectedRowCount() >0){
					fornecCadastro.clearSelection();
				}
			}
		});

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 60, 937, 10);
		SubFornecPanel_2.add(separator_4);


		/**
		 * Labels.
		 */

		JLabel lblFiltrarPorStatusFornec = new JLabel("Filtrar por Status:");
		lblFiltrarPorStatusFornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFiltrarPorStatusFornec.setBounds(547, 402, 153, 28);
		SubFornecPanel_2.add(lblFiltrarPorStatusFornec);

		JLabel lblPesquisaFornec = new JLabel("Pesquisa (Nome):");
		lblPesquisaFornec.setBounds(261, 81, 136, 23);
		SubFornecPanel_2.add(lblPesquisaFornec);
		lblPesquisaFornec.setFont(new Font("Gabriola", Font.PLAIN, 25));

		/**
		 * Formatted Fields.
		 */

		formattedTextPesquisa_Fornec = new JTextField();
		formattedTextPesquisa_Fornec.setBounds(456, 81, 186, 20);
		SubFornecPanel_2.add(formattedTextPesquisa_Fornec);

		formattedTextPesquisa_Fornec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PesquisaDados.PesqNomeFornec(p, statusComboFornec, fornecCadastro, formattedTextPesquisa_Fornec);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				PesquisaDados.PesqNomeFornec(p, statusComboFornec, fornecCadastro, formattedTextPesquisa_Fornec);
			}
		});
		/**
		 * ComboBoxes.
		 */

		statusComboFornec = new JComboBox<Object>();
		statusComboFornec.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		statusComboFornec.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "Ativo", "Inativo"}));
		statusComboFornec.setBounds(720, 408, 125, 18);
		SubFornecPanel_2.add(statusComboFornec);

		statusComboFornec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(statusComboFornec.getSelectedItem().toString().equals("Selecione...")){
					FornecedorDAO.LoadFornecedorTable(fornecCadastro);
				}else if(statusComboFornec.getSelectedItem().toString().equals("Ativo")){
					FornecedorDAO.LoadAtivoFornecedorTable(fornecCadastro);
				}else if(statusComboFornec.getSelectedItem().toString().equals("Inativo")){
					FornecedorDAO.LoadInativoFornecedorTable(fornecCadastro);
				}
			}
		});


		/**
		 * ScrollPanes and Tables.
		 */
		JScrollPane fornecScroll = new JScrollPane();
		fornecScroll.setBounds(36, 125, 897, 253);
		fornecScroll.setOpaque(false);
		SubFornecPanel_2.add(fornecScroll);
		SubFornecPanel_2.setLayout(null);

		FornecOptionsMenu = new JPopupMenu();
		addPopup(fornecScroll, FornecOptionsMenu);

		AtivarFornecOption = new JMenuItem("Ativar Fornecedor");
		AtivarFornecOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtivarFornecActionPerformed(e);
			}
		});
		FornecOptionsMenu.add(AtivarFornecOption);

		DesativarFornecOption = new JMenuItem("Desativar Fornecedor");
		DesativarFornecOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesativarFornecActionPerformed(e);
			}
		});
		FornecOptionsMenu.add(DesativarFornecOption);


		fornecCadastro = new JTable();
		fornecCadastro.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {

					int rowSelection = fornecCadastro.getSelectedRow();
					String FornecInfoStatus = fornecCadastro.getValueAt(rowSelection, 3).toString();

					boolean rowIsInactive = FornecInfoStatus.toString().equals("Inativo");
					boolean rowIsActive = FornecInfoStatus.toString().equals("Ativo");

					DesativarFornecOption.setEnabled(rowIsActive);
					AtivarFornecOption.setEnabled(rowIsInactive);

					// get row that pointer is over
					int row = fornecCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (fornecCadastro.isRowSelected(row)) {
						FornecOptionsMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {


					int rowSelection = fornecCadastro.getSelectedRow();
					String FornecInfoStatus = fornecCadastro.getValueAt(rowSelection, 3).toString();

					boolean rowIsInactive = FornecInfoStatus.toString().equals("Inativo");
					boolean rowIsActive = FornecInfoStatus.toString().equals("Ativo");

					DesativarFornecOption.setEnabled(rowIsActive);
					AtivarFornecOption.setEnabled(rowIsInactive);



					// get row that pointer is over
					int row = fornecCadastro.rowAtPoint(e.getPoint());

					// if pointer is over a selected row, show popup
					if (fornecCadastro.isRowSelected(row)) {
						FornecOptionsMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					EditarFornecMouseEvent(e);
				}
			}
		});
		fornecCadastro.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
					boolean rowsAreSelected = fornecCadastro.getSelectedRowCount() > 0;


					DetalhesButtonFornec.setEnabled(rowsAreSelected);
				}
			}
		});
		fornecCadastro.setBounds(50, 50, 50, 50);
		fornecCadastro.setOpaque(false);
		fornecCadastro.setShowGrid(true);
		fornecCadastro.getSelectionModel().clearSelection();
		fornecScroll.setViewportView(fornecCadastro);

		//fornecCadastro.

		DetalhesButtonFornec = new JButton("Relat\u00F3rio");
		DetalhesButtonFornec.setFont(new Font("Gabriola", Font.PLAIN, 20));
		DetalhesButtonFornec.setEnabled(false);
		DetalhesButtonFornec.setBounds(36, 402, 96, 28);
		SubFornecPanel_2.add(DetalhesButtonFornec);

		DetalhesButtonFornec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				RelatorioFornecActionPerformed(arg0);
			}
		});


		//FornecedorDAO.LoadFornecedorTable(fornecCadastro);
		atualizarFornecTable();

		NewFornecButton = new JButton("Novo Fornecedor");
		NewFornecButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		NewFornecButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				FornecCadastro_Info pci = new FornecCadastro_Info();
				pci.setTitle("Novo Fornecedor");
				pci.setVisible(true);
				pci.FornecCadastroPanel.setVisible(true);
				pci.setFuncionario(func);
			}
		});
		NewFornecButton.setBounds(781, 11, 136, 28);
		SubFornecPanel_2.add(NewFornecButton);

		//-------------------------LivroPanel elements-----------------------------------


		LivroPanel = new JPanel();
		SystemTabPanel.addTab("Livros", null, LivroPanel, null);
		LivroPanel.setLayout(null);

		Sub_Livro_Panel = new JPanel();
		Sub_Livro_Panel.setBounds(24, 42, 957, 471);
		Sub_Livro_Panel.setBackground(new Color(135,206,250,100));
		Sub_Livro_Panel.setLayout(null);
		LivroPanel.add(Sub_Livro_Panel);

		Sub_Livro_Panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(LivroCadastro.getSelectedRowCount() >0){
					LivroCadastro.clearSelection();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(LivroCadastro.getSelectedRowCount() >0){
					LivroCadastro.clearSelection();
				}
			}
		});

		NewBookButton = new JButton("Novo Livro");
		NewBookButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		NewBookButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Prod_LivroCadastro_Info lci = new Prod_LivroCadastro_Info();
				lci.setTitle("Novo Livro");
				lci.setVisible(true);
				lci.setFuncionario(func);
			}
		});
		NewBookButton.setBounds(781, 11, 136, 28);
		Sub_Livro_Panel.add(NewBookButton);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(10, 60, 937, 10);
		Sub_Livro_Panel.add(separator_6);

		JLabel lblPesquisaLivro = new JLabel("Pesquisa (Nome):");
		lblPesquisaLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblPesquisaLivro.setBounds(189, 79, 153, 23);
		Sub_Livro_Panel.add(lblPesquisaLivro);

		JLabel lblFiltrarPorStatusLivro = new JLabel("Filtrar por Status:");
		lblFiltrarPorStatusLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFiltrarPorStatusLivro.setBounds(547, 402, 153, 28);
		Sub_Livro_Panel.add(lblFiltrarPorStatusLivro);


		formattedPesquisaLivro = new JTextField();
		formattedPesquisaLivro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PesquisaDados.PesqNomeLivro(pl, statusComboLivro, LivroCadastro, formattedPesquisaLivro);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				PesquisaDados.PesqNomeLivro(pl, statusComboLivro, LivroCadastro, formattedPesquisaLivro);
			}
		});
		formattedPesquisaLivro.setBounds(373, 81, 186, 20);
		Sub_Livro_Panel.add(formattedPesquisaLivro);

		JScrollPane Livroscroll = new JScrollPane();
		Livroscroll.setOpaque(false);
		Livroscroll.setBounds(36, 125, 897, 253);
		Sub_Livro_Panel.add(Livroscroll);

		LivroCadastro = new JTable();
		LivroCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					EditarLivroMouseEvent(e);
				}
			}
		});
		LivroCadastro.setBounds(50, 50, 50, 50);
		Livroscroll.setViewportView(LivroCadastro);
		atualizarLivroTable();

		ContatoOnlinePanel = new JPanel();
		SystemTabPanel.addTab("Contatos Online", null, ContatoOnlinePanel, null);
		ContatoOnlinePanel.setLayout(null);

		SubContatoOnlinePanel = new JPanel();
		SubContatoOnlinePanel.setBounds(24, 42, 957, 471);
		SubContatoOnlinePanel.setBackground(new Color(135,206,250,100));
		ContatoOnlinePanel.add(SubContatoOnlinePanel);
		SubContatoOnlinePanel.setLayout(null);

		SubContatoOnlinePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(ContatoOnlineTable.getSelectedRowCount() >0){
					ContatoOnlineTable.clearSelection();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(ContatoOnlineTable.getSelectedRowCount() >0){
					ContatoOnlineTable.clearSelection();
				}

			}
		});


		statusComboLivro = new JComboBox<Object>();
		statusComboLivro.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		statusComboLivro.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "Ativo", "Inativo"}));
		statusComboLivro.setBounds(720, 408, 125, 18);
		Sub_Livro_Panel.add(statusComboLivro);

		statusComboLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(statusComboLivro.getSelectedItem().toString().equals("Selecione...")){
					ProdutoLivroDAO.loadLivroProdTable(LivroCadastro);
				}else if(statusComboLivro.getSelectedItem().toString().equals("Ativo")){
					ProdutoLivroDAO.loadAtivoLivroProdTable(LivroCadastro);
				}else if(statusComboLivro.getSelectedItem().toString().equals("Inativo")){
					ProdutoLivroDAO.loadInativoLivroProdTable(LivroCadastro);
				}
			}
		});


		ContatoScroll = new JScrollPane();
		ContatoScroll.setBounds(36, 125, 897, 253);
		SubContatoOnlinePanel.add(ContatoScroll);

		ContatoOnlineTable = new JTable();
		ContatoOnlineTable.setBounds(64, 70, 1, 1);
		ContatoScroll.setViewportView(ContatoOnlineTable);


		ContatoOnlineTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					verContatoMouseEvent(e);
				}
			}
		});



		LoadContatoTable();

		JLabel lblPesquisanome = new JLabel("Pesquisa (Nome):");
		lblPesquisanome.setVisible(false);
		lblPesquisanome.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblPesquisanome.setBounds(261, 79, 139, 23);
		SubContatoOnlinePanel.add(lblPesquisanome);

		textField = new JTextField();
		textField.setVisible(false);
		textField.setColumns(10);
		textField.setBounds(415, 81, 186, 20);
		SubContatoOnlinePanel.add(textField);

		btnAtualizar = new JButton("Atualizar Tabela");
		btnAtualizar.setFont(new Font("Gabriola", Font.PLAIN, 20));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadContatoTable();
			}
		});
		btnAtualizar.setBounds(781, 11, 136, 28);
		SubContatoOnlinePanel.add(btnAtualizar);

		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(10, 58, 937, 10);
		SubContatoOnlinePanel.add(separator_9);


		//CadastroConsultaTabPanel.setEnabledAt(0, false);

	}

	//------------------------------------------------------------------------------------	 

	//------------------------------------------------------------------------------------	 


	public void DesativarCliActionPerformed(ActionEvent e){

		int rowSelected = clienteCadastro.getSelectedRow();
		int codigo = Integer.parseInt(clienteCadastro.getValueAt(rowSelected, 0).toString());


		ClienteBean c = new ClienteBean();

		String nomeCli = clienteCadastro.getValueAt(rowSelected, 1).toString();

		c.setId_cli(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja desativar o(a) cliente "+nomeCli+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			ClienteDAO.desativarCliente(c);
		}

		if(statusCombo.getSelectedItem().toString().equals("Ativo")){
			ClienteDAO.LoadAtivoClienteTable(clienteCadastro);
		}
		else if(statusCombo.getSelectedItem().toString().equals("Inativo")){
			ClienteDAO.LoadInativoClienteTable(clienteCadastro);
		}else{
			ClienteDAO.LoadClienteTable(clienteCadastro);
		}
	}

	public void AtivarCliActionPerformed(ActionEvent e){

		int rowSelected = clienteCadastro.getSelectedRow();
		int codigo = Integer.parseInt(clienteCadastro.getValueAt(rowSelected, 0).toString());


		ClienteBean c = new ClienteBean();

		//int rowSelected = clienteCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(clienteCadastro.getValueAt(rowSelected, 0).toString());

		String nomeCli = clienteCadastro.getValueAt(rowSelected, 1).toString();

		c.setId_cli(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja Ativar o(a) cliente "+nomeCli+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			ClienteDAO.AtivarCliente(c);
		}

		if(statusCombo.getSelectedItem().toString().equals("Ativo")){
			ClienteDAO.LoadAtivoClienteTable(clienteCadastro);
		}
		else if(statusCombo.getSelectedItem().toString().equals("Inativo")){
			ClienteDAO.LoadInativoClienteTable(clienteCadastro);
		}else{
			ClienteDAO.LoadClienteTable(clienteCadastro);
		}

	}

	public void RelatorioCliActionPerformed(ActionEvent e){
		ClienteCadastro_Info cci = new ClienteCadastro_Info();
		cci.CadastroClientePanel.setVisible(false);
		cci.RelatorioClientePanel.setVisible(true);
		cci.setVisible(true);

		ClienteBean c = new ClienteBean();
		int rowSelected = clienteCadastro.getSelectedRow();
		int conversion = Integer.parseInt(clienteCadastro.getValueAt(rowSelected, 0).toString());

		c.setId_cli(conversion);
		ClienteCadastro_Info.LoadCliRelatorio(c);

		c = ClienteDAO.retornarCliente(c);


		cci.lblSituacaoCliInfo.setText(c.getAtivo_inativo_status());
		cci.lblDataCadastroInfoCli.setText(c.getDataCadastro_cli());
		cci.lblCadastradoPorInfo.setText(c.getCadastradoPor());
		cci.ResicaoDataCli.setText(c.getDataRecisaoCli());

		String Idcli = String.valueOf(c.getId_cli());
		cci.lblCodClienteInfo.setText(Idcli);


		String NomeCli = String.valueOf(clienteCadastro.getValueAt(rowSelected, 1));

		cci.setTitle("Relatório do(a) Cliente "+NomeCli+"");

	}

	public void RelatorioCliBalcaoActionPerformed(ActionEvent e){
		ClienteCadastro_Info cci = new ClienteCadastro_Info();
		cci.CadastroClientePanel.setVisible(false);
		cci.RelatorioClientePanel.setVisible(true);
		cci.setVisible(true);

		ClienteBean c = new ClienteBean();

		c.setId_cli(1);
		//PedidoVendaRegistroDAO.carregarPedidoItemTable(table, pvr)
		ClienteCadastro_Info.LoadCliRelatorio(c);

		c = ClienteDAO.retornarCliente(c);


		cci.lblSituacaoCliInfo.setText(c.getAtivo_inativo_status());
		cci.lblDataCadastroInfoCli.setText(c.getDataCadastro_cli());
		cci.lblCadastradoPorInfo.setText(c.getCadastradoPor());
		cci.ResicaoDataCli.setText(c.getDataRecisaoCli());

		String Idcli = String.valueOf(c.getId_cli());
		cci.lblCodClienteInfo.setText(Idcli);

		String NomeCli = "Balcão";

		cci.setTitle("Relatório do(a) Cliente "+NomeCli+"");

	}

	public void RelatorioFuncActionPerformed(ActionEvent e){
		FuncCadastro_Info cfi = new FuncCadastro_Info();
		cfi.CadastroFuncPanel.setVisible(false);
		cfi.RelatorioFuncPanel.setVisible(true);
		cfi.setVisible(true);

		FuncionarioBean f = new FuncionarioBean();
		int rowSelected = funcionarioCadastro.getSelectedRow();
		int conversion = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelected, 0).toString());


		f.setId_func(conversion);
		//PedidoVendaRegistroDAO.carregarPedidoItemTable(table, pvr)
		FuncCadastro_Info.LoadFuncRelatorio(f);

		f = FuncionarioDAO.retornarFuncionario(f);
		String IdNumber = String.valueOf(conversion);

		cfi.lblCodFuncInfo.setText(IdNumber);
		cfi.lblDataCadastroInfoFunc.setText(f.getDataAdmissao_func());
		cfi.lblSituacaoCliInfoFunc.setText(f.getAtivo_inativo_status());
		cfi.ResicaoDataFunc.setText(String.valueOf(f.getDataRecisao_func()));


		String NomeFunc = String.valueOf(funcionarioCadastro.getValueAt(rowSelected, 1));

		cfi.setTitle("Relatório do(a) Funcionario(a) "+NomeFunc+"");

	}

	public void RelatorioFornecActionPerformed(ActionEvent e){
		FornecCadastro_Info cfi = new FornecCadastro_Info();
		cfi.FornecCadastroPanel.setVisible(false);
		cfi.RelatorioFornecPanel.setVisible(true);
		cfi.setVisible(true);

		FornecedorBean p = new FornecedorBean();
		int rowSelected = fornecCadastro.getSelectedRow();
		int conversion = Integer.parseInt(fornecCadastro.getValueAt(rowSelected, 0).toString());



		p.setId_fornec(conversion);
		FornecCadastro_Info.LoadFornecRelatorio(p);

		p = FornecedorDAO.retornarFornecedor(p);
		System.out.println(p.getCadastradoPorFunc_fornec());
		cfi.lblCadastradoPorInfoFornec.setText(p.getCadastradoPorFunc_fornec());

		cfi.lblCodFornecInfo.setText(String.valueOf(p.getId_fornec()));
		cfi.lblDataCadastroInfoFornec.setText(String.valueOf(p.getData_Admissao_fornec()));
		cfi.lblSituacaoFornecInfo.setText(p.getAtivo_inativo_status_fornec());
		cfi.ResicaoDataFornec.setText(String.valueOf(p.getDataRescicao_fornec()));

		String NomeFornec = String.valueOf(fornecCadastro.getValueAt(rowSelected, 1));

		cfi.setTitle("Relatório do Fornecedor "+NomeFornec+"");

	}

	public void RelatorioVendaAction(ActionEvent e){


		PedidoVendaRegistroBean pvr = new PedidoVendaRegistroBean();

		int rowSelectedVenda = VendasTable.getSelectedRow();
		int rowZeroIntValueVenda = Integer.parseInt(VendasTable.getValueAt(rowSelectedVenda, 0).toString());


		pvr.setId_PedidoVendaRegistro(rowZeroIntValueVenda);

		pvr = PedidoVendaRegistroDAO.retornarVendaBoleto(pvr);
	}

	public void EditarFuncActionPerformed(MouseEvent e){
		FuncCadastro_Info fci = new FuncCadastro_Info();
		fci.setVisible(true);
		fci.CadastroFuncPanel.setVisible(true);
		fci.DisableFields();
		fci.FilechooserButton.setEnabled(false);
		fci.CadastrarButton_Func.setEnabled(false);
		fci.LimparButton_Func.setEnabled(false);
		fci.EditarButtonFunc.setEnabled(true);

		FuncionarioBean f = new FuncionarioBean();

		int rowSelectedFunc = funcionarioCadastro.getSelectedRow();
		int rowZeroIntValueFunc = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelectedFunc, 0).toString());
		String NomeFunc = funcionarioCadastro.getValueAt(rowSelectedFunc, 1).toString();

		//String NomeCargo = funcionarioCadastro.getValueAt(rowSelectedFunc, column)
		fci.setTitle("Funcionário(a) "+NomeFunc+"");


		f.setId_func(rowZeroIntValueFunc);

		f = FuncionarioDAO.retornarFuncionario(f);

		fci.FillUpEditFunc(f);

	}

	public void EditarCliMouseEvent(MouseEvent e){
		ClienteCadastro_Info ci = new ClienteCadastro_Info();
		ci.setVisible(true);
		//ci.setModal(true);
		ci.DisableFields();
		ci.CadastroClientePanel.setVisible(true);
		ci.CadastrarButtonCli.setEnabled(false);
		ci.LimparButtonCli_1.setEnabled(false);
		ci.EditarButtonCli.setEnabled(true);
		ci.lblDataCadastroInfoCli.setVisible(true);
		ci.lblSituacaoCliInfo.setVisible(true);
		ci.lblCadastradoPor.setVisible(true);
		ci.lblCadastradoPorInfo.setVisible(true);


		ClienteBean c = new ClienteBean();

		int rowSelectedCli = clienteCadastro.getSelectedRow();
		int rowZeroIntValueCli = Integer.parseInt(clienteCadastro.getValueAt(rowSelectedCli, 0).toString());
		String NomeCli = clienteCadastro.getValueAt(rowSelectedCli, 1).toString();

		ci.setTitle("Cliente "+NomeCli+"");

		c.setId_cli(rowZeroIntValueCli);

		c = ClienteDAO.retornarCliente(c);

		//ci.lblCadastradoPorInfo.setText(c.getCadastradoPor());
		ci.FillUpEditCli(c);

	}


	public void EditarFornecMouseEvent(MouseEvent e){
		FornecCadastro_Info pci = new FornecCadastro_Info();
		pci.setVisible(true);
		pci.EditarButtonFornec.setEnabled(true);
		pci.CadastrarButton_Fornec.setEnabled(false);


		FornecedorBean p = new FornecedorBean();


		int rowSelectedFornec = fornecCadastro.getSelectedRow();
		int rowZeroIntValueFornec = Integer.parseInt(fornecCadastro.getValueAt(rowSelectedFornec, 0).toString());
		String NomeFornec = fornecCadastro.getValueAt(rowSelectedFornec, 1).toString();
		pci.setTitle("Fornecedor: "+NomeFornec+"");
		p.setId_fornec(rowZeroIntValueFornec);

		p = FornecedorDAO.retornarFornecedor(p);

		pci.FillUpEditFornec(p);
		pci.DisableFields();

		/*byteArray = f.getFoto();
		pci.Photo_Func.length(byteArray);*/
	}

	public void EditarCargoMouseEvent(MouseEvent e){
		CargoCadastro_Info cci = new CargoCadastro_Info();
		cci.setVisible(true);
		cci.DisableFields();
		cci.CadastroCargoPanel.setVisible(true);
		cci.CadastrarCargoButton.setEnabled(false);
		cci.LimparButtonCargo.setEnabled(false);
		cci.EditarButtonCargo.setEnabled(true);


		CargoBean cb = new CargoBean();

		int rowSelectedCargo = cargoCadastro.getSelectedRow();
		int rowZeroIntValueCargo = Integer.parseInt(cargoCadastro.getValueAt(rowSelectedCargo, 0).toString());
		String NomeCargo = cargoCadastro.getValueAt(rowSelectedCargo, 1).toString();

		cci.setTitle("Cargo "+NomeCargo+"");

		cb.setIdCargo(rowZeroIntValueCargo);

		cb = CargoDAO.retornarCargo(cb);

		cci.DisableFields();
		cci.FillUpEditCargo(cb);

		//cci.setVisibleInfo();

	}
	public void EditarLivroMouseEvent(MouseEvent e){
		Prod_LivroCadastro_Info  pli = new Prod_LivroCadastro_Info();
		pli.setVisible(true);
		pli.DisableFields();
		pli.FilechooserButtonBook.setEnabled(false);
		pli.CadastroLivroPanel.setVisible(true);
		pli.CadastrarButton_Livro.setEnabled(false);
		pli.LimparButton_1_Livro.setEnabled(false);
		pli.EditarButton_Livro.setEnabled(true);
		pli.QtdSpinnerLivro.setEnabled(false);


		ProdutoLivroBean pl = new ProdutoLivroBean();

		int rowSelectedCli = LivroCadastro.getSelectedRow();
		int rowZeroIntValueCli = Integer.parseInt(LivroCadastro.getValueAt(rowSelectedCli, 0).toString());
		String NomeProd = LivroCadastro.getValueAt(rowSelectedCli, 1).toString();

		pli.setTitle(""+NomeProd+"");

		//pl.setId_cli(rowZeroIntValueCli);

		pl = ProdutoLivroDAO.buscarLivroProduto(rowZeroIntValueCli);

		//ci.lblCadastradoPorInfo.setText(c.getCadastradoPor());
		pli.FillUpEditLivro(pl);
		//pli.setVisibleInfo();

	}

	public void verContatoMouseEvent(MouseEvent e){
		Contact_Info ri = new Contact_Info();
		ri.setVisible(true);
		//ci.DisableFields();
		ContatoClienteBean ccb = new ContatoClienteBean();

		int rowSelectedContatoCli = ContatoOnlineTable.getSelectedRow();
		long rowZeroLongValueContatoCli = Integer.parseInt(ContatoOnlineTable.getValueAt(rowSelectedContatoCli, 0).toString());
		String NomeCli = ContatoOnlineTable.getValueAt(rowSelectedContatoCli, 1).toString();

		ri.setTitle("Contato "+NomeCli+"");

		ccb.setId_Cli_Contato(rowZeroLongValueContatoCli);

		ccb = ContatoOnlineDAO.retornarContato(ccb);

		//ci.lblCadastradoPorInfo.setText(c.getCadastradoPor());
		ri.FillUpEditContact(ccb);

	}


	public static void atualizarClienteTable(){
		ClienteDAO.LoadClienteTable(clienteCadastro);
	}

	public static void atualizarFuncTable(){
		FuncionarioDAO.LoadFuncionarioTable(funcionarioCadastro);
	}

	public void DesativarActionFuncPerformed(ActionEvent e){

		int rowSelected = funcionarioCadastro.getSelectedRow();
		int codigo = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelected, 0).toString());


		FuncionarioBean f = new FuncionarioBean();

		//int rowSelected = funcionarioCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelected, 0).toString());

		String nomeFunc = funcionarioCadastro.getValueAt(rowSelected, 1).toString();

		f.setId_func(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja desativar o(a) funcionário(a) "+nomeFunc+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			FuncionarioDAO.desativarFuncionario(f);
		}

		if(statusCombo.getSelectedItem().toString().equals("Ativo")){
			FuncionarioDAO.LoadAtivoFuncionarioTable(funcionarioCadastro);
		}
		else if(statusCombo.getSelectedItem().toString().equals("Inativo")){
			FuncionarioDAO.LoadInativoFuncionarioTable(funcionarioCadastro);
		}else{
			FuncionarioDAO.LoadFuncionarioTable(funcionarioCadastro);
		}
	}

	public void RecessoActionFuncPerformed(ActionEvent e){

		int rowSelected = funcionarioCadastro.getSelectedRow();
		int codigo = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelected, 0).toString());


		FuncionarioBean f = new FuncionarioBean();

		//int rowSelected = funcionarioCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelected, 0).toString());

		String nomeFunc = funcionarioCadastro.getValueAt(rowSelected, 1).toString();

		f.setId_func(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja Conceber Recesso ao funcionário "+nomeFunc+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			FuncionarioDAO.desativarFuncionarioVacation(f);
		}

		if(statusCombo.getSelectedItem().toString().equals("Ativo")){
			FuncionarioDAO.LoadAtivoFuncionarioTable(funcionarioCadastro);
		}else if(statusCombo.getSelectedItem().toString().equals("Inativo")){
			FuncionarioDAO.LoadInativoFuncionarioTable(funcionarioCadastro);
		}else if(statusCombo.getSelectedItem().toString().equals("em Recesso")){
			FuncionarioDAO.LoadInativoVacationFuncionarioTable(funcionarioCadastro);
		}
		else if(statusCombo.getSelectedItem().toString().equals("em Recesso")){
			FuncionarioDAO.LoadInativoVacationFuncionarioTable(funcionarioCadastro);

		}else{
			FuncionarioDAO.LoadFuncionarioTable(funcionarioCadastro);
		}
	}


	public void AtivarFuncActionPerformed(ActionEvent e){

		int rowSelected = funcionarioCadastro.getSelectedRow();
		int codigo = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelected, 0).toString());


		FuncionarioBean f = new FuncionarioBean();

		//int rowSelected = funcionarioCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(funcionarioCadastro.getValueAt(rowSelected, 0).toString());

		String nomeFunc = funcionarioCadastro.getValueAt(rowSelected, 1).toString();

		f.setId_func(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja Ativar o(a) funcionário(a) "+nomeFunc+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			FuncionarioDAO.AtivarFuncionario(f);
		}

		if(statusCombo.getSelectedItem().toString().equals("Ativo")){
			FuncionarioDAO.LoadAtivoFuncionarioTable(funcionarioCadastro);
		}
		else if(statusCombo.getSelectedItem().toString().equals("Inativo")){
			FuncionarioDAO.LoadInativoFuncionarioTable(funcionarioCadastro);
		}
		else if(statusCombo.getSelectedItem().toString().equals("em Recesso")){
			FuncionarioDAO.LoadInativoVacationFuncionarioTable(funcionarioCadastro);

		}else{
			FuncionarioDAO.LoadFuncionarioTable(funcionarioCadastro);
		}

	}

	public void AtivarCargoActionPerformed(ActionEvent e){

		int rowSelected = cargoCadastro.getSelectedRow();
		int codigo = Integer.parseInt(cargoCadastro.getValueAt(rowSelected, 0).toString());


		CargoBean cb = new CargoBean();

		//int rowSelected = cargoCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(cargoCadastro.getValueAt(rowSelected, 0).toString());

		String nomeCargo = cargoCadastro.getValueAt(rowSelected, 1).toString();

		cb.setIdCargo(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja Ativar o(a) Cargo "+nomeCargo+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			CargoDAO.AtivarCargo(cb);
		}

		if(statusComboCargo.getSelectedItem().toString().equals("Ativo")){
			CargoDAO.LoadAtivoCargoTable(cargoCadastro);
		}
		else if(statusComboCargo.getSelectedItem().toString().equals("Inativo")){
			CargoDAO.LoadInativoCargoTable(cargoCadastro);
		}else{
			CargoDAO.LoadCargoTable(cargoCadastro);
		}

	}

	public void AtivarFornecActionPerformed(ActionEvent e){

		int rowSelected = fornecCadastro.getSelectedRow();
		int codigo = Integer.parseInt(fornecCadastro.getValueAt(rowSelected, 0).toString());


		FornecedorBean p = new FornecedorBean();

		//int rowSelected = fornecCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(fornecCadastro.getValueAt(rowSelected, 0).toString());

		String nomeCargo = fornecCadastro.getValueAt(rowSelected, 1).toString();

		p.setId_fornec(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja Ativar o(a) Fornecedor "+nomeCargo+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			FornecedorDAO.AtivarFornecedor(p);
		}

		if(statusComboFornec.getSelectedItem().toString().equals("Ativo")){
			FornecedorDAO.LoadAtivoFornecedorTable(fornecCadastro);
		}
		else if(statusComboFornec.getSelectedItem().toString().equals("Inativo")){
			FornecedorDAO.LoadInativoFornecedorTable(fornecCadastro);
		}else{
			FornecedorDAO.LoadFornecedorTable(fornecCadastro);
		}

	}

	public void DesativarFornecActionPerformed(ActionEvent e){

		int rowSelected = fornecCadastro.getSelectedRow();
		int codigo = Integer.parseInt(fornecCadastro.getValueAt(rowSelected, 0).toString());


		FornecedorBean p = new FornecedorBean();

		//int rowSelected = fornecCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(fornecCadastro.getValueAt(rowSelected, 0).toString());

		String nomeCargo = fornecCadastro.getValueAt(rowSelected, 1).toString();

		p.setId_fornec(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja Desativar o(a) Fornecedor "+nomeCargo+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			FornecedorDAO.DesativarFornecedor(p);
		}

		if(statusComboFornec.getSelectedItem().toString().equals("Ativo")){
			FornecedorDAO.LoadAtivoFornecedorTable(fornecCadastro);
		}
		else if(statusComboFornec.getSelectedItem().toString().equals("Inativo")){
			FornecedorDAO.LoadInativoFornecedorTable(fornecCadastro);
		}else{
			FornecedorDAO.LoadFornecedorTable(fornecCadastro);
		}

	}


	public void DesativarCargoActionPerformed(ActionEvent e){

		int rowSelected = cargoCadastro.getSelectedRow();
		int codigo = Integer.parseInt(cargoCadastro.getValueAt(rowSelected, 0).toString());


		CargoBean cb = new CargoBean();

		//int rowSelected = cargoCadastro.getSelectedRow();
		// int codigo = Integer.parseInt(cargoCadastro.getValueAt(rowSelected, 0).toString());

		String nomeCargo = cargoCadastro.getValueAt(rowSelected, 1).toString();

		cb.setIdCargo(codigo);

		if (JOptionPane.showConfirmDialog(this, "Deseja Desativar o(a) Cargo "+nomeCargo+" ?", "Aviso", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			CargoDAO.DesativarCargo(cb);
		}

		if(statusComboCargo.getSelectedItem().toString().equals("Ativo")){
			CargoDAO.LoadAtivoCargoTable(cargoCadastro);
		}
		else if(statusComboCargo.getSelectedItem().toString().equals("Inativo")){
			CargoDAO.LoadInativoCargoTable(cargoCadastro);
		}else{
			CargoDAO.LoadCargoTable(cargoCadastro);
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});

	}

	public static void LoadVendasTable(){
		PedidoVendaRegistroDAO.carregarVendasTable(VendasTable);
	}

	public static void atualizarFornecTable() {
		FornecedorDAO.LoadFornecedorTable(fornecCadastro);

	}

	public static void atualizarCargoTable(){
		CargoDAO.LoadCargoTable(cargoCadastro);

	}

	public static void atualizarLivroTable(){
		ProdutoLivroDAO.loadLivroProdTable(LivroCadastro);
	}

	public static void LoadContatoTable() {
		ContatoOnlineDAO.LoadClienteTable(ContatoOnlineTable);

	}

	public static void setPermission(CargoBean cb){
		cargo = cb;
		if(!cargo.getVendasPermissao().equals("Yes")){
			SystemTabPanel.setEnabledAt(0, false);
		}
		if(!cargo.getCadastroConsultaCliPermissao().equals("Yes")){
			SystemTabPanel.setEnabledAt(1, false);
		}
		if(!cargo.getCadastroConsultaFuncPermissao().equals("Yes")){
			SystemTabPanel.setEnabledAt(2, false);
		}
		if(!cargo.getCadastroConsultaCargoPermissao().equals("Yes")){
			SystemTabPanel.setEnabledAt(3, false);
		}
		if(!cargo.getCadastroConsultaFornecPermissao().equals("Yes")){
			SystemTabPanel.setEnabledAt(4, false);
		}
		if(!cargo.getCadastroConsulta_Prod_Livro_Permissao().equals("Yes")){
			SystemTabPanel.setEnabledAt(5, false);
		}
		if(!cargo.getCadastroConsultaContatoClientePermissao().equals("Yes")){
			SystemTabPanel.setEnabledAt(6, false);
		}
	}

	public static void setCliente(ClienteBean c) {
		cli = c;
	}

	public static void setFuncionario(FuncionarioBean f) {
		func = f;
	}
}