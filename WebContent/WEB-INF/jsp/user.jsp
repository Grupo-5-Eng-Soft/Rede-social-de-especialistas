<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="user">
	<c:if test="${userSession.authenticated}">
		${userSession.authenticated}<br>
		Olá, ${userSession.loggedUser.login}! <a href="/${pageContext.request.contextPath}/logout/">logout</a>
	</c:if>
	<c:if test="${!userSession.authenticated}">
		<a href="<c:url value="/usuarios/cadastrar/"/>">Crie sua conta</a> ou faça o <a href="<c:url value="/login/"/>">login</a>
	</c:if>
	
</div>
