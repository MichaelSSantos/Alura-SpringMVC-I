<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
	
<!-- Forma para inclusão de taglibs pessoais. -->
<!-- tributo tagdir da diretiva taglib serve para indicar que o arquivo de tags 
a ser encontrado está em um diretório local e não remoto como é feito com o atributo mais comum, o uri.  -->
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
	
	<tags:pageTemplate titulo="${produto.titulo}">
		
		<!-- Diretiva da JSP para inclusão de arquivos. -->
		<%-- <%@ include file="/WEB-INF/views/cabecalho.jsp" %> --%>
		
		
		
		<article id="livro-css-eficiente">
			  <header id="product-highlight" class="clearfix">
			    <div id="product-overview" class="container">
			      <img width="280px" height="395px" src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145" class="product-featured-image" />
			      <h1 class="product-title">${produto.titulo}</h1>
			      <p class="product-author">
			        <span class="product-author-link">
			          
			        </span>
			      </p>	
				
			    <p class="book-description">
			    	${produto.descricao}
			    </p>
			    </div>
			  </header>
		
		  
		  <section class="buy-options clearfix">  
	<%-- 	  <form action='<c:url value="/carrinho/add" />' method="post" class="container"> --%>
		  <!-- servletRelativeAction: Gera o path completo sem a necessidade de criar o prefixo com o c:url ou s:mvcUrl -->
		  <form:form servletRelativeAction="/carrinho/add" method="post" cssClass="container">
		    <ul id="variants" class="clearfix">
		    	
		    <input type="hidden" value="${produto.id}" name="produtoId" />
					
			<c:forEach items="${produto.precos}" var="preco">
		    	  <li class="buy-option">
		            <input type="radio" name="tipoPreco" class="variant-radio" id="tipo" value="${preco.tipo}"  checked="checked"  />
		            <label  class="variant-label" for="product-variant-9720393823">
		              ${preco.tipo} 
		            </label>
		            <small class="compare-at-price">R$ 39,90</small>
		            <p class="variant-price">${preco.valor}</p>
		          </li>
	        </c:forEach>           
		    </ul>
		    <button type="submit" class="submit-image icon-basket-alt" alt="Compre Agora ${produto.titulo}" title="Compre Agora ${produto.titulo}"></button>
			<%-- O Spring Security espera um token para cada submit de formulário, a fim de confirmar que 
				 a origem do formulário é do próprio servidor. Esse token é oferecido por meio de do objeto _csrf que é 
				 gerenciado pelo próprio Spring.
				 Request para o formulário: Token gerado. Request do sumbit do formuário: Token entregue. 
				 Ataque: cross site request forgery (CSRF). --%>
			<%-- <input type="hidden" name="${_csrf.parameterName}" ${_csrf.token } /> --%>
			<!-- O código acima foi comentado porque a taglib form do Spring já fornece implicitamente o token de validação. -->    
		  </form:form>
		  
		</section>
		  
		<div class="container">
		  <section class="summary">
		    <ul>
		      	<li><h3>E muito mais... <a href='/pages/sumario-java8'>veja o sumário</a>.</h3></li>
		    </ul>
		  </section>
		  
		  <section class="data product-detail">
		    <h2 class="section-title">Dados do livro:</h2>
		    <p>Número de páginas: <span>${produto.paginas}</span></p>
		    <p></p>
		    <!-- o fmt formata apenas Date e não Calendar. Para recuperar um Date, vc precisa chamar o *.time do objeto de Calendar. -->
		    <p>Data de publicação: <fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time}"/> </p>
		    <p>Encontrou um erro? <a href='/submissao-errata' target='_blank'>Submeta uma errata</a></p>
		  </section>
		</div>
		
		</article>	
		
	</tags:pageTemplate>
