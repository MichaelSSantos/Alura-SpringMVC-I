package casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import casadocodigo.loja.models.Produto;

public class ProdutoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	
	/*
	 * ValidationUtils:
	 * 	rejectIfEmpty: Significa "rejeite se for vazio". 
	 * 		Três parâmetros: 
	 * 			Um objeto errors que contém os erros da validação; 
	 * 			O nome do campo que iremos validar passado como String; 
	 * 			errorCode que também é passado como String, mas que é reconhecido pelo Spring. 
	 * 			Neste último parâmetro, usaremos o errorCode com o valor "field.required" para 
	 * 			informar ao Spring que aquele campo é obrigatório.
	 * 
	 * Target: objeto alvo.
	 * */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required.produto.titulo");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required.produto.descricao");
		
		Produto produto = (Produto) target;
		if(produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required.produto.paginas");
		}
	}
	
}
