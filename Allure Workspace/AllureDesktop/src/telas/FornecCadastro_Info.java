package telas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import utilities.GeneralMethods;
import utilities.FieldMasks;
import utilities.FieldsValidation;
import acessoDadosBeans.FornecedorBean;
import acessoDadosBeans.FuncionarioBean;
import acessoDadosBeans.ProdutoLivroBean;


import dataAcessObjects.FornecedorDAO;
import dataAcessObjects.ProdutoLivroDAO;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;

public class FornecCadastro_Info extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GeneralMethods ccm = new GeneralMethods();

	//FornecCadastroPanel elements
	final JPanel FornecCadastroPanel = new JPanel();
	ArrayList<JTextField> JTextFieldListFornec = new ArrayList<JTextField>();
	ArrayList<JFormattedTextField> JFormattedTextFieldListFornec = new ArrayList<JFormattedTextField>();
	public static FuncionarioBean func;
	FornecedorBean p  = new FornecedorBean();

	Color GreenColor = (new Color(50, 205, 50));
	Color RedColor = Color.RED;
	Color OrangeColor = Color.ORANGE;

	JButton CadastrarButton_Fornec;
	JButton LimparButton_Fornec;
	JButton AlterarButton_Fornec;
	JButton EditarButtonFornec;

	JTextField textNome_Fornec;
	JTextField textEmail_Fornec;
	JTextField textLogradouro_Fornec;
	JTextField textNumero_Fornec;
	JTextField textBairro_Fornec;
	JTextField textCidade_Fornec;
	JTextField textComplemento_Fornec;


	JFormattedTextField formattedCNPJ_Fornec;
	JFormattedTextField formattedTel_Fornec;
	JFormattedTextField formattedCEP_Fornec;


	JComboBox<Object> estadoCombo_Fornec;
	JLabel lblCadastradoPor;
	
	//RelatorioFornecPanel elements
	
	JPanel RelatorioFornecPanel;
	JLabel lblDataCadastroInfoFornec;
	JLabel lblSituacaoFornecInfo;
	JLabel ResicaoDataFornec;
	JLabel lblCadastradoPorInfoFornec;
	JLabel lblCodFornecInfo;
	
	static JTable FornecProductsTable;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FornecCadastro_Info dialog = new FornecCadastro_Info();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FornecCadastro_Info() {
		setBounds(100, 100, 788, 576);
		getContentPane().setLayout(new CardLayout(0, 0));
		//---------------------FornecCadastroPanel elements------------------------------------------		
		FornecCadastroPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(FornecCadastroPanel);
		FornecCadastroPanel.setLayout(null);

		/**
		 * Labels.
		 */

		JLabel lblNome_Fornec = new JLabel("Nome:");
		lblNome_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNome_Fornec.setBounds(22, 39, 60, 20);
		FornecCadastroPanel.add(lblNome_Fornec);

		JLabel lblEmail_Fornec = new JLabel("Email:");
		lblEmail_Fornec.setBounds(280, 39, 49, 20);
		lblEmail_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEmail_Fornec.setToolTipText("Campo onde se insere o Endere\u00E7o eletronico do _Fornecente (Campo opcional)!");
		FornecCadastroPanel.add(lblEmail_Fornec);

		JLabel lblTelefone_Fornec = new JLabel("Telefone:");
		lblTelefone_Fornec.setBounds(515, 35, 69, 28);
		lblTelefone_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblTelefone_Fornec.setToolTipText("Campo onde se insere o numero de contato do _Fornecente!");
		FornecCadastroPanel.add(lblTelefone_Fornec);

		final JLabel lblWarningMessage_Fornec = new JLabel();
		lblWarningMessage_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblWarningMessage_Fornec.setBounds(60, 425, 152, 21);
		lblWarningMessage_Fornec.setText("Campos Obrigatorios!");
		/*try 
	        {
	            Image img = ImageIO.read(getClass().getResource("SourcePics/trouble17x14.png"));
	            lblWarningMessage_Fornec.setIcon(new ImageIcon(img));
	        } 
	        catch (IOException ex) {}*/
		FornecCadastroPanel.add(lblWarningMessage_Fornec);

		JLabel lblCnpj_Fornec = new JLabel("CNPJ:");
		lblCnpj_Fornec.setBounds(22, 70, 46, 20);
		lblCnpj_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCnpj_Fornec.setToolTipText("Campo onde se insere o numero do Cadastro de Pessoa Fisica do _Fornecente!");
		FornecCadastroPanel.add(lblCnpj_Fornec);

		JLabel lblLogradouro_Fornec = new JLabel("Logradouro:");
		lblLogradouro_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 19));
		lblLogradouro_Fornec.setBounds(22, 164, 87, 24);
		FornecCadastroPanel.add(lblLogradouro_Fornec);

		JLabel lblNumero_Fornec = new JLabel("N\u00BA:");
		lblNumero_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNumero_Fornec.setBounds(280, 166, 70, 18);
		FornecCadastroPanel.add(lblNumero_Fornec);

		JLabel lblBairro_Fornec = new JLabel("Bairro:");
		lblBairro_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 19));
		lblBairro_Fornec.setBounds(22, 205, 60, 20);
		FornecCadastroPanel.add(lblBairro_Fornec);

		JLabel lblCamposOpcionais = new JLabel();
		lblCamposOpcionais.setText("Campos Opcionais!");
		lblCamposOpcionais.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposOpcionais.setBounds(60, 457, 152, 21);
		FornecCadastroPanel.add(lblCamposOpcionais);

		JLabel lblCidade_Fornec = new JLabel("Cidade:");
		lblCidade_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCidade_Fornec.setBounds(260, 204, 69, 20);
		FornecCadastroPanel.add(lblCidade_Fornec);

		JLabel lblEstado_Fornec = new JLabel("Estado:");
		lblEstado_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEstado_Fornec.setBounds(515, 204, 69, 20);
		FornecCadastroPanel.add(lblEstado_Fornec);

		JLabel lblComplemento_Fornec = new JLabel("Complemento:");
		lblComplemento_Fornec.setToolTipText("Complemento:");
		lblComplemento_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 17));
		lblComplemento_Fornec.setBounds(491, 168, 87, 18);
		FornecCadastroPanel.add(lblComplemento_Fornec);

		JLabel lblEndereco_Fornec = new JLabel("Informe Endere\u00E7o por meio do CEP:");
		lblEndereco_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEndereco_Fornec.setBounds(141, 123, 292, 28);
		FornecCadastroPanel.add(lblEndereco_Fornec);



		/**
		 * TextFields.
		 */


		textNome_Fornec = new JTextField();
		//textNome_Fornec.setName("Nome");
		textNome_Fornec.setBounds(81, 40, 161, 20);
		FornecCadastroPanel.add(textNome_Fornec);

		textNome_Fornec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyTextField(textNome_Fornec);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyTextField(textNome_Fornec);


			}
		});

		textEmail_Fornec = new JTextField();
		textEmail_Fornec.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent e) {
				textEmail_Fornec.setFocusable(isCursorSet());
				if (textEmail_Fornec.getForeground().equals(Color.red)){


				}else{
					textEmail_Fornec.setForeground(Color.green);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				textEmail_Fornec.setFocusable(isCursorSet());
				if (textEmail_Fornec.getForeground().equals(Color.green)){

					textEmail_Fornec.setForeground(Color.black);

				}
			}
		});
		textEmail_Fornec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				FieldsValidation.EmailValidation(textEmail_Fornec);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				FieldsValidation.EmailValidation(textEmail_Fornec);


			}
		});
		textEmail_Fornec.setBounds(339, 40, 152, 20);
		FornecCadastroPanel.add(textEmail_Fornec);
		textEmail_Fornec.setColumns(10);


		textBairro_Fornec = new JTextField();
		textBairro_Fornec.setBounds(98, 205, 152, 20);
		FornecCadastroPanel.add(textBairro_Fornec);
		textBairro_Fornec.setColumns(10);


		textLogradouro_Fornec = new JTextField();
		textLogradouro_Fornec.setBounds(102, 166, 154, 21);
		FornecCadastroPanel.add(textLogradouro_Fornec);
		textLogradouro_Fornec.setColumns(10);

		textNumero_Fornec = new JTextField();
		textNumero_Fornec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "=ABCDEFGHIJKLMNOPQRSTUVWXYZ#$%¨&*()/!?{}[]*+-'£¢¬,|\"\";:<>º°? ";
				if (caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
				GeneralMethods.verifyTextField(textNumero_Fornec);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(textNumero_Fornec.getText().length()>=5){

					textNumero_Fornec.setText(textNumero_Fornec.getText().substring(0, 4));
				}
				GeneralMethods.verifyTextField(textNumero_Fornec);
			}
		});
		textNumero_Fornec.setBounds(320, 166, 152, 20);
		FornecCadastroPanel.add(textNumero_Fornec);


		textComplemento_Fornec = new JTextField();
		textComplemento_Fornec.setBounds(582, 166, 135, 20);
		FornecCadastroPanel.add(textComplemento_Fornec);
		textComplemento_Fornec.setColumns(10);

		textComplemento_Fornec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				GeneralMethods.verifyOptionalTextField(textComplemento_Fornec);
			}

			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyOptionalTextField(textComplemento_Fornec);
			}
		});

		textCidade_Fornec = new JTextField();
		textCidade_Fornec.setBounds(336, 205, 155, 20);
		FornecCadastroPanel.add(textCidade_Fornec);
		textCidade_Fornec.setColumns(10);

		/**
		 * Formatted TextFields.
		 */

		formattedCNPJ_Fornec = new JFormattedTextField();
		formattedCNPJ_Fornec.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				cnpjActionPerformed(e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				cnpjActionPerformed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				cnpjActionPerformed(e);
			}
		});
		formattedCNPJ_Fornec.setBounds(101, 71, 141, 20);
		FornecCadastroPanel.add(formattedCNPJ_Fornec);

		FieldMasks.CNPJMask(formattedCNPJ_Fornec);

		formattedCNPJ_Fornec.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedCNPJ_Fornec.setFocusable(isCursorSet());
				formattedCNPJ_Fornec.setCaretPosition(0);
			}
			@Override
			public void focusLost(FocusEvent arg0) {

			}
		});

		formattedTel_Fornec = new JFormattedTextField();
		formattedTel_Fornec.setBounds(594, 40, 123, 20);
		FornecCadastroPanel.add(formattedTel_Fornec);

		FieldMasks.TelMask(formattedTel_Fornec);

		formattedTel_Fornec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GeneralMethods.verifyFormattedField(formattedTel_Fornec);
			}
			public void keyReleased(KeyEvent e) {
				GeneralMethods.verifyFormattedField(formattedTel_Fornec);
			}
		});

		formattedTel_Fornec.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedTel_Fornec.setFocusable(isCursorSet());
				formattedTel_Fornec.setCaretPosition(0);	
			}
			@Override
			public void focusLost(FocusEvent arg0) {

			}
		});

		formattedCEP_Fornec = new JFormattedTextField();
		formattedCEP_Fornec.setBounds(432, 128, 152, 20);
		FornecCadastroPanel.add(formattedCEP_Fornec);

		FieldMasks.CepMask(formattedCEP_Fornec);

		formattedCEP_Fornec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				validarCEP();
				GeneralMethods.verifyTextField(textLogradouro_Fornec);
				GeneralMethods.verifyTextField(textBairro_Fornec);
				GeneralMethods.verifyTextField(textCidade_Fornec);
			}
		});

		/**
		 * ComboBoxes.
		 */

		estadoCombo_Fornec = new JComboBox<Object>();
		estadoCombo_Fornec.setModel(new DefaultComboBoxModel<Object>(new String[] {"Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		estadoCombo_Fornec.setBounds(582, 205, 135, 20);
		FornecCadastroPanel.add(estadoCombo_Fornec);

		/**
		 * Buttons.
		 */

		CadastrarButton_Fornec = new JButton("Cadastrar");
		CadastrarButton_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 20));
		CadastrarButton_Fornec.setBounds(477, 467, 98, 28);
		FornecCadastroPanel.add(CadastrarButton_Fornec);

		CadastrarButton_Fornec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FillFornecBean(p);


				if (GeneralMethods.verifyTextField(textNome_Fornec)||
						GeneralMethods.verifyTextField(textLogradouro_Fornec)||
						GeneralMethods.verifyTextField(textBairro_Fornec)||
						GeneralMethods.verifyTextField(textCidade_Fornec)||
						GeneralMethods.verifyTextField(textNumero_Fornec)||
						GeneralMethods.verifyFormattedField(formattedTel_Fornec)||
						GeneralMethods.verifyFormattedField(formattedCEP_Fornec)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					if (validarCnpj()) {

						if(validarEstado()){
							
							boolean resposta = FornecedorDAO.verificarCnpj(p.getCnpj_fornec()).getCnpj_fornec() == null;

							System.out.println(FornecedorDAO.verificarCnpj(p.getCnpj_fornec()).getCnpj_fornec());
							System.out.println(p.getCnpj_fornec());
							if (resposta ==true) {

							FornecedorDAO.cadastrarFornecedor(p);
							p = new FornecedorBean();
							SystemTabs.atualizarFornecTable();
							//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
							//ccm = new CadastroConsultaMethods();
							GeneralMethods.limpar(FornecCadastroPanel);
							dispose();
							FillUpJTextFieldList();
							FillUpJFormattedTextFieldList();
							/*Main.atualizarTableFuncionario();
	    		            			this.dispose();*/

						}else{
							JOptionPane.showMessageDialog(null, "Cnpj já existe");
						}
						}

					}else{
						JOptionPane.showMessageDialog(null, "CNPJ Inválido!");
					}

				}
				//}
			}
		});

		LimparButton_Fornec = new JButton("Limpar");
		LimparButton_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 20));
		LimparButton_Fornec.setBounds(369, 467, 98, 28);
		FornecCadastroPanel.add(LimparButton_Fornec);

		LimparButton_Fornec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.limpar(FornecCadastroPanel);
				GeneralMethods.verifyOptionalTextField(textComplemento_Fornec);
				FieldsValidation.EmailValidation(textEmail_Fornec);
				FillUpJTextFieldList();
				FillUpJFormattedTextFieldList();
			}
		});

		AlterarButton_Fornec = new JButton("Alterar");
		AlterarButton_Fornec.setFont(new Font("Gabriola", Font.PLAIN, 20));
		AlterarButton_Fornec.setBounds(261, 467, 98, 28);
		AlterarButton_Fornec.setEnabled(false);
		FornecCadastroPanel.add(AlterarButton_Fornec);

		AlterarButton_Fornec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				FillFornecBean(p);


				if (GeneralMethods.verifyTextField(textNome_Fornec)||
						GeneralMethods.verifyTextField(textLogradouro_Fornec)||
						GeneralMethods.verifyTextField(textBairro_Fornec)||
						GeneralMethods.verifyTextField(textCidade_Fornec)||
						GeneralMethods.verifyTextField(textNumero_Fornec)||
						GeneralMethods.verifyFormattedField(formattedTel_Fornec)||
						GeneralMethods.verifyFormattedField(formattedCEP_Fornec)||
						GeneralMethods.verifyFormattedField(formattedCNPJ_Fornec)) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					if (validarCnpj()) {

						if(validarEstado()){


							FornecedorDAO.AlterarFornecedor(p);
							p = new FornecedorBean();
							SystemTabs.atualizarFornecTable();
							//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
							//ccm = new CadastroConsultaMethods();
							GeneralMethods.limpar(FornecCadastroPanel);
							dispose();
							FillUpJTextFieldList();
							FillUpJFormattedTextFieldList();
							/*Main.atualizarTableFuncionario();
	    		            			this.dispose();*/

						}

					}else{
						JOptionPane.showMessageDialog(null, "CNPJ Inválido!");
					}

				}


			}


		});

		JSeparator separator = new JSeparator();
		separator.setBounds(27, 113, 709, 8);
		FornecCadastroPanel.add(separator);

		EditarButtonFornec = new JButton("Editar");
		EditarButtonFornec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FornecCadastro_Info.this.setTitle("Alterar Fornecedor");
				GeneralMethods.EnableFields(FornecCadastroPanel);
				AlterarButton_Fornec.setEnabled(true);

			}
		});
		EditarButtonFornec.setFont(new Font("Gabriola", Font.PLAIN, 20));
		EditarButtonFornec.setEnabled(false);
		EditarButtonFornec.setBounds(593, 467, 98, 28);
		FornecCadastroPanel.add(EditarButtonFornec);

		JLabel lblRedColor = new JLabel();
		lblRedColor.setBackground(RedColor);
		lblRedColor.setOpaque(true);
		lblRedColor.setBounds(22, 436, 20, 4);
		FornecCadastroPanel.add(lblRedColor);

		JLabel lblOrangeColor = new JLabel();
		lblOrangeColor.setOpaque(true);
		lblOrangeColor.setBackground(OrangeColor);
		lblOrangeColor.setBounds(22, 469, 20, 4);
		FornecCadastroPanel.add(lblOrangeColor);

		FillUpJTextFieldList();
		FillUpJFormattedTextFieldList();
		GeneralMethods.verifyOptionalTextField(textComplemento_Fornec);
		FieldsValidation.EmailValidation(textEmail_Fornec);
		//---------------------FornecCadastroPanel elements------------------------------------------	
		//---------------------RelatorioFornec elements----------------------------------------------
		
		RelatorioFornecPanel = new JPanel();
		getContentPane().add(RelatorioFornecPanel, "name_449595453660870");
		RelatorioFornecPanel.setLayout(null);

		JScrollPane FornecProductsScroll = new JScrollPane();
		FornecProductsScroll.setBounds(78, 60, 616, 180);
		RelatorioFornecPanel.add(FornecProductsScroll);

		FornecProductsTable = new JTable();
		FornecProductsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					VerLivroMouseEvent(e);
				}
			}
		});
		FornecProductsTable.setBounds(50, 50, 50, 50);
		FornecProductsScroll.setViewportView(FornecProductsTable);
		
		JLabel lblHistoricoDeCompras = new JLabel("Livros associados a Este Fornecedor:");
		lblHistoricoDeCompras.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblHistoricoDeCompras.setBounds(232, 26, 298, 23);
		RelatorioFornecPanel.add(lblHistoricoDeCompras);

		JLabel lblDataCadastro = new JLabel("Data de Cadastro:");
		lblDataCadastro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastro.setBounds(22, 360, 152, 20);
		RelatorioFornecPanel.add(lblDataCadastro);
		
		lblDataCadastroInfoFornec = new JLabel("");
		lblDataCadastroInfoFornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastroInfoFornec.setBounds(180, 360, 161, 20);
		RelatorioFornecPanel.add(lblDataCadastroInfoFornec);
		
		JLabel lblDataDeResicaoFornec = new JLabel("Data de Resi\u00E7\u00E3o:");
		lblDataDeResicaoFornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataDeResicaoFornec.setBounds(351, 360, 135, 20);
		RelatorioFornecPanel.add(lblDataDeResicaoFornec);
		
		lblCadastradoPor = new JLabel("Cadastrado por:");
		lblCadastradoPor.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCadastradoPor.setBounds(349, 391, 135, 20);
		RelatorioFornecPanel.add(lblCadastradoPor);
		
		JLabel lblSituacaoCli = new JLabel("Situa\u00E7\u00E3o/Status:");
		lblSituacaoCli.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoCli.setBounds(22, 391, 135, 20);
		RelatorioFornecPanel.add(lblSituacaoCli);
		
		lblSituacaoFornecInfo = new JLabel("");
		lblSituacaoFornecInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoFornecInfo.setBounds(180, 391, 152, 20);
		RelatorioFornecPanel.add(lblSituacaoFornecInfo);
		
		ResicaoDataFornec = new JLabel("");
		ResicaoDataFornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		ResicaoDataFornec.setBounds(492, 360, 161, 20);
		RelatorioFornecPanel.add(ResicaoDataFornec);
		
		lblCadastradoPorInfoFornec = new JLabel("");
		lblCadastradoPorInfoFornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCadastradoPorInfoFornec.setBounds(492, 391, 161, 20);
		RelatorioFornecPanel.add(lblCadastradoPorInfoFornec);
		
		JLabel lblCodCFornec = new JLabel("N\u00B0. Fornec:");
		lblCodCFornec.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCodCFornec.setBounds(22, 422, 128, 23);
		RelatorioFornecPanel.add(lblCodCFornec);
		
		lblCodFornecInfo = new JLabel("");
		lblCodFornecInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCodFornecInfo.setBounds(151, 422, 128, 23);
		RelatorioFornecPanel.add(lblCodFornecInfo);



	}

	private void FillUpJTextFieldList(){
		JTextFieldListFornec.add(textNome_Fornec);
		JTextFieldListFornec.add(textBairro_Fornec);
		JTextFieldListFornec.add(textCidade_Fornec);
		JTextFieldListFornec.add(textLogradouro_Fornec);
		JTextFieldListFornec.add(textNumero_Fornec);

		for(JTextField field1 : JTextFieldListFornec){
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			field1.setForeground(RedColor);
		}
		//verifyTextField();
	}

	private void FillUpJFormattedTextFieldList(){
		JFormattedTextFieldListFornec.add(formattedCEP_Fornec);
		JFormattedTextFieldListFornec.add(formattedCNPJ_Fornec);
		JFormattedTextFieldListFornec.add(formattedTel_Fornec);

		for(JFormattedTextField formfield1 : JFormattedTextFieldListFornec){
			formfield1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formfield1.setForeground(RedColor);
		}
	}

	private void cnpjActionPerformed(KeyEvent evt) {

		p.setCnpj_fornec(formattedCNPJ_Fornec.getText());
		//System.out.println(cpfField.getText());
		if (validarCnpj()) {
			formattedCNPJ_Fornec.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedCNPJ_Fornec.setForeground(GreenColor);
		} else {
			formattedCNPJ_Fornec.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedCNPJ_Fornec.setForeground(RedColor);
		}

	}

	private boolean validarCnpj() {

		if (FieldsValidation.validateFornCNPJ(p)) {

			return true;
		} else {

			return false;
		}

	}

	private void validarCEP(){
		if(GeneralMethods.buscaCep(formattedCEP_Fornec, textLogradouro_Fornec, textCidade_Fornec, textBairro_Fornec, estadoCombo_Fornec) == true){
			formattedCEP_Fornec.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedCEP_Fornec.setForeground(GreenColor);
		}else{
			formattedCEP_Fornec.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedCEP_Fornec.setForeground(RedColor);
		}


	}

	private boolean validarEstado(){
		if( p.getEstado_fornec() != "Selecione..."){
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "Verifique o estado e/ou Sexo");
			return false;
		}
	}

	/*private void cnpjfActionPerformed(KeyEvent evt) {

		p.setCnpj_fornec(formattedCNPJ_Fornec.getText());
		//System.out.println(cpfField.getText());
		if (validarCnpj()) {
			formattedCNPJ_Fornec.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedCNPJ_Fornec.setForeground(GreenColor);
		} else {
			formattedCNPJ_Fornec.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedCNPJ_Fornec.setForeground(RedColor);
		}

	}

	private boolean validarCnpj() {

		if (FieldsValidation.validateFuncCPF(f)) {

			return true;
		} else {

			return false;
		}

	}*/

	private void FillFornecBean(FornecedorBean p){
		p.setNome_fornec(textNome_Fornec.getText());
		p.setEmail_fornec(textEmail_Fornec.getText());
		p.setTel_fornec(formattedTel_Fornec.getText());
		p.setCnpj_fornec(formattedCNPJ_Fornec.getText());
		p.setCep_fornec(formattedCEP_Fornec.getText());
		p.setLogradouro_fornec(textLogradouro_Fornec.getText());
		p.setNumero_fornec(textNumero_Fornec.getText());
		p.setComplemento_fornec(textComplemento_Fornec.getText());
		p.setBairro_fornec(textBairro_Fornec.getText());
		p.setCidade_fornec(textCidade_Fornec.getText());
		p.setEstado_fornec(estadoCombo_Fornec.getSelectedItem().toString());
		p.setFunc(func);

	}


	public void verifyFornecFields(){
		GeneralMethods.verifyTextField(textNome_Fornec);
		GeneralMethods.verifyTextField(textLogradouro_Fornec);
		GeneralMethods.verifyTextField(textBairro_Fornec);
		GeneralMethods.verifyTextField(textCidade_Fornec);
		GeneralMethods.verifyTextField(textNumero_Fornec);
		GeneralMethods.verifyFormattedField(formattedTel_Fornec);
		GeneralMethods.verifyFormattedField(formattedCEP_Fornec);
		GeneralMethods.verifyFormattedField(formattedCNPJ_Fornec);
		GeneralMethods.verifyOptionalTextField(textComplemento_Fornec);
		FieldsValidation.EmailValidation(textEmail_Fornec);

	}

	public void DisableFields(){
		GeneralMethods.DisableFields(FornecCadastroPanel);
		LimparButton_Fornec.setEnabled(false);
	}

	public void FillUpEditFornec(FornecedorBean p){
		this.p = p;

		textNome_Fornec.setText(p.getNome_fornec());
		textEmail_Fornec.setText(p.getEmail_fornec());
		formattedTel_Fornec.setText(p.getTel_fornec());
		formattedCNPJ_Fornec.setText(p.getCnpj_fornec());
		formattedCEP_Fornec.setText(p.getCep_fornec());
		textLogradouro_Fornec.setText(p.getLogradouro_fornec());
		textNumero_Fornec.setText(p.getNumero_fornec());
		textComplemento_Fornec.setText(p.getComplemento_fornec());
		textBairro_Fornec.setText(p.getBairro_fornec());
		textCidade_Fornec.setText(p.getCidade_fornec());
		estadoCombo_Fornec.setSelectedItem(p.getEstado_fornec().toString());
		

//		txtDataCadastroInfoForn.setText(String.valueOf(p.getData_Admissao_fornec()));
//		txtSituacaoFornInfo.setText(p.getAtivo_inativo_status_fornec());
//		txtResicaoDataForn.setText(p.getD)
//		
		verifyFornecFields();
	}
	
	public void VerLivroMouseEvent(MouseEvent e){
		Prod_LivroCadastro_Info  pli = new Prod_LivroCadastro_Info();
		pli.setVisible(true);
		pli.DisableFields();
		pli.CadastroLivroPanel.setVisible(true);
		pli.CadastrarButton_Livro.setEnabled(false);
		pli.LimparButton_1_Livro.setEnabled(false);
		pli.EditarButton_Livro.setEnabled(true);


		ProdutoLivroBean pl = new ProdutoLivroBean();

		int rowSelectedCli = FornecProductsTable.getSelectedRow();
		int rowZeroIntValueCli = Integer.parseInt(FornecProductsTable.getValueAt(rowSelectedCli, 0).toString());
		String NomeProd = FornecProductsTable.getValueAt(rowSelectedCli, 1).toString();

		pli.setTitle(""+NomeProd+"");

		//pl.setId_cli(rowZeroIntValueCli);

		pl = ProdutoLivroDAO.buscarLivroProduto(rowZeroIntValueCli);

		//ci.lblCadastradoPorInfo.setText(c.getCadastradoPor());
		pli.FillUpEditLivro(pl);
		//pli.setVisibleInfo();

	}

	
	public static void LoadFornecRelatorio(FornecedorBean p){
		FornecedorDAO.carregarProductsFornecTable(FornecProductsTable, p);
	}

	public static void setFuncionario(FuncionarioBean f) {
		func = f;
	}

}
