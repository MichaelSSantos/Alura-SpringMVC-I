<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do código</title>
		
		<c:url value="/resources/imagens" var="imgPath" />
		<c:url value="/resources/css" var="cssPath" />
		<link rel="stylesheet" href="${cssPath}/bootstrap.css" />
		<link rel="stylesheet" href="${cssPath}/bootstrap-theme.css" />
		<link rel="icon" href="${imgPath}/favicon.png" type="image/png">
		
	</head>
	<body>

		<div class="container">
			
			<h1>Login da Casa do Código</h1>
	
			<form:form servletRelativeAction="/login" method="POST">
				<div class="form-group">
					<label>E-mail:</label>
					<input type="text" name="username" class="form-control" />
				</div>
				<div class="form-group">
					<label>Senha:</label>
					<input type="password" name="password" class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">Logar</button>
			</form:form>
	
		</div>
	</body>
</html>