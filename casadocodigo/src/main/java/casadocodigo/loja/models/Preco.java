package casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

/*
 *  Permite que a classe Preco seja persistida, 
 *  desde que ela seja um atributo de uma entidade, 
 *  nesse caso ela é um atributo da classe Produto que por sinal é uma entidade.
 *  Não cria um id para cada preco.
 * */
@Embeddable
public class Preco {

	private BigDecimal valor;
	private TipoPreco tipo;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return this.tipo.name() + " - " + this.valor;
	}
}
