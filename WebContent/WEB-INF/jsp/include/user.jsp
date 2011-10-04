<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="user">
	<c:if test="${userSession.authenticated}">
		<span>Olá, ${userSession.loggedUser.login}!  |  <a href="<c:url value="/usuarios/editar/${userSession.loggedUser.id}/"/>">Editar perfil</a>  |  <a href="<c:url value="/logout/"/>">Logout</a></span>	
	</c:if>
	<c:if test="${!userSession.authenticated}">
		<span><a href="<c:url value="/usuarios/cadastrar/"/>">Crie sua conta</a> ou <a href="<c:url value="/login/"/>"> faça o login</a></span>
	</c:if>
	
</div>
