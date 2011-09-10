<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp" />
<title>Teoremas!</title>
</head>
<body>
	<div id="content">
		<h1>Especialidades</h1>
		<a href="../nova/">Cadastrar nova</a>
		<table>
			<c:forEach var="specialty" items="${specialties}">
				<tr>
					<td>${specialty.name}</td>
				</tr>
			</c:forEach>
		</table>
	</div> 
</body>
               
</html>