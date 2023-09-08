package utilities;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.toedter.calendar.JDateChooser;

import acessoDadosBeans.ClienteBean;
import acessoDadosBeans.FornecedorBean;
import acessoDadosBeans.FuncionarioBean;


public class FieldsValidation {
	
	static Color GreenColor = (new Color(50, 205, 50));
	Color RedColor = Color.RED;
	static Color OrangeColor = Color.ORANGE;
	
//---------------------------CPF Validation--------------------------------------
	public static boolean validation_CPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111")
				|| CPF.equals("22222222222") || CPF.equals("33333333333")
				|| CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777")
				|| CPF.equals("88888888888") || CPF.equals("99999999999")
				|| (CPF.length() != 11)) {
			return (false);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protects the code for eventual conversion type errors
		// (int)
		try {
			// Calc of the 1°st verification digit
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converts the i-esimo character of the CPF in a number:
				// for example, it turns the character '0' on integer 0
				// (48 is the a position '0' on the ASCII table)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48); // converts the following character
				// numeric
			}
			// Calc. of the Second Verification Digit
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
				return (true);
			} else {
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String printCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
				+ CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
	}
	
	public static boolean validateCliCPF(ClienteBean c) {

		String cpf = c.getCpf_cli();

		try{
			cpf = cpf.replace(".", "");
			cpf = cpf.replace("-", "");
		}catch(Exception e){

		}
		if (FieldsValidation.validation_CPF(cpf) == true) {

			return true;
		} else {

			return false;
		}

	}

	public static boolean validateFuncCPF(FuncionarioBean f) {

		String cpf = f.getCpf_func();

		try{
			cpf = cpf.replace(".", "");
			cpf = cpf.replace("-", "");
		}catch(Exception e){

		}
		if (FieldsValidation.validation_CPF(cpf) == true) {

			return true;
		} else {

			return false;
		}

	}

//---------------------------CPF Validation--------------------------------------

//---------------------------Email Validation--------------------------------------
		
	public static boolean EmailValidation (JTextField emailField){
		
		if ((emailField.getText().contains("@")) && (emailField.getText().contains(".")) && (!emailField.getText().contains(" "))) { 

			String usuario = new String(emailField.getText().substring(0, emailField.getText().lastIndexOf('@'))); 
			String dominio = new String(emailField.getText().substring(emailField.getText().lastIndexOf ('@') + 1, emailField.getText().length())); 

			if ((usuario.length() >=1) && (!usuario.contains("@")) && (dominio.contains(".")) && (!dominio.contains("@")) && (dominio.indexOf(".") >= 1) && (dominio.lastIndexOf(".") < dominio.length() - 1)) { 
				emailField.setBorder(new BevelBorder(BevelBorder.LOWERED, GreenColor, GreenColor, GreenColor, GreenColor));
				emailField.setForeground(GreenColor);
				return true;
			} else { 
				
				emailField.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
				emailField.setForeground(OrangeColor);
				
				 
				
			} 
		} else { 
			
			emailField.setBorder(new BevelBorder(BevelBorder.LOWERED, OrangeColor, OrangeColor, OrangeColor, OrangeColor));
			emailField.setForeground(OrangeColor);
			return false;

		}
		return false;

		
		
	}
	

	public void buscarCep(JFormattedTextField formattedfield1, JTextField field1, JTextField field2, 
			JTextField field3, JComboBox<Object> combo1) {
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

			//caso haja problemas imprime as exce??es.
		} else {
			//JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
			//JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());

			JOptionPane.showMessageDialog(null, webServiceCep.getResulCode() + webServiceCep.getResultText());}
			//JOptionPane.showMessageDialog(null, webServiceCep.getResulCode() +  "" + webServiceCep.getResultText());}
	}
	
	public static boolean validation_CNPJ(String cnpj){  

		int soma = 0, /*aux,*/ dig;  
		String cnpj_calc = cnpj.substring(0,12);  

		if ( cnpj.length() != 14 )  
			return false;  

		char[] chr_cnpj = cnpj.toCharArray();  

		/* Primeira parte */  
		for( int i = 0; i < 4; i++ )  
			if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )  
				soma += (chr_cnpj[i] - 48 ) * (6 - (i + 1)) ;  
		for( int i = 0; i < 8; i++ )  
			if ( chr_cnpj[i+4]-48 >=0 && chr_cnpj[i+4]-48 <=9 )  
				soma += (chr_cnpj[i+4] - 48 ) * (10 - (i + 1)) ;  
		dig = 11 - (soma % 11);  

		cnpj_calc += ( dig == 10 || dig == 11 ) ?  
				"0" : Integer.toString(dig);  

		/* Segunda parte */  
		soma = 0;  
		for ( int i = 0; i < 5; i++ )  
			if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )  
				soma += (chr_cnpj[i] - 48 ) * (7 - (i + 1)) ;  
		for ( int i = 0; i < 8; i++ )  
			if ( chr_cnpj[i+5]-48 >=0 && chr_cnpj[i+5]-48 <=9 )  
				soma += (chr_cnpj[i+5] - 48 ) * (10 - (i + 1)) ;  
		dig = 11 - (soma % 11);  
		cnpj_calc += ( dig == 10 || dig == 11 ) ?  
				"0" : Integer.toString(dig);  

		return cnpj.equals(cnpj_calc);  

	}
	
	public static String printCNPJ(String CNPJ) {
		return (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "."
				+ CNPJ.substring(5, 8) + "/" + CNPJ.substring(8, 12)+ "-" +CNPJ.substring(12,14));
	}
	
	public static boolean validateFornCNPJ(FornecedorBean p) {

		String cnpj = p.getCnpj_fornec();

		try{
			cnpj = cnpj.replace(".", "");
			cnpj = cnpj.replace("/", "");
			cnpj = cnpj.replace("-", "");
		}catch(Exception e){

		}
		if (FieldsValidation.validation_CNPJ(cnpj) == true) {

			return true;
		} else {

			return false;
		}

	}
	
//-------------------------------------------------------------------------
	
	public static void DateValidationInterval(JDateChooser datechooser){
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	Date dtAtual = new Date();
	
	Calendar cal = Calendar.getInstance();
	cal.setTime(dtAtual);
	cal.add(Calendar.YEAR, -120);
	
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(dtAtual);
	cal2.add(Calendar.YEAR, -16);
	
	Date dtAtualSubtracted = cal2.getTime();
	
	Date dtIni = cal.getTime();
	
	System.out.println("dtIni:" + sdf.format(dtIni));
	System.out.println("dtAtual:" + sdf.format(dtAtual));
	System.out.println("dtAtualSubtracted:" +sdf.format(dtAtualSubtracted));
	
	datechooser.setSelectableDateRange(dtIni, dtAtualSubtracted);
	
	
}
}
	
	

