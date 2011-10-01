<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../include/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar nova especialidade</title>
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
					<td>Nome</td>
					<td><input type="text" name="specialty.name"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Enviar" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>