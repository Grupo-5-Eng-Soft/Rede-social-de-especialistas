<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/header.jsp" />
</head>
<body>
	<div id="content">
		<h1>Top 5 Especialistas</h1>
		<table>
			<c:forEach var="specialist" items="${specialist}">
				<tr>
					<td> <a href="<c:url value="/usuarios/${specialist.user.id}/"/>">${specialist.user.login}</a></td>
					<td>${specialist.score}</td>
				</tr>
			</c:forEach>
		</table>
	</div> 
</body>
               
</html>