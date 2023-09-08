package acessoDadosBeans;

import java.util.Arrays;

public class ProdutoLivroBean {
	
	int Id_livro_prod;
	int id_genero_fk;
	String nome_livro_prod;
	String Nome_Genero;
	String ISBN_livro;
	String Autor_livro;
	String Edicao_livro;
	int Ano_livro;
	float preco_livro_prod;
	int qtd_livro_prod;
	int Qtd_venda_livro_prod;
	int id_fornec_fk;
	String Nome_Fornecedor;
	byte[] Photo_Book;
	
	String dataInsercaoProdLivro;
	String dataResicaoProdLivro;
	String ativoInativoStatusProdLivro;
	FuncionarioBean func;
	
	
	
	public int getId_livro_prod() {
		return Id_livro_prod;
	}

	public void setId_livro_prod(int id_livro_prod) {
		Id_livro_prod = id_livro_prod;
	}

	public String getNome_livro_prod() {
		return nome_livro_prod;
	}

	public void setNome_livro_prod(String nome_livro_prod) {
		this.nome_livro_prod = nome_livro_prod;
	}

	public String getNome_Genero() {
		return Nome_Genero;
	}

	public void setNome_Genero(String nome_Genero) {
		Nome_Genero = nome_Genero;
	}

	public float getPreco_livro_prod() {
		return preco_livro_prod;
	}

	public void setPreco_livro_prod(float preco_livro_prod) {
		this.preco_livro_prod = preco_livro_prod;
	}

	public int getQtd_livro_prod() {
		return qtd_livro_prod;
	}

	public void setQtd_livro_prod(int qtd_livro_prod) {
		this.qtd_livro_prod = qtd_livro_prod;
	}

	public int getQtd_venda_livro_prod() {
		return Qtd_venda_livro_prod;
	}

	public void setQtd_venda_livro_prod(int qtd_venda_livro_prod) {
		Qtd_venda_livro_prod = qtd_venda_livro_prod;
	}

	public int getId_genero_fk() {
		return id_genero_fk;
	}

	public void setId_genero_fk(int id_genero_fk) {
		this.id_genero_fk = id_genero_fk;
	}

	public String getISBN_livro() {
		return ISBN_livro;
	}

	public void setISBN_livro(String iSBN_livro) {
		ISBN_livro = iSBN_livro;
	}

	public String getAutor_livro() {
		return Autor_livro;
	}

	public void setAutor_livro(String autor_livro) {
		Autor_livro = autor_livro;
	}

	public String getEdicao_livro() {
		return Edicao_livro;
	}

	public void setEdicao_livro(String edicao_livro) {
		Edicao_livro = edicao_livro;
	}


	public int getAno_livro() {
		return Ano_livro;
	}

	public void setAno_livro(int ano_livro) {
		Ano_livro = ano_livro;
	}

	public byte[] getPhoto_Book() {
		return Photo_Book;
	}

	public void setPhoto_Book(byte[] photo_Book) {
		Photo_Book = photo_Book;
	}

	public int getId_fornec_fk() {
		return id_fornec_fk;
	}

	public void setId_fornec_fk(int id_fornec_fk) {
		this.id_fornec_fk = id_fornec_fk;
	}

	public String getNome_Fornecedor() {
		return Nome_Fornecedor;
	}

	public void setNome_Fornecedor(String nome_Fornecedor) {
		Nome_Fornecedor = nome_Fornecedor;
	}

	public String getDataInsercaoProdLivro() {
		return dataInsercaoProdLivro;
	}

	public void setDataInsercaoProdLivro(String dataInsercaoProdLivro) {
		this.dataInsercaoProdLivro = dataInsercaoProdLivro;
	}

	public String getDataResicaoProdLivro() {
		return dataResicaoProdLivro;
	}

	public void setDataResicaoProdLivro(String dataResicaoProdLivro) {
		this.dataResicaoProdLivro = dataResicaoProdLivro;
	}

	public String getAtivoInativoStatusProdLivro() {
		return ativoInativoStatusProdLivro;
	}

	public void setAtivoInativoStatusProdLivro(String ativoInativoStatusProdLivro) {
		this.ativoInativoStatusProdLivro = ativoInativoStatusProdLivro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Ano_livro;
		result = prime * result
				+ ((Autor_livro == null) ? 0 : Autor_livro.hashCode());
		result = prime * result
				+ ((Edicao_livro == null) ? 0 : Edicao_livro.hashCode());
		result = prime * result
				+ ((ISBN_livro == null) ? 0 : ISBN_livro.hashCode());
		result = prime * result + Id_livro_prod;
		result = prime * result + Arrays.hashCode(Photo_Book);
		result = prime * result + Qtd_venda_livro_prod;
		result = prime
				* result
				+ ((ativoInativoStatusProdLivro == null) ? 0
						: ativoInativoStatusProdLivro.hashCode());
		result = prime
				* result
				+ ((dataInsercaoProdLivro == null) ? 0 : dataInsercaoProdLivro
						.hashCode());
		result = prime
				* result
				+ ((dataResicaoProdLivro == null) ? 0 : dataResicaoProdLivro
						.hashCode());
		result = prime * result + id_fornec_fk;
		result = prime * result + id_genero_fk;
		result = prime * result
				+ ((nome_livro_prod == null) ? 0 : nome_livro_prod.hashCode());
		result = prime * result + Float.floatToIntBits(preco_livro_prod);
		result = prime * result + qtd_livro_prod;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoLivroBean other = (ProdutoLivroBean) obj;
		if (Ano_livro != other.Ano_livro)
			return false;
		if (Autor_livro == null) {
			if (other.Autor_livro != null)
				return false;
		} else if (!Autor_livro.equals(other.Autor_livro))
			return false;
		if (Edicao_livro == null) {
			if (other.Edicao_livro != null)
				return false;
		} else if (!Edicao_livro.equals(other.Edicao_livro))
			return false;
		if (ISBN_livro == null) {
			if (other.ISBN_livro != null)
				return false;
		} else if (!ISBN_livro.equals(other.ISBN_livro))
			return false;
		if (Id_livro_prod != other.Id_livro_prod)
			return false;
		if (!Arrays.equals(Photo_Book, other.Photo_Book))
			return false;
		if (Qtd_venda_livro_prod != other.Qtd_venda_livro_prod)
			return false;
		if (ativoInativoStatusProdLivro == null) {
			if (other.ativoInativoStatusProdLivro != null)
				return false;
		} else if (!ativoInativoStatusProdLivro
				.equals(other.ativoInativoStatusProdLivro))
			return false;
		if (dataInsercaoProdLivro == null) {
			if (other.dataInsercaoProdLivro != null)
				return false;
		} else if (!dataInsercaoProdLivro.equals(other.dataInsercaoProdLivro))
			return false;
		if (dataResicaoProdLivro == null) {
			if (other.dataResicaoProdLivro != null)
				return false;
		} else if (!dataResicaoProdLivro.equals(other.dataResicaoProdLivro))
			return false;
		if (id_fornec_fk != other.id_fornec_fk)
			return false;
		if (id_genero_fk != other.id_genero_fk)
			return false;
		if (nome_livro_prod == null) {
			if (other.nome_livro_prod != null)
				return false;
		} else if (!nome_livro_prod.equals(other.nome_livro_prod))
			return false;
		if (Float.floatToIntBits(preco_livro_prod) != Float
				.floatToIntBits(other.preco_livro_prod))
			return false;
		if (qtd_livro_prod != other.qtd_livro_prod)
			return false;
		return true;
	}

	public FuncionarioBean getFunc() {
		return func;
	}

	public void setFunc(FuncionarioBean func) {
		this.func = func;
	}
	

	public ProdutoLivroBean(){
		super();
		this.func = new FuncionarioBean();
	}

}
