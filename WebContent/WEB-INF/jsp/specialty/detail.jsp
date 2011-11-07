<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include/head.jsp" />
<title>Especialidade: ${specialty.name}</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />	
	<jsp:include page="../include/menu.jsp" />

	<div id="content">
		<div id="tabs">
			<h1>${specialty.name}</h1>
			<a href="<c:url value="/perguntas/nova/${specialty.id}"/>">Postar uma dúvida</a>
			<c:if test="${userSession.loggedUser.admin}">
				<a href="<c:url value="/especialidades/${specialty.id}/editar/"/>">Editar</a>
				<a href="<c:url value="/especialidades/${specialty.id}/remover/" />">Remover</a>
			</c:if>
		</div>
			<table>
				<tr>
					<th>Login</th>
					<th>Pontuação</th>
				</tr>
			<c:forEach var="specialist" items="${specialists}">
				<tr>
					<td>
						<a href="<c:url value="/usuarios/${specialist.user.id}/"/>">${specialist.user.login}</a>
						<c:if test="${specialist.user.certified}">
							<img src="<c:url value="/img/star.png"/>">
						</c:if>
					</td>		
					<td>${specialist.score}</td>
				</tr>
			</c:forEach>
		</table>
	</div> 
	<div id="bottom">
	</div>
</body>
               
</html>