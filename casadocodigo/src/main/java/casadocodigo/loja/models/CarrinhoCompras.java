package casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
//Todo bean gerenciado pelo Spring é um Singleton, ou seja, seu escopo é de aplicação. Todo usuário utiliza a mesma instância.
//@Scope(value=WebApplicationContext.SCOPE_APPLICATION): Define o escopo do bean como sendo de aplicação, ou seja, para todos os usuários.
@Scope(value=WebApplicationContext.SCOPE_SESSION, 
		proxyMode=ScopedProxyMode.TARGET_CLASS)//Contexto de sessão do usuário. Coloca na sessão. 
//proxyMode: Cria um proxy para o CarrinhoCompras e possibilita que as informações possam ser transitadas de um escopo para o outro 
//sem a necessidade de definir um escopo deo tipo request para cada controller.
public class CarrinhoCompras implements Serializable{

	private static final long serialVersionUID = 7936282958988341530L;

	/*Um carrinho é composto por itens. Para cada item, deve-se saber a quantidade a ser comprada.*/
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public Collection<CarrinhoItem> getItens(){
		return itens.keySet();//Retorna um Set com todas as chaves. No Set não existe elementro repitido.
	}
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	/*Necessário o HashCode em CarrinhoItem e em Produto para executar a comparação de objetos.*/
	public int getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
			itens.put(item, 0);//Como são duas chaves iguais, o put acima irá sobrescrever a posição do item.
		}
		return itens.get(item);//Retorna o valor do map, que é a quantidade de itens do carrinho.
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador); 
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		itens.remove(new CarrinhoItem(new Produto(produtoId), tipoPreco));
	}
	
}
