package casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.models.Produto;
import casadocodigo.loja.models.TipoPreco;

@Controller
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

	@RequestMapping(method=RequestMethod.POST)
	public String grava(Produto produto) {//O Spring faz o binding com as propriedades de produto.
		System.out.println(produto.toString());
		produtoDao.grava(produto);
		return "produtos/ok";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}

}
