package dataAcessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utilities.ConnectionFactory;
import acessoDadosBeans.GeneroLivroBean;

public class GeneroLivroDAO {
	
	private static final String Select_Genero_Livro = "select * from Genero_Livro order by Id_Genero";
	
	public List<GeneroLivroBean> buscarGenero() {
		List<GeneroLivroBean> lista = new ArrayList<GeneroLivroBean>();
		
		//lista.add(new CargoInformacao(1, "Gerente"));
		//lista.add(new CargoInformacao(2, "Recepcionista"));
//		lista.add(new Cargo(2, "Coordenador"));
//		lista.add(new Cargo(3, "Caixa"));
//		lista.add(new Cargo(4, "Recepcionista"));
//		lista.add(new Cargo(5, "Analista"));
		
		PreparedStatement statement = null;
		Connection conexao = null;
		ResultSet result = null;
		GeneroLivroBean genero = null;
		try {
			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Select_Genero_Livro);

			result = statement.executeQuery();
			while (result.next()) {
				
				genero = getBean(result);
				lista.add(genero);
			}

		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			try {
				ConnectionFactory.close(null, statement, conexao);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		return lista;
	}

	private GeneroLivroBean getBean(ResultSet result) throws SQLException {
		return new GeneroLivroBean(result.getInt("id_genero"), result.getString("nome_genero"));
	}


}
