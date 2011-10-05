<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Perguntas</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	
	<div id="content">
		<div id="tabs">
			<h1>Perguntas</h1>
			<a href="nova/">Nova pergunta</a>
		</div>
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
	</div>
	<div id="bottom">
	</div>
</body>
</html>