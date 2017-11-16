package casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserDetails: Implementação necessária para Usuario, a fim de ser manipulada
 * pelo Spring Security.
 */
@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 5637432690880716424L;

	@Id
	private String email;
	private String senha;
	private String nome;

	// Retorna a lista de permissões quando retorna usuário.
	// Sem essa configuração, seria necessário fazer junção na query.
	@OneToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}
	
	/**
	 * Informa que a conta nunca irá expirar.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Informa que a conta nunca irá ser bloqueada.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Informa que as credenciais sempre estarão ativas.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * A conta sempre estará ativa.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
