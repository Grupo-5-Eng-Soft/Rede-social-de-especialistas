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
		<form method="post" action="../salvar/" >
			<table>
				<tr>
					<td>Login</td>
					<td><input type="text" name="user.login"/></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="user.email" /></td>
				</tr>
				<tr>
					<td>Senha</td>
					<td><input type="password" name="user.password" id="password"/></td>
				</tr>
				<tr>
					<td>Confirmação de senha</td>
					<td><input type="password" name="password-confirmation" /></td>
				</tr>
				<tr>
					<td>Privilégio</td>
					<td>
						<p><input name="user.role" type="radio" value="USER"/>Usuário</p>
						<p><input name="user.role" type="radio" value="ADMIN"/>Administrador</p>
					</td>
				</tr>
				
				<tr>
					<td> Especialidades </td>
					
						<c:forEach var="specialty" items="${specialties}">
						<tr>
							<td> <input name="specialties_ids" type="checkbox" value="${specialty.id}"> ${specialty.name} </td>
						</tr>
						</c:forEach>
					
				</tr>
				
				<tr>			
	  				<td><input type="submit" value="Enviar"/></td>
				</tr>
			</table>
		</form>
	</div>
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
                required: "O campo nome é obrigatorio.",
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