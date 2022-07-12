package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoBanco.Categoria;
import conexaoBanco.Produto;

public class CategoriaDAO {

	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Categoria> listar() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		
		System.out.println("Executando a Query de listar categoria");
		
		String sql = "SELECT ID, NOME FROM CATEGORIA";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.execute();
			
			try(ResultSet result = statement.getResultSet()) {
				while(result.next()) {
					Categoria categoria = new Categoria(result.getInt(1), result.getString(2));
					
					categorias.add(categoria);
				}
					
			}
		}
		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		
		Categoria ultima = null;
		List<Categoria> categorias = new ArrayList<>();
		
		System.out.println("Executando a Query de listar categoria");
		
		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN" + " PRODUTO P ON C.ID = P.CATEGORIA_ID";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.execute();
			
			try(ResultSet result = statement.getResultSet()) {
				while(result.next()) {
					if(ultima == null || !ultima.getNome().equals(result.getString(2))) {
						Categoria categoria = new Categoria(result.getInt(1), result.getString(2));
						ultima = categoria;
						categorias.add(categoria);
					}
					Produto produto = new Produto(result.getInt(3), result.getString(4), result.getString(5));
					ultima.adicionar(produto);
				}
					
			}
		}
		return categorias;
	}
}
