<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Rede social de especialistas</title>
</head>
<body>
<jsp:include page="../header.jsp" />
<jsp:include page="../user.jsp" />
	<h1>Rede social de especialistas</h1>

<div id="menu">
	<div class="item"><a href="<c:url value="/especialidades/listar/"/>">Especialidades</a></div>
	<div class="item"><a href="<c:url value="/usuarios/listar_especialistas/"/>">Top5 Especialistas</a></div>
	<div class="item"><a href="<c:url value="/perguntas/"/>">Perguntas</a></div>
</div>
</body>
</html>