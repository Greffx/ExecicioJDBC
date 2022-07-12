package conexaoBanco;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexao {
	
	public static void main(String[] args) throws SQLException {
		//UMA EXECEPTION PARA SER JOGADA E FUNCIONAR A conectividade
		//AQUI FOI INSTANCIADO O drivermanager. connection, daí o caminho para a database, o usuario e senha de quem controla
//		Connection connection = DriverManager.
//		getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC","root","10210202");
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		Connection connection = connectionFactory.recuperarConexao();
		
		System.out.println("Fechando conexão");
		connection.close();
	}
}
