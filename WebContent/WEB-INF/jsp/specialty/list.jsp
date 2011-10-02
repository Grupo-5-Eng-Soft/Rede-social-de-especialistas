<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
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
			<a href="../nova/">Cadastrar nova</a>
		</div>
		<ul>
			<c:forEach var="specialty" items="${specialties}">
				<li><a href="<c:url value="/especialidades/${specialty.id}/"/>">${specialty.name}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div id="bottom">
	</div> 
</body>
               
</html>