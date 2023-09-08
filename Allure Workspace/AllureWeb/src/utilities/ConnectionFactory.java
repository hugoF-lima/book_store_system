package utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection connection = null;
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Allure";
	private static final String LOGIN = "sa";
	private static final String SENHA = "12345678";
	
	/*Metodo que faz conexao com o banco de dados 
	 * retorna true se houver sucesso, ou false em caso necessario
	 * */
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL,LOGIN,SENHA);
		} catch (Exception erro) {
			throw new RuntimeException(erro);
		}
	}
	
	public static void close(
			ResultSet rs,
			PreparedStatement statement, 
			Connection conexao)
			throws SQLException {
		if (rs != null) {
			rs.close();	
		}
		if (statement != null) {
			statement.close();	
		}
		if (conexao != null) {
			conexao.close();
		}
	}
}
