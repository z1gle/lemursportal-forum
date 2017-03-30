<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:url var="userInfoFormAction" value="/registration"/>
	<form:form modelAttribute="userInfoForm" method="POST" action="${userInfoFormAction}">
		<form:hidden path="id"/>
		<div>
			<label>Nom:</label>
			<span><form:input path="nom" /></span>
			<span><form:errors path="nom"/></span>
		</div>
		<div>
			<label>Prénom:</label>
			<span><form:input path="prenom" /></span>
			<span><form:errors path="prenom"/></span>
		</div>
		<div>
			<label>E-mail:</label>
			<span><form:input path="email" /></span>
			<span><form:errors path="email"/></span>
		</div>
		<div>
			<label>Date de naissance:</label>
			<span><form:input path="dateNaissance" /></span>
			<span><form:errors path="dateNaissance"/></span>
		</div>
		<div>
			<label>Login:</label>
			<span><form:input path="login" /></span>
			<span><form:errors path="login"/></span>
		</div>
		<div>
			<label>Mot de passe:</label>
			<span><form:password path="password" /></span>
			<span><form:errors path="password"/></span>
		</div>
		<div>
			<label>Confirmation de mot de passe:</label>
			<span><form:password path="passwordConfirm" /></span>
			<span><form:errors path="passwordConfirm"/></span>
		</div>
		<div>
			<label>Biographie:</label>
			<span><form:textarea path="biographie" /></span>
			<span><form:errors path="biographie"/></span>
		</div>
		<div>
			<form:button value="signup">S'inscrire</form:button>
		</div>
	</form:form>
</body>
</html>