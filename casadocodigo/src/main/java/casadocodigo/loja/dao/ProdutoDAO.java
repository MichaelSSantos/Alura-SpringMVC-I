package casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import casadocodigo.loja.models.Produto;

@Repository //Inclui ProdutoDAO no gerenciamento do Spring
@Transactional //Informa ao Spring que esta classe ter gerenciamento de transação.
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void grava(Produto produto) {
		manager.persist(produto);
	}

	/**
	 * Aplicação utilizando o padrão Lazy Initialization, logo não é necesário fazer join fetch nas tabelas relacionais. 
	 * Atenção! esta abordagem pode ter queda de performance. O ideal é utiliza-la quando não se sabe quantos objetos irão 
	 * retornar.
	 */
	public List<Produto> listar() {
		//Utilizando TypedQuery e encadeando métodos.
		/*return manager.createQuery("select distinct(p) from Produto p join fetch p.precos", 
				Produto.class).getResultList();*/
		return manager.createQuery("select distinct(p) from Produto p", 
				Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p "
				+ "join fetch p.precos preco where p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
				
	}
	
}
