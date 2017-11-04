package casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		factoryBean.setDataSource(dataSource);
		
		//Propriedades específicas do Hibernate
		Properties props = new Properties();
		//Dialeto que o Hibernate vai usar para conversar com o banco de dados, 
		//pois cada banco, além do SQL ANSI, possui implementações específicas.
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.show_sql", "true");//Mostra SQL
		props.setProperty("hibernate.hbm2ddl.auto", "update");//Para cada bean alterado, o BD será atualizado.
		factoryBean.setJpaProperties(props);
		
		//Define o pacote que será gerenciado pelo bean.
		factoryBean.setPackagesToScan("casadocodigo.loja.models");
		
		return factoryBean;
	}
	
	@Bean//Bean qualificado para cuidar do gerenciamento da transação, unindo a transação ao EntityManager.
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
