package utilities;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import acessoDadosBeans.ClienteBean;
import acessoDadosBeans.FornecedorBean;
import acessoDadosBeans.FuncionarioBean;
import acessoDadosBeans.PedidoVendaRegistroBean;
import acessoDadosBeans.ProdutoLivroBean;
import dataAcessObjects.ClienteDAO;
import dataAcessObjects.FornecedorDAO;
import dataAcessObjects.FuncionarioDAO;
import dataAcessObjects.PedidoVendaRegistroDAO;
import dataAcessObjects.ProdutoLivroDAO;

public class PesquisaDados {

	
	public static void PesqNomeCli(ClienteBean c, JComboBox<Object> statusCombo, JTable tableFunc, JTextField pesquisaField){

		c.setNome_cli(pesquisaField.getText().trim());
		String nomeColumn = "Nome_cli";
		String getterName = String.valueOf(c.getNome_cli());

		if(!c.getNome_cli().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo")){
			c.setAtivo_inativo_status("Ativo");
			ClienteDAO.preencherTableClientePesquisaAtivoInativo(tableFunc, c, nomeColumn, getterName);
		}
		else if(!c.getNome_cli().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){

			c.setAtivo_inativo_status("Inativo");
			ClienteDAO.preencherTableClientePesquisaAtivoInativo(tableFunc, c, nomeColumn, getterName);

		}
		else if(!c.getNome_cli().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			ClienteDAO.preencherTableClientePesquisa(tableFunc, c, nomeColumn, getterName);

		}else if(c.getNome_cli().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo") ){
			ClienteDAO.LoadAtivoClienteTable(tableFunc);
		}
		else if(c.getNome_cli().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){
			ClienteDAO.LoadInativoClienteTable(tableFunc);
		}else if (c.getNome_cli().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			ClienteDAO.LoadClienteTable(tableFunc);
		}
	}

	
	
	//FuncPanel dados

	public static void PesqNomeFunc(FuncionarioBean f, JComboBox<Object> statusCombo, JTable tableFunc, JTextField pesquisaField){

		f.setNome_func(pesquisaField.getText().trim());
		String nomeColumn = "Nome_func";
		String getterName = String.valueOf(f.getNome_func());

		if(!f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo")){
			f.setAtivo_inativo_status("Ativo");
			FuncionarioDAO.preencherTableFuncionarioPesquisaAticoInativo(tableFunc, f, nomeColumn, getterName);
		}
		else if(!f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){

			f.setAtivo_inativo_status("Inativo");
			FuncionarioDAO.preencherTableFuncionarioPesquisaAticoInativo(tableFunc, f, nomeColumn, getterName);

		}else if(!f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("em Recesso") ){

			f.setAtivo_inativo_status("em Recesso");
			FuncionarioDAO.preencherTableFuncionarioPesquisaAticoInativo(tableFunc, f, nomeColumn, getterName);

		}
		else if(!f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			FuncionarioDAO.preencherTableFuncionarioPesquisa(tableFunc, f, nomeColumn, getterName);

		}else if(f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo") ){
			FuncionarioDAO.LoadAtivoFuncionarioTable(tableFunc);
		}
		else if(f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){
			FuncionarioDAO.LoadInativoFuncionarioTable(tableFunc);
		}else if (f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("em Recesso") ){
			FuncionarioDAO.LoadInativoVacationFuncionarioTable(tableFunc);
		}else if (f.getNome_func().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			FuncionarioDAO.LoadFuncionarioTable(tableFunc);
		}
	}
	

	public static void PesqNomeFornec(FornecedorBean p, JComboBox<Object> statusCombo, JTable tableFunc, JTextField pesquisaField){

		p.setNome_fornec(pesquisaField.getText().trim());
		String nomeColumn = "Nome_fornec";
		String getterName = String.valueOf(p.getNome_fornec());

		if(!p.getNome_fornec().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo")){
			p.setAtivo_inativo_status_fornec("Ativo");
			FornecedorDAO.preencherTableFornecedorPesquisaAticoInativo(tableFunc, p, nomeColumn, getterName);
		}else if(!p.getNome_fornec().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){

			p.setAtivo_inativo_status_fornec("Inativo");
			FornecedorDAO.preencherTableFornecedorPesquisaAticoInativo(tableFunc, p, nomeColumn, getterName);

		}else if(!p.getNome_fornec().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			FornecedorDAO.preencherTableFuncionarioPesquisa(tableFunc, p, nomeColumn, getterName);

		}else if(p.getNome_fornec().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo") ){
			FornecedorDAO.LoadAtivoFornecedorTable(tableFunc);
		}
		else if(p.getNome_fornec().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){
			FornecedorDAO.LoadInativoFornecedorTable(tableFunc);

		}else if (p.getNome_fornec().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			FornecedorDAO.LoadFornecedorTable(tableFunc);
		}

	}
	
	public static void PesqNomeLivro(ProdutoLivroBean pl, JComboBox<Object> statusCombo, JTable tableFunc, JTextField pesquisaField){

		pl.setNome_livro_prod(pesquisaField.getText().trim());
		String nomeColumn = "Nome_Produto_livro";
		String getterName = String.valueOf(pl.getNome_livro_prod());

		if(!pl.getNome_livro_prod().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo")){
			pl.setAtivoInativoStatusProdLivro("Ativo");
			ProdutoLivroDAO.preencherTableLivroPesquisaAtivoInativo(tableFunc, pl, nomeColumn, getterName);
		}else if(!pl.getNome_livro_prod().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){

			pl.setAtivoInativoStatusProdLivro("Inativo");
			ProdutoLivroDAO.preencherTableLivroPesquisaAtivoInativo(tableFunc, pl, nomeColumn, getterName);

		}else if(!pl.getNome_livro_prod().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			ProdutoLivroDAO.preencherTableLivroPesquisa(tableFunc, pl, nomeColumn, getterName);

		}else if(pl.getNome_livro_prod().isEmpty() & statusCombo.getSelectedItem().toString().equals("Ativo") ){
			ProdutoLivroDAO.loadAtivoLivroProdTable(tableFunc);
		}
		else if(pl.getNome_livro_prod().isEmpty() & statusCombo.getSelectedItem().toString().equals("Inativo") ){
			ProdutoLivroDAO.loadInativoLivroProdTable(tableFunc);

		}else if (pl.getNome_livro_prod().isEmpty() & statusCombo.getSelectedItem().toString().equals("Selecione...") ){
			ProdutoLivroDAO.loadLivroProdTable(tableFunc);
		}

	}
	
	//continue here, insert pesqContactMethod
	
	public static void PesqIDVenda(PedidoVendaRegistroBean pvr, JTable tableFunc, JTextField pesquisaField){

		pvr.setId_PedidoVendaRegistro(Integer.parseInt(pesquisaField.getText().trim()));
		String nomeColumn = "Id_Pedido_VendaRegistro";
		String getterName = String.valueOf(pvr.getId_PedidoVendaRegistro());

		PedidoVendaRegistroDAO.preencherTableVendaPesquisa(tableFunc, pvr, nomeColumn, getterName);
	}
	

}
