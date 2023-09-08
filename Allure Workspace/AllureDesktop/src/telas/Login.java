package telas;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataAcessObjects.ClienteDAO;
import dataAcessObjects.FuncionarioDAO;

import acessoDadosBeans.CargoBean;
import acessoDadosBeans.ClienteBean;
import acessoDadosBeans.FuncionarioBean;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {
	/**
	 * 
	 */
	JLabel CapsLockActiveMessage;
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordSenha;
	private JTextField textUsuario;
	JButton Okbutton;

	@SuppressWarnings("deprecation")
	public Login() {
		super("Tela de Login");
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		//-------------------------ContentPane elements---------------------------------       

		/**
		 * PasswordFields.
		 */

		textUsuario = new JTextField();
		textUsuario.setBounds(102, 77, 158, 20);
		textUsuario.setColumns(10);
		contentPanel.add(textUsuario);

		/**
		 * PasswordFields.
		 */	

		passwordSenha = new JPasswordField();
		passwordSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				boolean CapsIsActive = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
				CapsLockActiveMessage.setVisible(CapsIsActive);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				boolean CapsIsActive = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
				CapsLockActiveMessage.setVisible(CapsIsActive);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				boolean CapsIsActive = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
				CapsLockActiveMessage.setVisible(CapsIsActive);
			}
		});
		passwordSenha.setEchoChar('*');
		passwordSenha.setBounds(104, 126, 129, 20);
		contentPanel.add(passwordSenha);

		/**
		 * Buttons.
		 */	

		JButton CheckPasswordButton = new JButton("");
		CheckPasswordButton.setBounds(232, 124, 28, 23);
		contentPanel.add(CheckPasswordButton);

		try {
			Image img = ImageIO.read(getClass().getResource("SourcePics/password reveal button.png"));
			CheckPasswordButton.setIcon(new ImageIcon(img));
		} 
		catch (IOException ex) {}

		CheckPasswordButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				passwordSenha.setEchoChar((char)0);	
			}
			@Override
			public void mouseReleased(MouseEvent e){
				passwordSenha.setEchoChar('*');
			} 
		});

		Okbutton = new JButton();
		Okbutton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		Okbutton.setText("Ok");
		Okbutton.setBounds(38, 226, 92, 29);
		Okbutton.setToolTipText("Realiza a entrada dos dados de Usuário e Senha no Sistema!");

		Okbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FuncionarioBean f = new FuncionarioBean();
				ClienteBean c = new ClienteBean();
				CargoBean cb = new CargoBean();

				String char_toString = String.valueOf(passwordSenha.getPassword());

				f.setNome_func(textUsuario.getText());
				f.setSenha_func(char_toString);

				if(FuncionarioDAO.LogIntoSystem(f, cb) == true){

					TelaPrincipal tp = new TelaPrincipal();

					TelaPrincipal.setPermission(cb);
					TelaPrincipal.setFuncionario(f);

					SystemTabs.setFuncionario(f);
					SystemTabs.setPermission(cb);


					tp.setVisible(true);

					if(ClienteDAO.LogBalcaoCliStandard(c) == true){
						TelaPrincipal.setClienteBalcao(c);
						SystemTabs.setCliente(c);
						//Pedido_Info.setCliente(c);
					}
					dispose();
				}

			}
		});

		getContentPane().add(Okbutton);

		JButton Cancelarbutton = new JButton();
		Cancelarbutton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		Cancelarbutton.setText("Cancelar");
		Cancelarbutton.setBounds(168, 226, 92, 29);
		Cancelarbutton.setToolTipText("Fecha a Tela de Login e, consequentemente o sistema!");
		/*try {
                Image img2 = ImageIO.read(getClass().getResource("SourcePics/CustomJbuttonCancelar120x23.png"));
                Cancelarbutton.setIcon(new ImageIcon(img2));
            } 
            catch (IOException ex) {}*/
		getContentPane().add(Cancelarbutton);
		
		Cancelarbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit (0);
			}
		});

		/**
		 * Labels.
		 */

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNome.setBounds(21, 76, 71, 22);
		getContentPane().add(lblNome);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblSenha.setBounds(21, 125, 71, 22);
		lblSenha.setToolTipText("pre determinados pelo Usuario cadastrado no Sistema!");
		getContentPane().add(lblSenha);

		CapsLockActiveMessage = new JLabel();
		CapsLockActiveMessage.setFont(new Font("Gabriola", Font.PLAIN, 20));
		CapsLockActiveMessage.setBounds(102, 168, 158, 14);
		CapsLockActiveMessage.setForeground(Color.ORANGE);
		CapsLockActiveMessage.setText("Caps Lock Ativada!");
		CapsLockActiveMessage.setVisible(false);
		getContentPane().add(CapsLockActiveMessage);


		//----------------------^^---ContentPane elements-----^^----------------------------         

		// add listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// show application
		setLocation(32, 32);
		setSize(321, 312);
		show();
	}

	public static void main(String[] args) {

		try {
			// select Look and Feel
			//UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

			// start application
			new Login();

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// end main
} // end class MinFrame