package conexaoBanco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsert {

	public static void main(String[] args) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		
		Statement statement = connection.createStatement(); 
				
		statement.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('mesa','mesa redonda');", Statement.RETURN_GENERATED_KEYS);
		

		ResultSet result = statement.getGeneratedKeys();
		
		while(result.next()) {
			Integer id = result.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}
		
		connection.close();
	}
}
