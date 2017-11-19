package casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import casadocodigo.loja.models.CarrinhoCompras;
import casadocodigo.loja.models.DadosPagamento;
import casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	
	//Classe do Spring responsável por fazer requisições Rest.
	@Autowired
	private RestTemplate restTemplate;
	
	//Interface do Spring que tem como responsabilidade o envio de e-mails.
	@Autowired
	private MailSender sender;
	
	/**
	 * Callable<V>: Especifica que o requisição feita ao servidor é assíncrona. Todos os outros 
	 * usuários estaram usando normalmente o sistema e apenas o usuário que fez a requisição irá esperar pela resposta.
	 * 
	 * @AuthenticationPrincipal: O usuário autenticado será injeto pelo Spring Security como parâmetro do método.
	 */
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";

			try {
				//Segundo parâmtro: O objeto que representa a mensagem. DadosPagamento:o Spring irá transformar o objeto desta classe em um objeto JSON. 
				//Por padrão ele irá criar a chave com o nome do atributo da classe e o valor será o mesmo do definido no atributo.
				//Terceiro parâmetro: Uma classe na qual esperamos receber uma resposta do tipo.
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(response);
				enviaEmailCompraProduto(usuario);
				model.addFlashAttribute("sucesso", response);
			} catch (HttpClientErrorException  e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				model.addFlashAttribute("sucesso", "Valor maior que o permitido");
			}
			
			return new ModelAndView("redirect:/");
//			return new ModelAndView("redirect:/produtos");
		};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {
		//Objeto do Spring que formata o e-mail.
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso.");//Assunto.
//		email.setTo(usuario.getEmail());
		email.setTo("mssantos.k2@gmail.com");
		email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());//Corpo do e-email.
		email.setFrom("compras@casadocodigo.com.br");
		
		sender.send(email);
	}

}
