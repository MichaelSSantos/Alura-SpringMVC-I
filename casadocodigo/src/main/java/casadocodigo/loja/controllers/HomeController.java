package casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//Controlador do Spring
public class HomeController {

	//O metodo index atenderá as requisições que chegarem na raiz do nosso projeto ("/").
	@RequestMapping("/")
	public String index() {
		return "home";
	}
	
}
