package casadocodigo.loja.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CarrinhoCompras {

	/*Um carrinho é composto por itens. Para cada item, deve-se saber a quantidade a ser comprada.*/
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	/*Necessário o HashCode em CarrinhoItem e em Produto para executar a comparação de objetos.*/
	private int getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
			itens.put(item, 0);//Como são duas chaves iguais, o put acima irá sobrescrever a posição do item.
		}
		return itens.get(item);//Retorna o valor do map, que é a quantidade de itens do carrinho.
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador); 
	}
	
}
