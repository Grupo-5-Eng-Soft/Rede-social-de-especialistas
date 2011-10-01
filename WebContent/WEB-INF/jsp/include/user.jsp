<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="user">
	<c:if test="${userSession.authenticated}">
		Olá, ${userSession.loggedUser.login}! <a href="<c:url value="/logout/"/>">logout</a>
	</c:if>
	<c:if test="${!userSession.authenticated}">
		<span><a href="<c:url value="/usuarios/cadastrar/"/>">Crie sua conta</a> ou faça o <a href="<c:url value="/login/"/>">login</a></span>
	</c:if>
	
</div>
