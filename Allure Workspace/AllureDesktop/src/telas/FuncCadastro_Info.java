package telas;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utilities.GeneralMethods;
import utilities.FieldMasks;
import utilities.FieldsValidation;
import utilities.ImagePanel;
import utilities.TextFieldLimiter;

import acessoDadosBeans.CargoBean;
import acessoDadosBeans.FuncionarioBean;
import acessoDadosBeans.PedidoVendaRegistroBean;

import com.toedter.calendar.JDateChooser;

import dataAcessObjects.CargoDAO;
import dataAcessObjects.FuncionarioDAO;
import javax.swing.JPasswordField;
import java.awt.CardLayout;
import javax.swing.JScrollPane;

public class FuncCadastro_Info extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final JPanel contentPanel = new JPanel();

	Color GreenColor = (new Color(50, 205, 50));
	Color RedColor = Color.RED;
	Color OrangeColor = Color.ORANGE;

	GeneralMethods ccm = new GeneralMethods();
	FuncionarioBean f = new FuncionarioBean();

	ArrayList<JTextField> JTextFieldListFunc = new ArrayList<JTextField>();
	ArrayList<JFormattedTextField> JFormattedTextFieldListFunc = new ArrayList<JFormattedTextField>();


	//CadastroFuncPanel component initialization

	JPanel CadastroFuncPanel;
	ImagePanel previewPanel;

	JButton PasswordCheckButtonFunc;
	JButton CadastrarButton_Func;
	JButton LimparButton_Func;
	JButton AlterarButton_Func;
	JButton FilechooserButton;

	JTextField textNome_Func;
	JTextField textEmail_Func;
	JTextField textLogradouro_Func;
	JTextField textNumero_Func;
	JTextField textBairro_Func;
	JTextField textCidade_Func;
	JTextField textComplemento_Func;

	JTextField textRG_Func;
	JFormattedTextField formattedCPF_Func;
	JFormattedTextField formattedTel_Func;
	JFormattedTextField formattedCEP_Func;

	JDateChooser data_Nasc_Func;


	JComboBox<Object> estadoCombo_Func;
	static JComboBox<Object> cargoCombo_Func;

	JRadioButton RadioF_Func = new JRadioButton("F");
	JRadioButton RadioM_Func = new JRadioButton("M");
	private ButtonGroup masc_fem_Func;

	byte[] Photo_Func;
	//byte[] byteArray = null;
	JPasswordField generateSenhaFieldFunc;
	private JTextField ChooseFileField;
	JTextField textInfoEstado_Func;
	JButton ExcluirButton_Func;
	byte[] Photo_Info_Func;

	//RelatorioFuncPanel Elements

	JPanel RelatorioFuncPanel;
	private static JTable VendasFuncTable;
	JButton VerifySellButtonFunc;
	JButton EditarButtonFunc;

	JLabel lblDataCadastroInfoFunc;
	JLabel lblSituacaoCliInfoFunc;
	JLabel ResicaoDataFunc;
	JLabel lblCodFuncInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FuncCadastro_Info dialog = new FuncCadastro_Info();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FuncCadastro_Info() {
		setBounds(100, 100, 1043, 603);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);



		getContentPane().setLayout(new CardLayout(0, 0));


		//-------------\/------------CadastroFuncPanel elements-----------\/------------------------

		CadastroFuncPanel = new JPanel();
		getContentPane().add(CadastroFuncPanel, "name_737634223173517");
		CadastroFuncPanel.setLayout(null);
		CadastroFuncPanel.setVisible(false);

		previewPanel = new ImagePanel(this.Photo_Func);
		previewPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		previewPanel.setBounds(758, 39, 241, 265);
		CadastroFuncPanel.add(previewPanel);

		/**
		 * Labels.
		 */

		JLabel lblNomeFunc = new JLabel("Nome:");
		lblNomeFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNomeFunc.setBounds(22, 39, 60, 20);
		CadastroFuncPanel.add(lblNomeFunc);

		JLabel lblRgFunc = new JLabel("RG:");
		lblRgFunc.setBounds(515, 70, 39, 20);
		lblRgFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblRgFunc.setToolTipText("Campo onde se insere o numero do Registro Geral do Funcionario!");
		CadastroFuncPanel.add(lblRgFunc);

		JLabel lblEmailFunc = new JLabel("Email:");
		lblEmailFunc.setBounds(280, 39, 49, 20);
		lblEmailFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEmailFunc.setToolTipText("Campo onde se insere o Endere\u00E7o eletronico do Funcente (Campo opcional)!");
		CadastroFuncPanel.add(lblEmailFunc);

		JLabel lblTelefoneFunc = new JLabel("Telefone:");
		lblTelefoneFunc.setBounds(515, 35, 69, 28);
		lblTelefoneFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblTelefoneFunc.setToolTipText("Campo onde se insere o numero de contato do Funcente!");
		CadastroFuncPanel.add(lblTelefoneFunc);

		JLabel lblCpfFunc = new JLabel("CPF:");
		lblCpfFunc.setBounds(22, 70, 46, 20);
		lblCpfFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCpfFunc.setToolTipText("Campo onde se insere o numero do Cadastro de Pessoa Fisica do Funcente!");
		CadastroFuncPanel.add(lblCpfFunc);

		JLabel lblSexoFunc = new JLabel("Sexo:");
		lblSexoFunc.setBounds(280, 70, 46, 20);
		lblSexoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSexoFunc.setToolTipText("Campo onde se insere o genero da pessoa (M = Masculino, F = Feminino)");
		CadastroFuncPanel.add(lblSexoFunc);

		JLabel lblDataDeNascimentoFunc = new JLabel("Data de Nascimento:");
		lblDataDeNascimentoFunc.setToolTipText("Data de Nascimento!");
		lblDataDeNascimentoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataDeNascimentoFunc.setBounds(22, 104, 183, 18);
		CadastroFuncPanel.add(lblDataDeNascimentoFunc);

		JLabel lblLogradouroFunc = new JLabel("Logradouro:");
		lblLogradouroFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblLogradouroFunc.setBounds(22, 231, 111, 24);
		CadastroFuncPanel.add(lblLogradouroFunc);

		JLabel lblNumeroFunc = new JLabel("N\u00BA:");
		lblNumeroFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNumeroFunc.setBounds(280, 233, 70, 18);
		CadastroFuncPanel.add(lblNumeroFunc);

		JLabel lblBairroFunc = new JLabel("Bairro:");
		lblBairroFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblBairroFunc.setBounds(22, 272, 60, 20);
		CadastroFuncPanel.add(lblBairroFunc);

		JLabel lblCidadeFunc = new JLabel("Cidade:");
		lblCidadeFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCidadeFunc.setBounds(260, 271, 69, 20);
		CadastroFuncPanel.add(lblCidadeFunc);

		JLabel lblEstadoFunc = new JLabel("Estado:");
		lblEstadoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEstadoFunc.setBounds(515, 271, 69, 20);
		CadastroFuncPanel.add(lblEstadoFunc);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSenha.setBounds(339, 103, 67, 20);
		CadastroFuncPanel.add(lblSenha);

		JLabel lblComplementoFunc = new JLabel("Complemento:");
		lblComplementoFunc.setToolTipText("Complemento:");
		lblComplementoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblComplementoFunc.setBounds(455, 235, 123, 18);
		CadastroFuncPanel.add(lblComplementoFunc);

		JLabel lblEndereco = new JLabel("Informe Endere\u00E7o por meio do CEP:");
		lblEndereco.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEndereco.setBounds(141, 190, 292, 28);
		CadastroFuncPanel.add(lblEndereco);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCargo.setBounds(22, 144, 60, 25);
		CadastroFuncPanel.add(lblCargo);

		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFoto.setBounds(758, 323, 46, 20);
		CadastroFuncPanel.add(lblFoto);

		JLabel lblCamposOpcionais = new JLabel();
		lblCamposOpcionais.setText("Campos Opcionais!");
		lblCamposOpcionais.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposOpcionais.setBounds(59, 529, 167, 21);
		CadastroFuncPanel.add(lblCamposOpcionais);

		JLabel lblCamposObrigatorios = new JLabel();
		lblCamposObrigatorios.setText("Campos Obrigatorios!");
		lblCamposObrigatorios.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposObrigatorios.setBounds(59, 497, 183, 21);
		CadastroFuncPanel.add(lblCamposObrigatorios);

		JLabel lblRedColor = new JLabel();
		lblRedColor.setOpaque(true);
		lblRedColor.setBackground(Color.RED);
		lblRedColor.setBounds(22, 505, 20, 4);
		CadastroFuncPanel.add(lblRedColor);

		JLabel lblOrangeColor = new JLabel();
		lblOrangeColor.setOpaque(true);
		lblOrangeColor.setBackground(Color.ORANGE);
		lblOrangeColor.setBounds(22, 538, 20, 4);
		CadastroFuncPanel.add(lblOrangeColor);

		/**
		 * TextFields.
		 */

		textNome_Func = new JTextField();
		textNome_Func.setBounds(81, 40, 161, 20);
		CadastroFuncPanel.add(textNome_Func);

		textNome_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textNome_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textNome_Func);


			}
		});

		textNome_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textNome_Func.setFocusable(isCursorSet());
				textNome_Func.setCaretPosition(0);
				if (textNome_Func.getForeground().equals(RedColor)){


				}else{
					textNome_Func.setForeground(GreenColor);
					textNome_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textNome_Func.getForeground().equals(GreenColor)){
					textNome_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textNome_Func.setForeground(Color.black);

				}
			}
		});


		textEmail_Func = new JTextField();
		textEmail_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
		textEmail_Func.setBounds(339, 40, 152, 20);
		CadastroFuncPanel.add(textEmail_Func);
		textEmail_Func.setColumns(10);

		textEmail_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				//FieldsValidation.EmailValidation(textEmailFunc);

				String caracteres = "=ABCDEFGHIJKLMNOPQRSTUVWXYZ#$%¨&*()/!?{}[]*+-'£¢¬,|\"\";:<>º°? ";
				if (caracteres.contains(e.getKeyChar()+"")){
					e.consume();	
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				FieldsValidation.EmailValidation(textEmail_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				FieldsValidation.EmailValidation(textEmail_Func);
			}

		});

		textEmail_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textEmail_Func.setFocusable(isCursorSet());
				textEmail_Func.setCaretPosition(0);
				if (textEmail_Func.getForeground().equals(OrangeColor)){


				}else{
					textEmail_Func.setForeground(GreenColor);
					textEmail_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textEmail_Func.getForeground().equals(GreenColor)){
					textEmail_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textEmail_Func.setForeground(Color.black);

				}
			}
		});

		textBairro_Func = new JTextField();
		textBairro_Func.setBounds(98, 272, 152, 20);
		CadastroFuncPanel.add(textBairro_Func);
		textBairro_Func.setColumns(10);

		textBairro_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textBairro_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textBairro_Func);

			}
		});

		textBairro_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textBairro_Func.setFocusable(isCursorSet());
				textBairro_Func.setCaretPosition(0);
				if (textBairro_Func.getForeground().equals(RedColor)){


				}else{
					textBairro_Func.setForeground(GreenColor);
					textBairro_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textBairro_Func.getForeground().equals(GreenColor)){
					textBairro_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textBairro_Func.setForeground(Color.black);

				}
			}
		});


		textLogradouro_Func = new JTextField();
		textLogradouro_Func.setBounds(133, 233, 123, 21);
		CadastroFuncPanel.add(textLogradouro_Func);
		textLogradouro_Func.setColumns(10);

		textLogradouro_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textLogradouro_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textLogradouro_Func);


			}
		});

		textLogradouro_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textLogradouro_Func.setFocusable(isCursorSet());
				textLogradouro_Func.setCaretPosition(0);
				if (textLogradouro_Func.getForeground().equals(RedColor)){


				}else{
					textLogradouro_Func.setForeground(GreenColor);
					textLogradouro_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textLogradouro_Func.getForeground().equals(GreenColor)){
					textLogradouro_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textLogradouro_Func.setForeground(Color.black);

				}
			}
		});

		textNumero_Func = new JTextField();
		textNumero_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "=ABCDEFGHIJKLMNOPQRSTUVWXYZ#$%¨&*()/!?{}[]*+-'£¢¬,|\"\";:<>º°? ";
				if (caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(textNumero_Func.getText().length()>=5){

					textNumero_Func.setText(textNumero_Func.getText().substring(0, 4));
				}
			}
		});
		textNumero_Func.setBounds(320, 233, 111, 20);
		CadastroFuncPanel.add(textNumero_Func);

		textNumero_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textNumero_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textNumero_Func);


			}
		});

		textNumero_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textNumero_Func.setFocusable(isCursorSet());
				textNumero_Func.setCaretPosition(0);
				if (textNumero_Func.getForeground().equals(RedColor)){


				}else{
					textNumero_Func.setForeground(GreenColor);
					textNumero_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textNumero_Func.getForeground().equals(GreenColor)){
					textNumero_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textNumero_Func.setForeground(Color.black);

				}
			}
		});

		textComplemento_Func = new JTextField();
		textComplemento_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
		textComplemento_Func.setBounds(582, 233, 135, 20);
		CadastroFuncPanel.add(textComplemento_Func);
		textComplemento_Func.setColumns(10);

		textComplemento_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyOptionalTextField(textComplemento_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyOptionalTextField(textComplemento_Func);
			}
		});

		textCidade_Func = new JTextField();
		textCidade_Func.setBounds(336, 272, 155, 20);
		CadastroFuncPanel.add(textCidade_Func);
		textCidade_Func.setColumns(10);

		textCidade_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textCidade_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textCidade_Func);


			}
		});

		textCidade_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textCidade_Func.setFocusable(isCursorSet());
				textCidade_Func.setCaretPosition(0);
				if (textCidade_Func.getForeground().equals(RedColor)){


				}else{
					textCidade_Func.setForeground(GreenColor);
					textCidade_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textCidade_Func.getForeground().equals(GreenColor)){
					textCidade_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textCidade_Func.setForeground(Color.black);

				}
			}
		});


		ChooseFileField = new JTextField();
		ChooseFileField.setEnabled(false);
		ChooseFileField.setBounds(814, 322, 135, 20);
		CadastroFuncPanel.add(ChooseFileField);
		ChooseFileField.setColumns(10);

		/**
		 * Formatted TextFields.
		 */

		textRG_Func = new JTextField();
		textRG_Func.setBounds(565, 71, 152, 20);
		textRG_Func.setDocument(new TextFieldLimiter(13));
		CadastroFuncPanel.add(textRG_Func);

		textRG_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textRG_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textRG_Func);


			}
		});

		textRG_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textRG_Func.setFocusable(isCursorSet());
				textRG_Func.setCaretPosition(0);
				if (textRG_Func.getForeground().equals(RedColor)){


				}else{
					textRG_Func.setForeground(GreenColor);
					textRG_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textRG_Func.getForeground().equals(GreenColor)){
					textRG_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textRG_Func.setForeground(Color.black);

				}
			}
		});


		formattedCPF_Func = new JFormattedTextField();
		formattedCPF_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
		formattedCPF_Func.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				cpfActionPerformed(e);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				cpfActionPerformed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				cpfActionPerformed(e);
			}
		});
		formattedCPF_Func.setBounds(101, 71, 141, 20);
		CadastroFuncPanel.add(formattedCPF_Func);

		FieldMasks.CpfMask(formattedCPF_Func);

		formattedCPF_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedCPF_Func.setFocusable(isCursorSet());
				formattedCPF_Func.setCaretPosition(0);
				if (formattedCPF_Func.getForeground().equals(RedColor)){


				}else{
					formattedCPF_Func.setForeground(GreenColor);
					formattedCPF_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (formattedCPF_Func.getForeground().equals(GreenColor)){
					formattedCPF_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					formattedCPF_Func.setForeground(Color.black);

				}
			}
		});


		formattedTel_Func = new JFormattedTextField();
		formattedTel_Func.setBounds(594, 40, 123, 20);
		CadastroFuncPanel.add(formattedTel_Func);

		FieldMasks.TelMask(formattedTel_Func);

		formattedTel_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyFormattedField(formattedTel_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyFormattedField(formattedTel_Func);
			}
		});

		formattedTel_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedTel_Func.setFocusable(isCursorSet());
				formattedTel_Func.setCaretPosition(0);
				if (formattedTel_Func.getForeground().equals(RedColor)){


				}else{
					formattedTel_Func.setForeground(GreenColor);
					formattedTel_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (formattedTel_Func.getForeground().equals(GreenColor)){
					formattedTel_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					formattedTel_Func.setForeground(Color.black);

				}
			}
		});

		formattedCEP_Func = new JFormattedTextField();
		formattedCEP_Func.setBounds(432, 195, 152, 20);
		CadastroFuncPanel.add(formattedCEP_Func);

		FieldMasks.CepMask(formattedCEP_Func);

		formattedCEP_Func.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				validarCEP();
				GeneralMethods.verifyTextField(textLogradouro_Func);
				GeneralMethods.verifyTextField(textBairro_Func);
				GeneralMethods.verifyTextField(textCidade_Func);
			}
		});

		formattedCEP_Func.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedCEP_Func.setFocusable(isCursorSet());
				formattedCEP_Func.setCaretPosition(0);
				if (formattedCEP_Func.getForeground().equals(RedColor)){


				}else{
					formattedCEP_Func.setForeground(GreenColor);
					formattedCEP_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (formattedCEP_Func.getForeground().equals(GreenColor)){
					formattedCEP_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					formattedCEP_Func.setForeground(Color.black);

				}
			}
		});

		/**
		 * Radio Buttons.
		 */

		RadioM_Func.setBounds(349, 69, 49, 23);
		RadioM_Func.setFont(new Font("Gabriola", Font.PLAIN, 25));
		CadastroFuncPanel.add(RadioM_Func);

		RadioF_Func.setBounds(423, 70, 49, 21);
		RadioF_Func.setFont(new Font("Gabriola", Font.PLAIN, 25));
		CadastroFuncPanel.add(RadioF_Func);

		masc_fem_Func = new ButtonGroup();
		masc_fem_Func.clearSelection();
		masc_fem_Func.add(RadioF_Func);
		masc_fem_Func.add(RadioM_Func);


		/**
		 * Date choosers.
		 */

		data_Nasc_Func = new JDateChooser("dd/MM/yyyy", "##/##/####", ' ');
		data_Nasc_Func.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyDate(data_Nasc_Func);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyDate(data_Nasc_Func);
			}
		});
		data_Nasc_Func.setBounds(198, 102, 117, 20);
		CadastroFuncPanel.add(data_Nasc_Func);
		
		FieldsValidation.DateValidationInterval(data_Nasc_Func);

		/**
		 * ComboBoxes.
		 */

		estadoCombo_Func = new JComboBox<Object>();
		estadoCombo_Func.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		estadoCombo_Func.setBounds(582, 272, 135, 20);
		CadastroFuncPanel.add(estadoCombo_Func);

		cargoCombo_Func = new JComboBox<Object>();
		cargoCombo_Func.setBounds(92, 147, 141, 20);
		CadastroFuncPanel.add(cargoCombo_Func);

		carregarComboCargoAtivo();

		/**
		 * Buttons.
		 */

		PasswordCheckButtonFunc = new JButton();
		PasswordCheckButtonFunc.setBounds(534, 101, 26, 22);
		CadastroFuncPanel.add(PasswordCheckButtonFunc);

		try {
			Image img = ImageIO.read(getClass().getResource("SourcePics/password reveal button.png"));
			PasswordCheckButtonFunc.setIcon(new ImageIcon(img));
		} 
		catch (IOException ex) {}

		PasswordCheckButtonFunc.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				generateSenhaFieldFunc.setEchoChar((char)0);	
			}
			@Override
			public void mouseReleased(MouseEvent e){
				generateSenhaFieldFunc.setEchoChar('*');
			} 
		});

		CadastrarButton_Func = new JButton("Cadastrar");
		CadastrarButton_Func.setFont(new Font("Gabriola", Font.PLAIN, 20));
		CadastrarButton_Func.setBounds(479, 522, 98, 28);
		CadastroFuncPanel.add(CadastrarButton_Func);

		CadastrarButton_Func.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FillFuncBean(f);


				if (GeneralMethods.verifyTextField(textNome_Func) || GeneralMethods.verifyTextField(textLogradouro_Func) ||
						GeneralMethods.verifyTextField(textBairro_Func) || GeneralMethods.verifyTextField(textNumero_Func) || GeneralMethods.verifyTextField(textRG_Func) ||
						GeneralMethods.verifyTextField(textCidade_Func) || GeneralMethods.verifyFormattedField(formattedTel_Func)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					if (validarCpf()) {

						if(validarEstado_Sexo()){

							String pass = String.valueOf(generateSenhaFieldFunc.getPassword());
							f.setSenha_func(pass);
							

							if(verificar_Cargo_Senha()){
								if(verifyCargoStatus() == true){
								
								boolean resposta = FuncionarioDAO.verificarCpf(f.getCpf_func()).getCpf_func() == null;
								System.out.println(FuncionarioDAO.verificarCpf(f.getCpf_func()).getCpf_func());
								System.out.println(f.getCpf_func());
								if (resposta == true) {

									java.sql.Date dataNascFuncSql = new java.sql.Date(data_Nasc_Func.getDate().getTime());
									f.setDataNasc_func(dataNascFuncSql);
									FuncionarioDAO.cadastrarFuncionario(f);
									f = new FuncionarioBean();
									SystemTabs.atualizarFuncTable();
									SystemTabs.atualizarCargoTable();
									GeneralMethods.limpar(CadastroFuncPanel);
									dispose();
									FillUpJTextFieldList();
									FillUpJFormattedTextFieldList();
									/*Main.atualizarTableFuncionario();
		            			this.dispose();*/

								}else{
									JOptionPane.showMessageDialog(null, "CPF já existe!");
								}
							
								}
					
						}
					}
					}else{
						JOptionPane.showMessageDialog(null, "CPF Invalido!");
				}
			}
			}
		});

		LimparButton_Func = new JButton("Limpar");
		LimparButton_Func.setFont(new Font("Gabriola", Font.PLAIN, 20));
		LimparButton_Func.setBounds(368, 522, 98, 28);
		CadastroFuncPanel.add(LimparButton_Func);

		LimparButton_Func.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.limpar(CadastroFuncPanel);
				GeneralMethods.verifyOptionalTextField(textComplemento_Func);
				FieldsValidation.EmailValidation(textEmail_Func);
				FillUpJTextFieldList();
				FillUpJFormattedTextFieldList();

			}
		});

		AlterarButton_Func = new JButton("Alterar");
		AlterarButton_Func.setFont(new Font("Gabriola", Font.PLAIN, 20));
		AlterarButton_Func.setBounds(260, 522, 98, 28);
		AlterarButton_Func.setEnabled(false);
		CadastroFuncPanel.add(AlterarButton_Func);

		AlterarButton_Func.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FillFuncBean(f);


				if (GeneralMethods.verifyTextField(textNome_Func) || GeneralMethods.verifyTextField(textLogradouro_Func) ||
						GeneralMethods.verifyTextField(textBairro_Func) || GeneralMethods.verifyTextField(textNumero_Func) || GeneralMethods.verifyTextField(textRG_Func) ||
						GeneralMethods.verifyTextField(textCidade_Func) || GeneralMethods.verifyFormattedField(formattedTel_Func)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					if (validarCpf()) {

						if(validarEstado_Sexo()){

							String pass = String.valueOf(generateSenhaFieldFunc.getPassword());
							f.setSenha_func(pass);

							if(verificar_Cargo_Senha()){
								if(verifyCargoStatus() == true){

								java.sql.Date dataNascFuncSql = new java.sql.Date(data_Nasc_Func.getDate().getTime());
								f.setDataNasc_func(dataNascFuncSql);
								FuncionarioDAO.AlterarFuncionario(f);
								f = new FuncionarioBean();
								SystemTabs.atualizarFuncTable();
								//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
								//ccm = new CadastroConsultaMethods();
								GeneralMethods.limpar(CadastroFuncPanel);
								dispose();
								FillUpJTextFieldList();
								FillUpJFormattedTextFieldList();
								/*Main.atualizarTableFuncionario();
		            			this.dispose();*/

							}
							}
							}
				
						}else{
						JOptionPane.showMessageDialog(null, "CPF Invalido!");
					}

				}
			}
		});

		EditarButtonFunc = new JButton("Editar");
		EditarButtonFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.EnableFields(CadastroFuncPanel);
				AlterarButton_Func.setEnabled(true);
				verifyFuncFields();
				FilechooserButton.setEnabled(true);
				formattedCPF_Func.setEnabled(false);
				FuncCadastro_Info.this.setTitle("Alterar Funcionario(a)");
			}
		});
		EditarButtonFunc.setFont(new Font("Gabriola", Font.PLAIN, 20));
		EditarButtonFunc.setEnabled(false);
		EditarButtonFunc.setBounds(594, 522, 98, 28);
		CadastroFuncPanel.add(EditarButtonFunc);
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 180, 709, 8);
		CadastroFuncPanel.add(separator);

		generateSenhaFieldFunc = new JPasswordField();
		generateSenhaFieldFunc.setEchoChar('*');
		generateSenhaFieldFunc.setBounds(410, 102, 123, 20);
		CadastroFuncPanel.add(generateSenhaFieldFunc);

		FilechooserButton = new JButton("...");
		FilechooserButton.setBounds(947, 322, 33, 20);
		CadastroFuncPanel.add(FilechooserButton);

		FilechooserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				escolherFoto();

				/*FilechooserWindow fcw = new FilechooserWindow();
				fcw.setVisible(true);
				ChooseFileField.write(new BufferedWriter(new FileWriter(fcw.fileChooser.getSelectedFile().getAbsolutePath() + fcw.fileChooser.getFileFilter().getDescription().replace("All Files", ""))));*/
			}
		});



		FillUpJTextFieldList();
		FillUpJFormattedTextFieldList();
		FieldsValidation.EmailValidation(textEmail_Func);
		
	
		//-------------\/------------RelatorioFuncPanel elements-----------\/------------------------	

		RelatorioFuncPanel = new JPanel();
		getContentPane().add(RelatorioFuncPanel, "name_246598893611816");
		RelatorioFuncPanel.setLayout(null);

		JScrollPane VendasCliScroll = new JScrollPane();
		VendasCliScroll.setBounds(188, 59, 616, 180);
		RelatorioFuncPanel.add(VendasCliScroll);

		VendasFuncTable = new JTable();
		VendasFuncTable.setBounds(50, 50, 50, 50);
		VendasCliScroll.setViewportView(VendasFuncTable);

		VendasFuncTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting())
				{
					boolean rowsAreSelected = VendasFuncTable.getSelectedRowCount() > 0;

					VerifySellButtonFunc.setEnabled(rowsAreSelected);
				}
			}
		});


		VerifySellButtonFunc = new JButton("Ver Venda");
		VerifySellButtonFunc.setEnabled(false);
		VerifySellButtonFunc.setFont(new Font("Gabriola", Font.PLAIN, 20));
		VerifySellButtonFunc.setBounds(751, 420, 121, 28);
		RelatorioFuncPanel.add(VerifySellButtonFunc);

		VerifySellButtonFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pedido_Info vd = new Pedido_Info();
				vd.setVisible(true);

				PedidoVendaRegistroBean pvr = new PedidoVendaRegistroBean();
				int rowSelected = VendasFuncTable.getSelectedRow();
				int conversion = Integer.parseInt(VendasFuncTable.getValueAt(rowSelected, 0).toString());

				pvr.setId_PedidoVendaRegistro(conversion);
				//PedidoVendaRegistroDAO.carregarPedidoItemTable(table, pvr)
				Pedido_Info.LoadVendaDetails(pvr);

				vd.lblValorTotal.setText(String.valueOf(pvr.getValor_Total()));
				//vd.lblCliWhoMade.setText(VendasCliTable.getvalueAt(rowSelected, ()));

			}
		});

		JLabel lblHistoricoDeVendas = new JLabel("Historico de Vendas:");
		lblHistoricoDeVendas.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblHistoricoDeVendas.setBounds(377, 25, 185, 23);
		RelatorioFuncPanel.add(lblHistoricoDeVendas);

		JLabel lblDataCadastro = new JLabel("Data de Cadastro:");
		lblDataCadastro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastro.setBounds(22, 360, 152, 20);
		RelatorioFuncPanel.add(lblDataCadastro);

		lblDataCadastroInfoFunc = new JLabel("");
		lblDataCadastroInfoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastroInfoFunc.setBounds(180, 360, 214, 20);
		RelatorioFuncPanel.add(lblDataCadastroInfoFunc);

		JLabel lblDataDeResicaoFunc = new JLabel("Data de Resi\u00E7\u00E3o:");
		lblDataDeResicaoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataDeResicaoFunc.setBounds(421, 357, 135, 20);
		RelatorioFuncPanel.add(lblDataDeResicaoFunc);


		JLabel lblSituacaoFunc = new JLabel("Situa\u00E7\u00E3o/Status:");
		lblSituacaoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoFunc.setBounds(22, 391, 135, 20);
		RelatorioFuncPanel.add(lblSituacaoFunc);

		lblSituacaoCliInfoFunc = new JLabel("");
		lblSituacaoCliInfoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoCliInfoFunc.setBounds(180, 391, 152, 20);
		RelatorioFuncPanel.add(lblSituacaoCliInfoFunc);

		ResicaoDataFunc = new JLabel("");
		ResicaoDataFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		ResicaoDataFunc.setBounds(562, 357, 202, 20);
		RelatorioFuncPanel.add(ResicaoDataFunc);


		JLabel lblCodFunc = new JLabel("N\u00B0. Funcion\u00E1rio:");
		lblCodFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCodFunc.setBounds(421, 388, 128, 23);
		RelatorioFuncPanel.add(lblCodFunc);

		lblCodFuncInfo = new JLabel("");
		lblCodFuncInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCodFuncInfo.setBounds(561, 388, 128, 23);
		RelatorioFuncPanel.add(lblCodFuncInfo);

	}

	//-------------/\------------InfoFuncFuncPanel elements-----------/\------------------------



	private void FillUpJTextFieldList(){
		JTextFieldListFunc.add(textNome_Func);
		JTextFieldListFunc.add(textBairro_Func);
		JTextFieldListFunc.add(textCidade_Func);
		JTextFieldListFunc.add(textLogradouro_Func);
		JTextFieldListFunc.add(textNumero_Func);
		JTextFieldListFunc.add(textRG_Func);

		for(JTextField field1 : JTextFieldListFunc){
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			field1.setForeground(RedColor);
		}
		//verifyTextField();
	}

	private void FillUpJFormattedTextFieldList(){
		JFormattedTextFieldListFunc.add(formattedCEP_Func);
		JFormattedTextFieldListFunc.add(formattedCPF_Func);
		JFormattedTextFieldListFunc.add(formattedTel_Func);

		for(JFormattedTextField formfield1 : JFormattedTextFieldListFunc){
			formfield1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formfield1.setForeground(RedColor);
		}
	}

	private void validarCEP(){
		if(GeneralMethods.buscaCep(formattedCEP_Func, textLogradouro_Func, textCidade_Func, textBairro_Func, estadoCombo_Func) == true){
			formattedCEP_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedCEP_Func.setForeground(GreenColor);
		}else{
			formattedCEP_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedCEP_Func.setForeground(RedColor);
		}


	}

	private boolean validarEstado_Sexo(){
		if(f.getSexo_func() != null && f.getEstado_func() != "Selecione..."){
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "Verifique o estado e/ou Sexo");
			return false;
		}
	}

	private boolean verificar_Cargo_Senha(){
		if(f.getId_cargo_fk() > 0 && f.getSenha_func().length()!=0){
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "Verifique Cargo e/ou Senha");
			return false;
		}
	}

	private void cpfActionPerformed(KeyEvent evt) {

		f.setCpf_func(formattedCPF_Func.getText());
		//System.out.println(cpfField.getText());
		if (validarCpf()) {
			formattedCPF_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedCPF_Func.setForeground(GreenColor);
		} else {
			formattedCPF_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedCPF_Func.setForeground(RedColor);
		}

	}

	private boolean validarCpf() {

		if (FieldsValidation.validateFuncCPF(f)) {

			return true;
		} else {

			return false;
		}

	}

	private void FillFuncBean(FuncionarioBean f){
		f.setNome_func(textNome_Func.getText());
		f.setEmail_func(textEmail_Func.getText());
		f.setTelefone_func(formattedTel_Func.getText());
		f.setCpf_func(formattedCPF_Func.getText());
		f.setSexo_func(ccm.getRadio(RadioM_Func, RadioF_Func));
		f.setRg_func(textRG_Func.getText());
		f.setCep_func(formattedCEP_Func.getText());
		f.setLogradouro_func(textLogradouro_Func.getText());
		f.setNumero_func(textNumero_Func.getText());
		f.setComplemento_func(textComplemento_Func.getText());
		f.setBairro_func(textBairro_Func.getText());
		f.setCidade_func(textCidade_Func.getText());
		f.setEstado_func(estadoCombo_Func.getSelectedItem().toString());
		f.setFoto(Photo_Func);
		f.setId_cargo_fk(cargoCombo_Func.getSelectedIndex());
		
//		if (cargoCombo_Func.getSelectedItem().equals("Selecione...")) {
//			//throw new IllegalArgumentException("Selecione um cargo");
//			JOptionPane.showMessageDialog(null, "Selecione um cargo");
//		}
////		if (cargoCombo_Func.getSelectedItem() instanceof String) {
////			//throw new IllegalArgumentException("Selecione um cargo");
////			JOptionPane.showMessageDialog(null, "Selecione um cargo");
////		}
//		CargoBean cargo = (CargoBean)cargoCombo_Func.getSelectedItem();
//		
		
	}
	
	private boolean verifyCargoStatus(){
		boolean isCargoValid = false;
		String statusValueFromDB = String.valueOf(CargoDAO.returnAtivoInativoById(cargoCombo_Func.getSelectedIndex()));
		//String NomeCargo = String.valueOf(cargoCombo_Func.getSelectedItem());
		//System.out.println(NomeCargo);
		System.out.println(statusValueFromDB);
		
		if(statusValueFromDB.equals("Ativo")){
			isCargoValid = true;
			
		}else if(statusValueFromDB.equals("Inativo")){
			String nomeCargo = String.valueOf(cargoCombo_Func.getSelectedItem());
			int dialogButton = JOptionPane.showConfirmDialog(null, "O cargo "+nomeCargo+" Se encontra Inativo. Deseja Ativar este Cargo para que o Cadastro possa ser \n efetuado?", null, 0);  

			if (dialogButton == JOptionPane.YES_OPTION) {
				CargoBean cb = new CargoBean();
				cb.setIdCargo(cargoCombo_Func.getSelectedIndex());
				CargoDAO.AtivarCargo(cb);
				SystemTabs.atualizarCargoTable();
				isCargoValid = true;

		} else if(dialogButton == JOptionPane.NO_OPTION){  
			remove(dialogButton);
			isCargoValid = false;

		}
		}
		return isCargoValid;
		
	}
	
//	private void returnID_Edit(){
//		try{
//			int Idcargo = f.getId_cargo_fk();
//			String NomeCargo = CargoDAO.returnIdByName(Idcargo);
//			ListItem NomeItem = new ListItem(NomeCargo);
//			
////			Component[] comp = cargoCombo_Func.getComponents();
////			  for (int i = 0; i < comp.length; i++) {
////			        if (comp[i] instanceof JComboBox<?>) {
////			            ((JComboBox<?>) comp[i]).setSelectedItem(NomeItem);
////			        } 
////			  }
//			Object obj = NomeCargo;
//			cargoCombo_Func.equals(obj);
//			System.out.println(NomeCargo);
//			System.out.println(Idcargo);
//			
//			System.out.println(obj);
//		
//		}catch(NumberFormatException e){
//			//e.printStackTrace();
//		}
//	}

//	@Override
//    public boolean equals(Object obj) {
//		
//        if (obj == null) 
//            return false;
//        if (!(obj instanceof ListItem)) 
//            return false;
//
//        ListItem other = (ListItem) obj;
//        if (this.cargoCombo_Func.equals(other.equals(obj))) 
//            return true;
//
//        return false;
//    }
//	
	
	public void verifyFuncFields(){
		GeneralMethods.verifyTextField(textNome_Func);
		GeneralMethods.verifyTextField(textLogradouro_Func);
		GeneralMethods.verifyTextField(textBairro_Func);
		GeneralMethods.verifyTextField(textRG_Func);
		GeneralMethods.verifyTextField(textCidade_Func);
		GeneralMethods.verifyTextField(textNumero_Func);
		GeneralMethods.verifyFormattedField(formattedTel_Func);
		GeneralMethods.verifyFormattedField(formattedCEP_Func);
		GeneralMethods.verifyOptionalTextField(textComplemento_Func);
		formattedCPF_Func.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FieldsValidation.EmailValidation(textEmail_Func);

	}

	public void DisableFields(){
		GeneralMethods.DisableFields(CadastroFuncPanel);
	}

	public void FillUpEditFunc(FuncionarioBean f){
		this.f = f;

		textNome_Func.setText(f.getNome_func());
		textEmail_Func.setText(f.getEmail_func());
		formattedTel_Func.setText(f.getTelefone_func());
		formattedCPF_Func.setText(f.getCpf_func());
		formattedCPF_Func.setEnabled(false);
		if(f.getSexo_func().toString().equals("M")){
			RadioM_Func.setSelected(true);
			RadioF_Func.setSelected(false);   		    		
		}else{
			RadioF_Func.setSelected(true);
			RadioM_Func.setSelected(false);
		}

		textRG_Func.setText(f.getRg_func());
		data_Nasc_Func.setDate(f.getDataNasc_func());
		formattedCEP_Func.setText(f.getCep_func());
		textLogradouro_Func.setText(f.getLogradouro_func());
		textNumero_Func.setText(f.getNumero_func());
		textComplemento_Func.setText(f.getComplemento_func());
		textBairro_Func.setText(f.getBairro_func());
		textCidade_Func.setText(f.getCidade_func());
		estadoCombo_Func.setSelectedItem(f.getEstado_func().toString());
		generateSenhaFieldFunc.setText(f.getSenha_func());
		cargoCombo_Func.setSelectedIndex(f.getId_cargo_fk());
		Photo_Func = (f.getFoto());
		previewPanel.setImagem(f.getFoto());
		//returnID_Edit();

		verifyFuncFields();
	}
	
	

	private void escolherFoto(){

		this.Photo_Func = GeneralMethods.showTelaEscolheImage(new File("C:\\"), this.previewPanel, this.ChooseFileField, this, "jpg", "gif");

	}



	private void carregarComboCargoAtivo() {
		CargoDAO dao = new CargoDAO();

		List<CargoBean> lista = dao.buscarCargoAtivo();
		cargoCombo_Func.removeAll();
		cargoCombo_Func.addItem("Selecione ...");
		for (CargoBean cargo : lista) {
			cargoCombo_Func.addItem(cargo);
		}
	}

	public static void LoadFuncRelatorio(FuncionarioBean f){
		FuncionarioDAO.carregarVendasFuncTable(VendasFuncTable, f);
	}
}
