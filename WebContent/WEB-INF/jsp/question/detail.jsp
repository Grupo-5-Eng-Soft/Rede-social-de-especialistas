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

<h2>Titulo: ${question.title}</h2>
Autor: ${question.author.login} <br />
Pergunta: ${question.description} <br />
Especialidade: ${question.specialty.name} <br />

<form method="post" action="<c:url value="/perguntas/responder/"/>">
	<table>
		<tr>
			<td>Resposta: </td>
			<td><textarea cols="80" rows="6" name="answer.description"></textarea></td>
		</tr>
	</table>
	<input type="submit" value="Enviar" />
</form>

</body>
</html>