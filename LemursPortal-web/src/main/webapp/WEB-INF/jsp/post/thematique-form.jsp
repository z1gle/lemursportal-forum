<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>formulaire de création/modification de Thématique</title>
</head>
<body>
	<c:url value="/secured/thematique" var="formAction"></c:url>
	<form:form modelAttribute="thematique" action="${formAction}" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<form:hidden path="id"/>
		<div>Libelle: <form:input path="libelle"/><form:errors path="libelle"/> </div>
		<div>Description: <form:textarea path="description"/><form:errors path="description"/></div>
		<div><form:button value="save">Enregistrer</form:button> </div>
	</form:form>	
</body>
</html>