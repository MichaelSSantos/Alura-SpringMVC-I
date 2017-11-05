package casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Component: Informa que esta classe é um compomente genérico do Spring.
 * Não é um compomente de controle, service ou dao. 
 * É um componente genérico que será usado dentro de um componentes principais.
 * 
 * ESTA ANOTAÇÃO É O QUE PERMITA A INJEÇÃO DE DEPENDÊNCIA.
 * 
 * */
@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest request;

	/*Retorna o path completo do arquivo.
	 * baseFolder: Nome da pasta onde o arquivo será salvo.
	 * file: Nome do arquivo.
	 * file.transferTo: Transfere o arquivo recebido (file) para o arquivo de destino fornecido(file com caminho completo).
	 * */
	public String write(String baseFolder, MultipartFile file) {	
		try {
			
			//Retorna o caminho real de onde está uma determinada pasta dentro do servidor.
			String realPath = request.getServletContext().getRealPath("/"+baseFolder);
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));//Cria um new File com todo o caminho de destino para armazenamento.

			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
