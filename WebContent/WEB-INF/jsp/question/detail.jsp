<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perguntas</title>
</head>
<body>

<div id="upperHeader">
	<jsp:include page="../include/user.jsp" />
</div>
<div id="header">
</div>
<jsp:include page="../include/menu.jsp" />
<div id="content">
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
</div>

</body>
</html>