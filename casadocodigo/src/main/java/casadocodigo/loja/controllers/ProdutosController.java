package casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {

	@Autowired//Serve para que nós não nos preocupemos em criar manualmente o ProdutoDAO no Controller.
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/form";
	}

	@RequestMapping("/produtos")
	public String grava(Produto produto) {
		System.out.println(produto.toString());
		produtoDao.grava(produto);
		return "produtos/ok";
	}

}
