<div id="user">
	<c:if test="${userSession.authenticated}">
		Ol�, ${userSession.loggedUser.login}! <a href="./logout/">logout</a>
	</c:if>
</div>
