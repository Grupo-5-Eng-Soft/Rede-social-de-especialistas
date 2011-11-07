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
				<h1>Bem Vindo a Rede Social de Especialistas</h1>
				<p>Texto bonito que ilustrará o objetivo da Rede Social de Especialistas.
				</p>
				
			</div>
			<div id="column2">
			<h1>Top 5 Especialistas</h1>
			<table>
				<tr>
					<td> Nome </td> 
					<td> Especialidade </td> 
					<td> Pontuação </td>
				</tr>
				<c:forEach var="specialist" items="${specialist}">
					<tr>
						<td> <a href="<c:url value="/usuarios/${specialist.user.id}/"/>">${specialist.user.login}</a></td>
						<td> <a href="<c:url value="/especialidades/${specialist.specialty.id}/"/>">${specialist.specialty.name}</a></td>
						<td>${specialist.score}</td>
					</tr>
				</c:forEach>
			</table>
			
			
			<h1> Últimas 5 Perguntas </h1>
			<table>
				<tr>
					<td> Título </td>
					<td> Especialidade </td> 
					<td> Autor </td> 		
				</tr>
				<c:forEach var="question" items="${question}">
					<tr>
						<td> <a href="<c:url value="/perguntas/${question.id}/"/>">${question.title}</a></td>
						<td> <a href="<c:url value="/especialidades/${question.specialty.id}/"/>">${question.specialty.name}</a></td>
						<td> <a href="<c:url value="/usuarios/${question.author.id}/"/>">${question.author.login}</a></td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<div id="clear"></div>
	</div>

	<div id="bottom">
	</div>
</body>
</html>