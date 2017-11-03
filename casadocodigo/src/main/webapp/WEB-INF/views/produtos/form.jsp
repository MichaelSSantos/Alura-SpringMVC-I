<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do c�digo</title>
	</head>
	<body>
		
		<%-- commandName="produto": Informa que o objeto dentro do formul�rio refere-se a um produto. --%>
		<%-- ${s:mvcUrl('PC#gravar').build()}: Taglib do Spring usada para construir URLs.
			 PC: Refere-se �s iniciais em mai�sculo do controller alvo. --%>
		<form:form action="${s:mvcUrl('PC#grava').build()}" method="POST" commandName="produto">
			<div>
				<label>T�tulo:</label>
				<input type="text" name="titulo" />
				<form:errors path="titulo" />
			</div>
			<div>
				<label>Descri��o:</label>
				<textarea rows="10" cols="20" name="descricao"></textarea>
				<form:errors path="descricao" />
			</div>
			<div>
				<label>P�ginas:</label>
				<input type="text" name="paginas" />
				<form:errors path="paginas" />
			</div>
			
			<div>
				<label>Data de lan�amento:</label>
				<input name="dataLancamento" />
				<form:errors path="dataLancamento" />
			</div>
			
			<!-- varStatus tem informa��o de index -->
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status" >
				<div>
					<label>${tipoPreco}</label>
					<input type="text" name="precos[${status.index}].valor" />
					<input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}" />
				</div>
			</c:forEach>
			
			<button type="submit">Cadastrar</button>
		</form:form>
			
	</body>
</html>