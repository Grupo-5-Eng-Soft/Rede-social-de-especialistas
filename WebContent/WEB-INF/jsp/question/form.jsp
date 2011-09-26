<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nova pergunta</title>
</head>
<body>

<form method="post" action="<c:url value="/perguntas/salvar/"/>">
	<table>
		<tr>
			<td>Título: </td>
			<td><input type="text" name="question.title" /></td>
		</tr>
		<tr>
			<td>Descrição: </td>
			<td><textarea cols="80" rows="6" name="question.description"></textarea></td>
		</tr>
		<tr>
			<td>Especialidade: </td>
			<td>
				<select name="specialty_id">
					<c:forEach var="specialty" items="${specialties}">
						<option value="${specialty.id}">${specialty.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
	</table>
</form>

</body>
</html>