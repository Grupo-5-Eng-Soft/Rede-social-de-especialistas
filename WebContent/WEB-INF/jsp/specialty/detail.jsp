<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp" />
</head>
<body>
	<div id="content">
		<h1>${specialty.name}</h1>
		${specialty.users}
		<table>
			<c:forEach var="user" items="${specialty.users}">
				<tr>
					<td>${user.name}</td>
				</tr>
			</c:forEach>
		</table>
	</div> 
</body>
               
</html>