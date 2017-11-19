package casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Classe utilizada para definir as configurações do datasource de produção.
 */
@Profile("prod")
public class JPAProductionConfiguration {
	
	/**
	 *Enviroment: Objeto do Spring utilizado para pegar variáveis de ambiente.
	 */
	@Autowired
	private Environment environment;
	
	@Bean
	public Properties aditionalProperties() {
		//Propriedades específicas do Hibernate
		Properties props = new Properties();
		//Dialeto que o Hibernate vai usar para conversar com o banco de dados, 
		//pois cada banco, além do SQL ANSI, possui implementações específicas.
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.show_sql", "true");//Mostra SQL
		props.setProperty("hibernate.hbm2ddl.auto", "update");//Para cada bean alterado, o BD será atualizado.
		return props;
	}
	
	/**
	 * DATABASE_URL: Variável de ambiente que o Heroku define com as configurações do bando de dados.
	 * Localização dos parâmetros da variável: usuario:senha@host:port/path
	 * @throws URISyntaxException 
	 */
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		
		URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));
		
		dataSource.setUrl("jdbc:postgresql://"+dbUrl.getHost()+":"+dbUrl.getPort()+dbUrl.getPath());
		dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
		dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);
		return dataSource;
	}
	
}
