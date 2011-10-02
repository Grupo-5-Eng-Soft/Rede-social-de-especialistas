<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" ></script>
  	<script type="text/javascript" src="<c:url value="/javascript/jquery.validate.js"/>"></script>
	<jsp:include page="../include/head.jsp" />
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
		<form method="post" action="../salvar/" >
			<p>Login<br />
			<input type="text" name="user.login"/><br />
			Email<br />
			<input type="text" name="user.email" /><br />
			Senha<br />
			<input type="password" name="user.password" id="password"/><br />
			Confirmação de senha<br />
			<input type="password" name="confirmation" /><br />
			Privilégio<br />
			<input name="user.role" type="radio" value="USER"/>Usuário<br />
			<input name="user.role" type="radio" value="ADMIN"/>Administrador<br />
			Especialidades<br />
			<c:forEach var="specialty" items="${specialties}">
			<input name="specialties_ids" type="checkbox" value="${specialty.id}"> ${specialty.name}<br />
			</c:forEach>
			<br />
			<input type="submit" value="Enviar"/></td>
		</form>
		<ul>
			<c:forEach items="${errors}" var="error">
			<li style="color: #FF0000">${error.category} - ${error.message}</li>
			</c:forEach>
		</ul>
	</div>
	<div id="bottom"></div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("form").validate({
        rules:{
            "user.login":{
                required: true,
            },
            "user.email": {
                required: true,
                email: true
            },
            "user.password": {
                required: true
            },
            "password-confirmation":{
                required: true,
                equalTo: "#password"
            }
        },
        messages:{
            "user.login":{
                required: "O campo login é obrigatorio.",
            },
            "user.email": {
                required: "O campo email é obrigatorio.",
                email: "O campo email deve conter um email válido."
            },
            "user.password": {
                required: "O campo senha é obrigatorio."
            },
            "password-confirmation":{
                required: "O campo confirmação de senha é obrigatorio.",
                equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
            }
        }
    });
});
</script>
</html>