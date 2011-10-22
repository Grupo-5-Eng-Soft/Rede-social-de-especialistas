<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="../include/head.jsp" />
<script type="text/javascript" src="<c:url value="/javascript/user/validateUserForm.js"/>"></script>
<title>Cadastrar novo usuário</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	
	<div id="content">
		<div id="tabs">
			<h1>Cadastro</h1>
			<a href="<c:url value="/login/"/>">Login</a> 
		</div>
		<jsp:include page="../include/error.jsp" />
		<form method="post" action="../salvar/" >
			<p>Login<br />
			<input type="text" size="30" name="user.login"/><br />
			Email<br />
			<input type="text" size="30" name="user.email" /><br />
			Instituição<br />
			<input type="text" size="30" name="user.institution" /><br />
			Nome<br />
			<input type="text" size="30" name="user.name" /><br />
			Senha<br />
			<input type="password" size="30" name="user.password" id="password"/><br />
			Confirmação de senha<br />
			<input type="password" size="30" name="confirmation" /><br /><br />
			Privilégio<br />
			<input name="user.role" type="radio" value="USER"/>Usuário<br />
			<input name="user.role" type="radio" value="ADMIN"/>Administrador<br /><br />
			Especialidades<br />
			<c:forEach var="specialty" items="${specialties}">
			<input name="specialties_ids" type="checkbox" value="${specialty.id}"> ${specialty.name}<br />
			</c:forEach>
			<br />
			<input type="submit" value="Enviar"/>
		</form>
	</div>
	<div id="bottom"></div>
</body>
</html>