<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do c�digo</title>
		
		<c:url value="/resources/imagens" var="imgPath" />
		<c:url value="/resources/css" var="cssPath" />
		<link rel="stylesheet" href="${cssPath}/bootstrap.css" />
		<link rel="stylesheet" href="${cssPath}/bootstrap-theme.css" />
		<link rel="icon" href="${imgPath}/favicon.png" type="image/png" />
		
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
					<ul class="nav navbar-nav navbar-right">
						<li>
							<%-- Recupera dados do usu�rio atualmente logado. --%>
							<%-- <a href="#"><security:authentication property="principal.username"/></a> --%>
							<a href="#">
								<!-- O usu�rio est� dispon�vel em uma vari�vel -->
								<security:authentication property="principal" var="usuario" />
								Usu�rio: ${usuario.nome}
							</a>
						</li>
						<li>
							<a href="/casadocodigo/logout">Sair</a>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div>
		</nav>
		
		<div class="container">
			<h1>Lista de produtos</h1>
		
			<div>${sucesso}</div>
			<div>${fallha}</div>
					
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>T�tulo</th>
						<th>Descri��o</th>
						<th>P�ginas</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${produtos}" var="produto">
						<tr>
							<td>
								<!-- Forma amig�vel de gerar URLs. Observe o m�todo detalhe.
									arg: Posi��o do par�metro e valor. -->
								<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">${produto.titulo}</a>
		<%-- 						<a href="/casadocodigo/produtos/detalhe?id=${produto.id}">${produto.titulo}</a> --%>
							</td>
							<td>${produto.descricao}</td>
							<td>${produto.paginas}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
			
	</body>
</html>