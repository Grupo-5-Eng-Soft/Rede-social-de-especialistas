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
		Classifique o autor da resposta: 
		<form method="post" action="<c:url value="/perguntas/${questionId}/finalizar/"/>">
			<input type="radio" name="score" value="1" checked="true"/>1
			<input type="radio" name="score" value="2" />2
			<input type="radio" name="score" value="3" />3
			<input type="radio" name="score" value="4" />4
			<input type="radio" name="score" value="5" />5
			<br /><input type="submit" name="submit" value="Enviar" />
		</form>
	</div>
	<div id="bottom">
	</div>
</body>
</html>