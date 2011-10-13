<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="error">
	<p>
	<c:forEach items="${errors}" var="error">
		${error.message} <br />
	</c:forEach>
	</p>
</div>