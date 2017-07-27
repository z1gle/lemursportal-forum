<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div style="border: 1px #ccc solid;">
			<strong>Envoyé par ${pMessage.sender.label} le <fmt:formatDate pattern="${datetimeFormat}" value="${pMessage.date}" /></strong><br/>
			<c:url value="/secured/pmessage/${pMessage.id}" var="messageUrl"/>
			<a href="${messageUrl}">${pMessage.subject}</a>
		</div>
	</li>
</c:forEach>
</ul>
</div>
</div>