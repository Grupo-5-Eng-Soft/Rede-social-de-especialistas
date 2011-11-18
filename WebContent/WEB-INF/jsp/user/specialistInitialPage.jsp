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
			<h1>${user.name} (${user.login})
			<c:if test="${user.certified}">
				<imgcert> <img src="<c:url value="/img/star.png"/>"> <span> Especialista certificado pelo CCSL  </span> </imgcert>
			</c:if>
			</h1>
		</div>
		<c:forEach var="specialist" items="${user.specialists}">
			<div>
				<h3>${specialist.specialty.name}</h3>
				<table>
					<c:forEach var="question" items="${questionsHashMapBySpecialtyName[specialist.specialty.name]}">
						<tr>
							<td><a href="<c:url value="/perguntas/${question.id}/"/>">${question.title}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:forEach>
	</div>
	<div id="bottom">
	</div> 
</body>	
               
</html>