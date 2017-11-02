package casadocodigo.loja.controllers;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.models.Produto;
import casadocodigo.loja.models.TipoPreco;

@Controller
//Para o mapeamento "/produtos" usando o método GET, acessamos a lista de produtos e quando acessamos essa mesma URL via POST
@RequestMapping("produtos")
public class ProdutosController {

	@Autowired//Serve para que nós não nos preocupemos em criar manualmente o ProdutoDAO no Controller.
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		/*
		 * Quando utilizamos o ModelAndView, além retornar uma página, 
		 * temos a possibilidade de enviar objetos de qualquer classe para essas páginas.
		 * */
		ModelAndView modelAndView = new ModelAndView("produtos/form");//ou modelAndView.setView(String view);
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}

	//method: Utilizamos esse atributo para acessar duas funcionalidades distintas com o mesmo endereço.
	//RedirectAttributes (Flash scope): São atributos levados de um rerquest até outro e depois deixam de existir.
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView grava(Produto produto, RedirectAttributes redirectAttributes) {//O Spring faz o binding com as propriedades de produto.
		produtoDao.grava(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso.");//Informação pendurada no ModelAndView.
		return new ModelAndView("redirect:produtos");//Esta opção sem o redirect retorna a view produtos e não faz um request para produtos.
		//return listar();Se fosse listar(), o primeiro request que é o de gravar continuaria ativo e 
		//ao chamar novamente o segundo request, então o primeiro também seria submetido novamente.
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}

}
