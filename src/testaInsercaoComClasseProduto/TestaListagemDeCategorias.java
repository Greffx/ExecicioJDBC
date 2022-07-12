package testaInsercaoComClasseProduto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import conexaoBanco.Categoria;
import conexaoBanco.ConnectionFactory;
import conexaoBanco.Produto;
import dao.CategoriaDAO;

public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()) {
		
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			
			List<Categoria> listaDeCategoria = categoriaDAO.listarComProdutos();
			listaDeCategoria.stream().forEach(ct -> {
				System.out.println(ct.getNome());
				for(Produto produto : ct.getProdutos()) {
					System.out.println(ct.getNome() + " - " + produto.getNome());
				}
			});
		}
		
		
	}

}
