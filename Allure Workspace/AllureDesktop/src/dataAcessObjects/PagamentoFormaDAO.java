package dataAcessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utilities.ConnectionFactory;
import acessoDadosBeans.PagamentoFormaBean;

public class PagamentoFormaDAO {
	private static final String Select_Forma_Pagamento = "select * from PagamentoForma order by id_forma_pagamento";

	
	public List<PagamentoFormaBean> buscarFormaPagamento() {
		List<PagamentoFormaBean> lista = new ArrayList<PagamentoFormaBean>();
		

		
		PreparedStatement statement = null;
		Connection conexao = null;
		ResultSet result = null;
		PagamentoFormaBean pagamento = null;
		try {
			conexao = ConnectionFactory.getConnection();
			statement = conexao.prepareStatement(Select_Forma_Pagamento);

			result = statement.executeQuery();
			while (result.next()) {
				
				pagamento = getBean(result);
				lista.add(pagamento);
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

	private PagamentoFormaBean getBean(ResultSet result) throws SQLException {
		return new PagamentoFormaBean(result.getInt("id_forma_pagamento"), result.getString("nome_pagamento"));
	}

}
