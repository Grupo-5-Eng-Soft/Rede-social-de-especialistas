<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
			<input type="text" size="30" name="login"/><br />
			Senha<br />
			<input type="password" size="30" name="password" /></p>
			<div id="error">
				<p>${notFound}</p>
				<p>${notAuthenticated}</p>
			</div>
			<div id="tabs">
				<a href="<c:url value="/usuarios/recuperar/"/>"><i>Recuperar senha</i></a>
			</div>
			<p><input type="submit" value="Enviar" /></p>
		</form>
	</div>	
	<div id="bottom">
	</div>
	
</body>
</html>