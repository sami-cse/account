<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@include file="./includes/header.jsp"%>

<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="panel panel-success">
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>
    <%@include file="./includes/security.jsp"%>
    
	<div class="col-md-6 col-md-offset-3">
		<table class="table">
			<thead>
				<tr>
					<th>Menus</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${permissions}" var="permission">
					<tr>
						<td><a href="${contextPath}/${permission.url}" role="button"
							class="btn btn-primary">${permission.urlTitle}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>


