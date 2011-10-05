<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Permissão negada</title>
</head>
<body>	

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />

	<div id="content">
		<c:if test="${userSession.authenticated }">
			<h3> Você não tem permissão para isso.</h3>
		</c:if>
		<c:if test="${!userSession.authenticated }">
			<h3> Você não esta logado.</h3>
			<a href="../login/">Login</a><br />
		</c:if>
		<a href="../">Voltar</a>
	</div>
	<div id="bottom">
	</div>
</body>
</html>