package utilities;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	
	/**
	 * Author: Ygor
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private ArrayList linhas = null;
	private String[] colunas = null;

	@SuppressWarnings("rawtypes")
	public TableModel(ArrayList lin, String[] col){
		
		setLinhas(lin);
		setColunas(col);
		
	}

	@Override
	public int getColumnCount() {
	
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}
	
	public String getColumnName(int numCol){
		
		return colunas[numCol];
		
	}

	@Override
	public Object getValueAt(int numLin, int numCol) {
		
		Object[] linha = (Object[]) getLinhas().get(numLin);
		
		return linha[numCol];
	}

	@SuppressWarnings("rawtypes")
	public ArrayList getLinhas() {
		return linhas;
	}

	public void setLinhas(@SuppressWarnings("rawtypes") ArrayList linhas) {
		this.linhas = linhas;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}
	
	
}
