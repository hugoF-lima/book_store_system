package telas;

import acessoDadosBeans.ContatoClienteBean;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Contact_Info extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Beans initialization
	ContatoClienteBean ccb = new ContatoClienteBean();
	
	//JPanels initialization
	private final JPanel ContactPanel = new JPanel();

	
	//ContactPanel Component initialization
	JLabel lblContactNumberInfo;
	JLabel lblNomeContatoInfo;
	JLabel lblEmailContatoInfo;
	JLabel lblAssuntoContatoInfo;
	JTextArea lblMensagemContatoInfo;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			Contact_Info dialog = new Contact_Info();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Contact_Info() {
		setBounds(100, 100, 707, 531);
		getContentPane().setLayout(null);
		ContactPanel.setBounds(0, 0, 691, 492);
		ContactPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(ContactPanel);
		ContactPanel.setLayout(null);

		//-------------\/------------ContactPanel elements-----------\/------------------------

		/**
		 * Labels.
		 */

		JLabel lblContactNumber = new JLabel("N° Contato:");
		lblContactNumber.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblContactNumber.setBounds(76, 37, 135, 20);
		ContactPanel.add(lblContactNumber);

		lblContactNumberInfo = new JLabel("");
		lblContactNumberInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblContactNumberInfo.setBounds(76, 68, 222, 20);
		ContactPanel.add(lblContactNumberInfo);

		JLabel lblNomeContato = new JLabel("Nome:");
		lblNomeContato.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNomeContato.setBounds(415, 37, 135, 20);
		ContactPanel.add(lblNomeContato);

		lblNomeContatoInfo = new JLabel("");
		lblNomeContatoInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblNomeContatoInfo.setBounds(415, 68, 222, 20);
		ContactPanel.add(lblNomeContatoInfo);

		JLabel lblEmailContato = new JLabel("Email:");
		lblEmailContato.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEmailContato.setBounds(76, 161, 135, 20);
		ContactPanel.add(lblEmailContato);

		lblEmailContatoInfo = new JLabel("");
		lblEmailContatoInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblEmailContatoInfo.setBounds(76, 191, 222, 20);
		ContactPanel.add(lblEmailContatoInfo);

		JLabel lblAssuntoContato = new JLabel("Assunto:");
		lblAssuntoContato.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblAssuntoContato.setBounds(415, 160, 135, 20);
		ContactPanel.add(lblAssuntoContato);

		lblAssuntoContatoInfo = new JLabel("");
		lblAssuntoContatoInfo.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblAssuntoContatoInfo.setBounds(415, 191, 242, 20);
		ContactPanel.add(lblAssuntoContatoInfo);

		JLabel lblMensagemContato = new JLabel("Mensagem:");
		lblMensagemContato.setFont(new Font("Gabriola", Font.PLAIN, 25));
		lblMensagemContato.setBounds(76, 230, 135, 27);
		ContactPanel.add(lblMensagemContato);

		lblMensagemContatoInfo = new JTextArea();
		lblMensagemContatoInfo.setEditable(false);
		lblMensagemContatoInfo.setLineWrap(true);
		lblMensagemContatoInfo.setBackground(null);
		lblMensagemContatoInfo.setFont(new Font("Gabriola", Font.PLAIN, 20));
		lblMensagemContatoInfo.setBounds(45, 268, 592, 135);
		ContactPanel.add(lblMensagemContatoInfo);

	}
	//-------------/\------------ContactPanel elements-----------/\------------------------

	/**
	 * Contact_Methods.
	 */
	
	public void FillUpEditContact(ContatoClienteBean ccb){
		this.ccb = ccb;

		String IdNumber = String.valueOf(ccb.getId_Cli_Contato());

		lblContactNumberInfo.setText(IdNumber);
		lblNomeContatoInfo.setText(ccb.getNome_Cli_Contato());
		lblEmailContatoInfo.setText(ccb.getEmail_Cli_Contato());
		lblAssuntoContatoInfo.setText(ccb.getAssunto_Cli_Contato());
		lblMensagemContatoInfo.setText(ccb.getMensagem_Cli_Contato());
	}

}


