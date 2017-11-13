<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do c�digo</title>
		
		<c:url value="/resources/imagens" var="imgPath" />
		<c:url value="/resources/css" var="cssPath" />
		<link rel="stylesheet" href="${cssPath}/bootstrap.css" />
		<link rel="stylesheet" href="${cssPath}/bootstrap-theme.css" />
		<link rel="icon" href="${imgPath}/favicon.png" type="image/png">
		
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${s:mvcUrl('HC#index').build() }">
						<img alt="Brand" src="${imgPath}/cdc-logo.svg" height="20px">
					</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="${s:mvcUrl('PC#listar').build() }">Lista de Produtos</a></li>
						<li><a href="${s:mvcUrl('PC#form').build() }">Cadastro de Produtos</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div>
		</nav>
		
		<div class="container">
			
				<h1>Cadastro de produto</h1>
		
				<%-- commandName="produto": Informa que o objeto dentro do formul�rio refere-se a um produto. --%>
				<%-- ${s:mvcUrl('PC#gravar').build()}: Taglib do Spring usada para construir URLs.
					 PC: Refere-se �s iniciais em mai�sculo do controller alvo. --%>
				<%-- enctype="multipart/form-data": Informa ao servidor que o formul�rio est� enviado m�ltiplos tipos de dados. --%>
				<form:form action="${s:mvcUrl('PC#grava').build()}" method="POST" commandName="produto" enctype="multipart/form-data">
					<div class="form-group">
						<label>T�tulo:</label>
						<form:input path="titulo" cssClass="form-control" /><!-- Tag do Spring. O path substitui o name. -->
						<form:errors path="titulo" cssClass="text-danger" />
					</div>
					<div class="form-group">
						<label>Descri��o:</label>
						<form:textarea path="descricao" cssClass="form-control" />
						<form:errors path="descricao" cssClass="text-danger" />
					</div>
					
					<div class="row">
						<div class="form-group col-lg-3">
							<label>P�ginas:</label>
							<form:input path="paginas" cssClass="form-control" />
							<form:errors path="paginas" cssClass="text-danger" />
						</div>
						
						<div class="form-group col-lg-3">
							<label>Data de lan�amento:</label>
							<form:input path="dataLancamento" cssClass="form-control" />
							<form:errors path="dataLancamento" cssClass="text-danger" />
						</div>
					</div>
					
					<div class="row">
						<!-- varStatus tem informa��o de index -->
						<c:forEach items="${tipos}" var="tipoPreco" varStatus="status" >
							<div class="form-group col-lg-3">
								<label>${tipoPreco}</label>
								<form:input path="precos[${status.index}].valor" cssClass="form-control" />
								<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
							</div>
						</c:forEach>
					</div>
					<div class="form-group">
						<label>Sum�rio</label>
						<input type="file" name="sumario" cssClass="form-control" />
					</div>
					
					<button type="submit" class="btn btn-primary">Cadastrar</button>
					
					
					<br /><br />
					
				</form:form>
			
		</div>
	</body>
</html>