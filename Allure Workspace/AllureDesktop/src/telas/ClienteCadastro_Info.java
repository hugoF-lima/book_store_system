package telas;

import java.awt.Color;
import java.awt.Font;
import telas.TelaPrincipal;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import utilities.GeneralMethods;
import utilities.FieldMasks;
import utilities.FieldsValidation;
import utilities.TextFieldLimiter;

import acessoDadosBeans.ClienteBean;
import acessoDadosBeans.FuncionarioBean;
import acessoDadosBeans.PedidoVendaRegistroBean;

import com.toedter.calendar.JDateChooser;

import dataAcessObjects.ClienteDAO;
import dataAcessObjects.PedidoVendaRegistroDAO;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JSeparator;
import java.awt.CardLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteCadastro_Info extends JDialog {

	//ClienteInfo component initialization

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel CadastroClientePanel;
	GeneralMethods ccm = new GeneralMethods();
	ClienteBean c = new ClienteBean();
	TelaPrincipal tp;
	SystemTabs cc;
	ArrayList<JTextField> JTextFieldList = new ArrayList<JTextField>();
	ArrayList<JFormattedTextField> JFormattedTextFieldList = new ArrayList<JFormattedTextField>();
	public static FuncionarioBean func;

	Color GreenColor = (new Color(50, 205, 50));
	Color RedColor = Color.RED;
	Color OrangeColor = Color.ORANGE;

	//CadastroClientePanel component initialization

	JButton CadastrarButtonCli;
	JButton LimparButtonCli_1;
	JButton btnAlterar;

	JTextField textNomeCli;
	JTextField textEmailCli;
	JTextField textLogradouroCli;
	JTextField textNumeroCli;
	JTextField textBairroCli;
	JTextField textCidadeCli;
	JTextField textComplementoCli;

	JTextField textRG_Cli;
	JFormattedTextField formattedCPF_Cli;
	JFormattedTextField formattedTelCli;
	JFormattedTextField formattedCEP_Cli;

	JDateChooser data_Nasc_Cli;

	JComboBox<Object> estadoCombo_Cli;

	JRadioButton RadioF_Cli = new JRadioButton("F");
	JRadioButton RadioM_Cli = new JRadioButton("M");
	private ButtonGroup masc_fem_cli;
	JTextField textInfoEstado;
	JButton ExcluirButtonCli;
	JButton LimparButtonCli;
	JButton EditarButtonCli;

	//RelatorioClientePanel component initialization

	JPanel RelatorioClientePanel;
	private static JTable VendasCliTable;
	JLabel lblCadastradoPor;
	JLabel lblCadastradoPorInfo;
	JLabel lblDataCadastroInfoCli;
	JLabel lblSituacaoCliInfo;
	JLabel ResicaoDataCli;
	JLabel lblCodClienteInfo;


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {

			ClienteCadastro_Info dialog = new ClienteCadastro_Info();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Create the dialog.
	 */

	public ClienteCadastro_Info() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteCadastro_Info.class.getResource("/telas/SourcePics/AllureBookIcon256x256.png")));
		setBounds(100, 100, 788, 522);
		setTitle("Relatório e Detalhes sobre Cadastro");
		getContentPane().setLayout(new CardLayout(0, 0));

		//-------------\/------------CadastroClientePanel elements-----------\/------------------------

		CadastroClientePanel = new JPanel();
		CadastroClientePanel.setVisible(false);
		getContentPane().add(CadastroClientePanel);
		CadastroClientePanel.setLayout(null);

		/**
		 * Labels.
		 */

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNome.setBounds(22, 39, 60, 20);
		CadastroClientePanel.add(lblNome);

		JLabel lblRgCli = new JLabel("RG:");
		lblRgCli.setBounds(515, 70, 39, 20);
		lblRgCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblRgCli.setToolTipText("Campo onde se insere o numero do Registro Geral do Cliente!");
		CadastroClientePanel.add(lblRgCli);

		JLabel lblEmailCli = new JLabel("Email:");
		lblEmailCli.setBounds(280, 39, 49, 20);
		lblEmailCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEmailCli.setToolTipText("Campo onde se insere o Endere\u00E7o eletronico do Cliente (Campo opcional)!");
		CadastroClientePanel.add(lblEmailCli);

		JLabel lblTelefoneCli = new JLabel("Telefone:");
		lblTelefoneCli.setBounds(515, 35, 69, 28);
		lblTelefoneCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblTelefoneCli.setToolTipText("Campo onde se insere o numero de contato do Cliente!");
		CadastroClientePanel.add(lblTelefoneCli);

		final JLabel lblWarningMessageCli = new JLabel();
		lblWarningMessageCli.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblWarningMessageCli.setBounds(59, 403, 183, 21);
		lblWarningMessageCli.setText("Campos Obrigatorios!");
		/*try 
        {
            Image img = ImageIO.read(getClass().getResource("SourcePics/trouble17x14.png"));
            lblWarningMessageCli.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) {}*/
		CadastroClientePanel.add(lblWarningMessageCli);

		JLabel lblCpfCli = new JLabel("CPF:");
		lblCpfCli.setBounds(22, 70, 46, 20);
		lblCpfCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCpfCli.setToolTipText("Campo onde se insere o numero do Cadastro de Pessoa Fisica do Cliente!");
		CadastroClientePanel.add(lblCpfCli);

		JLabel lblSexoCli = new JLabel("Sexo:");
		lblSexoCli.setBounds(280, 70, 46, 20);
		lblSexoCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSexoCli.setToolTipText("Campo onde se insere o genero da pessoa (M = Masculino, F = Feminino)");
		CadastroClientePanel.add(lblSexoCli);

		JLabel lblDataDeNascimentoCli = new JLabel("Data de Nascimento:");
		lblDataDeNascimentoCli.setToolTipText("Data de Nascimento!");
		lblDataDeNascimentoCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataDeNascimentoCli.setBounds(22, 104, 167, 18);
		CadastroClientePanel.add(lblDataDeNascimentoCli);

		JLabel lblLogradouroCli = new JLabel("Logradouro:");
		lblLogradouroCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblLogradouroCli.setBounds(22, 210, 109, 24);
		CadastroClientePanel.add(lblLogradouroCli);

		JLabel lblNumeroCli = new JLabel("N\u00BA:");
		lblNumeroCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNumeroCli.setBounds(280, 212, 70, 18);
		CadastroClientePanel.add(lblNumeroCli);

		JLabel lblBairroCli = new JLabel("Bairro:");
		lblBairroCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblBairroCli.setBounds(22, 251, 60, 20);
		CadastroClientePanel.add(lblBairroCli);

		JLabel lblCamposOpcionais = new JLabel();
		lblCamposOpcionais.setText("Campos Opcionais!");
		lblCamposOpcionais.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposOpcionais.setBounds(59, 435, 167, 21);
		CadastroClientePanel.add(lblCamposOpcionais);

		JLabel lblCidadeCli = new JLabel("Cidade:");
		lblCidadeCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCidadeCli.setBounds(260, 250, 69, 20);
		CadastroClientePanel.add(lblCidadeCli);

		JLabel lblEstadoCli = new JLabel("Estado:");
		lblEstadoCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEstadoCli.setBounds(515, 250, 69, 20);
		CadastroClientePanel.add(lblEstadoCli);

		JLabel lblComplementoCli = new JLabel("Complemento:");
		lblComplementoCli.setToolTipText("Complemento:");
		lblComplementoCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblComplementoCli.setBounds(461, 213, 123, 18);
		CadastroClientePanel.add(lblComplementoCli);

		JLabel lblRedColor = new JLabel();
		lblRedColor.setBackground(RedColor);
		lblRedColor.setOpaque(true);
		lblRedColor.setBounds(22, 411, 20, 4);
		CadastroClientePanel.add(lblRedColor);

		/**
		 * TextFields.
		 */

		JLabel lblEndereco = new JLabel("Informe Endere\u00E7o por meio do CEP:");
		lblEndereco.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEndereco.setBounds(141, 169, 292, 28);
		CadastroClientePanel.add(lblEndereco);

		textNomeCli = new JTextField();
		//textNomeCli.setName("Nome");
		textNomeCli.setBounds(81, 40, 161, 20);
		textNomeCli.setDocument(new TextFieldLimiter(20));
		CadastroClientePanel.add(textNomeCli);

		textNomeCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				/*String caracteres = "0987654321@#$%¨&*()_-/!?{}[]*+-'£¢¬,.|\"\";:<>º°?";
				if (caracteres.contains(e.getKeyChar()+"")){
					e.consume();*/
				GeneralMethods.verifyTextField(textNomeCli);

				/*}/*else{

				}*/
			}

			@Override
			public void keyReleased(KeyEvent e) {
				/*String caracteres = "0987654321@#$%¨&*()_-/!?{}[]*+-'£¢¬,.|\"\";:<>º°?";
				if (caracteres.contains(e.getKeyChar()+"")){
					e.consume();*/
				GeneralMethods.verifyTextField(textNomeCli);


			}
		});

		textNomeCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textNomeCli.setFocusable(isCursorSet());
				textNomeCli.setCaretPosition(0);
				if (textNomeCli.getForeground().equals(RedColor)){


				}else{
					textNomeCli.setForeground(GreenColor);
					textNomeCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textNomeCli.getForeground().equals(GreenColor)){
					textNomeCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textNomeCli.setForeground(Color.black);

				}
			}
		});


		textEmailCli = new JTextField();
		textEmailCli.setBounds(339, 40, 152, 20);
		textEmailCli.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
		CadastroClientePanel.add(textEmailCli);
		textEmailCli.setColumns(10);

		textEmailCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textEmailCli.setFocusable(isCursorSet());
				textEmailCli.setCaretPosition(0);
				if (textEmailCli.getForeground().equals(OrangeColor)){


				}else{
					textEmailCli.setForeground(GreenColor);
					textEmailCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textEmailCli.getForeground().equals(GreenColor)){
					textEmailCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textEmailCli.setForeground(Color.black);

				}
			}
		});

		//FieldsValidation.isEmailValid(email)
		textEmailCli.addKeyListener(new KeyAdapter() {
			/*@Override
			public void keyTyped(KeyEvent e) {

				//FieldsValidation.EmailValidation(textEmailCli);

				String caracteres = "=ABCDEFGHIJKLMNOPQRSTUVWXYZ#$%¨&*()/!?{}[]*+-'£¢¬,|\"\";:<>º°? ";
				if (caracteres.contains(e.getKeyChar()+"")){
					e.consume();	
				}
			}*/

			@Override
			public void keyPressed(KeyEvent e) {
				FieldsValidation.EmailValidation(textEmailCli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				FieldsValidation.EmailValidation(textEmailCli);
			}


		});

		textBairroCli = new JTextField();
		textBairroCli.setBounds(98, 251, 152, 20);
		CadastroClientePanel.add(textBairroCli);
		textBairroCli.setColumns(10);
		textBairroCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyTextField(textBairroCli);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				GeneralMethods.verifyTextField(textBairroCli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textBairroCli);
			}

		});

		textBairroCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textBairroCli.setFocusable(isCursorSet());
				textBairroCli.setCaretPosition(0);
				if (textBairroCli.getForeground().equals(RedColor)){


				}else{
					textBairroCli.setForeground(GreenColor);
					textBairroCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textBairroCli.getForeground().equals(GreenColor)){
					textBairroCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textBairroCli.setForeground(Color.black);

				}
			}
		});


		textLogradouroCli = new JTextField();
		textLogradouroCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//verifyTextField();
				GeneralMethods.verifyTextField(textLogradouroCli);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				GeneralMethods.verifyTextField(textLogradouroCli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textLogradouroCli);
			}

		});
		textLogradouroCli.setBounds(121, 212, 135, 21);
		CadastroClientePanel.add(textLogradouroCli);
		textLogradouroCli.setColumns(10);

		textLogradouroCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textLogradouroCli.setFocusable(isCursorSet());
				textLogradouroCli.setCaretPosition(0);
				if (textLogradouroCli.getForeground().equals(RedColor)){


				}else{
					textLogradouroCli.setForeground(GreenColor);
					textLogradouroCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textLogradouroCli.getForeground().equals(GreenColor)){
					textLogradouroCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textLogradouroCli.setForeground(Color.black);

				}
			}
		});

		textNumeroCli = new JTextField();
		textNumeroCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				GeneralMethods.verifyTextField(textNumeroCli);

				String caracteres = "=ABCDEFGHIJKLMNOPQRSTUVWXYZ#$%¨&*()/!?{}[]*+-'£¢¬,|\"\";:<>º°? ";
				if (caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(textNumeroCli.getText().length()>=5){
					GeneralMethods.verifyTextField(textNumeroCli);

					textNumeroCli.setText(textNumeroCli.getText().substring(0, 4));
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textNumeroCli);

			}
		});
		textNumeroCli.setBounds(320, 212, 123, 20);
		CadastroClientePanel.add(textNumeroCli);

		textNumeroCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textNumeroCli.setFocusable(isCursorSet());
				textNumeroCli.setCaretPosition(0);
				if (textNumeroCli.getForeground().equals(RedColor)){


				}else{
					textNumeroCli.setForeground(GreenColor);
					textNumeroCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textNumeroCli.getForeground().equals(GreenColor)){
					textNumeroCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textNumeroCli.setForeground(Color.black);

				}
			}
		});

		textComplementoCli = new JTextField();
		textComplementoCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyOptionalTextField(textComplementoCli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyOptionalTextField(textComplementoCli);
			}
		});
		textComplementoCli.setBounds(582, 212, 135, 20);
		textComplementoCli.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
		CadastroClientePanel.add(textComplementoCli);
		textComplementoCli.setColumns(10);

		textComplementoCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textComplementoCli.setFocusable(isCursorSet());
				textComplementoCli.setCaretPosition(0);
				if (textComplementoCli.getForeground().equals(OrangeColor)){


				}else{
					textComplementoCli.setForeground(GreenColor);
					textComplementoCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textComplementoCli.getForeground().equals(GreenColor)){
					textComplementoCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textComplementoCli.setForeground(Color.black);

				}
			}
		});

		textCidadeCli = new JTextField();
		textCidadeCli.setBounds(336, 251, 155, 20);
		CadastroClientePanel.add(textCidadeCli);
		textCidadeCli.setColumns(10);
		textCidadeCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyTextField(textCidadeCli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textCidadeCli);
			}

		});

		textCidadeCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textCidadeCli.setFocusable(isCursorSet());
				textCidadeCli.setCaretPosition(0);
				if (textCidadeCli.getForeground().equals(RedColor)){


				}else{
					textCidadeCli.setForeground(GreenColor);
					textCidadeCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textCidadeCli.getForeground().equals(GreenColor)){
					textCidadeCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textCidadeCli.setForeground(Color.black);

				}
			}
		});

		/**
		 * Formatted TextFields.
		 */

		textRG_Cli = new JTextField();
		textRG_Cli.setBounds(565, 71, 152, 20);
		textRG_Cli.setDocument(new TextFieldLimiter(13));
		CadastroClientePanel.add(textRG_Cli);
		textRG_Cli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyTextField(textRG_Cli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textRG_Cli);
			}

		});

		textRG_Cli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				textRG_Cli.setFocusable(isCursorSet());
				textRG_Cli.setCaretPosition(0);
				if (textRG_Cli.getForeground().equals(RedColor)){


				}else{
					textRG_Cli.setForeground(GreenColor);
					textRG_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textRG_Cli.getForeground().equals(GreenColor)){
					textRG_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					textRG_Cli.setForeground(Color.black);

				}
			}
		});

		formattedCPF_Cli = new JFormattedTextField();
		formattedCPF_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
		formattedCPF_Cli.setForeground(RedColor);
		formattedCPF_Cli.setBounds(101, 71, 141, 20);
		CadastroClientePanel.add(formattedCPF_Cli);

		FieldMasks.CpfMask(formattedCPF_Cli);

		formattedCPF_Cli.addKeyListener(new KeyAdapter() {

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

		formattedCPF_Cli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedCPF_Cli.setFocusable(isCursorSet());
				formattedCPF_Cli.setCaretPosition(0);
				if (formattedCPF_Cli.getForeground().equals(RedColor)){


				}else{
					formattedCPF_Cli.setForeground(GreenColor);
					formattedCPF_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (formattedCPF_Cli.getForeground().equals(GreenColor)){
					formattedCPF_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					formattedCPF_Cli.setForeground(Color.black);

				}
			}
		});

		formattedTelCli = new JFormattedTextField();
		formattedTelCli.setBounds(594, 40, 123, 20);
		CadastroClientePanel.add(formattedTelCli);

		FieldMasks.TelMask(formattedTelCli);

		formattedTelCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyFormattedField(formattedTelCli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyFormattedField(formattedTelCli);
			}
		});

		formattedTelCli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedTelCli.setFocusable(isCursorSet());
				formattedTelCli.setCaretPosition(0);
				if (formattedTelCli.getForeground().equals(RedColor)){


				}else{
					formattedTelCli.setForeground(GreenColor);
					formattedTelCli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (formattedTelCli.getForeground().equals(GreenColor)){
					formattedTelCli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					formattedTelCli.setForeground(Color.black);

				}
			}
		});


		formattedCEP_Cli = new JFormattedTextField();
		formattedCEP_Cli.setBounds(432, 174, 152, 20);
		formattedCEP_Cli.setForeground(RedColor);
		CadastroClientePanel.add(formattedCEP_Cli);

		FieldMasks.CepMask(formattedCEP_Cli);

		formattedCEP_Cli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				//GeneralMethods.buscaCep(formattedCEP_Cli, textLogradouroCli, textCidadeCli, textBairroCli, estadoCombo_Cli);
				validarCEP();
				GeneralMethods.verifyTextField(textLogradouroCli);
				GeneralMethods.verifyTextField(textBairroCli);
				GeneralMethods.verifyTextField(textCidadeCli);
			}
		});

		formattedCEP_Cli.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedCEP_Cli.setFocusable(isCursorSet());
				formattedCEP_Cli.setCaretPosition(0);
				if (formattedCEP_Cli.getForeground().equals(RedColor)){


				}else{
					formattedCEP_Cli.setForeground(GreenColor);
					formattedCEP_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				}

			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (formattedCEP_Cli.getForeground().equals(GreenColor)){
					formattedCEP_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					formattedCEP_Cli.setForeground(Color.black);

				}
			}
		});

		/**
		 * Radio Buttons.
		 */

		RadioM_Cli.setBounds(349, 69, 49, 23);
		RadioM_Cli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		CadastroClientePanel.add(RadioM_Cli);

		RadioF_Cli.setBounds(423, 70, 49, 21);
		RadioF_Cli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		CadastroClientePanel.add(RadioF_Cli);

		masc_fem_cli = new ButtonGroup();
		//		masc_fem_cli.setEnabled(true);
		masc_fem_cli.add(RadioF_Cli);
		masc_fem_cli.add(RadioM_Cli);
		masc_fem_cli.clearSelection();



		/**
		 * Date choosers.
		 */

		//		String Data = data_Nasc_Cli.getDateFormatString();
		//		System.out.println(Data);
		//		
		data_Nasc_Cli = new JDateChooser("dd/MM/yyyy", "##/##/####", ' ');
		data_Nasc_Cli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyDate(data_Nasc_Cli);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyDate(data_Nasc_Cli);
			}
		});
		//data_Nasc_Cli.setSelectableDateRange(dtIni, dtAtual);
		//data_Nasc_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED, Color.RED, Color.RED));
		data_Nasc_Cli.setBounds(199, 102, 109, 20);
		CadastroClientePanel.add(data_Nasc_Cli);
		
		FieldsValidation.DateValidationInterval(data_Nasc_Cli);

		/**
		 * ComboBoxes.
		 */

		estadoCombo_Cli = new JComboBox<Object>();
		estadoCombo_Cli.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		estadoCombo_Cli.setBounds(582, 251, 135, 20);
		CadastroClientePanel.add(estadoCombo_Cli);

		/**
		 * Buttons.
		 */

		CadastrarButtonCli = new JButton("Cadastrar");
		CadastrarButtonCli.setFont(new Font("Gabriola", Font.PLAIN, 20));
		CadastrarButtonCli.setBounds(486, 431, 98, 28);
		CadastroClientePanel.add(CadastrarButtonCli);

		CadastrarButtonCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FillBean(c);


				if (GeneralMethods.verifyTextField(textNomeCli) || GeneralMethods.verifyTextField(textLogradouroCli) ||
						GeneralMethods.verifyTextField(textBairroCli) || GeneralMethods.verifyTextField(textNumeroCli) || GeneralMethods.verifyTextField(textRG_Cli) ||
						GeneralMethods.verifyTextField(textCidadeCli) || GeneralMethods.verifyFormattedField(formattedTelCli)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					if (validarCpf()) {

						if(validarEstado_Sexo()){

							boolean resposta = ClienteDAO.verificarCpfCli(c.getCpf_cli()).getCpf_cli() == null;

							System.out.println(ClienteDAO.verificarCpfCli(c.getCpf_cli()).getCpf_cli());
							System.out.println(c.getCpf_cli());
							if (resposta ==true) {

								java.sql.Date dataNascCliSql = new java.sql.Date(data_Nasc_Cli.getDate().getTime());
								c.setDataNasc_cli(dataNascCliSql);
								ClienteDAO.cadastrarCliente(c);
								c = new ClienteBean();
								SystemTabs.atualizarClienteTable();
								GeneralMethods.limpar(CadastroClientePanel);
								dispose();
								FillUpJTextFieldList();
								FillUpJFormattedTextFieldList();

							}else{
								JOptionPane.showMessageDialog(null, "CPF já existe!");
							}

						}

					}else{
						JOptionPane.showMessageDialog(null, "CPF Invalido!");
					}

				}

			}
		});

		LimparButtonCli_1 = new JButton("Limpar");
		LimparButtonCli_1.setFont(new Font("Gabriola", Font.PLAIN, 20));
		LimparButtonCli_1.setBounds(374, 431, 98, 28);
		CadastroClientePanel.add(LimparButtonCli_1);

		LimparButtonCli_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.limpar(CadastroClientePanel);
				GeneralMethods.verifyOptionalTextField(textComplementoCli);
				FieldsValidation.EmailValidation(textEmailCli);
				FillUpJTextFieldList();
				FillUpJFormattedTextFieldList();
				//GeneralMethods.verifyTextField(textLogradouroCli);
			}
		});

		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Gabriola", Font.PLAIN, 20));
		btnAlterar.setBounds(260, 431, 98, 28);
		btnAlterar.setEnabled(false);
		CadastroClientePanel.add(btnAlterar);

		btnAlterar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				FillBean(c);


				if (GeneralMethods.verifyTextField(textNomeCli) || GeneralMethods.verifyTextField(textLogradouroCli) ||
						GeneralMethods.verifyTextField(textBairroCli) ||GeneralMethods.verifyTextField(textNumeroCli) || GeneralMethods.verifyTextField(textRG_Cli) ||
						GeneralMethods.verifyTextField(textCidadeCli) || GeneralMethods.verifyFormattedField(formattedTelCli)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					if (validarCpf()) {
						if(GeneralMethods.verifyDate(data_Nasc_Cli) == false){

							if(validarEstado_Sexo()){



								java.sql.Date dataNascCliSql = new java.sql.Date(data_Nasc_Cli.getDate().getTime());
								c.setDataNasc_cli(dataNascCliSql);

								ClienteDAO.alterarCliente(c);
								c = new ClienteBean();
								SystemTabs.atualizarClienteTable();
								//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
								//ccm = new CadastroConsultaMethods();
								GeneralMethods.limpar(CadastroClientePanel);
								dispose();
								FillUpJTextFieldList();
								FillUpJFormattedTextFieldList();
								/*Main.atualizarTableFuncionario();
		            			this.dispose();*/
								//limpar();

							}else{
								JOptionPane.showMessageDialog(null, "Verifique Data de Nascimento");
							}
						}else{
							JOptionPane.showMessageDialog(null, "CPF Invalido!");
						}
					}
				}
			}

		});

		EditarButtonCli = new JButton("Editar");
		EditarButtonCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GeneralMethods.EnableFields(CadastroClientePanel);
				btnAlterar.setEnabled(true);
				ClienteCadastro_Info.this.setTitle("Alterar Cliente");
			}
		});
		EditarButtonCli.setFont(new Font("Gabriola", Font.PLAIN, 20));
		EditarButtonCli.setEnabled(false);
		EditarButtonCli.setBounds(594, 431, 98, 25);
		CadastroClientePanel.add(EditarButtonCli);

		JSeparator separator = new JSeparator();
		separator.setBounds(27, 159, 709, 8);
		CadastroClientePanel.add(separator);

		JLabel lblOrangeColor = new JLabel();
		lblOrangeColor.setOpaque(true);
		lblOrangeColor.setBackground(OrangeColor);
		lblOrangeColor.setBounds(22, 444, 20, 4);
		CadastroClientePanel.add(lblOrangeColor);


		FillUpJFormattedTextFieldList();
		FillUpJTextFieldList();
		GeneralMethods.verifyOptionalTextField(textComplementoCli);
		FieldsValidation.EmailValidation(textEmailCli);

		//-------------/\------------CadastroDetailsPanel elements-----------/\------------------------
		//-------------\/------------RelatorioClientePanel elements-----------\/------------------------

		RelatorioClientePanel = new JPanel();
		getContentPane().add(RelatorioClientePanel, "name_449595453660870");
		RelatorioClientePanel.setLayout(null);

		JScrollPane VendasCliScroll = new JScrollPane();
		VendasCliScroll.setBounds(78, 60, 616, 180);
		RelatorioClientePanel.add(VendasCliScroll);

		VendasCliTable = new JTable();
		VendasCliTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RelatorioVendaAction(e);
			}
		});
		VendasCliTable.setBounds(50, 50, 50, 50);
		VendasCliScroll.setViewportView(VendasCliTable);

		JLabel lblHistoricoDeCompras = new JLabel("Historico de Compras:");
		lblHistoricoDeCompras.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblHistoricoDeCompras.setBounds(267, 26, 185, 23);
		RelatorioClientePanel.add(lblHistoricoDeCompras);

		JLabel lblDataCadastro = new JLabel("Data de Cadastro:");
		lblDataCadastro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastro.setBounds(22, 360, 152, 20);
		RelatorioClientePanel.add(lblDataCadastro);

		lblDataCadastroInfoCli = new JLabel("");
		lblDataCadastroInfoCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastroInfoCli.setBounds(180, 360, 161, 20);
		RelatorioClientePanel.add(lblDataCadastroInfoCli);

		JLabel lblDataDeResicaoFunc = new JLabel("Data de Resi\u00E7\u00E3o:");
		lblDataDeResicaoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataDeResicaoFunc.setBounds(351, 360, 135, 20);
		RelatorioClientePanel.add(lblDataDeResicaoFunc);

		lblCadastradoPor = new JLabel("Cadastrado por:");
		lblCadastradoPor.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCadastradoPor.setBounds(349, 391, 135, 20);
		RelatorioClientePanel.add(lblCadastradoPor);

		JLabel lblSituacaoCli = new JLabel("Situa\u00E7\u00E3o/Status:");
		lblSituacaoCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoCli.setBounds(22, 391, 135, 20);
		RelatorioClientePanel.add(lblSituacaoCli);

		lblSituacaoCliInfo = new JLabel("");
		lblSituacaoCliInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoCliInfo.setBounds(180, 391, 152, 20);
		RelatorioClientePanel.add(lblSituacaoCliInfo);

		ResicaoDataCli = new JLabel("");
		ResicaoDataCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		ResicaoDataCli.setBounds(492, 360, 161, 20);
		RelatorioClientePanel.add(ResicaoDataCli);

		lblCadastradoPorInfo = new JLabel("");
		lblCadastradoPorInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCadastradoPorInfo.setBounds(492, 391, 161, 20);
		RelatorioClientePanel.add(lblCadastradoPorInfo);

		JLabel lblCodCliente = new JLabel("N\u00B0. Cliente:");
		lblCodCliente.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCodCliente.setBounds(22, 422, 128, 23);
		RelatorioClientePanel.add(lblCodCliente);

		lblCodClienteInfo = new JLabel("");
		lblCodClienteInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCodClienteInfo.setBounds(151, 422, 128, 23);
		RelatorioClientePanel.add(lblCodClienteInfo);


	}

	private void FillUpJTextFieldList(){
		JTextFieldList.add(textNomeCli);
		JTextFieldList.add(textBairroCli);
		JTextFieldList.add(textCidadeCli);
		JTextFieldList.add(textLogradouroCli);
		JTextFieldList.add(textNumeroCli);
		JTextFieldList.add(textRG_Cli);

		for(JTextField field1 : JTextFieldList){
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			field1.setForeground(RedColor);
		}
		//verifyTextField();
	}

	private void FillUpJFormattedTextFieldList(){
		JFormattedTextFieldList.add(formattedTelCli);
		JFormattedTextFieldList.add(formattedCEP_Cli);
		JFormattedTextFieldList.add(formattedCPF_Cli);

		for(JFormattedTextField formattedfield1 : JFormattedTextFieldList){
			formattedfield1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedfield1.setForeground(RedColor);
		}
		//verifyEmptyMaskFields();
		//GeneralMethods.verifyJFormattedTextFields(JFormattedTextFieldList);
	}


	private boolean validarCpf() {

		if (FieldsValidation.validateCliCPF(c)) {

			return true;
		} else {

			return false;
		}

	}

	private void validarCEP(){
		if(GeneralMethods.buscaCep(formattedCEP_Cli, textLogradouroCli, textCidadeCli, textBairroCli, estadoCombo_Cli) == true){
			formattedCEP_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedCEP_Cli.setForeground(GreenColor);
		}else{
			formattedCEP_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedCEP_Cli.setForeground(RedColor);
		}


	}

	private void cpfActionPerformed(KeyEvent evt) {

		c.setCpf_cli(formattedCPF_Cli.getText());
		//System.out.println(cpfField.getText());
		if (validarCpf()) {
			formattedCPF_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedCPF_Cli.setForeground(GreenColor);
		} else {
			formattedCPF_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedCPF_Cli.setForeground(RedColor);
		}

	}

	private void FillBean(ClienteBean c){
		c.setNome_cli(textNomeCli.getText());
		c.setEmail_cli(textEmailCli.getText());
		c.setTelefone_cli(formattedTelCli.getText());
		c.setCpf_cli(formattedCPF_Cli.getText());
		c.setSexo_cli(ccm.getRadio(RadioM_Cli, RadioF_Cli));
		c.setRg_cli(textRG_Cli.getText());
		c.setCep_cli(formattedCEP_Cli.getText());
		c.setLogradouro_cli(textLogradouroCli.getText());
		c.setNumero_cli(textNumeroCli.getText());
		c.setComplemento_cli(textComplementoCli.getText());
		c.setBairro_cli(textBairroCli.getText());
		c.setCidade_cli(textCidadeCli.getText());
		c.setEstado_cli(estadoCombo_Cli.getSelectedItem().toString());
		c.setFunc(func);
	}

	private boolean validarEstado_Sexo(){
		if(c.getSexo_cli() != null && c.getEstado_cli() != "Selecione..."){
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "Verifique o estado e/ou Sexo");
			return false;
		}
	}


	public void verifyFields(){
		GeneralMethods.verifyTextField(textNomeCli);
		GeneralMethods.verifyTextField(textLogradouroCli);
		GeneralMethods.verifyTextField(textBairroCli);
		GeneralMethods.verifyTextField(textRG_Cli);
		GeneralMethods.verifyTextField(textCidadeCli);
		GeneralMethods.verifyTextField(textNumeroCli);
		GeneralMethods.verifyFormattedField(formattedTelCli);
		GeneralMethods.verifyFormattedField(formattedCEP_Cli);
		GeneralMethods.verifyOptionalTextField(textComplementoCli);
		formattedCPF_Cli.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FieldsValidation.EmailValidation(textEmailCli);

	}

	public void DisableFields(){
		GeneralMethods.DisableFields(CadastroClientePanel);
	}

	public void FillUpEditCli(ClienteBean c){
		this.c = c;

		textNomeCli.setText(c.getNome_cli());
		textEmailCli.setText(c.getEmail_cli());
		formattedTelCli.setText(c.getTelefone_cli());
		formattedCPF_Cli.setText(c.getCpf_cli());
		formattedCPF_Cli.setEnabled(false);
		if(c.getSexo_cli().toString().equals("M")){
			RadioM_Cli.setSelected(true);
			RadioF_Cli.setSelected(false);   		    		
		}else{
			RadioF_Cli.setSelected(true);
			RadioM_Cli.setSelected(false);
		}

		textRG_Cli.setText(c.getRg_cli());
		data_Nasc_Cli.setDate(c.getDataNasc_cli());
		formattedCEP_Cli.setText(c.getCep_cli());
		textLogradouroCli.setText(c.getLogradouro_cli());
		textNumeroCli.setText(c.getNumero_cli());
		textComplementoCli.setText(c.getComplemento_cli());
		textBairroCli.setText(c.getBairro_cli());
		textCidadeCli.setText(c.getCidade_cli());
		estadoCombo_Cli.setSelectedItem(c.getEstado_cli().toString());


		verifyFields();
	}

	public void RelatorioVendaAction(MouseEvent e){


		PedidoVendaRegistroBean pvr = new PedidoVendaRegistroBean();

		int rowSelectedVenda = VendasCliTable.getSelectedRow();
		int rowZeroIntValueVenda = Integer.parseInt(VendasCliTable.getValueAt(rowSelectedVenda, 0).toString());


		pvr.setId_PedidoVendaRegistro(rowZeroIntValueVenda);

		pvr = PedidoVendaRegistroDAO.retornarVendaBoleto(pvr);
	}


	public static void LoadCliRelatorio(ClienteBean c){
		ClienteDAO.carregarVendasCliTable(VendasCliTable, c);
	}

	public static void setFuncionario(FuncionarioBean f) {
		func = f;
	}
}

//-------------\/------------ClienteInfoTabPanel elements-----------\/------------------------
