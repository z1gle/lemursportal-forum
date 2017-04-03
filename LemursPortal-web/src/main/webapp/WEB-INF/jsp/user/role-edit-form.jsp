<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<spring:message code="date.format" var="dateFormat"/>
<!-- 	<div style="width:300px; float:right;"> -->
<!-- 		 <a href="?lang=mg">Malagasy</a> | <a href="?lang=en">English </a> | <a href="?lang=fr">Français</a> -->
<!-- 	</div> -->
	<c:url var="formAction" value="/admin/roles/user"/>
	<form:form modelAttribute="userRoleEditForm" method="POST" action="${formAction}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<form:hidden path="userId"/>
		<div>
			<label><spring:message code="message.label.nom"/>:</label>
			<span><c:out value="${user.nom}"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.prenom"/>:</label>
			<span><c:out value="${user.prenom}"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.email"/>:</label>
			<span><c:out value="${user.email}"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.datenaissance"/>:</label>
			<span><fmt:formatDate value="${user.dateNaissance}" pattern="${dateFormat}"/>(<c:out value="${dateFormat}"/>)</span>
		</div>
		<div>
			<label><spring:message code="message.label.login"/>:</label>
			<span><c:out value="${user.login}"/></span>
		</div>
		<div>
			<label><spring:message code="message.label.biographie"/>:</label>
			<div><c:out value="${user.biographie}"/></div>
		</div>
		<div>
			<label>Rôles</label>
			<div>
				<ul>
					<form:checkboxes element="li" items="${userTypes}" path="roles" itemValue="id" itemLabel="libelle"/>
<%-- 					<c:forEach items="${userTypes}" var="role"> --%>
<!-- 						<li> -->
<%-- 							<form:checkbox path="roles" value="${role.id}" label="${role.libelle}"/> --%>
<!-- 						</li>					 -->
<%-- 					</c:forEach> --%>
				</ul>
			<form:errors path="roles"/>
			</div>
		</div>
		<div>
			<span><form:button value="edit"><spring:message code="message.label.enregistrer"/></form:button></span>
			<c:url var="userListUrl" value="/admin/user/list"/>
			<span><a href="${userListUrl}"><spring:message code="message.label.annuler"/></a></span>
		</div>
	</form:form>
</body>
</html>