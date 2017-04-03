<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<sec:authorize access="hasRole('ADMIN')">
	<spring:message code="date.format" var="dateFormat"/>
	<table>
		<tr>
			<th>Id</th>
			<th>Login</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Email</th>
			<th>Date de naissance</th>
			<th>Date de dernier accès</th>
			<th></th>
<!-- 			<th></th> -->
		</tr>
		<c:url var="editBaseUrl" value="/admin/roles/user/"/>
		<c:forEach items="${paginationResponse.results}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>
				<td>${user.nom}</td>
				<td>${user.prenom}</td>
				<td>${user.email}</td>
				<td>
					<c:if test="${not empty user.dateNaissance }">
						<fmt:formatDate pattern="${dateFormat}" value="${user.dateNaissance}"/>
					</c:if>
				</td>
				<td>
					<c:if test="${not empty user.lastAccessDate }">
						<fmt:formatDate pattern="${dateFormat}" value="${user.lastAccessDate}"/>
					</c:if>
				</td>
				<td><a href="${editBaseUrl}${user.id}">Rôles</a></td>
<!-- 				<td><a href="#">Désactiver</a></td> -->
			</tr>
		</c:forEach>
	</table>
	</sec:authorize>
</body>
</html>