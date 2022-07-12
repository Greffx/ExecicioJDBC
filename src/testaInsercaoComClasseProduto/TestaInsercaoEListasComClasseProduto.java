package testaInsercaoComClasseProduto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import conexaoBanco.ConnectionFactory;
import conexaoBanco.Produto;
import dao.ProdutoDAO;

public class TestaInsercaoEListasComClasseProduto {

	public static void main(String[] args) throws SQLException {

		Produto produto = new Produto("Video Game", "Play4");
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()) {
			
			ProdutoDAO produtoDao =	new ProdutoDAO(connection);
			
			produtoDao.salvar(produto);
			
			List<Produto> listaDeProdutos = produtoDao.listar();
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));//lambda para exibir a lista de produtos
		}
				
			
	}
}
