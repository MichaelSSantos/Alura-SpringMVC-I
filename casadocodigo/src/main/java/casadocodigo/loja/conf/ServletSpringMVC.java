package casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
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
	
	@Override//encondig -> Codificação
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		//Definição do mapa de caracteres utilizado pela aplicação. 
		//UTF-8 ou ISO-8859-1
		encodingFilter.setEncoding("ISO-8859-1");
		return new Filter[] {encodingFilter};
	}
	
	/**
	 * Registrador de tipos de arquivos.
	 * new MultipartConfigElement(""): Informa o que deve ser considerado 
	 * ou desconsiderado do path após o upload do arquivo.
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
