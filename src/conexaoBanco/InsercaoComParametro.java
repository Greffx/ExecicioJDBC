package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection connection = factory.recuperarConexao();) {
			
			connection.setAutoCommit(false);
			
			try( PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);){
				adicionarVariavel("Tv Smart", "TV 45 polegadas", statement);
				adicionarVariavel("Radio", "radio de bateria", statement);
				
				connection.commit();
					
			} catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();//volte para trás
			}
		}
	}
	//CRIADO PARA ADICIONAR MAIS DE UM PRODUTO
	private static void adicionarVariavel(String nome, String descricao, PreparedStatement statement) throws SQLException {
	
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		if(nome.equals("Radio")) {
			throw new RuntimeException("Dado inválido");
		}
		
		statement.execute();// PARA EXECUTAR O PROGRAMA
		//RESULTSET PARA MOSTRAR O RESULTADO
		try(ResultSet result = statement.getGeneratedKeys();){//try com recurso, daí não precisamos fechar
			
		
			while(result.next()) {
				Integer id = result.getInt(1);
				System.out.println("O ID criado foi: " + id);
			}
		}
		
	}
}
