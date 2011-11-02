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
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam iaculis iaculis nibh bibendum venenatis. Vestibulum eu massa sed leo dapibus commodo. Fusce eu sem purus. Nunc eu turpis sapien. Nam tempus, risus a elementum faucibus, sapien mauris hendrerit sapien, quis consequat massa felis et risus. Sed accumsan vulputate blandit. Vestibulum ut aliquet tellus.
	Mauris id nunc elit, vehicula volutpat nulla. Aliquam erat volutpat. Vivamus non augue sem. Vivamus arcu arcu, pulvinar eget convallis quis, convallis id nunc. Vestibulum magna dolor, viverra sit amet commodo ut, placerat in lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed purus sapien, adipiscing quis mollis ut, tempor in erat. Proin malesuada, justo at tristique imperdiet, dui eros ornare leo, gravida luctus magna justo sed massa. Cras vel turpis eros, id luctus neque. Nunc fringilla sollicitudin pellentesque. Aliquam adipiscing faucibus vehicula. Praesent eget justo quam.Mauris id nunc elit, vehicula volutpat nulla. Praesent eget justo quam.Mauris id nunc elit, vehicula volutpat nulla. Praesent eget justo quam.Mauris id nunc elit, vehicula volutpat nulla.Praesent eget justo quam.Mauris id nunc elit, vehicula volutpat nulla.
	
			</p>
		</div>
				<div id="column2">
		<h1>Top 5 Especialistas</h1>
		<table>
			<tr>
				<td width="180"> Nome </td> 
				<td width="180"> Especialidade </td> 
				<td width="100"> Pontuação </td>
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
				<td width="180"> Título </td>
				<td width="180"> Especialidade </td> 
				<td width="180"> Autor </td> 		
			</tr>
			<c:forEach var="question" items="${question}">
				<c:if test="${question.publicQuestion || userSession.authenticated && userSession.loggedUser.isSpecialistIn(question.specialty)}">
					<tr>
						<td> <a href="<c:url value="/perguntas/${question.id}/"/>">${question.title}</a></td>
						<td> <a href="<c:url value="/especialidades/${question.specialty.id}/"/>">${question.specialty.name}</a></td>
						<td> <a href="<c:url value="/usuarios/${question.author.id}/"/>">${question.author.login}</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		</div>

	</div>

	<div id="bottom">
	</div>
</body>
</html>