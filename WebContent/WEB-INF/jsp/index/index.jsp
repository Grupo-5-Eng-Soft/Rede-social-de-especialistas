<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/head.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Rede social de especialistas</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="../include/menu.jsp" />
	<div id="content">
	
	<h1>Top 5 Especialistas</h1>
	<table>
		<tr>
			<td> Nome </td> 
			<td> Especialidade </td> 
			<td> Pontuação </td>
		</tr>
		<c:forEach var="specialist" items="${specialist}">
			<tr>
				<td> <a href="<c:url value="/usuarios/${specialist.user.id}/"/>">${specialist.user.login}</a></td>
				<td> <a href="<c:url value="/especialidades/${specialist.specialty.id}/"/>">${specialist.specialty.name}</a></td>
				<td>${specialist.score}</td>
			</tr>
		</c:forEach>
	</table>
	
	
	<h1> Ultimas 5 Perguntas </h1>
	<table>
		<tr>
			<td> Título </td>
			<td> Especialidade </td> 
			<td> Autor </td> 		
		</tr>
		<c:forEach var="question" items="${question}">
			<tr>
				<td> <a href="<c:url value="/perguntas/${question.id}/"/>">${question.title}</a></td>
				<td> <a href="<c:url value="/especialidades/${question.specialty.id}/"/>">${question.specialty.name}</a></td>
				<td>${question.author.login}</td>
			</tr>
		</c:forEach>
	</table>
	
		<h1>Bem Vindo a Rede Social de Especialistas</h1>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam iaculis iaculis nibh bibendum venenatis. Vestibulum eu massa sed leo dapibus commodo. Fusce eu sem purus. Nunc eu turpis sapien. Nam tempus, risus a elementum faucibus, sapien mauris hendrerit sapien, quis consequat massa felis et risus. Sed accumsan vulputate blandit. Vestibulum ut aliquet tellus.
Mauris id nunc elit, vehicula volutpat nulla. Aliquam erat volutpat. Vivamus non augue sem. Vivamus arcu arcu, pulvinar eget convallis quis, convallis id nunc. Vestibulum magna dolor, viverra sit amet commodo ut, placerat in lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed purus sapien, adipiscing quis mollis ut, tempor in erat. Proin malesuada, justo at tristique imperdiet, dui eros ornare leo, gravida luctus magna justo sed massa. Cras vel turpis eros, id luctus neque. Nunc fringilla sollicitudin pellentesque. Aliquam adipiscing faucibus vehicula. Praesent eget justo quam.
Phasellus tincidunt gravida dignissim. In malesuada tincidunt malesuada. Fusce dignissim, ante at consectetur rhoncus, felis nisi gravida est, et molestie orci orci vel nunc. Proin quis quam eget dui scelerisque lobortis at faucibus orci. Morbi fringilla lectus cursus ligula tristique eget cursus arcu venenatis. Vivamus purus velit, egestas non vestibulum in, porttitor eu nisi. Ut vel porttitor magna. Integer lacinia, felis consequat convallis vulputate, justo mi scelerisque dolor, eu vestibulum urna metus id sem. In in purus et enim sodales commodo. Nam et magna magna, a tempus arcu.
Cras lorem purus, laoreet in hendrerit ac, consequat eget nisl. Nam id dolor quis augue mollis suscipit ac et tortor. Nullam vitae diam turpis, eu consequat enim. Pellentesque a elementum nunc. Mauris pulvinar, elit non ornare mollis, leo ante rutrum nisl, vel iaculis massa lorem quis risus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam porttitor velit at urna iaculis tempus. Praesent pretium mi vel libero vehicula sit amet porttitor nisi posuere. Quisque suscipit dapibus aliquam. Maecenas justo leo, fermentum eu vehicula eget, euismod quis est. Duis et nulla id enim convallis condimentum. Vestibulum tincidunt feugiat leo quis mollis. Vestibulum et quam et lacus egestas luctus.
Phasellus tincidunt gravida dignissim. In malesuada tincidunt malesuada. Fusce dignissim, ante at consectetur rhoncus, felis nisi gravida est, et molestie orci orci vel nunc. Proin quis quam eget dui scelerisque lobortis at faucibus orci. Morbi fringilla lectus cursus ligula tristique eget cursus arcu venenatis. Vivamus purus velit, egestas non vestibulum in, porttitor eu nisi. Ut vel porttitor magna. Integer lacinia, fel.
		</p>
	</div>

	<div id="bottom">
	</div>
</body>
</html>