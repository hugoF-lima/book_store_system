package acessoDadosBeans;

public class GeneroLivroBean {
	
	private int id_Genero;
	private String Nome_Genero;
	
	public GeneroLivroBean(int id_Genero, String Nome_Genero){
		super();
		this.id_Genero = id_Genero;
		this.Nome_Genero = Nome_Genero;
		
	}
	
	@Override
	public String toString() {
		return Nome_Genero;
	}

	public int getId_Genero() {
		return id_Genero;
	}

	public void setId_Genero(int id_Genero) {
		this.id_Genero = id_Genero;
	}

	public String getNome_Genero() {
		return Nome_Genero;
	}

	public void setNome_Genero(String nome_Genero) {
		Nome_Genero = nome_Genero;
	}
	
	public GeneroLivroBean(){
		
	}

	/*@Override
	public String toString() {
		return "GeneroLivroBean [id_Genero=" + id_Genero + ", Nome_Genero="
				+ Nome_Genero + "]";
	}*/

}
