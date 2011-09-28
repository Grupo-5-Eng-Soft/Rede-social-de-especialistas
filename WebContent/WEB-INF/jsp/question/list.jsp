<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perguntas</title>
</head>
<body>

<h1>Perguntas</h1>
<div><a href="nova/">Nova pergunta</a></div>
<table>
	<tr>
		<th>Título</th>
		<th>Especialidade</th>
	</tr>
	<c:forEach var="question" items="${questions}">
		<tr>
			<td><a href="${question.id}/">${question.title}</a></td>
			<td>${question.specialty.name}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>