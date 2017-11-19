<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
	
	<tags:pageTemplate titulo="Produto não encontrado">
	
		<section id="index-section" class="container middle">
			
			<h2>Ocorreu um erro interno.</h2>
			
			<!-- A exceção será compilada e exibida no comentário. -->
			<!-- 
				Mensagem: ${exception.message}
				 <c:forEach items="${exception.stackTrace}" var="stk">
				 	${stk}
				 </c:forEach>
			 -->
				
			
		</section>
	
	</tags:pageTemplate>
