package casadocodigo.loja.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Arquivo de configuração do Spring para atender a todas as requisições a partir do path "/" 
 * Extende das configurações do AbstractAnnotationConfigDispatcherServletInitializer, 
 * que inicializa as configurações do Spring.
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//Classes de configuração do servlet (esta classe) do SpringMVC.
		return new Class[] {AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		//O servlet do SpringMVC (esta classe) atenderá as requisições a partir da raiz do projeto (/).
		return new String[]{"/"};
	}

}
