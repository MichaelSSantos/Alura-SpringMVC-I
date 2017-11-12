package casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.infra.FileSaver;
import casadocodigo.loja.models.Produto;
import casadocodigo.loja.models.TipoPreco;
import casadocodigo.loja.validation.ProdutoValidation;

@Controller
//Para o mapeamento "/produtos" usando o método GET, acessamos a lista de produtos e quando acessamos essa mesma URL via POST
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired//Serve para que nós não nos preocupemos em criar manualmente o ProdutoDAO no Controller.
	private ProdutoDAO produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
	/*Este método é invocado antes do resquest com a anotação de @Valid.
	 *O ProdutoValidation que implementa Validator é adicionado ao WebDataBinder, fazendo assim, a ligação de Produto ao seu validador.
	 *O produto alvo deve ser anotado com @Valid.*/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	/*Passo produto por parâmetro para ficar sob controle do Spring. 
	Isso é importante para validação, porque se algum campo não for validado e o formulário precisar ser recarregado, 
	o objeto de produto já estará com alguns campos preenchidos.*/
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
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
	//BindingResult: Objeto que retorna as informações de validação. Deve vir após o parâmetro a ser validado.
	//MultipartFile: Classe do Spring que converte um arquivo enviado pelo formulário em um MultipartFile.
	//CacheEvict: Paga o cache produtosHome toda vez que a lista de produtos é atualizada. allEntries: Todos os valores.
	@CacheEvict(value="produtosHome", allEntries=true)
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView grava(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {//O Spring faz o binding com as propriedades de produto.
		
		try {
			if(result.hasErrors()) {
				return form(produto);
			}
			
			String path = fileSaver.write("arquivos-sumario", sumario);
			produto.setSumarioPath(path);
			
			produtoDao.grava(produto);
			redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso.");//Informação pendurada no ModelAndView.
			return new ModelAndView("redirect:produtos");//Esta opção sem o redirect retorna a view produtos e não faz um request para produtos.
			//return listar();Se fosse listar(), o primeiro request que é o de gravar continuaria ativo e 
			//ao chamar novamente o segundo request, então o primeiro também seria submetido novamente.
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("redirect:produtos");
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	/**
	 * A única forma de utilizar a URL amigável é com a taglib do Spring.
	 * Ela vai buscar o request detalhe e verificará que existe um @PathVariable, 
	 * então adicionará no request. 
	 */
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
 
}
