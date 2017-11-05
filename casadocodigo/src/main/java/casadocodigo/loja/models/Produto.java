package casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String descricao;
	private int paginas;
	
	//Se não for informado o padrão de data, então o padrão será yyyy/MM/dd e ocorrerá um erro do bind do formulário para o controller.
	@DateTimeFormat//(pattern="dd/MM/yyyy") O serviço de conversão substitui este atributo.
	private Calendar dataLancamento;
	
	/*A lista de preços não me limita a três preços. Caso exista um preço promocional poderei incluí-lo*/
	@ElementCollection/*Para cada produto existirá lista de precos sem criar tabela precos.*/
	private List<Preco> precos;
	
	/*Para armazenamento de arquivos, o ideal é armazenar o arquivo dentro do servidor e o caminho no bando de dados.*/
	private String sumarioPath;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
