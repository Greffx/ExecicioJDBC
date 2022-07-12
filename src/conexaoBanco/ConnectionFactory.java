package conexaoBanco;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource; //vai fazer o pull funcionar
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("10210202");
		comboPooledDataSource.setMaxPoolSize(15);// max de pessoas que podem fazer conexão
		
		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() throws SQLException{
		
		return this.dataSource.getConnection();
	}

}
