<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu">
	<div class="item"><a href="<c:url value="/" />">In�cio</a></div>
	<div class="item"><a href="<c:url value="/usuarios/listar/"/>">Usu�rios</a></div>
	<div class="item"><a href="<c:url value="/especialidades/listar/"/>">Especialidades</a></div>
	<div class="item"><a href="<c:url value="/perguntas/"/>">Perguntas</a></div>
	<div class="item"><a href="<c:url value="/sobre/"/>">Sobre</a></div>
	<c:if test="${userSession.authenticated}">
		<div class="item"><a href="<c:url value="/usuarios/inicio/comum/"/>">Minha p�gina inicial</a></div>	
	</c:if>
</div>