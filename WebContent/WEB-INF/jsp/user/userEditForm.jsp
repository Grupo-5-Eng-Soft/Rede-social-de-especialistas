<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="../include/head.jsp" />
<script type="text/javascript" src="<c:url value="/javascript/user/validateUserForm.js"/>"></script>
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
			<h1>Editar</h1>
		</div>
		<form method="post" action="../../atualizacao/" >
			Email<br />
			<input type="text" size="30" value="${user.email}" name="user.email" /><br /><br />
			Especialidades<br />
			<c:forEach var="specialty" items="${specialties}">
				<% String s = new String("");%>
				<c:forEach var="userspecialty" items="${user.specialists }">
					<c:if test="${specialty.id == userspecialty.specialty.id }"> 	
						<% s = "checked"; %>
					</c:if>
				</c:forEach>
				<input name="specialties_ids" <%= s %> type="checkbox" value="${specialty.id}"> ${specialty.name}<br />
			</c:forEach>
			<br />
			<input type="submit" value="Atualizar"/>
		</form>
		<div id="error">
			<p>
			<c:forEach items="${errors}" var="error">
				${error.category} - ${error.message}<br />
			</c:forEach>
			</p>
		</div>
	</div>
	<div id="bottom"></div>
</body>
</html>