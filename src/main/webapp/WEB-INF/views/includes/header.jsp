<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${empty username}">
		<c:redirect url="http://localhost:8080/account/" />
	</c:when>
	
</c:choose>