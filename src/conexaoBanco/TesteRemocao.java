package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteRemocao {

	public static void main(String[] args) throws SQLException{

		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		
		PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		statement.setInt(1, 2);
		statement.execute();
		
		Integer linhasModificadas = statement.getUpdateCount();
		
		System.out.println("Quantidade de linhas excluídas: " + linhasModificadas);
		
		connection.close();
	}

}
