package casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import casadocodigo.loja.dao.UsuarioDAO;

/**
 * Classe de configuração do Spring Security.
 * 
 * EnableWebMvcSecurity:  Habilita as configurações do Spring Security.
 * WebSecurityConfigurerAdapter: Configura os detalhes de segurança do Spring Security.
 * 
 * Como se trata de uma classe de configuração, esta é adicionada ao SevletSpringMVC.
 */
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioDAO usuarioDAO;

	/**
	 * configure: Em sua implementação padrão, bloqueia e redireciona todas as requisições não autenticadas.
	 * Sobrescrita de configure: Descrever os padrões de URLs que queremos ou não bloquear. 
	 * Estes padrões são definididos através do método antMatchers da classe HttpSecurity.
	 * 
	 * A cada requisição, a verificação é feita e caso uma das páginas bloqueadas tenha tentativas de acesso sem autenticação, 
	 * estas serão redirecionadas para o formulário de login. 
	 * 
	 * antMatchers("/"): Todos terão acesso ao home.
	 * loginPage("/login"): Redireciona para a página específica de login.
	 * logoutRequestMatcher: Libera o Logout a partir de uma URL via GET.
	 * 
	 * ===ATENÇÃO: A role ADMIN deve ser cadastrado como ROLE_ADMIN. O método hasRole verifica por: ROLE_ALGUMA COISA.
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/produtos/form").hasRole("ADMIN")
			.antMatchers("/carrinho/**").permitAll()
			.antMatchers("/pagamento/**").permitAll()
			.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
			.antMatchers("/produtos/**").permitAll()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/").permitAll()
			
			.antMatchers("/url-GUP-qlickl468412fefdx2723e").permitAll()
			
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	/**
	 * Manipula o objeto que será utilizado para autenticação na aplicação.
	 * 
	 * userDetailsService espera receber um objeto que implemente a Interface UserDetailsService.
	 * BCryptPasswordEncoder: Classe que gera um Hash da senha.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDAO)
		  	.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
