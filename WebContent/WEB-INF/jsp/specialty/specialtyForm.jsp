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
		<div id="tabs">
			<h1>Especialidades</h1>
			<a href="../listar/">Listar</a>
		</div>

		<form method="post" action="../salvar/" >
			<p><b>Título:</b><br /> <input type="text" size="40" name="specialty.name"/><br /><br />
			<input type="submit" value="Enviar" /></p>
		</form>
	</div>
</body>
</html>