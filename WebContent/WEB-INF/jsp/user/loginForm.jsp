<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />

	<div id="content">
		<div id="tabs">
			<h1>Login</h1>
			<a href="<c:url value="/usuarios/cadastrar/"/>">Crie sua conta</a> 
		</div>
		<form method="post" action="./autenticar/" >
			<p>Login<br />
			<input type="text" name="login"/><br />
			Senha<br />
			<input type="password" name="password" /></p>
			<div id="error">
				<p>${notFound}</p>
				<p>${notAuthenticated}</p>
			</div>
			<p><input type="submit" value="Enviar" /></p>
		</form>
	</div>	
	<div id="bottom">
	</div>
	
</body>
</html>