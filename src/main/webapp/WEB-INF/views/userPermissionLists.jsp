<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title></title>
<%@include file="./includes/header.jsp"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<br> <br> <br>

		<div class="col-md-8 col-md-offset-2">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<h2>
					Welcome ${pageContext.request.userPrincipal.name} | <a
						onclick="document.forms['logoutForm'].submit()">Logout</a>
				</h2>
			</c:if>
			<%@include file="./includes/navigation.jsp"%>
			<%@include file="./includes/security.jsp"%>
			<a href="${contextPath}/userPermissions" role="button"
				class="btn btn-primary">Create User's Permission</a> <a
				href="${contextPath}/userPermissionLists" role="button"
				class="btn btn-primary">User's Permission Lists</a>
			<table class="table">
				<thead>
					<tr>
						<th>Url Title</th>
						<th>Url</th>
						<th>User Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${permissions.getContent()}" var="permission"
						varStatus="loop">
						<tr>
							<td>${permission.urlTitle}</td>
							<td>${permission.url}</td>
							<td>${permission.username}</td>
							<%-- <td><a href="${contextPath}/edit/${user.id}">Edit</a>|| <a
								href="${contextPath}/delete/${user.id}"
								onclick="return confirm('Are you sure you want to delete?');">Delete
							</a></td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="col-md-4 col-md-offset-4">
				<ul class="pagination">
					<li><a
						href="${contextPath}/userPermissionLists?page=${permissions.previousPageable().getPageNumber()}">Previous</a></li>
					<li><a
						href="${contextPath}/userPermissionLists?page=${permissions.nextPageable().getPageNumber()}">Next</a></li>
				</ul>
				<p style="text-align: center;">${page*size+1}to
					${(page*size+size)>permissions.getTotalElements()? permissions.getTotalElements() : (page*size+size)}
					of ${permissions.getTotalElements()}</p>
			</div>
		</div>


	</div>

</body>
</html>
