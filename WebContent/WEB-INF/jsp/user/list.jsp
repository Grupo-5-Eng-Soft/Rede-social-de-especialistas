<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
<title>Usu�rios</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />


	<div id="content">
		<div id="tabs">
			<h1>Usu�rios</h1>
		</div>
		<ul>
			<c:forEach var="user" items="${user}">
				<li><a href="<c:url value="/usuarios/${user.id}/"/>">${user.login}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div id="bottom">
	</div> 
</body>
               
</html>