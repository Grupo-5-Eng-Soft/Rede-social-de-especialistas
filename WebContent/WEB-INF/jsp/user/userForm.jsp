<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar novo usuário</title>
</head>
<body>
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
				<td><input type="password" name="user.password" /></td>
			</tr>
			<tr>
				<td>Confirmação de senha</td>
				<td><input type="password" name="password-confirmation" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Enviar" /></td>
			</tr>
		</table>
	</form>
</body>
</html>