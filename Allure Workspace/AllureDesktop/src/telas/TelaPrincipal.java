package telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import utilities.GeneralMethods;
import javax.swing.JDesktopPane;
import acessoDadosBeans.CargoBean;
import acessoDadosBeans.ClienteBean;
import acessoDadosBeans.FuncionarioBean;
import utilities.ImagePanel;

public class TelaPrincipal extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//General Components Initialization
	public static FuncionarioBean func = new FuncionarioBean();
	public static ClienteBean cli = new ClienteBean();
	public static CargoBean cargo = new CargoBean();

	//MenuPanel component initialization
	JPanel MenuPanel;
	static JLabel lblFuncionarioLogadoMain;
	static JLabel lblCargoFunc;
	static ImagePanel FotoFuncPanel;
	GeneralMethods ccm = new GeneralMethods();
	JDesktopPane PanelReceiver;

	SystemTabs cc = new SystemTabs();
	private JLabel lblLoggedIn;



	@SuppressWarnings("deprecation")
	public TelaPrincipal() {
		super("Tela Principal");
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/telas/SourcePics/AllureBookIcon256x256.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

				int dialogButton = JOptionPane.showConfirmDialog(null, "Deseja Sair do Sistema?", null, 0);  
				if (dialogButton == JOptionPane.YES_OPTION) {  
					dispose();
					Login l = new Login();
					l.setVisible(true);
					func = null;
					return;  
				} else if(dialogButton == JOptionPane.NO_OPTION){  
					remove(dialogButton);

					return;  
				}  

			} 

		});
		// setup widgets
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		getContentPane().setLayout(null);

		PanelReceiver = new JDesktopPane();
		PanelReceiver.setBackground( new Color (240,240,240));
		PanelReceiver.setBounds(0, 98, 1043, 589);
		contentPanel.add(PanelReceiver);
		cc.setVisible(true);
		cc.setSize(1043, 626);
		cc.setLocation(0, -25);
		PanelReceiver.add(cc);
		//-----------\/--------------MenuPanel elements------------\/-----------------------

		MenuPanel = new JPanel();
		MenuPanel.setBounds(0, 0, 1043, 94);
		contentPanel.add(MenuPanel);
		MenuPanel.setLayout(null);

		lblFuncionarioLogadoMain = new JLabel("");
		lblFuncionarioLogadoMain.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblFuncionarioLogadoMain.setBounds(778, 22, 151, 23);
		MenuPanel.add(lblFuncionarioLogadoMain);

		lblCargoFunc = new JLabel("");
		lblCargoFunc.setFont(new Font("Gabriola", Font.PLAIN, 20));
		lblCargoFunc.setBounds(778, 45, 151, 15);
		MenuPanel.add(lblCargoFunc);

		lblLoggedIn = new JLabel("");
		lblLoggedIn.setFont(new Font("Gabriola", Font.PLAIN, 20));
		lblLoggedIn.setBounds(778, 57, 239, 30);
		MenuPanel.add(lblLoggedIn);

		DateFormat outputDate = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat outputTime = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();

		String outputTextDate = outputDate.format(date);
		String outputTextTime = outputTime.format(date);

		lblLoggedIn.setText("Login efetuado "+outputTextDate+" ás "+outputTextTime+"");

		FotoFuncPanel = new ImagePanel((byte[]) null);
		FotoFuncPanel.setBounds(702, 22, 66, 61);
		MenuPanel.add(FotoFuncPanel);

		//CadastroConsulta.setOpaque(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/telas/SourcePics/AllureBookIcon256x256.png")));

		/*masc_fem_cli = new ButtonGroup();
    		masc_fem_cli.clearSelection();
	        masc_fem_cli_pesquisa = new ButtonGroup();
			masc_fem_cli_pesquisa.clearSelection();*/


		//ImageIcon icon = new ImageIcon (getClass().getResource("SourcePics/BackgroundCadastroConsulta1305x630.png")) ;

		//-------------/\------------ContentPanel elements-----------/\------------------------ 

		//-------------------------Other elements-----------------------------------

		// add listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// show application
		setLocation(32, 32);
		setSize(1052, 718);
		show();
	} // end CTor MinFrame

	public static void main(String[] args) {
		try {

			/*Properties props = new Properties();
        	  props.put("logoString", "my company");*/
			// select Look and Feel
			//UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			// start application
			new TelaPrincipal();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	} // end main



	@SuppressWarnings("unused")
	private static void addPopup(final Component component, final JPopupMenu popup) {
	}

	public static void setFuncionario(FuncionarioBean f) {
		func = f;
		func.getId_func();
		lblFuncionarioLogadoMain.setText(func.getNome_func());
		FotoFuncPanel.setImagem(func.getFoto());

	}

	public static void setPermission(CargoBean cb){
		cargo = cb;
		lblCargoFunc.setText(cargo.getNomeCargo());
		System.out.println(cargo.getNomeCargo());
	}

	public static void setClienteBalcao (ClienteBean c){
		cli = c;
	}
}