<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	<c:url value="/" var="contextPath" />

  <header id="layout-header">
		<div class="clearfix container">
		
			<a href="${s:mvcUrl('HC#index').build() }" id="logo">
				<img src="${contexPath}resources/imagens/cdc-logo.svg">
			</a>
			<div id="header-content">
				<nav id="main-nav">
					
					<ul class="clearfix">
						<%-- <a href="/casadocodigo/carrinho" rel="nofollow">Seu Carrinho (${carrinhoCompras.quantidade})</a> --%>
						<!-- Equivale à /carrinho. Este mapeamento é para o Spring construir um path até o método itens() -->
						<li>
							<a href="${s:mvcUrl('CCC#itens').build()}" rel="nofollow">
								
								<%--Utilização da taglib fmt:message que recebe uma chave e parâmetros.
									Foi configurado no Spring o método messageSource() que indica o local do arquivo de mensagens.
									Na compilação desta taglib, o fmt irá procurar a key menu.carrinho dentro do arquivo indicado e irá 
									associar os parâmetros às posições indicadas.  --%>
								<%-- <fmt:message key="menu.carrinho">
									<fmt:param value="${carrinhoCompras.quantidade}" />
								</fmt:message> --%>
								
								<!-- Alternativa oferencida pela taglib do Spring para obter as mensagens do arquivo de messages. -->
								<!-- Para passar mais argumentos, basta separar por vírgula. -->
								<s:message code="menu.carrinho"
									arguments="${carrinhoCompras.quantidade}" />
									
							</a>
						</li>
						
						<%-- <security:authorize access="isAuthenticated()"> --%><%-- hasRole: Passa a role requerida para o acesso. --%>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="${s:mvcUrl('PC#listar').build()}" rel="nofollow">Lista de produtos</a></li>
	
							<li><a href="${s:mvcUrl('PC#form').build()}" rel="nofollow">Cadastro de Produtos</a></li>
						</security:authorize>

						<li>
							<a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">
								<fmt:message key="menu.sobre" />
							</a>
						</li>
						<li>
							<a href="?locale=pt_BR" rel="nofollow">
								<fmt:message key="menu.pt" />
							</a>
						</li>
						<li>
							<a href="?locale=en_US" rel="nofollow">
								<fmt:message key="menu.en" />
							</a>
						</li>

						<!-- <li><a href="/pages/perguntas-frequentes" rel="nofollow">Perguntas Frequentes</a></li> -->
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<nav class="categories-nav">
		<ul class="container">
			<li class="category">
				<a href="http://www.casadocodigo.com.br">
					<fmt:message key="navegacao.categoria.home" />
				</a>
			</li>
			<li class="category">
				<a href="/collections/livros-de-agile">
					<fmt:message key="navegacao.categoria.agile" />	 
				</a>
			</li>
			<li class="category">
				<a href="/collections/livros-de-front-end">
					<fmt:message key="navegacao.categoria.front_end" />
				</a>
			</li>
			<li class="category">
				<a href="/collections/livros-de-games">
					<fmt:message key="navegacao.categoria.games" />
				</a>
			</li>
			<li class="category">
				<a href="/collections/livros-de-java">
					<fmt:message key="navegacao.categoria.java" />
				</a>
			</li>
			<li class="category">
				<a href="/collections/livros-de-mobile">
					<fmt:message key="navegacao.categoria.mobile" />
				</a>
			</li>
			<li class="category"><a
				href="/collections/livros-desenvolvimento-web"> Web </a></li>
			<li class="category"><a href="/collections/outros"> Outros </a></li>
		</ul>
	</nav>
