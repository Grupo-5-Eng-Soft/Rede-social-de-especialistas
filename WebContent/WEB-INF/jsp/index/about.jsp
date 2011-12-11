<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Rede social de especialistas</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	<div id="content">
		<div id="notice">${notice}</div>
		
			<div id="column1">
				<h1>Sobre o projeto</h1>
				<p>A "Rede Social de Especialistas" foi um projeto começado pelo Grupo 5
					para a disciplina MAC0332 - Engenharia de Software no ano de 2011 
					sob a orientação do professor Marco Aurélio Gerosa do IME-USP.
				</p><p></p>
				<h2>Membros:</h2>
				<ul>
					<li>Caio Valente;</li>
					<li>Felipe Torres;</li>
					<li>Francisco Sokol;</li>
					<li>Giancarlo Rigo;</li>
					<li>Renato Avila;</li>
					<li>Thiago Nunes;</li>
					<li>William Gnann.</li>
				</ul>
			</div>
			<div id="clear"></div>
	</div>
	<div id="bottom">
	</div>
</body>
</html>