package casadocodigo.loja.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller especial que observa todos os outros controllers.
 */
@ControllerAdvice
public class ExceptionHandlerController {

	/**
	 * ExceptionHandler: Indica ao Spring que o método irá tratar a exceção passada
	 * por parâmetro.
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView trataExceptionGenerica(Exception exception) {
		exception.getStackTrace();
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}

}
