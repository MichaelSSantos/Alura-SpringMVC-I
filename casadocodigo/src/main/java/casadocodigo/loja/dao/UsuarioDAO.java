package casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import casadocodigo.loja.models.Usuario;

/**
 * Implementa UserDetailService porque é utilizada pelo Spring Security para autenticação.
 */
@Repository
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * método sobrescrito da Interface UserDetailsService.
	 * Este método retorna um tipo UserDetails que deve ser implementada pela classe Usuario.
	 */
	@Override
	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
		.setParameter("email", email)
		.getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado.");
		}
		
		return usuarios.get(0);
	}

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

}
