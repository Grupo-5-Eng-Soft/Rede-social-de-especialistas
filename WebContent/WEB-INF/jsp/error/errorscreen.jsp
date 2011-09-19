<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>	
	<c:if test="${userSession.authenticated }">
		<h3> Você não tem permissão para isso.</h3>
	</c:if>
	<c:if test="${!userSession.authenticated }">
		<h3> Você não esta logado.</h3>
		<a href="../login/"/>Login</a>
	</c:if>
	<a href="../"/>Voltar</a>

</body>
</html>