package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexaoBanco.Categoria;
import conexaoBanco.Produto;

public class ProdutoDAO {
	
	private Connection connection;
	
	public ProdutoDAO(Connection connection) {
		
		this.connection = connection;
	}
	
	public void salvar(Produto produto) throws SQLException {
		
		String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)";
		
		try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			
			statement.execute();
			
			try(ResultSet result = statement.getGeneratedKeys()) {
				
				while(result.next()) {
					produto.setId(result.getInt(1));
				}
			}

		}
	}
	
	public List<Produto> listar() throws SQLException {
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT id, nome, descricao FROM PRODUTO";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			
			try(ResultSet result = statement.getResultSet()) {
				while(result.next()) {
					Produto produto = new Produto(result.getInt(1), result.getString(2), result.getString(3));
					
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
	
	public List<Produto> buscar(Categoria ct) throws SQLException {
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		System.out.println("Executando a Query de buscar produto por categoria");
		
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, ct.getId());
			statement.execute();
			
			try(ResultSet result = statement.getResultSet()) {
				while(result.next()) {
					Produto produto = new Produto(result.getInt(1), result.getString(2), result.getString(3));
					
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
}