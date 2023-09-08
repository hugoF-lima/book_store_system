package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;
import dataAcessObjects.CargoDAO;
import acessoDadosBeans.CargoBean;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import acessoDadosBeans.FuncionarioBean;
import utilities.GeneralMethods;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import utilities.MonetarioDocument;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JSeparator;

public class CargoCadastro_Info extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Color GreenColor = (new Color(50, 205, 50));
	Color RedColor = Color.RED;
	Color OrangeColor = Color.ORANGE;

	public static FuncionarioBean func;
	CargoBean cb = new CargoBean();
	GeneralMethods gm = new GeneralMethods();

	final JPanel CadastroCargoPanel = new JPanel();
	private JTextField textNomeCargo;
	private JTextField textSalarioCargo;
	JTextArea DescriptionCargoTextPane;
	JButton CadastrarCargoButton;
	JButton LimparButtonCargo;
	private JButton AlterarButtonCargo;
	private JCheckBox acessoSistemaCheck;
	private JCheckBox ClienteCheck;
	private JCheckBox FuncCheck;
	private JCheckBox FornCheck;
	private JCheckBox CargoCheck;
	private JCheckBox ProdLivroCheck;
	private JCheckBox VendasCheck;

	ArrayList<JTextField> JTextFieldCargoList = new ArrayList<JTextField>();
	private JCheckBox ContatoClienteCheck;
	JButton EditarButtonCargo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CargoCadastro_Info dialog = new CargoCadastro_Info();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CargoCadastro_Info() {
		setBounds(100, 100, 1014, 470);
		getContentPane().setLayout(new CardLayout(0, 0));

		//---------------CadastroCargo elements----------------------------------------------

		CadastroCargoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(CadastroCargoPanel);
		CadastroCargoPanel.setLayout(null);

		/**
		 * Labels.
		 */
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNome.setBounds(44, 34, 72, 21);
		CadastroCargoPanel.add(lblNome);

		JLabel lblSalarior = new JLabel("Salario(R$):");
		lblSalarior.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSalarior.setBounds(263, 31, 90, 26);
		CadastroCargoPanel.add(lblSalarior);

		JLabel lblDescrioDoCargo = new JLabel("Descri\u00E7\u00E3o do Cargo:");
		lblDescrioDoCargo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDescrioDoCargo.setBounds(507, 34, 160, 21);
		CadastroCargoPanel.add(lblDescrioDoCargo);

		textNomeCargo = new JTextField();
		textNomeCargo.setBounds(106, 35, 127, 20);
		CadastroCargoPanel.add(textNomeCargo);
		textNomeCargo.setColumns(10);

		textNomeCargo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textNomeCargo);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textNomeCargo);


			}
		});

		textSalarioCargo = new JTextField();
		textSalarioCargo.setBounds(363, 34, 118, 20);
		CadastroCargoPanel.add(textSalarioCargo);
		textSalarioCargo.setColumns(10);			
		textSalarioCargo.setDocument(new MonetarioDocument());

		textSalarioCargo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textSalarioCargo);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textSalarioCargo);
			}
		});

		DescriptionCargoTextPane = new JTextArea();
		DescriptionCargoTextPane.setBounds(507, 66, 437, 94);
		DescriptionCargoTextPane.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
		DescriptionCargoTextPane.setForeground(OrangeColor);
		DescriptionCargoTextPane.setLineWrap(true);
		CadastroCargoPanel.add(DescriptionCargoTextPane);

		//---------------------------Separator---------------------------

		JSeparator separator = new JSeparator();
		separator.setBounds(28, 183, 940, 10);
		CadastroCargoPanel.add(separator);
		//---------------------------Separator---------------------------

		JLabel lblPrivilgiosERestries = new JLabel("Privil\u00E9gios e Restri\u00E7\u00F5es:");
		lblPrivilgiosERestries.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblPrivilgiosERestries.setBounds(404, 198, 192, 21);
		CadastroCargoPanel.add(lblPrivilgiosERestries);

		JLabel lblCamposOpcionais = new JLabel();
		lblCamposOpcionais.setText("Campos Opcionais!");
		lblCamposOpcionais.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposOpcionais.setBounds(50, 399, 167, 21);
		CadastroCargoPanel.add(lblCamposOpcionais);

		JLabel lblOrangeColor = new JLabel();
		lblOrangeColor.setOpaque(true);
		lblOrangeColor.setBackground(Color.ORANGE);
		lblOrangeColor.setBounds(13, 408, 20, 4);
		CadastroCargoPanel.add(lblOrangeColor);

		JLabel lblRedColor = new JLabel();
		lblRedColor.setOpaque(true);
		lblRedColor.setBackground(Color.RED);
		lblRedColor.setBounds(13, 375, 20, 4);
		CadastroCargoPanel.add(lblRedColor);

		JLabel lblCamposObrigatorios = new JLabel();
		lblCamposObrigatorios.setText("Campos Obrigatorios!");
		lblCamposObrigatorios.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposObrigatorios.setBounds(50, 367, 183, 21);
		CadastroCargoPanel.add(lblCamposObrigatorios);

		acessoSistemaCheck = new JCheckBox("Acesso ao Sistema");
		acessoSistemaCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean EnableSystemChecks = acessoSistemaCheck.isSelected();

				ClienteCheck.setEnabled(EnableSystemChecks);
				FuncCheck.setEnabled(EnableSystemChecks);
				CargoCheck.setEnabled(EnableSystemChecks);
				FornCheck.setEnabled(EnableSystemChecks);
				ProdLivroCheck.setEnabled(EnableSystemChecks);
				ContatoClienteCheck.setEnabled(EnableSystemChecks);
				VendasCheck.setEnabled(EnableSystemChecks);

				ClienteCheck.setSelected(EnableSystemChecks);
				FuncCheck.setSelected(EnableSystemChecks);
				CargoCheck.setSelected(EnableSystemChecks);
				FornCheck.setSelected(EnableSystemChecks);
				ProdLivroCheck.setSelected(EnableSystemChecks);
				ContatoClienteCheck.setSelected(EnableSystemChecks);
				VendasCheck.setSelected(EnableSystemChecks);
			}
		});
		acessoSistemaCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		acessoSistemaCheck.setBounds(143, 234, 167, 23);
		CadastroCargoPanel.add(acessoSistemaCheck);


		ClienteCheck = new JCheckBox("Clientes");
		ClienteCheck.setEnabled(false);
		ClienteCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		ClienteCheck.setBounds(44, 292, 97, 23);
		CadastroCargoPanel.add(ClienteCheck);

		FuncCheck = new JCheckBox("Funcion\u00E1rios");
		FuncCheck.setEnabled(false);
		FuncCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		FuncCheck.setBounds(143, 292, 135, 23);
		CadastroCargoPanel.add(FuncCheck);

		FornCheck = new JCheckBox("Fornecedores");
		FornCheck.setEnabled(false);
		FornCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		FornCheck.setBounds(371, 292, 135, 23);
		CadastroCargoPanel.add(FornCheck);

		CargoCheck = new JCheckBox("Cargos");
		CargoCheck.setEnabled(false);
		CargoCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		CargoCheck.setBounds(280, 292, 89, 23);
		CadastroCargoPanel.add(CargoCheck);

		ProdLivroCheck = new JCheckBox("Livros");
		ProdLivroCheck.setEnabled(false);
		ProdLivroCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		ProdLivroCheck.setBounds(524, 292, 89, 23);
		CadastroCargoPanel.add(ProdLivroCheck);

		VendasCheck = new JCheckBox("Vendas");
		VendasCheck.setEnabled(false);
		VendasCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		VendasCheck.setBounds(817, 292, 107, 23);
		CadastroCargoPanel.add(VendasCheck);

		ContatoClienteCheck = new JCheckBox("Contatos Online");
		ContatoClienteCheck.setEnabled(false);
		ContatoClienteCheck.setFont(new Font("Gabriola", Font.PLAIN, 25));
		ContatoClienteCheck.setBounds(627, 292, 165, 23);
		CadastroCargoPanel.add(ContatoClienteCheck);

		/**
		 * Buttons.
		 */

		CadastrarCargoButton = new JButton("Cadastrar");
		CadastrarCargoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FillCargoBean(cb);


				if (GeneralMethods.verifyTextField(textNomeCargo) || GeneralMethods.verifyTextField(textSalarioCargo)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else{

					boolean resposta = CargoDAO.verificarNome(cb.getNomeCargo()).getNomeCargo() == null;
					System.out.println(CargoDAO.verificarNome(cb.getNomeCargo()).getNomeCargo());
					System.out.println(cb.getNomeCargo());
					if (resposta == true) {
						CargoDAO.cadastrarCargo(cb);
						cb = new CargoBean();
						SystemTabs.atualizarCargoTable();
						//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
						//ccm = new CadastroConsultaMethods();
						GeneralMethods.limpar(CadastroCargoPanel);
						dispose();
						FillUpJTextFieldList();
						/*Main.atualizarTableFuncionario();
		            			this.dispose();*/
					}else{
						JOptionPane.showMessageDialog(null, "Este Nome já existe no banco");
					}
				}


			}



			//}

		});
		CadastrarCargoButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		CadastrarCargoButton.setBounds(742, 394, 107, 26);
		CadastroCargoPanel.add(CadastrarCargoButton);

		LimparButtonCargo = new JButton("Limpar");
		LimparButtonCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.limpar(CadastroCargoPanel);
				FillUpJTextFieldList();
				GeneralMethods.verifyOptionalTextPane(DescriptionCargoTextPane);
			}
		});
		LimparButtonCargo.setFont(new Font("Gabriola", Font.PLAIN, 20));
		LimparButtonCargo.setBounds(635, 394, 97, 26);
		CadastroCargoPanel.add(LimparButtonCargo);

		AlterarButtonCargo = new JButton("Alterar");
		AlterarButtonCargo.setEnabled(false);
		AlterarButtonCargo.setFont(new Font("Gabriola", Font.PLAIN, 20));
		AlterarButtonCargo.setBounds(528, 394, 97, 26);
		CadastroCargoPanel.add(AlterarButtonCargo);
		
		AlterarButtonCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FillCargoBean(cb);


				if (GeneralMethods.verifyTextField(textNomeCargo) || GeneralMethods.verifyTextField(textSalarioCargo)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {

					//if (CargoDAO.verificarCargo(cb) == false) {
//continue here, proceed to Func to insert cpf verification in db
						CargoDAO.alterarCargo(cb);
						cb = new CargoBean();
						SystemTabs.atualizarCargoTable();
						//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
						//ccm = new CadastroConsultaMethods();
						GeneralMethods.limpar(CadastroCargoPanel);
						dispose();
						FillUpJTextFieldList();
						
					}
				//}


			}



			//}

		});
		

		EditarButtonCargo = new JButton("Editar");
		EditarButtonCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.EnableFields(CadastroCargoPanel);
				//EnabledIfSysAccessChecked();
				AlterarButtonCargo.setEnabled(true);
				CargoCadastro_Info.this.setTitle("Alterar Cargo");
			}
		});
		EditarButtonCargo.setFont(new Font("Gabriola", Font.PLAIN, 20));
		EditarButtonCargo.setEnabled(false);
		EditarButtonCargo.setBounds(859, 394, 97, 26);
		CadastroCargoPanel.add(EditarButtonCargo);


		FillUpJTextFieldList();

	}

	private void FillUpJTextFieldList(){
		JTextFieldCargoList.add(textNomeCargo);
		JTextFieldCargoList.add(textSalarioCargo);

		for(JTextField field1 : JTextFieldCargoList){
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			field1.setForeground(RedColor);
		}
	}

	private void FillCargoBean(CargoBean cb){
		cb.setNomeCargo(textNomeCargo.getText());
		cb.setDescricaoCargo(DescriptionCargoTextPane.getText());
		cb.setAcessoSistemaPermissao(gm.getCheck(acessoSistemaCheck));
		cb.setCadastroConsultaCliPermissao(gm.getCheck(ClienteCheck));
		cb.setCadastroConsultaFuncPermissao(gm.getCheck(FuncCheck));
		cb.setCadastroConsultaCargoPermissao(gm.getCheck(CargoCheck));
		cb.setCadastroConsultaFornecPermissao(gm.getCheck(FornCheck));
		cb.setCadastroConsulta_Prod_Livro_Permissao(gm.getCheck(ProdLivroCheck));
		cb.setCadastroConsultaContatoClientePermissao(gm.getCheck(ProdLivroCheck));
		cb.setVendasPermissao(gm.getCheck(VendasCheck));
		cb.setFunc(func);

		try {
			String salarioConversion = textSalarioCargo.getText();
			NumberFormat nf = NumberFormat.getInstance();
			Number n;
			n = nf.parse(salarioConversion);
			cb.setSalarioCargo(n.floatValue());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

	}

	public void verifyCargoFields(){
		GeneralMethods.verifyTextField(textNomeCargo);
		GeneralMethods.verifyTextField(textSalarioCargo);
		GeneralMethods.verifyOptionalTextPane(DescriptionCargoTextPane);

	}

	public void FillUpEditCargo(CargoBean cb){
		this.cb = cb;

		textNomeCargo.setText(cb.getNomeCargo());
		
		//float salaryValue = cb.getSalarioCargo();
		
		//Double salaryDouble = new Double(salaryValue.toString()); 
		//String salarioConverted = String.valueOf(salaryValue);
		textSalarioCargo.setText(String.valueOf(cb.getSalarioCargo()));
		//System.out.println(salarioConverted);
		DescriptionCargoTextPane.setText(cb.getDescricaoCargo());

		boolean isSysAcessMarked = cb.getCadastroConsultaCliPermissao().equals("Yes");
		acessoSistemaCheck.setSelected(isSysAcessMarked);
		boolean isCliMarked = cb.getCadastroConsultaCliPermissao().equals("Yes");
		ClienteCheck.setSelected(isCliMarked);
		boolean isFuncMarked = cb.getCadastroConsultaFuncPermissao().equals("Yes");
		FuncCheck.setSelected(isFuncMarked);
		boolean isCargoMarked = cb.getCadastroConsultaCargoPermissao().equals("Yes");
		CargoCheck.setSelected(isCargoMarked);
		boolean isFornecMarked = cb.getCadastroConsultaFornecPermissao().equals("Yes");
		FornCheck.setSelected(isFornecMarked);
		boolean isProdLivroMarked = cb.getCadastroConsulta_Prod_Livro_Permissao().equals("Yes");
		ProdLivroCheck.setSelected(isProdLivroMarked);
		boolean isContatoCliMarked = cb.getCadastroConsultaContatoClientePermissao().equals("Yes");
		ContatoClienteCheck.setSelected(isContatoCliMarked);
		boolean isVendaMarked = cb.getVendasPermissao().equals("Yes");
		VendasCheck.setSelected(isVendaMarked);

		verifyCargoFields();
	}
	
	public void EnabledIfSysAccessChecked(){
		
		boolean EnableSystemChecks = acessoSistemaCheck.isSelected();

		acessoSistemaCheck.setEnabled(EnableSystemChecks);
		ClienteCheck.setEnabled(EnableSystemChecks);
		FuncCheck.setEnabled(EnableSystemChecks);
		CargoCheck.setEnabled(EnableSystemChecks);
		FornCheck.setEnabled(EnableSystemChecks);
		ProdLivroCheck.setEnabled(EnableSystemChecks);
		ContatoClienteCheck.setEnabled(EnableSystemChecks);
		VendasCheck.setEnabled(EnableSystemChecks);
	}

	public void DisableFields(){
		GeneralMethods.DisableFields(CadastroCargoPanel);
	}
	
	public void EnableFields(){
		GeneralMethods.EnableFields(CadastroCargoPanel);
	}
	
	public static void setFuncionario(FuncionarioBean f) {
		func = f;
	}
}
