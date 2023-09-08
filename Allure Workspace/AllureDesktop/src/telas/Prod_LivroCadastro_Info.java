package telas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import utilities.FieldMasks;
import utilities.GeneralMethods;
import utilities.ImagePanel;
import utilities.MonetarioDocument;
import acessoDadosBeans.FornecedorBean;
import acessoDadosBeans.FuncionarioBean;
import acessoDadosBeans.GeneroLivroBean;
import acessoDadosBeans.ProdutoLivroBean;

import com.toedter.calendar.JYearChooser;

import dataAcessObjects.FornecedorDAO;
import dataAcessObjects.GeneroLivroDAO;
import dataAcessObjects.ProdutoLivroDAO;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;


public class Prod_LivroCadastro_Info extends JDialog {


	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	ProdutoLivroBean pl = new ProdutoLivroBean();
	ArrayList<JTextField> TextFieldLivroList = new ArrayList<JTextField>();
	ArrayList<JFormattedTextField> formattedLivroList = new ArrayList<JFormattedTextField>();
	public static FuncionarioBean func;


	Color GreenColor = (new Color(50, 205, 50));
	Color RedColor = Color.RED;
	Color OrangeColor = Color.ORANGE;

	//LivroPanel component initialization
	final JPanel CadastroLivroPanel = new JPanel();

	ImagePanel previewBookPanel;

	JButton CadastrarButton_Livro;
	JButton LimparButton_1_Livro;
	JButton AlterarButton_1_Livro;
	JButton FilechooserButtonBook;

	JTextField textNome_Livro;
	JFormattedTextField formattedISBN_Livro;
	JTextField textAutor_Livro;
	JTextField textEdicao_Livro;
	JTextField textPreco_Livro;
	JTextField FileBookPath;

	JYearChooser Ano_Livro;
	SpinnerNumberModel BookSpinnerModel;
	JSpinner QtdSpinnerLivro;

	static JComboBox<Object> Fornecedor_Combo_Livro;
	static JComboBox<Object> Genero_Livro_Combo;

	byte[] Book_Photo;
	private JLabel lblGeneroLivro;
	private JLabel lblQuantidadeLivro;
	JButton EditarButton_Livro;

	private JSeparator separator_1;
	JLabel lblDataCadastro;
	JLabel lblCadastradoPor;
	JLabel lblCadastradoPorInfo;
	JLabel lblSituacaoForn;
	private JLabel lblDataCadastroInfoLivro;
	private JLabel lblSituacaoFornInfo;
	private JLabel ResicaoDataForn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Prod_LivroCadastro_Info dialog = new Prod_LivroCadastro_Info();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Prod_LivroCadastro_Info() {
		setBounds(100, 100, 1044, 498);
		getContentPane().setLayout(new CardLayout(0, 0));
		CadastroLivroPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(CadastroLivroPanel);
		CadastroLivroPanel.setLayout(null);

		previewBookPanel = new ImagePanel(this.Book_Photo);
		previewBookPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		previewBookPanel.setBounds(758, 39, 241, 265);
		CadastroLivroPanel.add(previewBookPanel);


		/**
		 * Labels.
		 */

		JLabel lblNomeLivro = new JLabel("Nome:");
		lblNomeLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNomeLivro.setBounds(22, 39, 60, 20);
		CadastroLivroPanel.add(lblNomeLivro);

		JLabel lblISBNLivro = new JLabel("ISBN:");
		lblISBNLivro.setBounds(498, 39, 57, 20);
		lblISBNLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblISBNLivro.setToolTipText("Campo onde se insere o numero do Registro Geral do Livroionario!");
		CadastroLivroPanel.add(lblISBNLivro);

		JLabel lblAutorLivro = new JLabel("Autor:");
		lblAutorLivro.setBounds(22, 70, 49, 20);
		lblAutorLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblAutorLivro.setToolTipText("Campo onde se insere o Endere\u00E7o eletronico do Livroente (Campo opcional)!");
		CadastroLivroPanel.add(lblAutorLivro);

		JLabel lblEdicaoLivro = new JLabel("Edição:");
		lblEdicaoLivro.setBounds(256, 66, 69, 28);
		lblEdicaoLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEdicaoLivro.setToolTipText("Campo onde se insere o numero de contato do Livroente!");
		CadastroLivroPanel.add(lblEdicaoLivro);

		JLabel lblCamposOpcionaisLivro = new JLabel();
		lblCamposOpcionaisLivro.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposOpcionaisLivro.setBounds(63, 406, 152, 21);
		lblCamposOpcionaisLivro.setText("Campos Opcionais!");
		CadastroLivroPanel.add(lblCamposOpcionaisLivro);

		JLabel lblCamposObrigatoriosLivro = new JLabel();
		lblCamposObrigatoriosLivro.setFont(new Font("Gabriola", Font.PLAIN, 22));
		lblCamposObrigatoriosLivro.setBounds(63, 373, 152, 21);
		lblCamposObrigatoriosLivro.setText("Campos Obrigatorios!");
		CadastroLivroPanel.add(lblCamposObrigatoriosLivro);

		JLabel lblAnoLivro = new JLabel("Ano:");
		lblAnoLivro.setBounds(279, 102, 46, 20);
		lblAnoLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblAnoLivro.setToolTipText("Campo onde se insere o genero da pessoa (M = Masculino, F = Feminino)");
		CadastroLivroPanel.add(lblAnoLivro);

		JLabel lblPrecoLivro = new JLabel("Pre\u00E7o (R$):");
		lblPrecoLivro.setToolTipText("Data de Nascimento!");
		lblPrecoLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblPrecoLivro.setBounds(22, 104, 98, 18);
		CadastroLivroPanel.add(lblPrecoLivro);

		lblGeneroLivro = new JLabel("G\u00EAnero:");
		lblGeneroLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblGeneroLivro.setBounds(257, 39, 69, 20);
		CadastroLivroPanel.add(lblGeneroLivro);

		JLabel lblFotoLivro = new JLabel("Foto:");
		lblFotoLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFotoLivro.setBounds(758, 323, 46, 20);
		CadastroLivroPanel.add(lblFotoLivro);

		lblQuantidadeLivro = new JLabel("Quantidade:");
		lblQuantidadeLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblQuantidadeLivro.setBounds(498, 70, 104, 28);
		CadastroLivroPanel.add(lblQuantidadeLivro);

		JLabel lblFornecedorLivro = new JLabel("Fornecedor:");
		lblFornecedorLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFornecedorLivro.setBounds(22, 143, 98, 20);
		CadastroLivroPanel.add(lblFornecedorLivro);

		/**
		 * TextFields.
		 */

		textNome_Livro = new JTextField();
		//textNomeFunc.setName("Nome");
		textNome_Livro.setBounds(81, 40, 161, 20);
		CadastroLivroPanel.add(textNome_Livro);

		textNome_Livro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textNome_Livro);

			}

			@Override
			public void keyReleased(KeyEvent e) {

				GeneralMethods.verifyTextField(textNome_Livro);


			}
		});

		formattedISBN_Livro = new JFormattedTextField();
		formattedISBN_Livro.setBounds(565, 39, 152, 20);
		CadastroLivroPanel.add(formattedISBN_Livro);
		formattedISBN_Livro.setColumns(10);

		FieldMasks.IsbnMask(formattedISBN_Livro);

		formattedISBN_Livro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyFormattedField(formattedISBN_Livro);

			}

			@Override
			public void keyReleased(KeyEvent e) {

				GeneralMethods.verifyFormattedField(formattedISBN_Livro);


			}
		});

		textAutor_Livro = new JTextField();
		textAutor_Livro.setBounds(91, 71, 152, 20);
		CadastroLivroPanel.add(textAutor_Livro);
		textAutor_Livro.setColumns(10);

		textAutor_Livro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textAutor_Livro);

			}

			@Override
			public void keyReleased(KeyEvent e) {

				GeneralMethods.verifyTextField(textAutor_Livro);


			}
		});


		textEdicao_Livro = new JTextField();
		textEdicao_Livro.setBounds(335, 71, 135, 21);
		CadastroLivroPanel.add(textEdicao_Livro);
		textEdicao_Livro.setColumns(10);

		textEdicao_Livro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textEdicao_Livro);

			}

			@Override
			public void keyReleased(KeyEvent e) {

				GeneralMethods.verifyTextField(textEdicao_Livro);


			}
		});

		textPreco_Livro = new JTextField();
		textPreco_Livro.setBounds(130, 103, 112, 20);
		CadastroLivroPanel.add(textPreco_Livro);
		textPreco_Livro.setColumns(10);
		textPreco_Livro.setDocument(new MonetarioDocument());

		textPreco_Livro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				GeneralMethods.verifyTextField(textPreco_Livro);

			}

			@Override
			public void keyReleased(KeyEvent e) {

				GeneralMethods.verifyTextField(textPreco_Livro);


			}
		});

		FileBookPath = new JTextField();
		FileBookPath.setEnabled(false);
		FileBookPath.setBounds(814, 322, 135, 20);
		CadastroLivroPanel.add(FileBookPath);
		FileBookPath.setColumns(10);

		/**
		 * YearChoosers and Spinners.
		 */
		Integer value = new Integer(0); 
		Integer min = new Integer(0);
		Integer max = new Integer(105); 
		Integer step = new Integer(1); 
		BookSpinnerModel = new SpinnerNumberModel(value, min, max, step); 
		//int fifty = model.getNumber().intValue();

		QtdSpinnerLivro = new JSpinner(BookSpinnerModel);
		QtdSpinnerLivro.setBounds(613, 75, 104, 20);
		CadastroLivroPanel.add(QtdSpinnerLivro);	

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		final int StartYear = 2007;
		final int CurrentYear = cal.get(Calendar.YEAR);
		System.out.println(CurrentYear);

		Ano_Livro = new JYearChooser();
		Ano_Livro.setEndYear(CurrentYear);
		Ano_Livro.setStartYear(StartYear);
		Ano_Livro.setBounds(335, 105, 135, 20);
		CadastroLivroPanel.add(Ano_Livro);

		JSpinner spinner = (JSpinner)Ano_Livro.getSpinner();
		((javax.swing.JTextField)spinner.getEditor()).setEditable(false);


		/**
		 * ComboBoxes.
		 */

		Fornecedor_Combo_Livro = new JComboBox<Object>();
		Fornecedor_Combo_Livro.setBounds(130, 144, 340, 20);
		CadastroLivroPanel.add(Fornecedor_Combo_Livro);

		carregarComboFornec();

		Genero_Livro_Combo = new JComboBox<Object>();
		Genero_Livro_Combo.setBounds(339, 40, 135, 20);
		CadastroLivroPanel.add(Genero_Livro_Combo);

		carregarComboGeneroLivro();

		/**
		 * Buttons.
		 */

		CadastrarButton_Livro = new JButton("Cadastrar");
		CadastrarButton_Livro.setFont(new Font("Gabriola", Font.PLAIN, 20));
		CadastrarButton_Livro.setBounds(472, 391, 98, 28);
		CadastroLivroPanel.add(CadastrarButton_Livro);

		CadastrarButton_Livro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherLivroBean(pl);


				if (GeneralMethods.verifyTextField(textNome_Livro)||
						GeneralMethods.verifyFormattedField(formattedISBN_Livro)||
						GeneralMethods.verifyTextField(textAutor_Livro)||
						GeneralMethods.verifyTextField(textEdicao_Livro)||
						GeneralMethods.verifyTextField(textPreco_Livro) ) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {

					if(verifyGenero_Fornec()){
						if(verifyQuantity()){
							if(verifyFornecStatus() == true){

								boolean resposta = ProdutoLivroDAO.verificarIsbn(pl.getISBN_livro()).getISBN_livro() == null;

								System.out.println(ProdutoLivroDAO.verificarIsbn(pl.getISBN_livro()).getISBN_livro());
								System.out.println(pl.getISBN_livro());
								if (resposta ==true) {

									ProdutoLivroDAO.cadastrarLivroProduto(pl);
									pl = new ProdutoLivroBean();
									//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
									//ccm = new CadastroConsultaMethods();
									GeneralMethods.limpar(CadastroLivroPanel);
									dispose();
									FillTextFieldLivroList();
									SystemTabs.atualizarLivroTable();


								}else{
									JOptionPane.showMessageDialog(null, "ISBN já existe!");
								}
							}
						}
					}
				}
			}

		});

		LimparButton_1_Livro = new JButton("Limpar");
		LimparButton_1_Livro.setFont(new Font("Gabriola", Font.PLAIN, 20));
		LimparButton_1_Livro.setBounds(364, 391, 98, 28);
		CadastroLivroPanel.add(LimparButton_1_Livro);

		LimparButton_1_Livro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.limpar(CadastroLivroPanel);

			}
		});

		AlterarButton_1_Livro = new JButton("Alterar");
		AlterarButton_1_Livro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherLivroBean(pl);


				if (GeneralMethods.verifyTextField(textNome_Livro)||
						GeneralMethods.verifyFormattedField(formattedISBN_Livro)||
						GeneralMethods.verifyTextField(textAutor_Livro)||
						GeneralMethods.verifyTextField(textEdicao_Livro)||
						GeneralMethods.verifyTextField(textPreco_Livro) ) {
					JOptionPane.showMessageDialog(null, "Verifique os campos Obrigatórios", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {

					if(verifyGenero_Fornec()){
						if(verifyQuantity()){

							ProdutoLivroDAO.alterarLivroProduto(pl);
							pl = new ProdutoLivroBean();
							//CadastroConsultaMethods ccm = new CadastroConsultaMethods();
							//ccm = new CadastroConsultaMethods();
							GeneralMethods.limpar(CadastroLivroPanel);
							dispose();
							FillTextFieldLivroList();
							SystemTabs.atualizarLivroTable();
							/*Main.atualizarTableFuncionario();
		            			this.dispose();*/

						}
					}
				}
			}


		});
		AlterarButton_1_Livro.setFont(new Font("Gabriola", Font.PLAIN, 20));
		AlterarButton_1_Livro.setBounds(256, 391, 98, 28);
		AlterarButton_1_Livro.setEnabled(false);
		CadastroLivroPanel.add(AlterarButton_1_Livro);

		FilechooserButtonBook = new JButton("...");
		FilechooserButtonBook.setBounds(947, 322, 33, 20);
		CadastroLivroPanel.add(FilechooserButtonBook);

		EditarButton_Livro = new JButton("Editar");
		EditarButton_Livro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneralMethods.EnableFields(CadastroLivroPanel);
				AlterarButton_1_Livro.setEnabled(true);
				FilechooserButtonBook.setEnabled(true);
				QtdSpinnerLivro.setEnabled(true);
				Prod_LivroCadastro_Info.this.setTitle("Alterar Livro");
			}
		});
		EditarButton_Livro.setFont(new Font("Gabriola", Font.PLAIN, 20));
		EditarButton_Livro.setEnabled(false);
		EditarButton_Livro.setBounds(580, 391, 98, 28);
		CadastroLivroPanel.add(EditarButton_Livro);

		FilechooserButtonBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolherFotoProd();
			}
		});

		/*try 
        {
            Image img = ImageIO.read(getClass().getResource("SourcePics/trouble17x14.png"));
            lblWarningMessageFunc.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) {}*/

		separator_1 = new JSeparator();
		separator_1.setVisible(false);
		separator_1.setBounds(27, 194, 709, 8);
		CadastroLivroPanel.add(separator_1);

		lblDataCadastro = new JLabel("Data de Cadastro:");
		lblDataCadastro.setVisible(false);
		lblDataCadastro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastro.setBounds(22, 235, 152, 20);
		CadastroLivroPanel.add(lblDataCadastro);

		lblDataCadastroInfoLivro = new JLabel("");
		lblDataCadastroInfoLivro.setVisible(false);
		lblDataCadastroInfoLivro.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataCadastroInfoLivro.setBounds(180, 235, 161, 20);
		CadastroLivroPanel.add(lblDataCadastroInfoLivro);

		JLabel lblDataDeResicaoFunc = new JLabel("Data de Resi\u00E7\u00E3o:");
		lblDataDeResicaoFunc.setVisible(false);
		lblDataDeResicaoFunc.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblDataDeResicaoFunc.setBounds(351, 235, 135, 20);
		CadastroLivroPanel.add(lblDataDeResicaoFunc);

		lblCadastradoPor = new JLabel("Cadastrado por:");
		lblCadastradoPor.setVisible(false);
		lblCadastradoPor.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCadastradoPor.setBounds(349, 266, 135, 20);
		CadastroLivroPanel.add(lblCadastradoPor);

		lblSituacaoForn = new JLabel("Situa\u00E7\u00E3o/Status:");
		lblSituacaoForn.setVisible(false);
		lblSituacaoForn.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoForn.setBounds(22, 266, 135, 20);
		CadastroLivroPanel.add(lblSituacaoForn);

		lblSituacaoFornInfo = new JLabel("");
		lblSituacaoFornInfo.setVisible(false);
		lblSituacaoFornInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSituacaoFornInfo.setBounds(180, 266, 152, 20);
		CadastroLivroPanel.add(lblSituacaoFornInfo);

		ResicaoDataForn = new JLabel("");
		ResicaoDataForn.setVisible(false);
		ResicaoDataForn.setFont(new Font("Gabriola", Font.PLAIN, 25));
		ResicaoDataForn.setBounds(492, 291, 161, 20);
		CadastroLivroPanel.add(ResicaoDataForn);

		lblCadastradoPorInfo = new JLabel("");
		lblCadastradoPorInfo.setVisible(false);
		lblCadastradoPorInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblCadastradoPorInfo.setBounds(492, 322, 161, 20);
		CadastroLivroPanel.add(lblCadastradoPorInfo);

		JLabel lblRedColor = new JLabel();
		lblRedColor.setBackground(RedColor);
		lblRedColor.setOpaque(true);
		lblRedColor.setBounds(22, 380, 20, 4);
		CadastroLivroPanel.add(lblRedColor);

		JLabel lblOrangeColor = new JLabel();
		lblOrangeColor.setOpaque(true);
		lblOrangeColor.setBackground(OrangeColor);
		lblOrangeColor.setBounds(22, 413, 20, 4);
		CadastroLivroPanel.add(lblOrangeColor);



		FillFormattedLivroList();
		FillTextFieldLivroList();


		/**
		 * Formatted TextFields.
		 */

		/**
		 * Radio Buttons.
		 */


		/**
		 * Date choosers.
		 */

		/**
		 * ComboBoxes.
		 */

		//-------------/\------------CadastroFuncPanel elements-----------/\------------------------

	}
	
	private void escolherFotoProd(){

		this.Book_Photo = GeneralMethods.showTelaEscolheImage(new File("C:\\"), this.previewBookPanel, this.FileBookPath, this, "jpg", "gif");

	}

	private void FillTextFieldLivroList(){
		TextFieldLivroList.add(textNome_Livro);
		TextFieldLivroList.add(textAutor_Livro);
		TextFieldLivroList.add(textEdicao_Livro);
		TextFieldLivroList.add(textPreco_Livro);

		for(JTextField f : TextFieldLivroList){
			f.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED, Color.RED, Color.RED));
			f.setForeground(Color.RED);
		}
	}

	private void FillFormattedLivroList(){

		formattedLivroList.add(formattedISBN_Livro);

		for(JFormattedTextField f : formattedLivroList){
			f.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED, Color.RED, Color.RED));
			f.setForeground(Color.RED);
		}
	}

	private void carregarComboGeneroLivro() {
		GeneroLivroDAO dao = new GeneroLivroDAO();

		List<GeneroLivroBean> lista = dao.buscarGenero();
		Genero_Livro_Combo.removeAll();
		Genero_Livro_Combo.addItem("Selecione ...");
		for (GeneroLivroBean genero : lista) {
			Genero_Livro_Combo.addItem(genero);
		}
	}

	private void carregarComboFornec() {
		FornecedorDAO dao = new FornecedorDAO();

		List<FornecedorBean> lista = dao.buscarFornecedor();
		Fornecedor_Combo_Livro.removeAll();
		Fornecedor_Combo_Livro.addItem("Selecione ...");
		for (FornecedorBean fornecedor : lista) {
			Fornecedor_Combo_Livro.addItem(fornecedor);
		}

	}

	private void preencherLivroBean(ProdutoLivroBean pl) {


		//GeneroLivroBean genero = (GeneroLivroBean)Genero_Livro_Combo.getSelectedItem();
		//FornecedorBean fornecedor = (FornecedorBean)Fornecedor_Combo_Livro.getSelectedItem();
		int QtdInt = (Integer) QtdSpinnerLivro.getValue();
		int YearInt = (Integer) Ano_Livro.getValue();

		try {
			if(!textPreco_Livro.getText().equals("")){
				String precoConversion = textPreco_Livro.getText();
				NumberFormat nf = NumberFormat.getInstance();
				Number n = nf.parse(precoConversion);
				pl.setPreco_livro_prod(n.floatValue());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}


		pl.setNome_livro_prod(textNome_Livro.getText());
		pl.setQtd_livro_prod(QtdInt);
		//pl.setId_genero_fk(genero.getId_Genero());
		pl.setId_genero_fk(Genero_Livro_Combo.getSelectedIndex());
		pl.setISBN_livro(formattedISBN_Livro.getText());
		pl.setAutor_livro(textAutor_Livro.getText());
		pl.setEdicao_livro(textEdicao_Livro.getText());
		pl.setAno_livro(YearInt);
		pl.setPhoto_Book(Book_Photo);
		//pl.setId_fornec_fk(fornecedor.getId_fornec());
		pl.setId_fornec_fk(Fornecedor_Combo_Livro.getSelectedIndex());
		pl.setFunc(func);
	}



	private boolean verifyGenero_Fornec(){
		if( pl.getId_genero_fk() > 0 && pl.getId_fornec_fk() >0){
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "Verifique o gênero e/ou Fornecedor");
			return false;
		}
	}

	//possibly would put photo verification here, or else work with standard images
	private boolean verifyQuantity(){



		if(pl.getQtd_livro_prod() !=0){
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "Verifique quantidade");
			return false;
		}
	}

	private boolean verifyFornecStatus(){
		boolean isFornecValid = false;
		String statusValueFromDB = String.valueOf(FornecedorDAO.returnAtivoInativoFornecById(Fornecedor_Combo_Livro.getSelectedIndex()));
		//String NomeCargo = String.valueOf(cargoCombo_Func.getSelectedItem());
		//System.out.println(NomeCargo);
		System.out.println(statusValueFromDB);

		if(statusValueFromDB.equals("Ativo")){
			isFornecValid = true;

		}else if(statusValueFromDB.equals("Inativo")){
			String nomeCargo = String.valueOf(Fornecedor_Combo_Livro.getSelectedItem());
			int dialogButton = JOptionPane.showConfirmDialog(null, "O Fornecedor "+nomeCargo+" Se encontra Inativo. Deseja Ativar este Cargo para que o Cadastro possa ser \n efetuado?", null, 0);  

			if (dialogButton == JOptionPane.YES_OPTION) {
				FornecedorBean p = new FornecedorBean();
				p.setId_fornec(Fornecedor_Combo_Livro.getSelectedIndex());
				FornecedorDAO.AtivarFornecedor(p);
				SystemTabs.atualizarFornecTable();
				isFornecValid = true;

			} else if(dialogButton == JOptionPane.NO_OPTION){  
				remove(dialogButton);
				isFornecValid = false;

			}
		}
		return isFornecValid;

	}

	public void verifyLivroFields(){
		GeneralMethods.verifyTextField(textNome_Livro);
		GeneralMethods.verifyTextField(textAutor_Livro);
		GeneralMethods.verifyTextField(textEdicao_Livro);
		GeneralMethods.verifyTextField(textPreco_Livro);

		GeneralMethods.verifyFormattedField(formattedISBN_Livro);

	}

	public void DisableFields(){
		GeneralMethods.DisableFields(CadastroLivroPanel);
	}

	public void FillUpEditLivro(ProdutoLivroBean pl){
		this.pl = pl;

		Genero_Livro_Combo.setSelectedIndex(pl.getId_genero_fk());
		textNome_Livro.setText(pl.getNome_livro_prod());
		formattedISBN_Livro.setText(pl.getISBN_livro());
		textAutor_Livro.setText(pl.getAutor_livro());
		textEdicao_Livro.setText(pl.getEdicao_livro());
		Ano_Livro.setValue(pl.getAno_livro());
		textPreco_Livro.setText(String.valueOf(pl.getPreco_livro_prod()));
		QtdSpinnerLivro.setValue(pl.getQtd_livro_prod());
		Fornecedor_Combo_Livro.setSelectedIndex(pl.getId_fornec_fk());
		Book_Photo = (pl.getPhoto_Book());
		previewBookPanel.setImagem(pl.getPhoto_Book());

		verifyLivroFields();
	}


	public static void setFuncionario(FuncionarioBean f) {
		func = f;
	}
}
