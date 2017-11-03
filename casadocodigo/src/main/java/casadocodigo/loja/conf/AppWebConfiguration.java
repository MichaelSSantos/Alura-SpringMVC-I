package casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import casadocodigo.loja.controllers.HomeController;
import casadocodigo.loja.dao.ProdutoDAO;

/**
 * Módulo de Web MVC do Spring.
 * Classe de configuração do SpringMVC.
 */
@EnableWebMvc//Habilita o recurso de Web MVC do SpringMVC
//@ComponentScan(basePackages= {"casadocodigo.loja.controllers"})//Informa o pacote dos controllers
//Informa a classe controller e o Spring MVC irá escanear todos os controllers do pacote da classe informada.
//O Spring irá escanear todos os controllers e daos.
@ComponentScan(basePackageClasses= {HomeController.class, ProdutoDAO.class})
public class AppWebConfiguration {

	/**
	 * Resolvedor Interno de Recursos de View: que ajuda o SpringMVC a encontrar as views. 
	 * setPrefix: Define o caminho onde estão nossas views.
	 * setSuffix: Adiciona a extenção dos arquivos de view.
	 */
	@Bean//Objeto gerenciado pelo Spring
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**
	 * Método responsável por mapear o arquivo de propriedades do Spring
	 * */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource 
			= new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
		/*Tempo para que o cache do arquivo de propriedades seja 
		  limpado sem que haja necessidade de reiniciar o servidor.*/
		messageSource.setCacheSeconds(1);
		
		return messageSource;
	}
	
}
	