package casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Módulo de JPA do Spring.
 * Classe de configuração do JPA e de acesso ao banco de dados
 */
@EnableTransactionManagement//Habilita o controle de transações para o Spring.
public class JPAConfiguration {
	
	/*
	 * Método responsável por gerar o EntityManager.
	 * setJpaVendorAdapter: Especifica o tipo de implementação da JPA
	 */
	@Bean //Objeto gerenciado pelo Spring
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties aditionalProperties) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		//Define o pacote que será gerenciado pelo bean.
		factoryBean.setPackagesToScan("casadocodigo.loja.models");
		factoryBean.setDataSource(dataSource);
		
		/*JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();*/
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(aditionalProperties);
		
		return factoryBean;
	}
	
	/**
	 * @Profile("dev"): Definição do profile dev  
	 * para especificar o datasource usado em desenvolvimento.
	 */
	@Bean
	@Profile("dev")
	public Properties aditionalProperties() {
		//Propriedades específicas do Hibernate
		Properties props = new Properties();
		//Dialeto que o Hibernate vai usar para conversar com o banco de dados, 
		//pois cada banco, além do SQL ANSI, possui implementações específicas.
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.show_sql", "true");//Mostra SQL
		props.setProperty("hibernate.hbm2ddl.auto", "update");//Para cada bean alterado, o BD será atualizado.
		return props;
	}

	/**
	 * @Profile("dev"): Definição do profile dev  
	 * para especificar o datasource usado em desenvolvimento.
	 */
	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Bean//Bean qualificado para cuidar do gerenciamento da transação, unindo a transação ao EntityManager.
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
