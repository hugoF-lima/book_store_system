package utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;


import utilities.ImagePanel;

import com.toedter.calendar.JDateChooser;



public class GeneralMethods {

	static Color GreenColor = (new Color(50, 205, 50));
	static Color RedColor = Color.RED;
	static Color OrangeColor = Color.ORANGE;

	public String getRadio(JRadioButton RdM, JRadioButton RdF) {
		String retornoRadioCli = null;
		if (RdM.isSelected()) {
			retornoRadioCli = RdM.getText();
		} else if (RdF.isSelected()) {
			retornoRadioCli = RdF.getText();
		}
		return retornoRadioCli;		
	}

	public String getCheck(JCheckBox Check) {
		String checkValue = "No";
		if (Check.isSelected()) {
			checkValue = "Yes";
		}
		return checkValue;		
	}

	
	@SuppressWarnings("rawtypes")
	public static void limpar(JPanel painel) {
		Component[] lista = painel.getComponents();
		for (Component component : lista) {
			//System.out.println("toString():" + component.toString());
			if (component instanceof Panel) {
				limpar((JPanel) component);
			} else if (component instanceof JTextField) {
				//System.out.println("Name:" + ((JTextField)component).getName());
				((JTextField)component).setText("");
			} else if (component instanceof JFormattedTextField) {
				((JFormattedTextField)component).setValue(null);
				((JFormattedTextField)component).setText("");
			} else if (component instanceof JDateChooser) {
				((JDateChooser)component).setDate(null);
			} else if (component instanceof JSpinner) {
				((JSpinner)component).setValue(0);
			} else if (component instanceof JComboBox) {
				((JComboBox)component).setSelectedIndex(0);
			} else if (component instanceof JRadioButton) {
				DefaultButtonModel model = (DefaultButtonModel) ((JRadioButton)component).getModel();
				ButtonGroup group = model.getGroup();
				group.clearSelection();
			}else if (component instanceof JCheckBox) {
				((JCheckBox)component).setSelected(false);
			}else if (component instanceof JTextPane) {
				((JTextPane)component).setText("");
			}
		}
	}


	@SuppressWarnings("rawtypes")
	public static void EnableFields(JPanel painel) {
		Component[] lista = painel.getComponents();
		for (Component component : lista) {
			if (component instanceof Panel) {
				EnableFields((JPanel) component);
			} else if (component instanceof JTextField) {
				((JTextField)component).setEnabled(true);
			} else if (component instanceof JFormattedTextField) {
				((JFormattedTextField)component).setEnabled(true);
			} else if (component instanceof JDateChooser) {
				((JDateChooser)component).setEnabled(true);
			} else if (component instanceof JComboBox) {
				((JComboBox)component).setEnabled(true);
			} else if (component instanceof JRadioButton) {
				((JRadioButton)component).setEnabled(true);
			}else if (component instanceof JCheckBox) {
				((JCheckBox)component).setEnabled(true);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void DisableFields(JPanel painel) {
		Component[] lista = painel.getComponents();
		for (Component component : lista) {
			if (component instanceof Panel) {
				DisableFields((JPanel) component);
			} else if (component instanceof JTextField) {
				((JTextField)component).setEnabled(false);
			} else if (component instanceof JFormattedTextField) {
				((JFormattedTextField)component).setEnabled(false);
			} else if (component instanceof JDateChooser) {
				((JDateChooser)component).setEnabled(false);
			} else if (component instanceof JComboBox) {
				((JComboBox)component).setEnabled(false);
			} else if (component instanceof JRadioButton) {
				((JRadioButton)component).setEnabled(false);
			} else if (component instanceof JCheckBox) {
				((JCheckBox)component).setEnabled(false);
			}
		}
	}


	
	public static boolean verifyFormattedField(JFormattedTextField formattedField1) {

		if (formattedField1.getText().contains(" ")) {
			formattedField1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			formattedField1.setForeground(RedColor);
			return true;
		} else {
			formattedField1.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			formattedField1.setForeground(GreenColor);
			return false;
		}

	}

	public static boolean verifyTextField(JTextField field1) {

		if (field1.getText().isEmpty() || field1.getText().trim().equals("") || field1.getText().trim().equals("0,00")) {
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			field1.setForeground(RedColor);
			return true;
		} else {
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			field1.setForeground(GreenColor);
			return false;
		}

	}

	public static boolean verifyDate(JDateChooser dateField1) {
		

		if (dateField1.getDateFormatString().equals(null)) {
			dateField1.setBorder(new BevelBorder(BevelBorder.LOWERED, RedColor, RedColor, RedColor, RedColor));
			dateField1.setForeground(RedColor);
			return true;
		} else {
			dateField1.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			dateField1.setForeground(GreenColor);
			return false;
		}

	}

	public static boolean verifyOptionalTextField(JTextField field1) {

		if (field1.getText().isEmpty() || field1.getText().trim().equals("")) {
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
			field1.setForeground(OrangeColor);
			return true;
		} else {
			field1.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			field1.setForeground(GreenColor);
			return false;
		}

	}

	public static boolean verifyOptionalTextPane(JTextArea descriptionCargoTextArea) {

		if (descriptionCargoTextArea.getText().isEmpty() || descriptionCargoTextArea.getText().trim().equals("")) {
			descriptionCargoTextArea.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
			descriptionCargoTextArea.setForeground(OrangeColor);
			return true;
		} else {
			descriptionCargoTextArea.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
			descriptionCargoTextArea.setForeground(GreenColor);
			return false;
		}

	}

	public static byte[] showTelaEscolheImage (File diretorioOrigem, ImagePanel previewPanel, JTextField txtCaminhoFoto, Component telaPai, String ... extensoes){

		JFileChooser telaEscolheFoto = new JFileChooser(diretorioOrigem);
		telaEscolheFoto.setFileFilter(new FiltroExtensao(extensoes));

		telaEscolheFoto.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		byte[] arrayAux = null;

		int ret = telaEscolheFoto.showSaveDialog(telaPai);

		if (ret == JFileChooser.APPROVE_OPTION) {
			File fileFoto = telaEscolheFoto.getSelectedFile();
			txtCaminhoFoto.setText(fileFoto.getAbsolutePath());
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileFoto));
				arrayAux = new byte[bis.available()];
				bis.read(arrayAux);

				previewPanel.setImagem(arrayAux);
				previewPanel.repaint();
			} catch (IOException e) {
				//throw new EntradaUsuarioException(txtCaminhoFoto, "Não foi possível ler a imagem da foto", e);
			}
		}
		return arrayAux;
	}

	public static boolean buscaCep(JFormattedTextField formattedfield1, JTextField field1, JTextField field2, 
			JTextField field3, @SuppressWarnings("rawtypes") JComboBox combo1) {
		//Faz a busca para o cep 58043-280
		WebServiceCep webServiceCep = WebServiceCep.searchCep(formattedfield1.getText());
		//A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

		//caso a busca ocorra bem, imprime os resultados.
		if (webServiceCep.wasSuccessful()) {
			//tipoCombo.getSelectedItem().toString().equals(webServiceCep.getLogradouroType());
			field1.setText(webServiceCep.getLogradouroFull());
			field2.setText(webServiceCep.getCidade());
			field3.setText(webServiceCep.getBairro());
			combo1.setSelectedItem(webServiceCep.getUf());
			System.out.println("Cep: " + webServiceCep.getCep());
			System.out.println("Logradouro: " + webServiceCep.getLogradouroFull());
			System.out.println("Bairro: " + webServiceCep.getBairro());
			System.out.println("Cidade: " + webServiceCep.getCidade());
			System.out.println("Estado: " + webServiceCep.getUf());

			return true;

			//caso haja problemas imprime as exce??es.
		} else {

			JOptionPane.showMessageDialog(null,"" + webServiceCep.getResultText());
		}

		return false;
	}
	


	

}

