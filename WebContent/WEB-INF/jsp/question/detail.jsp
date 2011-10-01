<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perguntas</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	<div id="content">
		<h2>Titulo: ${question.title}</h2>
		Autor: ${question.author.login} <br />
		Pergunta: ${question.description} <br />
		Especialidade: ${question.specialty.name} <br />
	
		<br />
		<h2>Respostas: </h2>
		
		<table>
			<tr>
				<th>Resposta</th>
				<th>Autor</th>
			</tr>
			<c:forEach var="answer" items="${question.answers}">
				<tr>
					<td>${answer.description}</td>
					<td>${answer.author.login}</td>
				</tr>
			</c:forEach>
		
		</table>
		
		<br />
		<form method="post" action="<c:url value="/perguntas/${question.id}/responder/"/>">
			<h2>Sua resposta: </h2>
			<table>
				<tr>
					<td><textarea cols="80" rows="6" name="answer.description"></textarea></td>
				</tr>
			</table>
			<input type="submit" value="Enviar" />
		</form>
	</div>

</body>
</html>