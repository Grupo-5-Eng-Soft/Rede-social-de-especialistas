<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="../include/head.jsp" />

<title>Upload de arquivo</title>
</head>
<body>

	<div id="upperHeader">
		<jsp:include page="../include/user.jsp" />
	</div>
	
	<jsp:include page="../include/header.jsp" />
	<jsp:include page="../include/menu.jsp" />
	
	<div id="content">
		<div id="tabs">
			<h1>Upload</h1>
		</div>
		<jsp:include page="../include/error.jsp" />
		
		<form method="post" action="./salvar/" enctype="multipart/form-data">
			<p>
				<input type="file" name="uploadFile"/>
				<input type="submit" value="Enviar"/>
			</p>
		</form>
	</div>

	<div id="bottom"></div>
</body>
</html>