package conexaoBanco;

import java.sql.SQLException;

public class TestePoolConexao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		
		for(int i = 0; i < 20; i++) {
			factory.recuperarConexao();
			System.out.println("conexão: " + i);
		}
	}

}
