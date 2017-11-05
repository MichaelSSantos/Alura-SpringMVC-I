<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do código</title>
	</head>
	<body>
		
		<%-- commandName="produto": Informa que o objeto dentro do formulário refere-se a um produto. --%>
		<%-- ${s:mvcUrl('PC#gravar').build()}: Taglib do Spring usada para construir URLs.
			 PC: Refere-se às iniciais em maiúsculo do controller alvo. --%>
		<%-- enctype="multipart/form-data": Informa ao servidor que o formulário está enviado múltiplos tipos de dados. --%>
		<form:form action="${s:mvcUrl('PC#grava').build()}" method="POST" commandName="produto" enctype="multipart/form-data">
			<div>
				<label>Título:</label>
				<form:input path="titulo" /><!-- Tag do Spring. O path substitui o name. -->
				<form:errors path="titulo" />
			</div>
			<div>
				<label>Descrição:</label>
				<form:textarea rows="10" cols="20" path="descricao" />
				<form:errors path="descricao" />
			</div>
			<div>
				<label>Páginas:</label>
				<form:input path="paginas" />
				<form:errors path="paginas" />
			</div>
			
			<div>
				<label>Data de lançamento:</label>
				<form:input path="dataLancamento" />
				<form:errors path="dataLancamento" />
			</div>
			
			<!-- varStatus tem informação de index -->
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status" >
				<div>
					<label>${tipoPreco}</label>
					<form:input path="precos[${status.index}].valor" />
					<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
				</div>
			</c:forEach>
			
			<div>
				<label>Sumário</label>
				<input type="file" name="sumario" />
			</div>
			
			<button type="submit">Cadastrar</button>
		</form:form>
			
	</body>
</html>