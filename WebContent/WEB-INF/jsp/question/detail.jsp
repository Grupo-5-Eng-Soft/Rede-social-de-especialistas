<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function toggle(obj) {
	var el = document.getElementById(obj);
	if ( el.style.display != 'none' ) {
		el.style.display = 'none';
	}
	else {
		el.style.display = '';
	}
}
</script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Perguntas</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	<div id="content">
		<div id="question">
			<h2>${question.title}</h2>
			<c:if test="${question.finalized}">
				<h3>Pergunta finalizada</h3>
			</c:if>
			<c:if test="${!question.finalized}">
				<c:if test="${question.answered}">
					<h3>Pergunta Respondida</h3>
				</c:if>
			</c:if>
			<h6>Especialidade: ${question.specialty.name}</h6>
			<c:if test="${!question.publicQuestion}">
				<h6>Dúvida privada</h6>
			</c:if>
			<c:if test="${question.author != null}">
				<h6>Autor: ${question.author.login} </h6>
			</c:if>
			<c:if test="${question.author == null}">
				<h6>Autor: ${question.email} </h6>
			</c:if>
			<h4>${question.description}<br /><br /></h4>
		</div>
		<c:forEach var="answer" items="${question.answers}">
			<div id="answer">
				<h5>Resposta para: ${question.title}</h5>
				<c:if test="${answerAuthorsQualification[answer.author.login]}">
					<h6>Especialista: 
				</c:if>
				<c:if test="${!answerAuthorsQualification[answer.author.login]}">
					<h6>Autor: 
				</c:if>
				${answer.author.login}
				<c:if test="${answer.author.certified}">
					<imgcert> <img src="<c:url value="/img/star.png"/>"  style="vertical-align:text-top;"> <span> Especialista certificado pelo CCSL  </span> </imgcert>
				</c:if>
				</h6>
				<p>${answer.description}<br /><br /></p>
				<p>
					<c:if test="${isQuestionAuthor && answer.author.id != question.author.id && !question.finalized}">
						<a href="<c:url value="/perguntas/${answer.id}/finalizar/formulario/"/>">Finalizar tópico e avaliar esta resposta</a>
					</c:if>
				</p>
			</div>
		</c:forEach>
		<br />
		<c:if test="${userSession.authenticated}">
			<a href="#" onclick="toggle('maisinfo');">Responder</a>
		</c:if>
		<c:if test="${!userSession.authenticated}">
			<b>Para responder esta pergunta você precisa estar logado.</b><br/>
			<a href="<c:url value="/login/"/>">Login</a>
		</c:if>
		<div id="maisinfo" style="display:none">
			<form method="post" action="<c:url value="/perguntas/${question.id}/responder/"/>">
					<textarea cols="80" rows="6" name="answer.description"></textarea><br />
					Esta resposta pode finalizar a pergunta? <input type="checkbox" name="answered" value="1"/><b>Sim</b><br/>
					<input type="submit" value="Enviar" />
			</form>		
		</div>
	</div>
	<div id="bottom">
	</div>
</body>
</html>