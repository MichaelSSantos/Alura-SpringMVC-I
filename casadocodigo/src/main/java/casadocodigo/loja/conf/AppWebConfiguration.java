package casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import casadocodigo.loja.controllers.HomeController;
import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.infra.FileSaver;
import casadocodigo.loja.models.CarrinhoCompras;

/**
 * Módulo de Web MVC do Spring.
 * Classe de configuração do SpringMVC.
 */
@EnableWebMvc//Habilita o recurso de Web MVC do SpringMVC
//@ComponentScan(basePackages= {"casadocodigo.loja.controllers"})//Informa o pacote dos controllers
//Informa a classe controller e o Spring MVC irá escanear todos os controllers do pacote da classe informada.
//O Spring irá escanear todos os controllers e daos.
@ComponentScan(basePackageClasses= {HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class})
@EnableCaching	
public class AppWebConfiguration extends WebMvcConfigurerAdapter{

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
		//Todos os beans sob controle do Spring ficarão disponíveis como atributos na JSP.
		//resolver.setExposeContextBeansAsAttributes(true);
		
		//Expõe um bean específico, que está sob controle do Spring, na JSP. 
		//Para que este bean fica disponível na JSP, ele deve estar anotado como @Component.
		//O padrão é o nome da classe com sua primeira em minúsculo, ou seja, a classe CarrinhoCompras fica carrinhoCompras. 
		resolver.setExposedContextBeanNames("carrinhoCompras");
		
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
	
	/**
	 * Serviço de conversão de formatos controlado pelo Spring.
	 * */
	@Bean
	public FormattingConversionService mvcConversionService() {
		//Instância do serviço de conversão.
		DefaultFormattingConversionService conversionService 
			= new DefaultFormattingConversionService();
		//Registrador de formatos.
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		//Informa o formato de data.
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		//Inclui o registro no serviço de conversão.
		registrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	/**
	 * Resolvedor de arquivos de múltiplos formatos.
	 * */
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	/**
	 * Configuração do Rest no Spring.
	 * Configuração para definir parãmetros de envio, como autorização etc.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * Gerenciador de cache Guava do Google.
	 */
	@Bean
	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager(); Este cache não é recomendado para produção, apenas para desenvolvimento.
		//Construtor das configurações do cache. O cache guardará até 100 elementos e ficará ativo por 5 minutos.
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
				.maximumSize(100)
				.expireAfterAccess(1, TimeUnit.DAYS);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);
		return manager;
	}
	
	/**
	 * Gerenciador de resolvedores do Spring.
	 * Possui em seu conteiner uma lista de resolvedores. No caso, 
	 * InternalViewResolver(JSP) e JsonViewResolver(JSON)
	 */
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		//Lista de resolvers.
		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());
		
		//Conteiner gerenciador de resolvers
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers);
		resolver.setContentNegotiationManager(manager);//Define qual view resolver irá retornar.
		
		return resolver;
	}
	
	/**
	 * Informa ao Spring que as requisições default serão manipuladas pelo Servlet padrão e não pelo Spring.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * Configuração de interceptadores do Spring.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//Adicionado o interceptador de região.
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	/**
	 * Após o usuário selecionar o locale, o Spring irá armazenar o locale no cookie do navegador do usuário, 
	 * para que ele não precise definir novamente o locale ao retornar à aplicação ou mudar de página. 
	 */
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}	 
	
	/**
	 * Configuração do enviador de e-mail.
	 * Configura-se as informações do rementente.
	 */
	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");//Definição do servidor de e-mail.
		mailSender.setUsername("mssantos.k2@gmail.com");
		mailSender.setPassword("ms1002ph");
		mailSender.setPort(587);
		
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);//Habilita a autenticação SMTP
		mailProperties.put("mail.smtp.starttls.enable", true);//Usa o tipo de conexão segura por meio de TLS.
		mailSender.setJavaMailProperties(mailProperties);
		
        return mailSender;
		
	}
	
}
	