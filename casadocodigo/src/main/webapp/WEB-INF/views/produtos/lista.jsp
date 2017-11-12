<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do c�digo</title>
	</head>
	<body>
		
		<h1>Lista de produtos</h1>
		
		<div>${sucesso}</div>
		<div>${fallha}</div>
				
		<table>
			<tr>
				<td>T�tulo</td>
				<td>Descri��o</td>
				<td>P�ginas</td>
			</tr>
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
		</table>
			
	</body>
</html>