package casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

/**
 * GrantedAuthority: Interface que identifica a autoridade (permissão de
 * acesso).
 */
@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = -4919763509457545574L;

	@Id
	private String nome;
	
	public Role() {
	}
	
	public Role(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
