package utilities;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class FieldMasks {
	
	public static void CepMask(JFormattedTextField formattedField1) {
		MaskFormatter CEPMask;
		try {
			CEPMask = new MaskFormatter("#####-###");
			CEPMask.setPlaceholderCharacter(' ');
			CEPMask.install(formattedField1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void IsbnMask(JFormattedTextField formattedField1) {
		MaskFormatter CEPMask;
		try {
			CEPMask = new MaskFormatter("###-##-####-###-#");
			CEPMask.setPlaceholderCharacter(' ');
			CEPMask.install(formattedField1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void CpfMask(JFormattedTextField formattedField1) {
		MaskFormatter CpfMask;
		try {
			CpfMask = new MaskFormatter("###.###.###-##");
			CpfMask.setPlaceholderCharacter(' ');
			CpfMask.install(formattedField1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void TelMask(JFormattedTextField formattedField1) {
		MaskFormatter TelMask;
		try {
			TelMask = new MaskFormatter("(##)########");
			TelMask.setPlaceholderCharacter(' ');
			TelMask.install(formattedField1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void CNPJMask(JFormattedTextField formattedField1) {
		MaskFormatter CNPJMask;
		try {
			CNPJMask = new MaskFormatter("##.###.###/####-##");
			CNPJMask.setPlaceholderCharacter(' ');
			CNPJMask.install(formattedField1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
