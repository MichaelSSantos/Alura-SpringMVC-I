package casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.models.CarrinhoCompras;
import casadocodigo.loja.models.CarrinhoItem;
import casadocodigo.loja.models.Produto;
import casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
//O escopo do controller existirá apenas no request. Um novo request gera um novo escopo.
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);

		carrinho.add(carrinhoItem);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.find(produtoId);
		return new CarrinhoItem(produto, tipoPreco);
	}

	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}
	
}
