<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="width:300px; float:right;">
		 <a href="?lang=mg">Malagasy</a> | <a href="?lang=en">English </a> | <a href="?lang=fr">Français</a>
	</div>
	
	<c:if test="${not empty successMessage}">
		<div style="color:#ffffff;background-color:green;width: 100%;height: 50px;">
			<c:out value="${successMessage}"/>
		</div>
	</c:if>
	
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<spring:message code="date.format" var="dateFormat"/>
	<h1><spring:message code="message.welcome"/></h1>
	<h3>Current Locale : ${pageContext.response.locale}</h3>
	<h3>Date (${dateFormat}): <fmt:formatDate pattern="${dateFormat}" value="${now}"/></h3>
	<sec:authorize access="hasRole('ADMIN')">
	This content will only be visible to users who have
	the "ADMIN" authority in their list of <tt>GrantedAuthority</tt>s.
	</sec:authorize>
	<sec:authorize access="hasRole('USER')">
	This content will only be visible to users who have
	the "USER" authority in their list of <tt>GrantedAuthority</tt>s.
	</sec:authorize>
	<c:choose>
		<c:when test="${isAuthenticated}">
			<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
			</script>
			<h2>
				Vous êtes connecté en tant que : <sec:authentication property="principal.username" /> | 
				<sec:authorize access="hasRole('ADMIN')">
					<c:url value="/admin/user/list" var="userListUrl"/>
					<a href="${userListUrl}"> Voir la Liste des utilisateurs enregistré</a> |
				</sec:authorize>
				<c:url value="/user/edit" var="currentUserEditUrl" />
				<a href="${currentUserEditUrl}"> Modifier votre profil</a> | 
				<a href="javascript:formSubmit()"> Logout</a> 
			</h2>
		</c:when>
		<c:otherwise>
			<c:url value="/login" var="loginUrl" />
			<c:url value="/signup" var="signupUrl" />
			Vous n'êtes pas authentifiés. <a href="${loginUrl}">Login</a> | <a href="${signupUrl}">S'inscrire</a> 
		</c:otherwise>
	</c:choose>
</body>
</html>