package casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import casadocodigo.loja.models.CarrinhoCompras;
import casadocodigo.loja.models.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	
	//Classe do Spring responsável por fazer requisições Rest.
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Callable<V>: Especifica que o requisição feita ao servidor é assíncrona. Todos os outros 
	 * usuários estaram usando normalmente o sistema e apenas o usuário que fez a requisição irá esperar pela resposta.
	 */
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";

			try {
				//Segundo parâmtro: O objeto que representa a mensagem. DadosPagamento:o Spring irá transformar o objeto desta classe em um objeto JSON. 
				//Por padrão ele irá criar a chave com o nome do atributo da classe e o valor será o mesmo do definido no atributo.
				//Terceiro parâmetro: Uma classe na qual esperamos receber uma resposta do tipo.
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(response);
				model.addFlashAttribute("sucesso", response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				model.addFlashAttribute("sucesso", "Valor maior que o permitido");
			}
			
			return new ModelAndView("redirect:/produtos");
		};
	}

}
