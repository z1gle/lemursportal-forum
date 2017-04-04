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
<div style="width:300px; float:right;">
		 <a href="?lang=mg">Malagasy</a> | <a href="?lang=en">English </a> | <a href="?lang=fr">Français</a>
	</div>
	<c:url var="userInfoFormAction" value="/user/edit"/>
	<form:form modelAttribute="registrationForm" method="POST" action="${userInfoFormAction}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<form:hidden path="id"/>
		<div>
			<label><spring:message code="message.label.nom"/>:</label>
			<span><form:input path="nom" /></span>
			<span><form:errors path="nom"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.prenom"/>:</label>
			<span><form:input path="prenom" /></span>
			<span><form:errors path="prenom"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.email"/>:</label>
			<span><form:input path="email" /></span>
			<span><form:errors path="email"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.datenaissance"/>:</label>
			<span><form:input path="dateNaissance" /><i>(<spring:message code="date.format"/>)</i></span>
			<span><form:errors path="dateNaissance"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.login"/>:</label>
			<span><c:out value="${registrationForm.login}" /></span>
		</div>
<!-- 		<div> -->
<%-- 			<label><spring:message code="message.label.motdepasse"/>:</label> --%>
<%-- 			<span><form:password path="password" /></span> --%>
<%-- 			<span><form:errors path="password"/></span> --%>
<!-- 		</div> -->
<!-- 		<div> -->
<%-- 			<label><spring:message code="message.label.motdepasse.confirmation"/>:</label> --%>
<%-- 			<span><form:password path="passwordConfirm" /></span> --%>
<%-- 			<span><form:errors path="passwordConfirm"/></span> --%>
<!-- 		</div> -->
		<div>
			<label><spring:message code="message.label.biographie"/>:</label>
			<span><form:textarea path="biographie" /></span>
			<span><form:errors path="biographie"/></span>
		</div>
		<div>
			<form:button value="edit"><spring:message code="message.label.enregistrer"/></form:button>
		</div>
	</form:form>
</body>
</html>