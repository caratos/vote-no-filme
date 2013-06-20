<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vote no filme</title>

<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/js/jquery-1.10.1.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/effects.js"/>" type="text/javascript"></script>
</head>

<body>

<div id="mainContent">
	<div id="header">
		<h1>Cadastro de Crianças - Natal 2012</h2>
		<hr>
	</div>

	<div id="menu">
	  <ul>
	  	<li>Inicio</li>
	  	<li>Crianças
	  		<ul>
			  	<li><a href="/criancas/index">Adiciona Criança</a>
			  	<li><a href="/criancas/lista">Listar Crianças</a>
			  	<!--<li><a href="/criancas/tabela">Tabela Criancas</a> -->
			  	<li><a href="/criancas/buscar">Buscar Criancas</a></li>
	  		</ul>
	  	</li>
	  	<li>Padrinhos
	  		<ul>
			  	<li><a href="/padrinhos/index">Mostra padrinhos</a>
			  	<li><a href="/padrinhos/buscar">Consulta</a>
	  		</ul>
	  	</li>
	  	<li>Estatísticas
	  		<ul>
	  			<li><a href="/estatisticas/index">Percentagem de crianças</a></li>
	  			<li><a href="/estatisticas/repetidas">Crianças com chance de ser repetidas</a></li>
	  		</ul>
	  	</li>
	  </ul>
	</div>
	
	<div id="content">
	
	<ul id="errors">
		<c:forEach var="error" items="${errors}">
    	 	<li>${error.category} - ${error.message}</li>
		</c:forEach>
	</ul>