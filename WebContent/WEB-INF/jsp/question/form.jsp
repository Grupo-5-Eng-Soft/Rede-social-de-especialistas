<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
<script type="text/javascript" src="<c:url value="/javascript/question/validateQuestionForm.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Nova pergunta</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	
	<div id="content">
		<h1>Postar Dúvida</h1>
		<form method="post" action="<c:url value="/perguntas/salvar/"/>">
			<p>Título: <br />
				<input type="text" size="40" name="question.title" /></p>
			<c:if test="${!userSession.authenticated}">
				<p>Email: <br />
					<input type="text" size="40" name="question.email" /></p>
			</c:if>
			<p>Pergunta: <br />
				<textarea cols="80" rows="6" name="question.description"></textarea></p>
			<p>Especialidade: 
				<select name="specialtyId">
					<c:forEach var="specialty" items="${specialties}">
						<option value="${specialty.id}">${specialty.name}</option>
					</c:forEach>
				</select>
			<br /><br />		
			<input type="submit" value="Enviar" />
			</p>
		</form>
		<jsp:include page="../include/error.jsp" />
	</div>
	<div id="bottom">
	</div>
</body>
</html>