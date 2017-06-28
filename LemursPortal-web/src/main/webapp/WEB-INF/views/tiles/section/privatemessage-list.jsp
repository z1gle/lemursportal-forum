<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:message code="datetime.format" var="datetimeFormat"/>

<div class="wrapper wrapper-content animated fadeInRight">
	<div class="forum-container">
<ul>
<c:forEach items="${messagesPage.content}" var="pMessage">
	<li>
		<strong><fmt:formatDate pattern="${datetimeFormat}" value="${pMessage.date}" /></strong>
		<div>
			<h3>${pMessage.subject}</h3>
			<p>${pMessage.body}</p>
		</div>
	</li>
</c:forEach>
</ul>
</div>
</div>