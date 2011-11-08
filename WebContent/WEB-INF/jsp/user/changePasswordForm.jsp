<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="../include/head.jsp" />
<%-- <script type="text/javascript" src="<c:url value="/javascript/user/validateUserForm.js"/>"></script> --%>
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
			<h1>Trocar Senha</h1>
		</div>
		<div id="error">
			${error}
		</div>
		<form method="post" action="<c:url value="/usuarios/salvarsenha/"/>" >
			Senha antiga<br />
			<input type="password" size="30" name="oldPassword" id="oldPassword"/><br />
			Senha nova<br />
			<input type="password" size="30" name="newPassword" id="newPassword"/><br />
			Confirmação de senha<br />
			<input type="password" size="30" name="confirmation" /><br /><br />
			<input type="submit" value="Enviar"/>
		</form>
	</div>
	<div id="bottom"></div>
</body>
</html>