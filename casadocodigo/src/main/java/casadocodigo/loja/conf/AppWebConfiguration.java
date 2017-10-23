package casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import casadocodigo.loja.controllers.HomeController;

@EnableWebMvc//Habilita o recurso de Web MVC do SpringMVC
//@ComponentScan(basePackages= {"casadocodigo.loja.controllers"})//Informa o pacote dos controllers
//Informa a classe controller e o Spring MVC irá escanear todos os controllers do pacote da classe informada.
@ComponentScan(basePackageClasses= {HomeController.class})
public class AppWebConfiguration {

	//Resolvedor interno de views(páginas).
	//Mapeia o prefixo e o sufixo para evitar repetição nos retornos do controllers.
	@Bean//Classe gerenciada pelo Spring
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
