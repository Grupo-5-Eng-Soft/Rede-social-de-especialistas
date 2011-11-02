<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<jsp:include page="../include/head.jsp" />
	<title>Usuário: ${user.login}</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	<div id="content">
		<div id="tabs">
			<h1>${user.login}</h1>
			<c:if test="${userSession.loggedUser.admin}">
				<c:if test="${!user.certified}">
					<a href="<c:url value="/usuario/certificado/${user.id}"/>">Certificar</a>
				</c:if>
				<c:if test="${user.certified}">
					<a href="<c:url value="/usuario/certificado/${user.id}"/>">Descertificar</a>
				</c:if>		
			</c:if>
		</div>
		<br />
		<br />
		<h3>Especialidades: </h3>
		<ul>
		<table>
			<tr>
				<th width="180"> </th> 
				<th width="100"> Pontuação </th>
			</tr>
			<c:forEach var="specialist" items="${user.specialists}">
				<tr>
					<td> <li><a href="<c:url value="/especialidades/${specialist.specialty.id}/"/>">${specialist.specialty.name}</a></li></td>
					<td>${specialist.score}</td>
				</tr>
			</c:forEach>
		</table>
		</ul>
	</div>
	<div id="bottom">
	</div> 
</body>	
               
</html>