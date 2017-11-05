package casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.models.CarrinhoCompras;
import casadocodigo.loja.models.CarrinhoItem;
import casadocodigo.loja.models.Produto;
import casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);

		carrinho.add(carrinhoItem);
		
		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.find(produtoId);
		return new CarrinhoItem(produto, tipoPreco);
	}

	
}
