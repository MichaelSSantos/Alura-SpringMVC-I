package casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Arquivo de configuração do Spring para atender a todas as requisições a partir do path "/" 
 * Extende das configurações do AbstractAnnotationConfigDispatcherServletInitializer, 
 * que inicializa as configurações do Spring.
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Sobe as classes de configuração quando a aplicação for iniciada.
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class};
	}
	
	//Sobe as classes de configuração quando a aplicação for acessada.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		//Classes de configuração do servlet (esta classe) do SpringMVC.
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		//O servlet do SpringMVC (esta classe) atenderá as requisições a partir da raiz do projeto (/).
		return new String[]{"/"};
	}
	
	/**
	 * ====== padrão Lazy Initialization ======
	 * Usado quando não se sabe quais objetos serão recuperados. Usar com cuidado, 
	 * porque isso diminui a performance da aplicação.
	 * 
	 * OpenEntityManagerInViewFilter: O EntityManager é instanciado e a sessão é aberta pelo JPA no momento em que 
	 * se conjunta os dados no DAO. Ao sair do DAO, então a transação é fechada.
	 * No entanto, ao chegar na JSP, se o objeto retornado precisar de informações de outros objetos que se relacionam com 
	 * ele, então ocorrerá um exceção de Lasy, porque estas informações não estarão disponíveis.
	 * 
	 * A utilização do filtro OpenEntityManagerInViewFilter permite manter a transação e o EntityManger abertos até que 
	 * o objeto chegue à JSP. Caso seja necessário fazer outra requisição ao BD, então o JPA se encarrega de obter essas 
	 * informações.
	 */
	@Override//encondig -> Codificação
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		//Definição do mapa de caracteres utilizado pela aplicação. 
		//UTF-8 ou ISO-8859-1
		encodingFilter.setEncoding("ISO-8859-1");
		return new Filter[] {encodingFilter, new OpenEntityManagerInViewFilter()};
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
