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

	public List<Produto> listar() {
		//Utilizando TypedQuery e encadeando métodos.
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}
	
}
