<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="../include/head.jsp" />
	<script type="text/javascript" src="<c:url value="/javascript/user/validateUserForm.js"/>"></script>
<title>Cadastrar novo usu�rio</title>
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
		<form method="post" action="../salvar/" >
			<p>Login<br />
			<input type="text" name="user.login"/><br />
			Email<br />
			<input type="text" name="user.email" /><br />
			Senha<br />
			<input type="password" name="user.password" id="password"/><br />
			Confirma��o de senha<br />
			<input type="password" name="confirmation" /><br /><br />
			Privil�gio<br />
			<input name="user.role" type="radio" value="USER"/>Usu�rio<br />
			<input name="user.role" type="radio" value="ADMIN"/>Administrador<br /><br />
			Especialidades<br />
			<c:forEach var="specialty" items="${specialties}">
			<input name="specialties_ids" type="checkbox" value="${specialty.id}"> ${specialty.name}<br />
			</c:forEach>
			<br />
			<input type="submit" value="Enviar"/>
			<input type="hidden" name="hostname" value="<% out.print(request.getServerName()); %>"/> 
		</form>
		<div id="error">
			<p>
			<c:forEach items="${errors}" var="error">
				${error.category} - ${error.message}<br />
			</c:forEach>
			</p>
		</div>
	</div>
	<div id="bottom"></div>
</body>
</html>