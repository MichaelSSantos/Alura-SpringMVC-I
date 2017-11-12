package casadocodigo.loja.models;

import java.math.BigDecimal;

public class DadosPagamento {
	//O atributo precisa ser value para que o Spring possa fazer o binding corretamente para o serviço rest.
	private BigDecimal value;

	public DadosPagamento() {
	}

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

}
