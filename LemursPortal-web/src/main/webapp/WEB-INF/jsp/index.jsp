<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><spring:message code="message.welcome"/></h1>
	<h3>Current Locale : ${pageContext.response.locale}</h3>
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
				Vous êtes connecté en tant que : ${userName} ou <sec:authentication property="principal.username" /> | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:when>
		<c:otherwise>
			<c:url value="/login" var="loginUrl" />
			<c:url value="/signup" var="signupUrl" />
			Vous n'êtes pas authentifiés. <a href="${loginUrl}">Login</a> | <a href="${signupUrl}">S'inscrire</a> 
		</c:otherwise>
	</c:choose>
	<div style="width:300px; float:right;">
		 <a href="?lang=mg">Malagasy</a> | <a href="?lang=en">English </a> | <a href="?lang=fr">Français</a>
	</div>
</body>
</html>