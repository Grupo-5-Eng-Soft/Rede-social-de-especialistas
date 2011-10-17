<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../include/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Cadastrar nova especialidade</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	<jsp:include page="../include/menu.jsp" />

	<div id="content">
		<div id="tabs">
			<h1>Editar</h1>
			<a href="<c:url value="/especialidades/${specialty.id}/" />">Voltar</a>
		</div>

		<form method="post" action="<c:url value="/especialidades/${specialty.id}/atualizar/" />" >
			<p><b>Título:</b><br /> <input type="text" size="40" name="specialty.name" value="${specialty.name}" /><br /><br />
			<input type="submit" value="Salvar" /></p>
		</form>
		<jsp:include page="../include/error.jsp" />
	</div>
	<div id="bottom">
	</div>
</body>
</html>