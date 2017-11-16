<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Forma de se passar atributos para a taglib. 
	 required="true" obriga a passagem de uma vari�vel -->
<%@ attribute name="titulo" required="true" %>
<%@ attribute name="bodyClass" required="false" %>

<!DOCTYPE html>
<html>
	<head>
		<c:url value="/" var="contextPath" />
		<c:url value="/resources/imagens" var="imgPath" />
				
		<meta charset="ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
		
		<link rel="icon" href="${imgPath}/favicon.png" type="image/png">
		<link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
		<link href="${contextPath}resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
		<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
		<link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/guia-do-programador-style.css" rel="stylesheet" type="text/css"  media="all"  />
		<link href="${contextPath}resources/css/produtos.css" rel="stylesheet" type="text/css"  media="all"  />
		<link rel="canonical" href="http://www.casadocodigo.com.br/" />	
		<link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
		<link href="${contextPath}resources/css/book-collection.css" rel="stylesheet" type="text/css" media="all" />
		
		<title>${titulo} - Casa do C�digo</title>
	</head>
	<body class="${bodyClass}">

	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	
	<jsp:doBody />
	
	<%@ include file="/WEB-INF/views/rodape.jsp" %>
	
	</body>
</html>
